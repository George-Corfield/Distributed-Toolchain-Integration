/*
 * This class is used to create optimisationParameter objects
 */
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
    private Boolean maximising;
    //add other variables here

    //Constructor
    public OptimisationParams(){
        super();
        this.paramsId = new ObjectId();
        this.iterations = 100;
        this.modules = new ArrayList<>();
        this.maximising = false;
    }

    //Constructor
    public OptimisationParams(ObjectId paramsId, Integer iterations, List<ObjectId> modules, Boolean max){
        super();
        this.paramsId = paramsId;
        this.iterations = iterations;
        this.modules = modules;
        this.maximising = max;
    }
    
    //getters and setters

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

    public Boolean getMaximising(){
        return this.maximising;
    }

    public void setMaximising(Boolean max){
        this.maximising = max;
    }

}
