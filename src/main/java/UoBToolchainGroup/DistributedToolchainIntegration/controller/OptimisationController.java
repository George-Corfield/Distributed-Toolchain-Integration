package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getOptimisation(@PathVariable String projectName, @PathVariable String partId, Model model){
        Part part = partService.getPartbyId(new ObjectId(partId));
        OptimisationParams op = part.getOptimisationParams();
        List<File> currentFiles = new ArrayList<>();
        List<ObjectId> currentFileId = op.getModules();

        //gets the data for the files based on id's stored in optimisation paramters
        for (int i = 0; i < currentFileId.size(); i++){
            currentFiles.add(fileService.getFileById(currentFileId.get(i)));
        }
        List<ModulesFile> mods = fileService.getAllModulesFiles();
        model.addAttribute("part", part);
        model.addAttribute("projectName", projectName);
        model.addAttribute("currentModules", currentFiles);
        model.addAttribute("opParams", op);
        model.addAttribute("allModules", mods);
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
        op.setMaximising(newOp.getMaximising());
        part.setOptimisationParams(op);

        //updates the part
        partService.updatePart(part);
        return "redirect:/projects/{projectName}/{partId}/optimise";
    }

    @GetMapping("/optimise/projects/{projectName}/{partId}")
    public String timeToOptimise(@PathVariable String projectName, @PathVariable String partId, Model model){
        Part p = partService.getPartbyId(new ObjectId(partId));
        OptimisationParams op = p.getOptimisationParams();
        List<ObjectId> modules = op.getModules();
        List<Variable> vars = variableService.getVariablesByPart(new ObjectId(partId));
        List<List<Variable>> variablesArray = new ArrayList<>();
        JSONArray modulesArray = new JSONArray();
        Random rand = new Random();
        for (int i = 0; i < modules.size(); i++) {
            modulesArray.put((ModulesFile) fileService.getFileById(modules.get(i)));

            //sets random variables currently for each module
            List<Variable> json = new ArrayList<>();
            Variable var1 = vars.get(rand.nextInt(vars.size()));
            json.add(var1);
            Variable var2 = vars.get(rand.nextInt(vars.size()));
            json.add(var2);
            if(i != 0){
                json.add(new Variable("PR"));
            }
            variablesArray.add(json);
            // System.out.println(modVariablesArray);
        }
        System.out.println(variablesArray);
        // System.out.println(modulesArray);
        // System.out.println(variablesArray);
        HillClimb h = new HillClimb(op.getIterations(),op.getMaximising(),variablesArray, modulesArray);
        updateResultsTable(h.getResults(), new ObjectId(partId));
        return "redirect:/projects/{projectName}/{partId}";
    }

    public void updateResultsTable(List<Result> results, ObjectId partId){
        for (Result r: results){
            r.setPartId(partId);
            resultService.creatResult(r);
        }
    }
}
