package UoBToolchainGroup.DistributedToolchainIntegration.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Projects")

public class Project {

    @Id
    private String projectId;
    private String projectName;
    private String projectDescription;
    private Date projectStartDate;
    private User user;
    private List<Part> parts;

    public Project(){
        super();
    }

    public Project(String projectId, 
    String projectName, 
    String projectDescription, 
    Date projectStartDate,
    User user,
    List<Part> parts){
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStartDate = projectStartDate;
        this.user = user;
        this.parts = parts;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }



    public String getProjectName() {
        return projectName;
    }

    public void setProjctName(String projectName) {
        this.projectName = projectName;
    }


    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    
    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Part> getParts(){
        return parts;
    }

    public void setParts(List<Part> parts){
        this.parts = parts;
    }



    
}
