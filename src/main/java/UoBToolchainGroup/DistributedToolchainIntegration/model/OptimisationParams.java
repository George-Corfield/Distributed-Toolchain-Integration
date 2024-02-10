package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptimisationParams")

public class OptimisationParams {

    @Id
    private String paramsId;
    private Project project;
    private CadFile cadFile;

    public OptimisationParams(){
        super();
    }

    public OptimisationParams(String paramsId, Project project, CadFile cadFile){
        super();
        this.paramsId = paramsId;
        this.project = project;
        this.cadFile = cadFile;
    }
    
    public String getParamsId(){
        return paramsId;
    }

    public void setParamsId(String paramsId){
        this.paramsId = paramsId;
    }

    public Project getProject(){
        return project;
    }

    public void setProject(Project project){
        this.project = project;
    }

    public CadFile getCadFile(){
        return cadFile;
    }

    public void setCadFile(CadFile cadFile){
        this.cadFile = cadFile;
    }


}
