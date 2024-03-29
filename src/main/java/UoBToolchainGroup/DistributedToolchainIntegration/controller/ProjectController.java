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
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PartService partService;

    @GetMapping("/projects")
    public String loadProjects(@CookieValue("userId") String id, Model model, HttpServletRequest request){
        List<Project> projects = projectService.getProjectByUser(new ObjectId(id));
        model.addAttribute("projects", projects);
        model.addAttribute("newProject", new Project());
        return "projects";
    }

    @GetMapping("/projects/{projectName}")
    public String getParts(@PathVariable String projectName, Model model){
        Project project = projectService.getProjectByName(projectName);
        model.addAttribute("project", project);
        model.addAttribute("newPart", new Part());
        return "parts";
    }

    @PostMapping("/projects/{projectName}")
    public String addPart(@PathVariable String projectName, @ModelAttribute("newPart") Part part, Model model){
        Project project = projectService.getProjectByName(projectName);
        part.setPartId(new ObjectId());
        part.setProjectId(project.getProjectId());
        partService.createPart(part);
        return "redirect:/projects/" + projectName;
    }

    @PostMapping("/projects")
    public String addProject(@ModelAttribute("newProject") Project project, @CookieValue("userId") String id, Model model){
        project.setUser(new ObjectId(id));
        project.setProjectId(new ObjectId());
        project.setProjectStartDate(new Date());
        projectService.createProject(project);
        return "redirect:/projects";
    }
}
