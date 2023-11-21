package UoBToolchainGroup.DistributedToolchainIntegration.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsersList(){
        return userService.getAllUsers();
    }

    // @RequestMapping(method = RequestMethod.GET)
    // public String getUsersList(Model model){
    //     model.addAttribute("usersList", userService.getAllUsers());
    //     return "users";
    // }

    // @RequestMapping(value ="/", method=RequestMethod.GET)
    // public ModelAndView users(){
    //     ModelAndView modelAndView = new ModelAndView();
    //     modelAndView.setViewName("users");
    //     return modelAndView;
    // }
    
}
