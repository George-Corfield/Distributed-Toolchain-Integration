package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;
import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;



@Controller
public class FileController {
    @Autowired
    private FileService fileService;


    @PostMapping("/saveFile")
    public String saveFile(@RequestParam("file") MultipartFile file){
        try{
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

    @PostMapping("/saveModule")
    public String saveModule(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId, @RequestParam(value = "publicFile", defaultValue = "false") boolean publicFile){
        try {
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
