/*
 * This class is used to create VariableFile objects.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.model;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.*;
import org.springframework.data.annotation.PersistenceCreator;

public class VariablesFile extends File{

    //Constructors

    @PersistenceCreator
    public VariablesFile(String fileName, String contentType, byte[] data){
        super( fileName, contentType, data);
    }

    public VariablesFile(String fileName, String contentType, List<Variable> variableList){
        super( fileName, contentType, new JSONArray(variableList).toString().getBytes());
    }

    //Converts any variable list to a JSONArray.
    //It is static so a VariableFile object does NOT need to be instantiated to use this function.
    public static JSONArray convertToJsonArray(List<Variable> varList){
        return new JSONArray(varList);
    }

    //Converts a Json Variable File (list of bytes) into a variable list.
    //It is static so a VariableFile object does NOT need to be instantiated to use this function.
    public static List<Variable> convertToVarList(byte[] bytes){
        //create a list to store the result
        List<Variable> vars = new ArrayList<>();
        //turn the bytes into a JSONArray
        JSONArray array = new JSONArray(new String(bytes, StandardCharsets.UTF_8));

        //for each variable in the provided file add it to the list
        for(int i=0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            Variable var = new Variable(new ObjectId(), obj.get("variableName").toString(),
                obj.getDouble("initVal"), 
                obj.getDouble("lowBound"),
                obj.getDouble("upBound")); 
            vars.add(var);
        }
        return vars;
    }  

    //getters

    public JSONArray getJsonArray(){
        return convertToJsonArray(convertToVarList(data));
    }

    public List<Variable> getVarList(){
        return convertToVarList(data);
    }
}
