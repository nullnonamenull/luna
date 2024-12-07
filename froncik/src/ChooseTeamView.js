// ChooseTeamView.js
import './ChooseTeamView.css';

function ChooseTeamView({ project, setView, availableMembers, addMemberToProject }) {
    const bestCandidate = availableMembers[Math.floor(Math.random() * availableMembers.length)];

    const handleAddMember = () => {
        addMemberToProject(project.id, bestCandidate.name);
        setView('showTeam');
    };

    return (
        <div className="choose-team-view">
            <h1>{project.name} - Choose Your Team</h1>
            <h2>Best Candidate to Add:</h2>
            <div className="candidate-card">
                <h3>{bestCandidate.name}</h3>
                <p>Skills: {bestCandidate.skills.join(', ')}</p>
                <button onClick={handleAddMember}>Add to Team</button>
            </div>
            <button onClick={() => setView('projects')}>Back to Projects</button>
        </div>
    );
}

export default ChooseTeamView;
