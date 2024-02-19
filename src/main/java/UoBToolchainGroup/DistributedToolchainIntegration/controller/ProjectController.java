package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
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

    @GetMapping("/projects")
    public String loadProjects(@CookieValue("userId") String id, Model model, HttpServletRequest request){
        User user = userService.getUserById(new ObjectId(id));
        Project project = projectService.getProjectByUser(user);
        System.out.println(id);
        System.out.println(user.getUsername());
        System.out.println(project);
        return "users";
    }
}
