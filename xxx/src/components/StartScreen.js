import React from "react";
import "../styles/StartScreen.css";

function StartScreen({ setView, projects, setCurrentProject }) {
    return (
        <div className="start-screen">
            <header>
                <img src="/logo192.png" alt="MatchUp Logo" />
                <h1>MatchUp</h1>
            </header>
            <div className="projects">
                {projects.map((project, index) => (
                    <div
                        key={index}
                        className="project-card"
                        onClick={() => {
                            setCurrentProject(project.name);
                            setView("ProjectDetails");
                        }}
                    >
                        <h2>{project.name}</h2>
                        <p>Employees: {project.employees.length}</p>
                    </div>
                ))}
            </div>
            <button className="import-button" onClick={() => setView("ImportScreen")}>
                Import
            </button>
            <div className="footer">
                <div className="icons">
                    <span>ℹ️ App Info</span>
                    <span>ℹ️ Screen Info</span>
                </div>
                <div className="version">Version 1.2</div>
            </div>
        </div>
    );
}

export default StartScreen;
