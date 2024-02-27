package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;
import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import org.bson.types.ObjectId;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;


    @PostMapping("/saveFile")
    public String saveFile(@RequestParam("file") MultipartFile file){
        
        try{
            ObjectId fileId = new ObjectId();
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] data = file.getBytes();
            File newfile = new File(fileId, fileName, contentType, data);
            fileService.createFile(newfile);
            return "Saved";
        }
        catch(Exception e){
            return "File not saved";
        }
    } 
}
