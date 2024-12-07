// ProjectsView.js
import './ProjectsView.css';

function ProjectsView({ projects, setView, setSelectedProject }) {
    return (
        <div className="projects-view">
            <h1>Projects</h1>
            <p>Number of Projects: {projects.length}</p>
            <div className="projects-grid">
                {projects.map((project) => (
                    <div key={project.id} className="project-card">
                        <h2>{project.name}</h2>
                        <p>Team Members: {project.teamMembers.length}</p>
                        <div className="button-group">
                            <button
                                onClick={() => {
                                    setSelectedProject(project);
                                    setView('showTeam');
                                }}
                            >
                                Show Team
                            </button>
                            <button
                                onClick={() => {
                                    setSelectedProject(project);
                                    setView('chooseTeam');
                                }}
                            >
                                Choose Your Team
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ProjectsView;
