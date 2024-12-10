import React, { useEffect, useState, useRef } from 'react';
import './ChatWindow.css';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

function ChatWindow({ sessionId }) {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');
    const stompClient = useRef(null);

    useEffect(() => {
        if (sessionId) {
            fetch(`http://localhost:8080/api/session/${sessionId}/message`)
                .then((response) => response.json())
                .then((data) => setMessages(data.messages))
                .catch((error) => console.error('Error fetching messages:', error));
        }
    }, [sessionId]);

    useEffect(() => {
        if (sessionId) {
            const socket = new SockJS('http://localhost:9090/ws');
            stompClient.current = new Client({
                webSocketFactory: () => socket,
                debug: console.log,
                onConnect: () => {
                    stompClient.current.subscribe(`/topic/session/${sessionId}`, (message) => {
                        const response = JSON.parse(message.body);
                        setMessages((prevMessages) => [
                            ...prevMessages,
                            { sender: response.sender, content: response.content },
                        ]);
                    });
                },
                onStompError: (frame) => {
                    console.error('WebSocket error:', frame);
                },
            });

            stompClient.current.activate();

            return () => {
                if (stompClient.current) stompClient.current.deactivate();
            };
        }
    }, [sessionId]);

    const sendMessage = () => {
        if (stompClient.current && input.trim()) {
            const message = {
                sessionId: sessionId,
                content: input.trim(),
            };

            stompClient.current.publish({
                destination: `/app/session/${sessionId}/send`,
                body: JSON.stringify(message),
            });

            setMessages((prevMessages) => [
                ...prevMessages,
                { sender: 'USER', content: input },
            ]);

            setInput('');
        }
    };

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
            <div className="InputBox">
                <input
                    type="text"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    placeholder="Enter your message..."
                />
                <button onClick={sendMessage}>Send</button>
            </div>
        </div>
    );
}

export default ChatWindow;
