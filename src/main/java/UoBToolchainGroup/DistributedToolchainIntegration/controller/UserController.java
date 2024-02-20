package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.util.Optional;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;


@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String checkLoginDetails(@ModelAttribute("userDetails") User userDetails, 
    Model mode,
    HttpServletResponse response){
        User foundUser = userService.getUserByUsername(userDetails.getUsername());
        if (foundUser == null){
        } else {
            if (foundUser.getPassword().equals(userDetails.getPassword())){
                Cookie cookie = new Cookie("userId", foundUser.getUserId().toString());
                cookie.setMaxAge(7*24*60*60);
                response.addCookie(cookie);
                return "projects";
            } else {
                System.out.println("Incorrect Password");
            }
        }
        return "index";
    }

    public Optional<String> getCookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie c: cookies){
            if (c.getName().equals("userId")){
                return Optional.ofNullable(c.getValue());
            }
        }
        return Optional.empty();

    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userDetails", new User());
        return "login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDetails") User newUser, Model model){
        User checkUser = userService.getUserByUsername(newUser.getUsername());
        if (checkUser == null){
            newUser.setUserId(new ObjectId());
            newUser.setRole(10);
            userService.createUser(newUser);
            System.out.println("User Successfully Created");
            return "index";
        } else {
            System.out.println("User already exists");
        }
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userDetails", new User());
        return "register";
    }


    
}
