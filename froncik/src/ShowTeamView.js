// ShowTeamView.js
import './ShowTeamView.css';

function ShowTeamView({ project, setView }) {
    return (
        <div className="show-team-view">
            <h1>{project.name} - Team Members</h1>
            {project.teamMembers.length > 0 ? (
                <ul>
                    {project.teamMembers.map((member) => (
                        <li key={member}>{member}</li>
                    ))}
                </ul>
            ) : (
                <p>No team members assigned.</p>
            )}
            <button onClick={() => setView('projects')}>Back to Projects</button>
        </div>
    );
}

export default ShowTeamView;
