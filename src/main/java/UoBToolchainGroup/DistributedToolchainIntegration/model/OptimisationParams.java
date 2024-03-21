package UoBToolchainGroup.DistributedToolchainIntegration.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptimisationParams")

public class OptimisationParams {

    @Id
    private ObjectId paramsId;
    private Integer iterations;
    private List<ModulesFile> modules;
    //add other variables here

    public OptimisationParams(){
        super();
    }

    public OptimisationParams(ObjectId paramsId, Integer iterations, List<ModulesFile> modules){
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

    public List<ModulesFile> getModules(){
        return this.modules;
    }

    public void setModules(List<ModulesFile> modules){
        this.modules = modules;
    }

    public void addModule(ModulesFile module){
        this.modules.add(module);
    }

}
