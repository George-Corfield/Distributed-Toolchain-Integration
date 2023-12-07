package UoBToolchainGroup.DistributedToolchainIntegration.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import UoBToolchainGroup.DistributedToolchainIntegration.PythonCaller;

@Controller
public class WebController {

    @GetMapping(value = "/")
    public String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                        Model model){
        model.addAttribute("greeting", "Hello " + name);
        return "index";
    }

    @GetMapping(value = "/pythonFoo")
    public String pythonFoo(Model model){
        String[] args = {"foo"};
        String res = PythonCaller.call("src\\main\\python\\testModule.py", args);
        model.addAttribute("greeting", res);
        return "index";
    }

        @GetMapping(value = "/pythonAdd")
    public String pythonAdd(@RequestParam(value = "value", required = false, defaultValue = "1") String value,
                        Model model){
        String[] args = {"add", "1", "2", "3", value};
        String res = PythonCaller.call("src\\main\\python\\testModule.py", args);
        model.addAttribute("greeting", res);
        return "index";
                        }
    
    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }
}

