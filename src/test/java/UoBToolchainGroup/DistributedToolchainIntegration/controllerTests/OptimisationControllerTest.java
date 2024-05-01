package UoBToolchainGroup.DistributedToolchainIntegration.controllerTests;

import java.util.List;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import UoBToolchainGroup.DistributedToolchainIntegration.controller.OptimisationController;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ResultService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.VariableService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OptimisationController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class OptimisationControllerTest {
    

    @Autowired
    private MockMvc mvc;

    @MockBean
    PartService partService;
    @MockBean
    ResultService resultService;
    @MockBean
    VariableService variableService;
    @MockBean
    FileService fileService;

    private Part test_part;
    private OptimisationParams test_opParam;
    private List<Variable> variables;
    private List<ModulesFile> modFiles;

}
