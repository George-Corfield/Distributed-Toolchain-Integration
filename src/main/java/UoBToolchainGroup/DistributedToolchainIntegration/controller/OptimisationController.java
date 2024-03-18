package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import UoBToolchainGroup.DistributedToolchainIntegration.service.OptimisationParamsService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import ch.qos.logback.core.model.Model;

@Controller
public class OptimisationController {
    @Autowired
    private PartService partService;
    @Autowired
    private OptimisationParamsService optimisationParamsService;

    @GetMapping("/projects/{projectName}/{partId}/optimise")
    public String getOptimisation(@PathVariable String projectName, @PathVariable String partId, Model model){
        return "optimise";
    }
}
