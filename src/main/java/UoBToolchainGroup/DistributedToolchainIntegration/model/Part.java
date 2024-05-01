/*
 * This class is used to create part objects
 */
package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Parts")
public class Part {
    
    @Id
    private ObjectId partId;
    private String partName;
    private String partDescription;
    private OptimisationParams optimisationParams;
    private ObjectId projectId;

    //Constructors

    public Part(){
        super();
        this.partId = new ObjectId();
        this.optimisationParams = new OptimisationParams();
    }

    public Part(ObjectId partId, String partName, String partDescription, OptimisationParams optimisationParams, ObjectId projectId){
        this.partId = partId;
        this.partName = partName;
        this.partDescription = partDescription;
        this.optimisationParams = optimisationParams;
        this.projectId = projectId;
    }   

    //getters and setters

    public ObjectId getPartId(){
        return partId;
    }

    public void setPartId(ObjectId partId){
        this.partId = partId;
    }

    public String getPartName(){
        return partName;
    }

    public void setPartName(String partName){
        this.partName = partName;
    }

    public String getPartDescription(){
        return partDescription;
    }

    public void setPartDescription(String partDescription){
        this.partDescription = partDescription;
    }


    public OptimisationParams getOptimisationParams(){
        return optimisationParams;
    }

    public void setOptimisationParams(OptimisationParams optimisationParams){
        this.optimisationParams = optimisationParams;
    }

    public ObjectId getProjectId(){
        return projectId;
    }

    public void setProjectId(ObjectId projectId){
        this.projectId = projectId;
    }


}
