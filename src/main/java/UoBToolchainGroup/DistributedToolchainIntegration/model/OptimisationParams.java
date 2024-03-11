package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptimisationParams")

public class OptimisationParams {

    @Id
    private ObjectId paramsId;
    private Integer iterations;
    //add other variables here

    public OptimisationParams(){
        super();
    }

    public OptimisationParams(ObjectId paramsId, Integer iterations){
        super();
        this.paramsId = paramsId;
        this.iterations = iterations;
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

}
