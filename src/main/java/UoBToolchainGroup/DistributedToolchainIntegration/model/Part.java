package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection="Parts")
public class Part {
    
    @Id
    private String partId;
    private String partName;
    private String partDescription;
    private List<Variable> variables;
    private List<Variable> optimisationVariables;

    public Part(){
        super();
    }

    public Part(String partId, String partName, String partDescription,
    List<Variable> variables, List<Variable> optimisationVariables){
        this.partId = partId;
        this.partName = partName;
        this.partDescription = partDescription;
        this.variables = variables;
        this.optimisationVariables = optimisationVariables;
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

    public List<Variable> getVariables(){
        return variables;
    }

    public void setVariables(List<Variable> variables){
        this.variables = variables;
    }

    public List<Variable> getOptimisationVariables(){
        return optimisationVariables;
    }

    public void setOptimisationVariables(List<Variable> optimisationVariables){
        this.optimisationVariables = optimisationVariables;
    }


}
