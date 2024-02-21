package UoBToolchainGroup.DistributedToolchainIntegration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Files")
public class File {
    @Id
    private String fileName;
    private String contentType;
    private byte[] data;

    public File(){
        super();
    }

    public File(String fileName, String contentType, byte[] data){
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    public String getFileName(){
        return fileName;
    }

    public String getContentType(){
        return contentType;
    }

    public byte[] getFileContent(){
        return data;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    public void setFileContent(byte[] fileContent){
        this.data = fileContent;
    }

}
