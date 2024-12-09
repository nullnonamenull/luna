import React, { useEffect, useState } from 'react';
import './ChatWindow.css';

function ChatWindow({ sessionId }) {
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        if (sessionId) {
            fetch(`http://localhost:8080/api/session/${sessionId}/message`)
                .then((response) => response.json())
                .then((data) => setMessages(data.messages))
                .catch((error) => console.error('Error fetching messages:', error));
        }
    }, [sessionId]);

    return (
        <div className="ChatWindow">
            <div className="Messages">
                {messages.map((message, index) => (
                    <div
                        key={index}
                        className={`Message ${message.sender === 'USER' ? 'Pete' : 'Luna'}`}
                    >
                        <div className="SenderName">
                            {message.sender === 'USER' ? 'Pete' : 'Luna'}
                        </div>
                        <div className="MessageText">{message.content}</div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ChatWindow;