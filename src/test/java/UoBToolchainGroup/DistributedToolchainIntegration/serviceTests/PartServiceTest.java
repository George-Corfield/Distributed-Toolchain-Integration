package UoBToolchainGroup.DistributedToolchainIntegration.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    //Test class to test PartService methods

    @Autowired
    private PartRepository partRepository;
    private PartService partService;


    @BeforeAll
    public void setup(){
        //Sets part service prior to testing
        partService = new PartService(partRepository);
    }

    @ParameterizedTest
    @CsvSource({"6728696e117fd47726a8115a,test-part-1","6728696e117fd47726a8115b,test-part-2","6728696e117fd47726a8115c,test-part-3"}) 
    public void testGetPartById(ObjectId id, String expectedName){
        //Test to ensure getPartById() returns correct part
        Part part = partService.getPartbyId(id);
        assertEquals(expectedName, part.getPartName());
    }

    @Test
    public void testGetAllParts(){
        //Test to ensure getAllParts() returns every part in the database
        List<Part> parts = partService.getAllParts();
        assertTrue(parts.size()==3);
    }

    @Test
    public void testGetPartsByProjectId(){
        //Test to ensure getPartsByProjectId() returns all parts of that project
        List<Part> parts = partService.getPartsByProjectId(new ObjectId("6628696e117fd47726a8115b"));
        assertTrue(parts.size()==2);
    }

    

}
