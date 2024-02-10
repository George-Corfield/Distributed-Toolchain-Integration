package UoBToolchainGroup.DistributedToolchainIntegration.model;


import java.sql.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "OptimisationResult")


public class OptimisationResult {
    
    @Id
    private String resultId;
    private Part part;
    private OptimisationParams params;
    private int iterNum;
    private List<Variable> outputVar;
    private List<Variable> optimisedVar;
    private Date resultTime;

    public OptimisationResult(){
        super();
    }

    public OptimisationResult(String resultId, Part part, OptimisationParams params, int iterNum, List<Variable> outputVar, List<Variable> optimisedVar, Date resultTime){
        this.resultId = resultId;
        this.part = part;
        this.params = params;
        this.iterNum = iterNum;
        this.outputVar = outputVar;
        this.optimisedVar = optimisedVar;
        this.resultTime = resultTime;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
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

    public List<Variable> getOutputVar() {
        return outputVar;
    }

    public void setOutputVar(List<Variable> outputVar) {
        this.outputVar = outputVar;
    }

    public List<Variable> getOptimisedVar() {
        return optimisedVar;
    }

    public void setOptimisedVar(List<Variable> optimisedVar) {
        this.optimisedVar = optimisedVar;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }



}
