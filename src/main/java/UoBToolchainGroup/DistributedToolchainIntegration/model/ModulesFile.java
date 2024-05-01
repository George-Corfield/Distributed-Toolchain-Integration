/*
 * This model is for module files which extend the custom file class. These specifically store module files.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.model;


import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.data.annotation.PersistenceCreator;

public class ModulesFile extends File{


    private ObjectId userId;
    private boolean publicFile;

    //Constructor
    @PersistenceCreator
    public ModulesFile(String fileName, String contentType, byte[] data, ObjectId userId, boolean publicFile){
        super( fileName, contentType, data);
        this.userId = userId;
        this.publicFile = publicFile;
    }

    //calls the superclass constuctor
    public ModulesFile(JSONObject json){
        super(new ObjectId(json.getString("fileId")),json.getString("fileName"),json.getString("contentType"), new byte[0]);
    }

    //getters and setters

    public ObjectId getUserId(){
        return userId;
    }

    public boolean getPublicFile(){
        return publicFile;
    }

    public void setUserId(ObjectId userId){
        this.userId = userId;
    }

    public void setPublicFile(boolean publicFile){
        this.publicFile = publicFile;
    }

    // converts the file to a string.
    public String stringToJson(){
        JSONObject jsonObject = new JSONObject(this);
        return jsonObject.toString();
    }

    //converts from a string to json
    public static ModulesFile jsonToString(JSONObject json) {
        return new ModulesFile(json);
    }

    //Converts file information to a string
    @Override
    public String toString(){
        return String.format("{\"fileId\":%s,\"fileName\":%s,\"contentType\":%s,\"data\":[]}", super.getFileId().toString() ,super.getFileName(), super.getContentType());
    }

    
}
