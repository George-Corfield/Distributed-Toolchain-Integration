package UoBToolchainGroup.DistributedToolchainIntegration.model;


import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptimisationResult")


public class OptimisationResult {
    
    @Id
    private String resultId;
    private String projectId;
    private String partId;
    private String paramsId;
    private int iterNum;
    private String braidFile;
    private String meshFile;
    private Date resultTime;

    public OptimisationResult(){
        super();
    }

    public OptimisationResult(String resultId, String projectId, String partId, String paramsId, int iterNum, String braidFile, String meshFile, Date resultTime){
        this.resultId = resultId;
        this.projectId = projectId;
        this.partId = partId;
        this.paramsId = paramsId;
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

    public String getParamsId() {
        return paramsId;
    }

    public void setParamsId(String paramsId) {
        this.paramsId = paramsId;
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
