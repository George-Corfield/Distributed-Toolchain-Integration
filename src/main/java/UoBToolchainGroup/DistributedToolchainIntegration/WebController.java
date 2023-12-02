package UoBToolchainGroup.DistributedToolchainIntegration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        String res = PythonCaller.call("src\\main\\python\\test.py", args);
        model.addAttribute("greeting", res);
        return "index";
    }

        @GetMapping(value = "/pythonAdd")
    public String pythonAdd(@RequestParam(value = "val", required = false, defaultValue = "1") String value,
                        Model model){
        String[] args = {"add", "1", "2", "3", value};
        String res = PythonCaller.call("src\\main\\python\\test.py", args);
        model.addAttribute("greeting", res);
        return "index";
    }
}
