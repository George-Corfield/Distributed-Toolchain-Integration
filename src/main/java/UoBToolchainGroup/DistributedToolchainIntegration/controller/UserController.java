package UoBToolchainGroup.DistributedToolchainIntegration.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String checkLoginDetails(@ModelAttribute("userDetails") User userDetails, Model model){
        User foundUser = userService.getUserByUsername(userDetails.getUsername());
        if (foundUser == null){
            System.out.println("Incorrect username");
        } else {
            if (foundUser.getPassword().equals(userDetails.getPassword())){
                System.out.println("Correct Details");
                return "index";
            } else {
                System.out.println("Incorrect Password");
            }
        }
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userDetails", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userDetails", new User());
        return "register";
    }


    
}
