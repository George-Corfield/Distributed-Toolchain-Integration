/*
 * This is the file controller. When a user wants to upload a file it is send to an endpoint in this file.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;
import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;



@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    //This is the endpoint to save general files.
    @PostMapping("/saveFile")
    public String saveFile(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "No file Submitted";
        }
        try{
            //Collects information about the file and then use the file service to save the file.
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] data = file.getBytes();
            File newfile = new File(fileName, contentType, data);
            fileService.createFile(newfile);
            return "Saved";
        }
        catch(Exception e){
            return "File not saved";
        }
    }

    //This is the endpoint to save module files.
    @PostMapping("/saveModule")
    public String saveModule(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId, @RequestParam(value = "publicFile", defaultValue = "false") boolean publicFile){
        if(file.isEmpty()){
            return "No file Submitted";
        }
        try {
            //Collects information about the file and then use the file service to save the file.
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] data = file.getBytes();
            ModulesFile newFile = new ModulesFile(fileName, contentType, data, new ObjectId(userId), publicFile);
            fileService.createFile(newFile);
        }catch(Exception e){
            //add exception logic if necessary
        }
        return "redirect:/projects";
    }
}
