import React from "react";
import "../styles/AddEmployee.css";

function AddEmployee({ setView }) {
    return (
        <div className="add-employee">
            <header>
                <img src="/logo192.png" alt="MatchUp Logo" />
                <h1>Add New Employee</h1>
            </header>
            <form className="form-container">
                <div className="form-group">
                    <label>Position:</label>
                    <select>
                        <option>Analyst</option>
                        <option>Backend Developer</option>
                        <option>Frontend Developer</option>
                        <option>Tester</option>
                        <option>Team Leader</option>
                        <option>Project Manager</option>
                        <option>Consultant</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Seniority:</label>
                    <select>
                        <option>Intern</option>
                        <option>Junior</option>
                        <option>Mid</option>
                        <option>Senior</option>
                        <option>Specialist</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Availability:</label>
                    <div className="radio-group">
                        <label>
                            <input type="radio" name="availability" /> Full-time
                        </label>
                        <label>
                            <input type="radio" name="availability" /> Part-time
                        </label>
                    </div>
                </div>
                <div className="form-group">
                    <label>
                        <input type="checkbox" /> Salary Negotiation: To Be Determined
                    </label>
                </div>
                <button type="button" className="submit-button" onClick={() => setView("SettingsScreen")}>
                    Submit
                </button>
            </form>
            <div className="icons">
                <span>ℹ️ App Info</span>
                <span>⚙️ Settings</span>
            </div>
            <footer>Version 1.2</footer>
        </div>
    );
}

export default AddEmployee;
