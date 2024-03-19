package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("part", part);
        model.addAttribute("projectName", projectName);
        model.addAttribute("opParams", op);
        model.addAttribute("newOp", new OptimisationParams());
        return "optimise";
    }

    @PostMapping("/projects/{projectName}/{partId}/optimise")
    public String setIterationLimit(@PathVariable String projectName, @PathVariable String partId, Model model, @ModelAttribute("newOp") OptimisationParams newOp){
        // Part part = partService.getPartbyId(new ObjectId(partId));
        // OptimisationParams op = part.getOptimisationParams();
        // op.setIterations(newOp.getIterations());
        // op.setModules(newOp.getModules());
        // part.setOptimisationParams(op);
        // partService.updatePart(part);
        return "redirect:/projects/{projectName}/{partId}/optimise";
    }
}
