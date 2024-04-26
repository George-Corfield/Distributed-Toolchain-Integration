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
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.VariableRepository;
import UoBToolchainGroup.DistributedToolchainIntegration.service.VariableService;

@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class VariableServiceTest extends ContainerTest{
    
    @Autowired
    private VariableRepository variableRepository;
    private VariableService variableService;

    @BeforeAll
    public void setup(){
        variableService = new VariableService(variableRepository);
    }

    @ParameterizedTest
    @CsvSource({"6928696e117fd47726a8115a,test-variable-1","6928696e117fd47726a8115b,test-variable-2","6928696e117fd47726a8115c,test-variable-3"})
    public void testGetOptimisationVarById(ObjectId id, String expectedName){
        Variable var = variableService.getOptimisationVarById(id);
        assertEquals(expectedName, var.getVariableName());
    }

    @Test
    public void testGetVariablesByPart(){
        List<Variable> vars = variableService.getVariablesByPart(new ObjectId("6728696e117fd47726a8115a"));
        assertTrue(vars.size()==2);
    }
}
