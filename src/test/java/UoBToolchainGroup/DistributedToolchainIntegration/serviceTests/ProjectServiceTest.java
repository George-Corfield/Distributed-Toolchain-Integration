package UoBToolchainGroup.DistributedToolchainIntegration.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Test
    @Order(1)
    public void testGetProjectByName(){
        Project expectedProject = projectService.getProjectByName("test-project-1");
        System.out.println(expectedProject);
        assertEquals(new ObjectId("6628696e117fd47726a8115b"), expectedProject.getProjectId());
    }
}

