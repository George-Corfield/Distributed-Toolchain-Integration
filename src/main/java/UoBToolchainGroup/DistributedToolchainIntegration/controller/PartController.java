/*
 * This is the parts controller. It has the endpoints for viewing and modifying parts.
 */
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
import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.model.VariablesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ResultService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.VariableService;

@Controller
public class PartController {
    @Autowired
    private PartService partService;
    @Autowired 
    private VariableService variableService;
    @Autowired
    private ResultService resultService;
    

    //Endpoint for the page that shows information for a specific part.
    @GetMapping("/projects/{projectName}/{partId}")
    public String getPart(@PathVariable String projectName, @PathVariable String partId, Model model){
        Part part = partService.getPartbyId(new ObjectId(partId));
        List<Variable> variables = variableService.getVariablesByPart(part.getPartId()); 
        List<Result> results = resultService.getResultsByPart(new ObjectId(partId));
        model.addAttribute("part", part);
        model.addAttribute("projectName", projectName);
        model.addAttribute("variable", new Variable());
        model.addAttribute("currentVariables", variables);
        model.addAttribute("results", results);
        return "optimisation";
    }

    //Endpoint for adding a variable to a part.
    @PostMapping("/projects/{projectName}/{partId}")
    public String addVar(@PathVariable String projectName, @PathVariable String partId, Model model, @ModelAttribute("varialble") Variable variable){
        variable.setPartId(new ObjectId(partId));
        variableService.createOptimisationVar(variable);
        return "redirect:/projects/{projectName}/{partId}";
    }

    //Endpoint for adding variables to a part using a variables file.
    @PostMapping("/projects/varsfile/{projectName}/{partId}")
    public String addVars(@PathVariable String projectName, @PathVariable String partId, Model model, @RequestParam("file") MultipartFile file) throws IOException{
        //turn the provided file into a variable file
        VariablesFile varFile = new VariablesFile(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        List<Variable> varsList = varFile.getVarList();

        //for each variable in the list, add it to the part
        for (int i = 0; i < varsList.size(); i++){
            Variable variable = new Variable(new ObjectId(), new ObjectId(partId), varsList.get(i).getVariableName(), varsList.get(i).getInitVal(), varsList.get(i).getLowBound(), varsList.get(i).getUpBound());
            variableService.createOptimisationVar(variable);
        }
        return "redirect:/projects/{projectName}/{partId}";
    }
}
