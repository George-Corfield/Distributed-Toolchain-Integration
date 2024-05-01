package UoBToolchainGroup.DistributedToolchainIntegration.controllerTests;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import UoBToolchainGroup.DistributedToolchainIntegration.controller.CustomErrorController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomErrorController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ErrorControllerTest {
    
    @Autowired
    private MockMvc mvc;

    
}
