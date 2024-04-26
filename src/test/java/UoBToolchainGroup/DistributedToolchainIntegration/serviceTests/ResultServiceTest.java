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
import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.ResultRepository;
import UoBToolchainGroup.DistributedToolchainIntegration.service.ResultService;

@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ResultServiceTest extends ContainerTest{

    @Autowired
    private ResultRepository resultRepository;
    private ResultService resultService;

    @BeforeAll
    public void setup(){
        resultService = new ResultService(resultRepository);
    }

    @ParameterizedTest
    @CsvSource({"6328696e117fd47726a8115a,500","6328696e117fd47726a8115b,350","6328696e117fd47726a8115c,200"})
    public void testGetResultById(ObjectId id, double expectedOutputValue){
        Result result = resultService.getResultById(id);
        assertEquals(expectedOutputValue, result.getOutputValue());
    }

    @Test
    public void testGetResultsByPart_MultipleResults(){
        List<Result> results = resultService.getResultsByPart(new ObjectId("6728696e117fd47726a8115a"));
        assertTrue(results.size() ==2);
    }

    @Test
    public void testGetResultsByPart_SingleResult(){
        List<Result> results = resultService.getResultsByPart(new ObjectId("6728696e117fd47726a8115b"));
        assertTrue(results.size() == 1);
    }


    
}
