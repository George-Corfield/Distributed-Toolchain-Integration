package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.model.VariablesFile;
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

    @PostMapping("/projects/varsfile/{projectName}/{partId}")
    public String addVars(@PathVariable String projectName, @PathVariable String partId, Model model, @RequestParam("file") MultipartFile file) throws IOException{
        //turn the provided file into a variable file
        VariablesFile varFile = new VariablesFile(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        List<Variable> varsList = varFile.getVarList();

        //for each variable in the list, add it to the part
        for (int i = 0; i < varsList.size(); i++){
            Variable variable = new Variable(new ObjectId(), varsList.get(i).getVariableName(), varsList.get(i).getInitVal(), varsList.get(i).getLowBound(), varsList.get(i).getUpBound());
            variableService.createOptimisationVar(variable);
            Part part = partService.getPartbyId(new ObjectId(partId));
            part.addVariable(variable);
            partService.updatePart(part);
        }
        return "redirect:/projects/{projectName}/{partId}";
    }
}
