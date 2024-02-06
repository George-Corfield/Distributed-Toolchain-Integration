package UoBToolchainGroup.DistributedToolchainIntegration.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Projects")

public class Project {

    @Id
    private String projectId;
    private String partId;
    private String projectName;
    private String partName;
    private String projectDescription;
    private Date projectStartDate;
    private String userId;

    public Project(){
        super();
    }

    public Project(String projectId, 
    String partId, 
    String projectName, 
    String partName,
    String projectDescription, 
    Date projectStartDate,
    String userId){
        this.projectId = projectId;
        this.partId = partId;
        this.projectName = projectName;
        this.partName = partName;
        this.projectDescription = projectDescription;
        this.projectStartDate = projectStartDate;
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjctName(String projectName) {
        this.projectName = projectName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }



    
}
