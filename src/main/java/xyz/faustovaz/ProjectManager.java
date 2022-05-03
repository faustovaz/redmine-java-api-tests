package xyz.faustovaz;

import java.util.List;
import java.util.Optional;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Project;

public class ProjectManager {

    private RedmineManager manager;
    
    public ProjectManager(String uri, String apikey) {
        this.manager = RedmineManagerFactory.createWithApiKey(uri, apikey);
    }
    
    public List<Project> getProjects() throws RedmineException {
        return this.manager.getProjectManager().getProjects();
    }
    
    public Optional<Project> getProjectByIdentifier(String identifier) throws RedmineException {
        return this.getProjects()
                .stream()
                .filter(project -> project.getIdentifier().equals(identifier))
                .findFirst();
    }
    
    public void checkIfIdentifierIsUsed(String identifier) throws RedmineException {
        Optional<Project> project = this.getProjectByIdentifier(identifier);
        if(project.isPresent())
            throw new RedmineException("Identificador já utilizado"); 
    }
    
    public Project createProject(String name, String identifier) throws RedmineException {
        checkIfIdentifierIsUsed(identifier);
        return new Project(this.manager.getTransport())
                .setName(name)
                .setIdentifier(identifier)
                .create();
    }
    
}
