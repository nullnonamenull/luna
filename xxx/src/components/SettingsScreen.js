import React from "react";
import "../styles/SettingsScreen.css";

function SettingsScreen() {
    return (
        <div className="settings-screen">
            <header>
                <img src="/logo192.png" alt="MatchUp Logo" />
                <h1>MatchUp</h1>
            </header>
            <h2>Criteria selection in progress</h2>
            <img src="/excavator.png" alt="Excavator" />
            <footer>Version 1.2</footer>
        </div>
    );
}

export default SettingsScreen;
