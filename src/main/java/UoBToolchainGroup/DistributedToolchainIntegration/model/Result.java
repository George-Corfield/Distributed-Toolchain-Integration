package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Results")
public class Result {

    @Id
    private ObjectId resultId;
    private ObjectId partId;
    private JSONArray variables;
    private double outputValue;
    private double fitnessLevel;


    public Result(){
        resultId = new ObjectId();
        variables = new JSONArray();
    }

    public Result(ObjectId partId, JSONArray json, double outputVal ,double fitness){
        this.partId = partId;
        this.variables = json;
        this.outputValue = outputVal;
        this.fitnessLevel = fitness;
    }

    public ObjectId getResultId(){
        return resultId;
    }

    public void setResultId(ObjectId id){
        this.resultId = id;
    }

    public ObjectId getPartId(){
        return partId;
    }

    public void setPartId(ObjectId id){
        this.partId = id;
    }

    public JSONArray getVariables(){
        return variables;
    }

    public void setVariables(JSONArray json){
        this.variables = json;
    }

    public void addVariable(JSONObject obj){
        variables.put(obj);
    }

    public double getOutputValue(){
        return outputValue;
    }

    public void setOutputValue(double outputValue){
        this.outputValue = outputValue;
    }

    public double getFitnessLevel(){
        return fitnessLevel;
    }

    public void setFitnessLevel(double fitness){
        this.fitnessLevel = fitness;
    }
}
