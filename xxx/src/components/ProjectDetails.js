import React from "react";
import "../styles/ProjectDetails.css";
import { ReactComponent as ArrowIcon } from "../assets/back-arrow.svg";

function ProjectDetails({ setView, project }) {
    return (
        <div className="project-details">
            <header>
                <div className="back-arrow" onClick={() => setView("StartScreen")}>
                    <ArrowIcon />
                </div>
                <h1>{project.name}</h1>
                <img src="/logo192.png" alt="MatchUp Logo" />
            </header>
            <div className="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Position</th>
                        <th>Seniority</th>
                    </tr>
                    </thead>
                    <tbody>
                    {project.employees.map((emp, index) => (
                        <tr key={index}>
                            <td>{emp.firstName}</td>
                            <td>{emp.lastName}</td>
                            <td>{emp.position}</td>
                            <td>{emp.seniority}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
            <button className="add-button" onClick={() => setView("AddEmployee")}>
                Add Employee
            </button>
            <footer>
                <div className="icons">
                    <span>ℹ️ App Info</span>
                    <span>ℹ️ Screen Info</span>
                </div>
                <div className="version">Version 1.2</div>
            </footer>
        </div>
    );
}

export default ProjectDetails;
