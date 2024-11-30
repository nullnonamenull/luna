import React from 'react';
import './MessageInput.css';

function MessageInput() {
    return (
        <div className="MessageInput">
            <input type="text" placeholder="Type a message" />
            <button>Send</button>
        </div>
    );
}

export default MessageInput;