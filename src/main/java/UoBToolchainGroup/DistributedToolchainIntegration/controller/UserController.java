package UoBToolchainGroup.DistributedToolchainIntegration.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.servlet.ModelAndView;
import java.util.List;

import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;


@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/users")
    public String createNewUser(@ModelAttribute("newUser") User newUser, Model model){
        System.out.println(newUser);
        userService.createUser(newUser);
        return "users";
    }

    @GetMapping("/users")
    public String getUsersList(Model model){
        List<User> users  = userService.getAllUsers();
        for (int i = 0; i < users.size() ; i++){
            User user = users.get(i);
            System.out.println(user.getId());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("usersList", users );
        return "users";
    }


    
}
