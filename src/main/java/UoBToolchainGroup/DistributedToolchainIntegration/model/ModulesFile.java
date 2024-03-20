package UoBToolchainGroup.DistributedToolchainIntegration.model;


import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.data.annotation.PersistenceCreator;

public class ModulesFile extends File{
    
    @PersistenceCreator
    public ModulesFile(String fileName, String contentType, byte[] data){
        super( fileName, contentType, data);
    }

    public ModulesFile(JSONObject json){
        super(new ObjectId(json.getString("fileId")),json.getString("fileName"),json.getString("contentType"), new byte[0]);
        
    }


    public String stringToJson(){
        JSONObject jsonObject = new JSONObject(this);
        return jsonObject.toString();
    }

    public static ModulesFile jsonToString(JSONObject json) {
        return new ModulesFile(json);
    }

    @Override
    public String toString(){
        return String.format("{\"fileId\":%s,\"fileName\":%s,\"contentType\":%s,\"data\":[]}", super.getFileId().toString() ,super.getFileName(), super.getContentType());
    }

    
}
