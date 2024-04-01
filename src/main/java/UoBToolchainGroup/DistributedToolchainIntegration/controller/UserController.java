package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;
import jakarta.servlet.http.HttpServletResponse;


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
            //user not found
        } else {
            if (foundUser.getPassword().equals(userDetails.getPassword())){
                //user found + correct pwd
                Cookie cookie = new Cookie("userId", foundUser.getUserId().toString());
                cookie.setMaxAge(7*24*60*60);
                response.addCookie(cookie);
                return "redirect:/projects";
            } else {
                //user found + incorret pwd
            }
        }
        return "redirect:/login?fail=true";
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
    public String login(@RequestParam(value ="fail", required = false) Boolean fail, Model model){
        if(fail != null){
            if(fail){
                //add error text here
            }
        }
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
            return "redirect:/login";
        } else {
            System.out.println("User already exists");
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userDetails", new User());
        return "register";
    }


    
}
