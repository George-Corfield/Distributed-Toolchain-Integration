/*
 * This class is used to create project objects
 */
package UoBToolchainGroup.DistributedToolchainIntegration.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Projects")

public class Project {

    @Id
    private ObjectId projectId;
    private String projectName;
    private String projectDescription;
    private Date projectStartDate;
    private ObjectId userId;

    //Constuctors

    public Project(){
        super();
        this.projectId = new ObjectId();
        this.projectStartDate = new Date();
    }

    public Project(ObjectId projectId, 
    String projectName, 
    String projectDescription, 
    Date projectStartDate,
    ObjectId userId){
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStartDate = projectStartDate;
        this.userId = userId;
    }

    //getters and setters

    public ObjectId getProjectId() {
        return projectId;
    }

    public void setProjectId(ObjectId projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
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

    public ObjectId getUser() {
        return userId;
    }

    public void setUser(ObjectId user) {
        this.userId = user;
    }

    @Override
    public String toString(){
        return String.format("{\"projectName\":\"%s\",\"projectDescription\":%s}", projectName,projectDescription);
    }

    
}
