package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.util.ArrayList;
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

import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;


@Controller
public class OptimisationController {
    @Autowired
    private PartService partService;
    @Autowired
    private FileService fileService;

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
        part.setOptimisationParams(op);

        //updates the part
        partService.updatePart(part);
        return "redirect:/projects/{projectName}/{partId}/optimise";
    }
}
