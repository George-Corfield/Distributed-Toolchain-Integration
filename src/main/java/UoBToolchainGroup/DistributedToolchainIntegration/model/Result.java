package UoBToolchainGroup.DistributedToolchainIntegration.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Results")
public class Result {

    @Id
    private ObjectId resultId;
    private ObjectId partId;
    private List<Variable> variables;
    private double outputValue;
    private double fitnessLevel;


    public Result(){
        resultId = new ObjectId();
        variables = new ArrayList<>();
    }

    public Result(ObjectId partId, List<Variable> json, double outputVal ,double fitness){
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

    public List<Variable> getVariables(){
        return variables;
    }

    public void setVariables(List<Variable> json){
        this.variables = json;
    }

    public void addVariable(Variable obj){
        System.out.println(variables);
        System.out.println(obj);
        if (!variables.contains(obj)){
            variables.add(obj);
        }
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
