package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.util.Date;
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

import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ProjectService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @GetMapping("/projects")
    public String loadProjects(@CookieValue("userId") String id, Model model, HttpServletRequest request){
        User user = userService.getUserById(new ObjectId(id));
        List<Project> projects = projectService.getProjectByUser(user);
        Project blank = new Project();
        System.out.println(blank);
        model.addAttribute("projects", projects);
        model.addAttribute("newProject", blank);
        return "projects";
    }

    @GetMapping("/projects/{projectName}")
    public String getParts(@PathVariable String projectName, Model model){
        Project project = projectService.getProjectByName(projectName);
        model.addAttribute("project", project);
        return "parts";
    }

    @PostMapping("/projects/{projectName}")
    public String addPart(@PathVariable String projectName, Model model){
        return "redirect:/projects/" + projectName;
    }

    @PostMapping("/projects")
    public String addProject(@ModelAttribute("newProject") Project project, @CookieValue("userId") String id, Model model){
        User user = userService.getUserById(new ObjectId(id));
        project.setUser(user);
        project.setProjectId(new ObjectId());
        project.setProjectStartDate(new Date());
        projectService.createProject(project);
        return "redirect:/projects";
    }
}
