package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CadFile")


public class CadFile {

    @Id
    private String cadFileId;
    private String userId;
    private String projectId;
    private String partId;
    private String fileName;
    


    public CadFile(){
        super();
    }

    public CadFile(String cadFileId, String userId, String projectId, String partId, String fileName){
        super();
        this.cadFileId = cadFileId;
        this.userId = userId;
        this.projectId = projectId;
        this.partId = partId;
        this.fileName = fileName;
    }

    public String getCadFileId(){
        return cadFileId;
    }

    public void setCadFileId(String cadFileId){
        this.cadFileId = cadFileId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getProjectId(){
        return projectId;
    }

    public void setProjectId(String projectId){
        this.projectId = projectId;
    }

    public String getPartId(){
        return partId;
    }

    public void setPartId(String partId){
        this.partId = partId;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }





}

