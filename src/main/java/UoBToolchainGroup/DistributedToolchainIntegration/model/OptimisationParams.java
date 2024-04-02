package UoBToolchainGroup.DistributedToolchainIntegration.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class OptimisationParams {

    @Id
    private ObjectId paramsId;
    private Integer iterations;
    private List<ObjectId> modules;
    //add other variables here

    public OptimisationParams(){
        super();
        this.paramsId = new ObjectId();
        this.iterations = 100;
        this.modules = new ArrayList<>();
    }

    public OptimisationParams(ObjectId paramsId, Integer iterations, List<ObjectId> modules){
        super();
        this.paramsId = paramsId;
        this.iterations = iterations;
        this.modules = modules;
    }
    
    public ObjectId getParamsId(){
        return paramsId;
    }

    public void setParamsId(ObjectId paramsId){
        this.paramsId = paramsId;
    }

    public Integer getIterations(){
        return iterations;
    }

    public void setIterations(Integer iter){
        this.iterations = iter;
    }

    public List<ObjectId> getModules(){
        return this.modules;
    }

    public void setModules(List<ObjectId> modules){
        this.modules = modules;
    }

    public void addModule(ObjectId module){
        this.modules.add(module);
    }

}
