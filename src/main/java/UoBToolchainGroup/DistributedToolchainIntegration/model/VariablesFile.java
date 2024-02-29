package UoBToolchainGroup.DistributedToolchainIntegration.model;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.json.*;

public class VariablesFile extends File{

    public VariablesFile(String fileName, String contentType, byte[] data){
        super( fileName, contentType, data);
    }

    public List<Variable> convertToVariableList(){
        List<Variable> vars = List.of();
        JSONArray array = new JSONArray(new String(data, StandardCharsets.UTF_8));
        for(int i=0; i < array.length(); i++){
            System.out.println(array.getJSONObject(i));
        }
        return vars;
    }

    public JSONArray convertToJsonArray(List<Variable> varList){
        return new JSONArray(varList);
    }
    
}
