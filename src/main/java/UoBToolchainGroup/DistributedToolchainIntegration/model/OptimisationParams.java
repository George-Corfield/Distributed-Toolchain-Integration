package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OptimisationParams")

public class OptimisationParams {

    @Id
    private String paramsId;
    //add other variables here

    public OptimisationParams(){
        super();
    }

    public OptimisationParams(String paramsId){
        super();
        this.paramsId = paramsId;
    }
    
    public String getParamsId(){
        return paramsId;
    }

    public void setParamsId(String paramsId){
        this.paramsId = paramsId;
    }


}
