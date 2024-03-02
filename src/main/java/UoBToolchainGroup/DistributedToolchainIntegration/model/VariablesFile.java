package UoBToolchainGroup.DistributedToolchainIntegration.model;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.json.*;

public class VariablesFile extends File{

    public VariablesFile(String fileName, String contentType, byte[] data){
        super( fileName, contentType, data);
    }

    public VariablesFile(String fileName, String contentType, List<Variable> variableList){
        super( fileName, contentType, new JSONArray(variableList).toString().getBytes());
    }

    public static JSONArray convertToJsonArray(List<Variable> varList){
        return new JSONArray(varList);
    }

    public static List<Variable> convertToVarList(byte[] bytes){
        List<Variable> vars = List.of();
        JSONArray array = new JSONArray(new String(bytes, StandardCharsets.UTF_8));
        for(int i=0; i < array.length(); i++){
            System.out.println(array.getJSONObject(i));
        }
        return vars;
    }  

    public JSONArray getJsonArray(){
        return convertToJsonArray(convertToVarList(data));
    }

    public List<Variable> getVarList(){
        return convertToVarList(data);
    }
}
