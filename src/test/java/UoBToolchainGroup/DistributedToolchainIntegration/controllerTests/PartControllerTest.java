package UoBToolchainGroup.DistributedToolchainIntegration.controllerTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bson.types.ObjectId;

import UoBToolchainGroup.DistributedToolchainIntegration.controller.PartController;
import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ResultService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.VariableService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PartController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class PartControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    PartService partService;
    @MockBean
    VariableService variableService;
    @MockBean
    ResultService resultService;

    private Part test_part;
    private List<Variable> variables;
    private List<Result> results;

    @BeforeEach
    public void setup(){
        test_part = new Part(new ObjectId(), "test_part", "test_part", new OptimisationParams(), new ObjectId());
        variables = List.of(new Variable(), new Variable());
        results = List.of(new Result(), new Result());
        when(partService.getPartbyId(test_part.getPartId())).thenReturn(test_part);
        when(variableService.getVariablesByPart(test_part.getPartId())).thenReturn(variables);
        when(resultService.getResultsByPart(test_part.getPartId())).thenReturn(results);
    }

    @Test
    public void testPartGetRequest() throws Exception{
        mvc.perform(get("/projects/{projectName}/{partId}","test_project",test_part.getPartId().toString()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("part","projectName","variable","currentVariables","results"))
        .andExpect(model().attribute("part", test_part))
        .andExpect(model().attribute("projectName", "test_project"))
        .andExpect(model().attribute("currentVariables", variables))
        .andExpect(model().attribute("results", results));
    }

    @Test
    public void testPartPostRequest() throws Exception{
        mvc.perform(post("/projects/{projectName}/{partId}","test_project",test_part.getPartId().toString()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/projects/test_project/"+test_part.getPartId().toString()));

    }


    
}
