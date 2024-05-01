/*
 * This class is used to create Variable objects.
 */
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
    private boolean result;

    //Constructors

    public Variable(){
        super();
        this.variableId = new ObjectId();
        this.result = false;
    }

    public Variable(Variable that){
        this(that.variableId,that.partId, that.variableName, that.initVal, that.lowBound, that.upBound);
    }

    public Variable(ObjectId variableId,ObjectId partId, String variableName, double initVal, double lowBound, double upBound){
        super();
        this.variableId = variableId;
        this.partId = partId;
        this.variableName = variableName;
        this.initVal = initVal;
        this.lowBound = lowBound;
        this.upBound = upBound;
        this.result = false;
    }

    public Variable(ObjectId variableId, String variableName, double initVal, double lowBound, double upBound){
        super();
        this.variableId = variableId;
        this.variableName = variableName;
        this.initVal = initVal;
        this.lowBound = lowBound;
        this.upBound = upBound;
        this.result = false;
    }

    public Variable(String variableName, double initVal){
        //for result variables only
        super();
        this.variableId = new ObjectId();
        this.variableName = variableName;
        this.initVal = initVal;
        this.lowBound = initVal;
        this.upBound = initVal;
        this.result = true;
    }

    public Variable(String variableName){
        this.variableName = variableName;
        this.result = false;
    }

    //getters and setters

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

    public boolean getResult(){
        return result;
    }

    public void setResult(boolean res){
        this.result = res;
    }

    @Override
    public String toString(){
        return String.format("{\"variableId\":\"%s\",\"variableName\":\"%s\",\"initVal\":%f,\"lowBound\":%f,\"upBound\":%f}",
        variableId.toString(),variableName, initVal, lowBound, upBound);
    }

    @Override
    public boolean equals(Object v){
        if (v == this){
            return true;
        }
        if (!(v instanceof Variable)){
            return false;
        }
        Variable that = (Variable) v;
        return this.variableId.equals(that.variableId) && this.variableName.equals(this.variableName);
    }

    @Override
    public final int hashCode(){
        int result = 17;
        if (variableId != null){
            result = 31 * result + variableId.hashCode();
        }
        if (variableName != null){
            result = 31 * result + variableName.hashCode();
        }
        return result;
    }
}
