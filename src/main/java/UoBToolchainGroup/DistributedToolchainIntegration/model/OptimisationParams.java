package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptimisationParams")

public class OptimisationParams {

    @Id
    private ObjectId paramsId;
    //add other variables here

    public OptimisationParams(){
        super();
    }

    public OptimisationParams(ObjectId paramsId){
        super();
        this.paramsId = paramsId;
    }
    
    public ObjectId getParamsId(){
        return paramsId;
    }

    public void setParamsId(ObjectId paramsId){
        this.paramsId = paramsId;
    }


}
