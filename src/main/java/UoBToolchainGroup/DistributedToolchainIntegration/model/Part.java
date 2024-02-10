package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Parts")
public class Part {
    
    @Id
    private String partId;
    private String partName;
    private String partDescription;

    public Part(){
        super();
    }

    public Part(String partId, String partName, String partDescription){
        this.partId = partId;
        this.partName = partName;
        this.partDescription = partDescription;
    }

    public String getPartId(){
        return partId;
    }

    public void setPartId(String partId){
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
}
