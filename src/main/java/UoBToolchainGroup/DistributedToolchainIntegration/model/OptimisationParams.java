package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptimisationParams")

public class OptimisationParams {

    @Id
    private String paramsId;
    private String projectId;
    private String cadFileId;

    public OptimisationParams(){
        super();
    }

    public OptimisationParams(String paramsId, String projectId, String cadFileId){
        super();
        this.paramsId = paramsId;
        this.projectId = projectId;
        this.cadFileId = cadFileId;
    }
    
    public String getParamsId(){
        return paramsId;
    }

    public void setParamsId(String paramsId){
        this.paramsId = paramsId;
    }

    public String getProjectId(){
        return projectId;
    }

    public void setProjectId(String projectId){
        this.projectId = projectId;
    }

    public String getCadFileId(){
        return cadFileId;
    }

    public void setCadFileId(String cadFileId){
        this.cadFileId = cadFileId;
    }


}
