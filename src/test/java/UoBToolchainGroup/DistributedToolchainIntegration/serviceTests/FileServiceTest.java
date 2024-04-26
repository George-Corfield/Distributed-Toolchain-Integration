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
import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.FileRepository;
import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;

@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class FileServiceTest extends ContainerTest{
    //Test class to test FileService methods
    
    @Autowired
    private FileRepository fileRepository;
    private FileService fileService;

    @BeforeAll
    public void setup(){
        //Sets file service prior to testing
        fileService = new FileService(fileRepository);
    }

    @ParameterizedTest
    @CsvSource({"6428696e117fd47726a8115a,test-modfile-1","6428696e117fd47726a8115b,test-modfile-2","6428696e117fd47726a8115c,test-varfile-1"})
    public void testGetFileById(ObjectId id, String expectedFileName){
        //Test to ensure getFileById() returns expected File (Files can be Modules or Variables)
        File file = fileService.getFileById(id);
        assertEquals(expectedFileName, file.getFileName());
    }

    @Test
    public void testGetAvailableModulesFiles_NotPublicUser(){
        //Test to ensure getAvailableModulesFiles() returns expected list of modules
        //Expected: list of length 1 as the other module file is not public to this user
        List<ModulesFile> mods = fileService.getAvailableModulesFiles(new ObjectId("6628696e117fd47726a8116e"));
        assertTrue(mods.size() == 1); 
    }

    @Test
    public void testGetAvailableModulesFiles_WithPublicUser(){
        //Test to ensure getAvailableModulesFiles() returns expected list of modules
        //Expected: list of length 2 as both modules are public to this user
        List<ModulesFile> mods = fileService.getAvailableModulesFiles(new ObjectId("6628696e117fd47726a8116b"));
        assertTrue(mods.size() == 2); 
    }

}
