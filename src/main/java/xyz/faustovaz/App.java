package xyz.faustovaz;

import com.taskadapter.redmineapi.RedmineCommunicationException;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.RedmineProcessingException;
import com.taskadapter.redmineapi.bean.Project;

public class App {
        

    static final String API_KEY = "f18b4a84fac62b72f3ebfc3bb328af09caeab97e";
    static final String URI = "http://localhost:3000";
    
    
    public static void main(String[] args) {
        RedmineManager manager = RedmineManagerFactory.createWithApiKey(App.URI, App.API_KEY);
        try {
            Project createdProject = new Project(manager.getTransport())
                    .setName("Projeto de Testes")
                    .setIdentifier("projeto-de-testes")
                    .create();      
            
            Project createdProject2 = new Project(manager.getTransport())
                    .setName("Projeto de Testes")
                    .setIdentifier("projeto-de-testes")
                    .create();      
            
            System.out.println(
                    String.format("Projeto[id=%s, identifier=%s, name=%s]", 
                            createdProject.getId(), 
                            createdProject.getIdentifier(), 
                            createdProject.getName()));
            
            System.out.println(
                    String.format("Projeto[id=%s, identifier=%s, name=%s]", 
                            createdProject2.getId(), 
                            createdProject2.getIdentifier(), 
                            createdProject2.getName()));            
        }
        catch(RedmineCommunicationException e) {
            System.out.println("Could not connect to the redmine instance. Is it really running on " + App.URI + "/ ?");
        }
        catch(RedmineProcessingException redmineException) {
            System.out.println("Erro ao criar projeto: " + redmineException.getMessage());
        }
        catch(RedmineException ex) {
            ex.printStackTrace();
        }

    }
    
}
