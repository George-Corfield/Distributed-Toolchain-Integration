/*
 * This is the controller for projects. It handles creating and modifying projects.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;


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

    //Endpoint for visiting the projects page
    @GetMapping("/projects")
    public String loadProjects(@CookieValue("userId") String id, Model model, HttpServletRequest request){
        List<Project> projects = projectService.getProjectsByUser(new ObjectId(id));
        model.addAttribute("userId", id);
        model.addAttribute("projects", projects);
        model.addAttribute("newProject", new Project());
        return "projects";
    }

    //Endpoint for viewing a specific project
    @GetMapping("/projects/{projectName}")
    public String getParts(@PathVariable String projectName, Model model){
        Project project = projectService.getProjectByName(projectName);
        List<Part> parts = partService.getPartsByProjectId(project.getProjectId());
        model.addAttribute("project", project);
        model.addAttribute("parts", parts);
        model.addAttribute("newPart", new Part());
        return "parts";
    }

    //Endpoint for adding a part to a project.
    @PostMapping("/projects/{projectName}")
    public String addPart(@PathVariable String projectName, @ModelAttribute("newPart") Part part, Model model){
        Project project = projectService.getProjectByName(projectName);
        part.setProjectId(project.getProjectId());
        partService.createPart(part);
        return "redirect:/projects/" + projectName;
    }

    //Endpoint for creating a new project.
    @PostMapping("/projects")
    public String addProject(@ModelAttribute("newProject") Project project, @CookieValue("userId") String id, Model model){
        project.setUser(new ObjectId(id));
        projectService.createProject(project);
        return "redirect:/projects";
    }

    // not functioning correctly, error #500
    // Endpoint for deleting a project
    //@DeleteMapping("/projects/{projectId}")
    //public String deleteProject(@PathVariable String projectId) {
        //projectService.deleteProject(projectId);
        //return "redirect:/projects";
    //}

}