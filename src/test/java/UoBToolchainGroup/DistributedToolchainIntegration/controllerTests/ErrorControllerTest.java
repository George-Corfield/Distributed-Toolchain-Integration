package UoBToolchainGroup.DistributedToolchainIntegration.controllerTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import UoBToolchainGroup.DistributedToolchainIntegration.controller.CustomErrorController;
import jakarta.servlet.RequestDispatcher;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomErrorController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ErrorControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void testErrorResourceNotFound() throws Exception{
        //test to ensure that when error 404 occurs, user is displayed correct error message
        mvc.perform(get("/error").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 404))
        .andExpect(model().attributeExists("err","message"))
        .andExpect(model().attribute("err", "Error: 404"))
        .andExpect(model().attribute("message", "Resource Not Found."))
        .andExpect(status().isOk());
    }

    @Test
    public void testErrorInternalServerError() throws Exception{
        //test to ensure that when error 500 occurs, user is displayed correct error message
        mvc.perform(get("/error").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 500))
        .andExpect(model().attributeExists("err","message"))
        .andExpect(model().attribute("err", "Error: 500"))
        .andExpect(model().attribute("message", "Internal Server Error."))
        .andExpect(status().isOk());
    }

    @Test
    public void testNoError() throws Exception{
        //test to ensure that when no error occurs user is safely moved on
        mvc.perform(get("/error"))
        .andExpect(status().isOk());
    }
}
