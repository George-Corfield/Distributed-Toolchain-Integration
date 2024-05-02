package UoBToolchainGroup.DistributedToolchainIntegration.controllerTests;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import UoBToolchainGroup.DistributedToolchainIntegration.controller.OptimisationController;
import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ResultService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.VariableService;
import jakarta.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



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
    private List<File> files;
    private ObjectId UserId;


    @BeforeAll
    public void setupMockServer(){

    }

    @BeforeEach
    public void setup(){
        //sets up all required parameters before tests begin
        UserId = new ObjectId();
        files = List.of((File) new ModulesFile("test-file", "application/python", new byte[0], UserId, false));
        test_opParam = new OptimisationParams(new ObjectId(), 10, List.of(files.get(0).getFileId()), true, "http://localhost:5000/optimise");
        test_part = new Part(new ObjectId(), "test_part", "test_part", test_opParam, new ObjectId());
        variables = List.of(new Variable(), new Variable());
        modFiles = List.of(new ModulesFile("test-mod", "application/python", new byte[0], UserId, true));
        //ensures services return correct data on specific calls
        when(partService.getPartbyId(test_part.getPartId())).thenReturn(test_part);
        when(fileService.getFileById(any())).thenReturn(files.get(0));
        when(fileService.getAvailableModulesFiles(UserId)).thenReturn(modFiles);
        when(variableService.getVariablesByPart(test_part.getPartId())).thenReturn(variables);
    }

    @Test
    public void testOptimisationsGetRequest() throws Exception{
        //test to ensure that optimisation parameters are correctly displayed to the user 
        mvc.perform(get("/projects/{projectName}/{partId}/optimise", "test_project", test_part.getPartId().toString())
        .cookie(new Cookie("userId", UserId.toString())))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("part","projectName","currentModules","opParams","allModules","variables"))
        .andExpect(model().attribute("part", test_part))
        .andExpect(model().attribute("projectName", "test_project"))
        .andExpect(model().attribute("currentModules", files))
        .andExpect(model().attribute("opParams", test_opParam))
        .andExpect(model().attribute("allModules", modFiles))
        .andExpect(model().attribute("variables", variables));
    }

    @Test
    public void testOptimisationsPostRequest() throws Exception{
        //test to ensure that user is safely redirected and that all parameters are parsed
        mvc.perform(post("/projects/{projectName}/{partId}/optimise", "test_project", test_part.getPartId().toString())
        .param("iterations", "10")
        .param("modules", "")
        .param("maximising", "false")
        .param("selectedModules", "6428696e117fd47726a8115a","6428696e117fd47726a8115b"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/projects/test_project/"+test_part.getPartId().toString()+"/optimise"));
    }

}
