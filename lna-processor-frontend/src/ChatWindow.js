import React from 'react';
import MessageInput from './MessageInput';
import './ChatWindow.css';

function ChatWindow() {
    const messages = [
        { sender: 'Pete', text: 'Hello' },
        { sender: 'Luna', text: 'Hi there!' },
    ];

    return (
        <div className="ChatWindow">
            <div className="Messages">
                {messages.map((message, index) => (
                    <div
                        key={index}
                        className={`Message ${message.sender === 'Pete' ? 'Pete' : 'Luna'}`}
                    >
                        <div className="SenderName">{message.sender}</div>
                        <div className="MessageText">{message.text}</div>
                    </div>
                ))}
            </div>
            <MessageInput />
        </div>
    );
}

export default ChatWindow;
