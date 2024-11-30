import React from 'react';
import './ChatList.css';

function ChatList() {
    const sessions = ['Session 1', 'Session 2', 'Session 3'];

    return (
        <div className="ChatList">
            <div className="ChatListItems">
                {sessions.map((session, index) => (
                    <div key={index} className="ChatListItem">
                        {session}
                    </div>
                ))}
            </div>
            <button className="NewSessionButton">+ New Session</button>
        </div>
    );
}

export default ChatList;
