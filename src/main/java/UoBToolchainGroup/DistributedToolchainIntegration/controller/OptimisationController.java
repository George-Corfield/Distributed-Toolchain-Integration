/*
 * This is the controller for optimisation. It contains the endpoints for an optimisation request.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import UoBToolchainGroup.DistributedToolchainIntegration.HillClimb;
import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ResultService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.VariableService;


@Controller
public class OptimisationController {
    @Autowired
    private PartService partService;
    @Autowired
    private FileService fileService;
    @Autowired
    private VariableService variableService;
    @Autowired
    private ResultService resultService;

    @GetMapping("/projects/{projectName}/{partId}/optimise")
    public String getOptimisation(@CookieValue("userId") String id, @PathVariable String projectName, @PathVariable String partId, Model model){
        Part part = partService.getPartbyId(new ObjectId(partId));
        OptimisationParams op = part.getOptimisationParams();
        List<File> currentFiles = new ArrayList<>();
        List<ObjectId> currentFileId = op.getModules();

        //gets the data for the files based on id's stored in optimisation paramters
        for (int i = 0; i < currentFileId.size(); i++){
            currentFiles.add(fileService.getFileById(currentFileId.get(i)));
        }
        List<ModulesFile> mods = fileService.getAvailableModulesFiles(new ObjectId(id));
        List<Variable> variables = variableService.getVariablesByPart(new ObjectId(partId));
        model.addAttribute("part", part);
        model.addAttribute("projectName", projectName);
        model.addAttribute("currentModules", currentFiles);
        model.addAttribute("opParams", op);
        model.addAttribute("allModules", mods);
        model.addAttribute("variables", variables);
        return "optimise";
    }

    @PostMapping("/projects/{projectName}/{partId}/optimise")
    public String setParams(@PathVariable String projectName, @PathVariable String partId, Model model, @ModelAttribute("opParams") OptimisationParams newOp, @RequestParam("selectedModules") String selectedModules){
        //gets the current part and optimisation paramters
        Part part = partService.getPartbyId(new ObjectId(partId));
        OptimisationParams op = part.getOptimisationParams();

        //splits the id's into a list via the comma, and appends them to the optimisation parameter list
        String[] mods = selectedModules.split(",");
        op.setModules(new ArrayList<>());
        for (String s: mods){
            op.addModule(new ObjectId(s));
        }

        //sets the iterations to the newly entered iterations, which is kept the same if the user does not iteract
        op.setIterations(newOp.getIterations());
        op.setIp(newOp.getIp());
        op.setMaximising(newOp.getMaximising());
        part.setOptimisationParams(op);

        //updates the part
        partService.updatePart(part);
        return "redirect:/projects/{projectName}/{partId}/optimise";
    }

    @PostMapping("/optimise/projects/{projectName}/{partId}")
    public String startOptimisation(@PathVariable String projectName, @PathVariable String partId, Model model, @RequestParam("selectedVariables") String selectedVariables) throws IOException, InterruptedException{
        String[][] variables = null;
        //maps the selected variables html list to java array
        try {
                ObjectMapper objectMapper = new ObjectMapper();
                variables = objectMapper.readValue(selectedVariables, String[][].class);
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/projects/{projectName}/{partId}/optimise";
            }
        Part p = partService.getPartbyId(new ObjectId(partId));
        OptimisationParams op = p.getOptimisationParams();
        List<ModulesFile> modulesArray = new ArrayList<>();
        List<List<Variable>> variablesArray = new ArrayList<>();

        //loop to translate variable id's into the actual variable object
        for (int i = 0; i < variables.length; i++){
             List<Variable> temp = new ArrayList<>();
             for (int j=0; j<variables[i].length; j++){
                Variable var = variableService.getOptimisationVarById(new ObjectId(variables[i][j]));
                temp.add(var);
             }
             variablesArray.add(temp);
        }

        //loop to translate module id's into actual module files stored in database
        List<ObjectId> modIds = op.getModules();
        for (int i = 0; i<modIds.size();i++){
            modulesArray.add((ModulesFile) fileService.getFileById(modIds.get(i)));
        }

        //runs hill climb algorithm and then redirects to results page
        HillClimb h = new HillClimb(op.getIterations(),op.getMaximising(),variablesArray, modulesArray, op.getIp());
        updateResultsTable(h.getResults(), new ObjectId(partId));
        return "redirect:/projects/{projectName}/{partId}";
    }

    public void updateResultsTable(List<Result> results, ObjectId partId){
        //adds results to the database
        for (Result r: results){
            r.setPartId(partId);
            resultService.creatResult(r);
        }
    }
}
