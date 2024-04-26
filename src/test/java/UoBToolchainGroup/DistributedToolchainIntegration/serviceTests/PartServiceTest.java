package UoBToolchainGroup.DistributedToolchainIntegration.serviceTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import UoBToolchainGroup.DistributedToolchainIntegration.ContainerTest;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.PartRepository;
import UoBToolchainGroup.DistributedToolchainIntegration.service.PartService;

@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class PartServiceTest extends ContainerTest{
    

    @Autowired
    private PartRepository partRepository;
    private PartService partService;


    @BeforeAll
    public void setup(){
        partService = new PartService(partRepository);
    }

    @Test 
    public void testGetPartById(){
        Part part = partService.getPartbyId(new ObjectId("6728696e117fd47726a8115a"));
        assertTrue(true);
    }
}
