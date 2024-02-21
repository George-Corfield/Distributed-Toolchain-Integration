package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class FileController {

    @PostMapping("/saveFile")
    public String saveFile(@RequestParam("file") MultipartFile file){
        
        try{
            //Add in the logic to save the file here.
            //we need to know the user and/or project and the part it relates to.
            //Add a max filesize to the files (security)
            return "Saved";
        }
        catch(Exception e){
            return "File not saved";
        }
    } 
}
