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
}
