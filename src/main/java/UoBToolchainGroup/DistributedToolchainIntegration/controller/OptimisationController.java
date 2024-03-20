package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.valves.JsonAccessLogValve;
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

import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.service.OptimisationParamsService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;


@Controller
public class OptimisationController {
    @Autowired
    private PartService partService;
    @Autowired
    private OptimisationParamsService optimisationParamsService;

    @GetMapping("/projects/{projectName}/{partId}/optimise")
    public String getOptimisation(@PathVariable String projectName, @PathVariable String partId, Model model){
        Part part = partService.getPartbyId(new ObjectId(partId));
        OptimisationParams op = part.getOptimisationParams();
        OptimisationParams ex = new OptimisationParams();
        model.addAttribute("part", part);
        model.addAttribute("projectName", projectName);
        model.addAttribute("opParams", op);
        model.addAttribute("newOp", new OptimisationParams());
        return "optimise";
    }

    @PostMapping("/projects/{projectName}/{partId}/optimise")
    public String setIterationLimit(@PathVariable String projectName, @PathVariable String partId, Model model, @ModelAttribute("newOp") OptimisationParams newOp, @RequestParam("selectedModules") String selectedModules){
        // System.out.println(newOp.getIterations());
        // System.out.println(selectedModules);
        List<JSONObject> json = new ArrayList<>();
        JSONArray jsonArray = new JSONArray("["+selectedModules+"]");
        for (int i = 0; i < jsonArray.length(); i++){
            json.add((JSONObject)jsonArray.get(i));
        }

        List<ModulesFile> mods = new ArrayList<>();
        for (JSONObject mod: json){
            mods.add(ModulesFile.jsonToString(mod));
        }
        System.out.println(mods.get(0));
        System.out.println(mods.get(0).getFileId().getClass());
        return "redirect:/projects/{projectName}/{partId}/optimise";
    }
}
