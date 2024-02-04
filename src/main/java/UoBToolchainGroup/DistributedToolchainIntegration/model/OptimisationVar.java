package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "OptimisationVar")

public class OptimisationVar {
    
    @Id 
    private String variableId;
    private String cadFileId;
    private String projectId;
    private String variableName;
    private float initVal;
    private float lowBound;
    private float upBound;

    public OptimisationVar(){
        super();
    }

    public OptimisationVar(String variableId, String cadFileId, String projectId, String variableName, float initVal, float lowBound, float upBound){
        super();
        this.variableId = variableId;
        this.cadFileId = cadFileId;
        this.projectId = projectId;
        this.variableName = variableName;
        this.initVal = initVal;
        this.lowBound = lowBound;
        this.upBound = upBound;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    public String getCadFileId() {
        return cadFileId;
    }

    public void setCadFileId(String cadFileId) {
        this.cadFileId = cadFileId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public float getInitVal() {
        return initVal;
    }

    public void setInitVal(float initVal) {
        this.initVal = initVal;
    }

    public float getLowBound() {
        return lowBound;
    }

    public void setLowBound(float lowBound) {
        this.lowBound = lowBound;
    }

    public float getUpBound() {
        return upBound;
    }

    public void setUpBound(float upBound) {
        this.upBound = upBound;
    }


}
