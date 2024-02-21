package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.util.List;
import java.util.Date;
import java.util.Collections;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ProjectService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private PartService partService;

    @GetMapping("/projects")
    public String loadProjects(@CookieValue("userId") String id, Model model, HttpServletRequest request){
        User user = userService.getUserById(new ObjectId(id));
        List<Project> projects = projectService.getProjectByUser(user);
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/projects/{projectName}")
    public String getProject(@PathVariable String projectName, Model model){
        Project project = projectService.getProjectByName(projectName);
        model.addAttribute("project", project);
        return "test";
    }
}
