package UoBToolchainGroup.DistributedToolchainIntegration.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

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
    public String pythonAdd(@RequestParam(value = "value", required = false, defaultValue = "1") String value,
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

    @GetMapping(value = "/add2")
    public String pythonAdd2(@RequestParam(value = "value", required = false, defaultValue = "1") String value,
                        Model model) throws IOException, InterruptedException{
        //create new http client
        HttpClient client = HttpClient.newHttpClient();

        //fake json data
        File jsonFile = new File("src\\main\\java\\UoBToolchainGroup\\DistributedToolchainIntegration\\controller\\json_file.json");
        
        //fake Python File
        File pyFile = new File("src\\main\\java\\UoBToolchainGroup\\DistributedToolchainIntegration\\controller\\python_file.py");
        
        //The JSON MUST go first since the pythonapi expects to recieve the JSON first.
        //In future you could modify the python_api to accept the files in any order.
        List<File> files = List.of(jsonFile, pyFile);

        //build the request
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:5000/optimise"))
                                    .header("Content-Type", "multipart/form-data; boundary=boundary")
                                    .POST(buildMultiPartRequestBody(files))
                                    .build();
        
        HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
        model.addAttribute("greeting", res.body());
        return "index";
    }

    @GetMapping("/upload")
    public String upload(Model model){
        return "upload";
    } 

    private static HttpRequest.BodyPublisher buildMultiPartRequestBody(List<File> files) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Start the multipart/form-data content
        String boundary = "boundary";
        String lineSeparator = "\r\n";

        // Add each file to the request body
        for (File file : files) {
            outputStream.write(("--" + boundary + lineSeparator).getBytes());
            outputStream.write(("Content-Disposition: form-data; name=\"file\";; filename=\"" + file.getName() + "\"" + lineSeparator).getBytes());
            outputStream.write(("Content-Type: application/octet-stream" + lineSeparator + lineSeparator).getBytes());

            FileInputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.write(lineSeparator.getBytes());
            inputStream.close();
        }

        // Add the closing boundary
        outputStream.write(("--" + boundary + "--" + lineSeparator).getBytes());

        return HttpRequest.BodyPublishers.ofByteArray(outputStream.toByteArray());
    }
}

