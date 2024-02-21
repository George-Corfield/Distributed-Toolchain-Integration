package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;

@Controller
public class PartController {
    @Autowired
    private PartService partService;


    @GetMapping("/projects/{projectName}/{partId}")
    public String getPart(@PathVariable String projectName, @PathVariable String partId, Model model){
        Part part = partService.getPartbyId(new ObjectId(partId));
        System.out.println("here");
        model.addAttribute("part", part);
        return "Optimisation";
    }
}
