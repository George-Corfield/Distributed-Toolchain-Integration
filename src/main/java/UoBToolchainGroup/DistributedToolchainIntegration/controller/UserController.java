/*
 * This is the controller for users. It handles creating, modifying users and logging in.
 */
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

    //Endpoint for logging in with credentials.
    @PostMapping("/login")
    public String checkLoginDetails(@ModelAttribute("userDetails") User userDetails, 
    Model mode,
    HttpServletResponse response) throws NoSuchAlgorithmException{

        //Search for the user in the database
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

    //Gets the cookie for the user.
    public Optional<String> getCookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie c: cookies){
            if (c.getName().equals("userId")){
                return Optional.ofNullable(c.getValue());
            }
        }
        return Optional.empty();

    }

    //Endpoint for a failed login. (Incorrect username/password/any other error)
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

    //Endpoint for registering a new user, takes the form and creates the user if they don't exist already.
    @PostMapping("/register")
    public String register(@ModelAttribute("userDetails") User newUser, Model model) throws NoSuchAlgorithmException{
        //Search for the user in the database
        User checkUser = userService.getUserByUsername(newUser.getUsername());
        if (checkUser == null){
            //If the user doesn't exist then create them
            newUser.setUserId(new ObjectId());
            newUser.setRole(10);
            //generate a salt for the user and store it in the database.
            byte[] salt = generateSalt();
            newUser.setSalt(salt);
            //set the password with the hash.
            newUser.setPassword(generatePasswordHash(newUser.getPassword(), salt));
            userService.createUser(newUser);
            return "redirect:/login";
        } else {
            return "redirect:/register?fail=true";
            //user already exists
        }
        // return "redirect:/login";
    }

    //Endpoint for registering a new user, shows the page.
    @GetMapping("/register")
    public String register(@RequestParam(value="fail", required = false) Boolean fail, Model model){
        if (fail != null){
            if (fail){
                //add error logic 
            }
        }
        model.addAttribute("userDetails", new User());
        return "register";
    }

    //Generates a password hash using the plaintext and salt.
    private byte[] generatePasswordHash(byte[] plaintext, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(plaintext);
        return hashedPassword;
    }

    //Function that generates salts for passwords.
    private byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    
}
