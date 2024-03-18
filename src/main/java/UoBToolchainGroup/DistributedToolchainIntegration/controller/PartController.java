package UoBToolchainGroup.DistributedToolchainIntegration.controller;



import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.VariableService;

@Controller
public class PartController {
    @Autowired
    private PartService partService;
    @Autowired 
    private VariableService variableService;
    


    @GetMapping("/projects/{projectName}/{partId}")
    public String getPart(@PathVariable String projectName, @PathVariable String partId, Model model){
        Part part = partService.getPartbyId(new ObjectId(partId));
        model.addAttribute("part", part);
        model.addAttribute("projectName", projectName);
        model.addAttribute("variable", new Variable());
        return "Optimisation";
    }

    @PostMapping("/projects/{projectName}/{partId}")
    public String addVar(@PathVariable String projectName, @PathVariable String partId, Model model, @ModelAttribute("varialble") Variable variable){
        variable.setVariableId(new ObjectId());
        variableService.createOptimisationVar(variable);
        Part part = partService.getPartbyId(new ObjectId(partId));
        part.addVariable(variable);
        partService.updatePart(part);
        return "redirect:/projects/{projectName}/{partId}";
    }
}
