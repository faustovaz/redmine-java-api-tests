package xyz.faustovaz;

import java.util.List;

import com.taskadapter.redmineapi.RedmineCommunicationException;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Project;

public class App {
        

    static final String API_KEY = "2ba1b9efab8878668e3883564dfa76521fe59e8e";
    static final String URI = "http://localhost:3000";
    
    
    public static void main(String[] args) {
        try {
            ProjectManager projectManager = new ProjectManager(URI, API_KEY);
            // Lista todos projetos no redmine
            List<Project> projects = projectManager.getProjects();
            projects.forEach(System.out::println);
            
            // Cria novo projeto no redmine
            Project createdProject = projectManager.createProject("Project1", "project_1");
            System.out.println(String.format("Projeto %s criado!",createdProject.getName()));
        }
        catch(RedmineCommunicationException rce) {
            System.out.println("Could not connect to the redmine instance. Is it really running on " + URI + " ?");
            //rce.printStackTrace();
        }
        catch(RedmineException e) {
            e.printStackTrace();
        }
        
    }
    
}
