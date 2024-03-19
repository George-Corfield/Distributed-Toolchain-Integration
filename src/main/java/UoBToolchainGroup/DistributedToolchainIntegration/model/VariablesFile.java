package UoBToolchainGroup.DistributedToolchainIntegration.model;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
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
        List<Variable> vars = new ArrayList<>();
        JSONArray array = new JSONArray(new String(bytes, StandardCharsets.UTF_8));
        for(int i=0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            Variable var = new Variable(new ObjectId(), obj.get("variableName").toString(),
                obj.getDouble("initVal"), 
                obj.getDouble("lowBound"), //I have no clue how to cast these to floats
                obj.getDouble("upBound")); 
            vars.add(var);
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
