import React from 'react';
import './ChatList.css';

function ChatList({ sessions, onSelectSession, onNewSession }) {
    return (
        <div className="ChatList">
            <div className="ChatListItems">
                {sessions.map((session) => (
                    <div
                        key={session.sessionId}
                        className="ChatListItem"
                        onClick={() => onSelectSession(session.sessionId)}
                    >
                        {session.sessionName}
                    </div>
                ))}
            </div>
            <button className="NewSessionButton" onClick={onNewSession}>
                + New Session
            </button>
        </div>
    );
}

export default ChatList;