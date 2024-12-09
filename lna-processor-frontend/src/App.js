import React, {useEffect, useState} from 'react';
import ChatList from './ChatList';
import ChatWindow from './ChatWindow';
import './App.css';

function App() {
    const [sessions, setSessions] = useState([]);
    const [selectedSessionId, setSelectedSessionId] = useState(null);

    // Pobierz sesje przy pierwszym uruchomieniu
    useEffect(() => {
        fetchSessions();
    }, []);

    const fetchSessions = () => {
        fetch('http://localhost:8080/api/session/')
            .then((response) => response.json())
            .then((data) => {
                setSessions(data);
                if (data.length > 0 && !selectedSessionId) {
                    setSelectedSessionId(data[0].sessionId);
                }
            })
            .catch((error) => console.error('Error fetching sessions:', error));
    };

    const handleNewSession = () => {
        fetch('http://localhost:8080/api/session/', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Failed to create a new session');
                }
                return response.json();
            })
            .then(() => {
                fetchSessions(); // Odswież listę sesji po utworzeniu nowej
            })
            .catch((error) => console.error('Error creating session:', error));
    };

    return (
        <div className="App">
            <ChatList
                sessions={sessions}
                onSelectSession={setSelectedSessionId}
                onNewSession={handleNewSession}
            />
            {selectedSessionId && <ChatWindow sessionId={selectedSessionId}/>}
        </div>
    );
}

export default App;
