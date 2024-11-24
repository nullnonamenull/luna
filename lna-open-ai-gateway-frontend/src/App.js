import React, { useState } from 'react';
import './App.css';
import ReactMarkdown from 'react-markdown';
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
import { atomDark } from 'react-syntax-highlighter/dist/esm/styles/prism';

function App() {
    const [model, setModel] = useState('');
    const [messages, setMessages] = useState([{ role: 'user', content: 'Hello, world!' }]);
    const [setResponseText] = useState('');
    const [chatHistory, setChatHistory] = useState([]);

    const addMessage = () => {
        setMessages([...messages, { role: '', content: '' }]);
    };

    const handleMessageChange = (index, field, value) => {
        const newMessages = [...messages];
        newMessages[index][field] = value;
        setMessages(newMessages);
    };

    const sendRequest = () => {
        const requestData = {
            model: model,
            messages: messages,
        };

        // Logowanie dla debugowania
        console.log('Request data:', requestData);

        // Weryfikacja danych przed wysłaniem
        if (!model) {
            console.error('Model is empty!');
            setResponseText('Error: Model cannot be empty.');
            return;
        }
        if (!messages.length) {
            console.error('Messages array is empty!');
            setResponseText('Error: At least one message is required.');
            return;
        }

        // Wysyłanie żądania
        fetch('http://localhost:8080/api/openai/chat', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestData),
        })
            .then((response) => {
                console.log('Response status:', response.status);
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then((data) => {
                console.log('Response data:', data);
                const newResponse = data.text || 'Brak odpowiedzi tekstowej';
                setResponseText(newResponse);
                setChatHistory([...chatHistory, { role: 'assistant', content: newResponse }]);
            })
            .catch((error) => {
                console.error('Error occurred:', error);
                setResponseText(`Błąd: ${error.message}`);
            });
    };

    return (
        <div className="App">
            <h1>Interfejs Chatu</h1>
            <div className="container">
                <div className="input-group">
                    <label className="label">Model:</label>
                    <input
                        type="text"
                        className="input"
                        value={model}
                        onChange={(e) => setModel(e.target.value)}
                    />
                </div>
                <div className="messages">
                    <h2>Wiadomości</h2>
                    {messages.map((message, index) => (
                        <div key={index} className="message-container">
                            <div className="input-group">
                                <label className="label">Rola:</label>
                                <input
                                    type="text"
                                    className="input"
                                    value={message.role}
                                    onChange={(e) =>
                                        handleMessageChange(index, 'role', e.target.value)
                                    }
                                />
                            </div>
                            <div className="input-group">
                                <label className="label">Treść:</label>
                                <input
                                    type="text"
                                    className="input"
                                    value={message.content}
                                    onChange={(e) =>
                                        handleMessageChange(index, 'content', e.target.value)
                                    }
                                />
                            </div>
                        </div>
                    ))}
                    <button className="add-button" onClick={addMessage}>
                        Dodaj Wiadomość
                    </button>
                </div>
                <button className="send-button" onClick={sendRequest}>
                    Wyślij
                </button>
                <div className="chat-history">
                    <h2>Historia Czatu</h2>
                    {chatHistory.map((msg, index) => (
                        <div key={index} className={`chat-message ${msg.role}`}>
                            <strong>{msg.role}:</strong>
                            <ReactMarkdown
                                children={msg.content}
                                components={{
                                    code({ node, inline, className, children, ...props }) {
                                        const match = /language-(\w+)/.exec(className || '');
                                        return !inline && match ? (
                                            <SyntaxHighlighter
                                                style={atomDark}
                                                language={match[1]}
                                                PreTag="div"
                                                {...props}
                                            >
                                                {String(children).replace(/\n$/, '')}
                                            </SyntaxHighlighter>
                                        ) : (
                                            <code className={className} {...props}>
                                                {children}
                                            </code>
                                        );
                                    },
                                }}
                            />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default App;
