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


import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;


import org.bson.types.ObjectId;

import UoBToolchainGroup.DistributedToolchainIntegration.controller.ProjectController;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ProjectService;
import jakarta.servlet.http.Cookie;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProjectController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ProjectControllerTest {
    

    @Autowired
    private MockMvc mvc;

    @MockBean
    ProjectService projectService;
    @MockBean
    PartService partService;

    private ObjectId userId;
    private Project test_project;
    private List<Project> test_projects;
    private List<Part> test_parts;

    @BeforeEach
    public void setup(){
        userId = new ObjectId();
        test_project = new Project(new ObjectId(), "test_project", "test_project", new Date(), userId);
        test_projects = List.of(new Project(), new Project());
        test_parts = List.of(new Part(), new Part());
        when(projectService.getProjectsByUser(userId)).thenReturn(test_projects);
        when(projectService.getProjectByName("test_project")).thenReturn(test_project);
        when(partService.getPartsByProjectId(test_project.getProjectId())).thenReturn(test_parts);

    }

    @Test
    public void testProjectGetRequest() throws Exception{
        mvc.perform(get("/projects")
        .cookie(new Cookie("userId", userId.toString())))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("userId","projects","newProject"))
        .andExpect(model().attribute("userId", userId.toString()))
        .andExpect(model().attribute("projects", test_projects));
    }

    @Test
    public void testProjectPostRequest() throws Exception{
        mvc.perform(post("/projects")
        .cookie(new Cookie("userId", userId.toString()))
        .param("projectName", "test_project")
        .param("projectDescription", "test_project")).andExpect(redirectedUrl("/projects"));
    }

    @Test
    public void testPartsGetRequest() throws Exception{
        mvc.perform(get("/projects/{projectName}",test_project.getProjectName()))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("project","parts","newPart"))
        .andExpect(model().attribute("project", test_project))
        .andExpect(model().attribute("parts", test_parts));
    }

    @Test
    public void testPartsPostRequest() throws Exception{
        mvc.perform(post("/projects/{projectName}",test_project.getProjectName())
        .param("partName", "test_part")
        .param("partDescription", "test_part"))
        .andExpect(redirectedUrl("/projects/"+test_project.getProjectName()));
        
    }
}
