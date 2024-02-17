package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Variables")

public class Variable {
    
    @Id 
    private ObjectId variableId;
    private String variableName;
    private float initVal;
    private float lowBound;
    private float upBound;

    public Variable(){
        super();
    }

    public Variable(ObjectId variableId, String variableName, float initVal, float lowBound, float upBound){
        super();
        this.variableId = variableId;
        this.variableName = variableName;
        this.initVal = initVal;
        this.lowBound = lowBound;
        this.upBound = upBound;
    }

    public ObjectId getVariableId() {
        return variableId;
    }

    public void setVariableId(ObjectId variableId) {
        this.variableId = variableId;
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
