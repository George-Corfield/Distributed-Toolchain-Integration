package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import java.util.Arrays;
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

import java.security.*;


@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String checkLoginDetails(@ModelAttribute("userDetails") User userDetails, 
    Model mode,
    HttpServletResponse response) throws NoSuchAlgorithmException{
    
        User foundUser = userService.getUserByUsername(userDetails.getUsername());
        if (foundUser == null){
            //user not found
        } else {
            if(Arrays.equals(foundUser.getPassword(), generatePasswordHash(userDetails.getPassword(), foundUser.getSalt()))){
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
    public String register(@ModelAttribute("userDetails") User newUser, Model model) throws NoSuchAlgorithmException{
        User checkUser = userService.getUserByUsername(newUser.getUsername());
        if (checkUser == null){
            newUser.setUserId(new ObjectId());
            newUser.setRole(10);
            byte[] salt = generateSalt();
            newUser.setSalt(salt);
            newUser.setPassword(generatePasswordHash(newUser.getPassword(), salt));
            userService.createUser(newUser);
            return "redirect:/login";
        } else {
            //user exists
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userDetails", new User());
        return "register";
    }

    private byte[] generatePasswordHash(byte[] plaintext, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(plaintext);
        return hashedPassword;
    }

    //create a generate salt function
    private byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    
}
