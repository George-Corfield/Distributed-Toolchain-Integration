package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Variables")

public class Variable {
    
    @Id 
    private ObjectId variableId;
    private ObjectId partId;
    private String variableName;
    private double initVal;
    private double lowBound;
    private double upBound;

    public Variable(){
        super();
        this.variableId = new ObjectId();
    }

    public Variable(ObjectId variableId,ObjectId partId, String variableName, double initVal, double lowBound, double upBound){
        super();
        this.variableId = variableId;
        this.partId = partId;
        this.variableName = variableName;
        this.initVal = initVal;
        this.lowBound = lowBound;
        this.upBound = upBound;
    }

    public Variable(ObjectId variableId, String variableName, double initVal, double lowBound, double upBound){
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

    public double getInitVal() {
        return initVal;
    }

    public void setInitVal(double initVal) {
        this.initVal = initVal;
    }

    public double getLowBound() {
        return lowBound;
    }

    public void setLowBound(double lowBound) {
        this.lowBound = lowBound;
    }

    public double getUpBound() {
        return upBound;
    }

    public void setUpBound(double upBound) {
        this.upBound = upBound;
    }

    public ObjectId getPartId(){
        return partId;
    }

    public void setPartId(ObjectId partId){
        this.partId = partId;
    }

    @Override
    public String toString(){
        return String.format("{\"variableName\":\"%s\",\"initVal\":%f,\"lowBound\":%f,\"upBound\":%f}",
        variableName, initVal, lowBound, upBound);
    }

}
