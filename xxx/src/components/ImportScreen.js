import React from "react";
import "../styles/ImportScreen.css";

function ImportScreen({ setView }) {
    return (
        <div className="import-screen">
            <header>
                <img src="/logo192.png" alt="MatchUp Logo" />
                <h1>MatchUp</h1>
            </header>
            <p>Do you want to import a new project for management?</p>
            <div className="file-options">
                <button>CSV</button>
                <button>XHTML</button>
            </div>
            <footer>Version 1.2</footer>
        </div>
    );
}

export default ImportScreen;
