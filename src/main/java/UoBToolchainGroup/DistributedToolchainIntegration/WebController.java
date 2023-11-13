package UoBToolchainGroup.DistributedToolchainIntegration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping(value = "/hello")
    @ResponseBody
    public String index(){
        return "Hello World!";
    }
}
