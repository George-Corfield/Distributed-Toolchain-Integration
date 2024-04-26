package UoBToolchainGroup.DistributedToolchainIntegration.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import UoBToolchainGroup.DistributedToolchainIntegration.ContainerTest;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.ProjectRepository;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ProjectService;


@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ProjectServiceTest extends ContainerTest{
    
    @Autowired
    private ProjectRepository projectRepository;
    private ProjectService projectService;

    @BeforeAll
    public void setup(){
        System.out.println("----Running Setup----");
        projectService = new ProjectService(projectRepository);
    }

    @AfterAll
    public void tearDown(){
        System.out.println("----Finished Tests----");
    }

    @ParameterizedTest
    @Order(1)
    @CsvSource({"test-project-1,6628696e117fd47726a8115b","test-project-2,6628696e117fd47726a8115d","test-project-3,6628696e117fd47726a8115c"})
    public void testGetProjectByName(String projectName, String expectedId){
        Project expectedProject = projectService.getProjectByName(projectName);
        System.out.println(expectedProject);
        assertEquals(expectedId, expectedProject.getProjectId().toString());
    }

    @Test
    public void testGetProjectByUser(){
        List<Project> projects = projectService.getProjectsByUser(new ObjectId("6628696e117fd47726a8116e"));
        assertTrue(projects.size()==2);
        //implement way to check that it contains the same objects as expected
    }

    @ParameterizedTest
    @CsvSource({"test-project-1,6628696e117fd47726a8115b","test-project-2,6628696e117fd47726a8115d","test-project-3,6628696e117fd47726a8115c"})
    public void testGetProjectById(String expectedName, String id){
        Project project = projectService.getPartbyId(id);
        assertEquals(expectedName, project.getProjectName());
    }
}

