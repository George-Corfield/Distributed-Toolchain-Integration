package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CadFile")


public class CadFile {

    @Id
    private String cadFileId;
    private User user;
    private Project project;
    private Part part;
    private String fileName;
    


    public CadFile(){
        super();
    }

    public CadFile(String cadFileId, User user, Project project, Part part, String fileName){
        super();
        this.cadFileId = cadFileId;
        this.user = user;
        this.project = project;
        this.part = part;
        this.fileName = fileName;
    }

    public String getCadFileId(){
        return cadFileId;
    }

    public void setCadFileId(String cadFileId){
        this.cadFileId = cadFileId;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Project getProject(){
        return project;
    }

    public void setProject(Project project){
        this.project = project;
    }

    public Part getPart(){
        return part;
    }

    public void setPart(Part part){
        this.part = part;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }





}

