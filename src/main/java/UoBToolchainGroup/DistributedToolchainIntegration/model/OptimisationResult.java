package UoBToolchainGroup.DistributedToolchainIntegration.model;


import java.sql.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptimisationResult")


public class OptimisationResult {
    
    @Id
    private String resultId;
    private Project project;
    private Part part;
    private OptimisationParams params;
    private int iterNum;
    private String braidFile;
    private String meshFile;
    private Date resultTime;

    public OptimisationResult(){
        super();
    }

    public OptimisationResult(String resultId, Project project, Part part, OptimisationParams params, int iterNum, String braidFile, String meshFile, Date resultTime){
        this.resultId = resultId;
        this.project = project;
        this.part = part;
        this.params = params;
        this.iterNum = iterNum;
        this.braidFile = braidFile;
        this.meshFile = meshFile;
        this.resultTime = resultTime;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public Project getProjectId() {
        return project;
    }

    public void setProjectId(Project project) {
        this.project = project;
    }

    public Part getPartId() {
        return part;
    }

    public void setPartId(Part part) {
        this.part = part;
    }

    public OptimisationParams getParamsId() {
        return params;
    }

    public void setParamsId(OptimisationParams params) {
        this.params = params;
    }

    public int getIterNum() {
        return iterNum;
    }

    public void setIterNum(int iterNum) {
        this.iterNum = iterNum;
    }

    public String getBraidFile() {
        return braidFile;
    }

    public void setBraidFile(String braidFile) {
        this.braidFile = braidFile;
    }

    public String getMeshFile() {
        return meshFile;
    }

    public void setMeshFile(String meshFile) {
        this.meshFile = meshFile;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }



}
