package UoBToolchainGroup.DistributedToolchainIntegration.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

@Controller
public class WebController {

    @GetMapping(value = "/")
    public String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                        Model model){
        model.addAttribute("greeting", "Hello " + name);
        return "index";
    }

    @GetMapping(value = "/foo")
    public String pythonFoo(@RequestParam(value = "value", required = false, defaultValue = "1") String value,
                        Model model) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:5000/foo")).GET().build();
        HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
        model.addAttribute("greeting", res.body());
        return "index";
    }

    @GetMapping(value = "/add")
    public String pythonAdd2(@RequestParam(value = "value", required = false, defaultValue = "1") String value,
                        Model model) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        String jsonData = "{\"array\": [1,2,3,2401,3.2]}";
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:5000/add"))
                                    .header("Content-Type", "application/json")
                                    .POST(BodyPublishers.ofString(jsonData)) 
                                    .build();
        HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
        model.addAttribute("greeting", res.body());
        return "index";
    }

    @GetMapping(value = "/upload")
    public String upload(Model model){
        return "upload";
    } 

}

