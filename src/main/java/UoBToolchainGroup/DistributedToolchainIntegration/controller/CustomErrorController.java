package UoBToolchainGroup.DistributedToolchainIntegration.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            String message = "";
            if(statusCode == 404){
                message = "Resource Not Found.";
            } else if(statusCode == 500){
                message = "Internal Server Error.";
            }
            model.addAttribute("err","Error: " + statusCode);
            model.addAttribute("message", message);
        }
        return "error";
    }
}
