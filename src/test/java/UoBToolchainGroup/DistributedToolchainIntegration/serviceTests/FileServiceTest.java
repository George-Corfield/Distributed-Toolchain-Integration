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
    
    @Autowired
    private FileRepository fileRepository;
    private FileService fileService;

    @BeforeAll
    public void setup(){
        fileService = new FileService(fileRepository);
    }

    @ParameterizedTest
    @CsvSource({"6428696e117fd47726a8115a,test-modfile-1","6428696e117fd47726a8115b,test-modfile-2","6428696e117fd47726a8115c,test-varfile-1"})
    public void testGetFileById(ObjectId id, String expectedFileName){
        File file = fileService.getFileById(id);
        assertEquals(expectedFileName, file.getFileName());
    }

    @Test
    public void testGetAvailableModulesFiles_NotPublicUser(){
        List<ModulesFile> mods = fileService.getAvailableModulesFiles(new ObjectId("6628696e117fd47726a8116e"));
        assertTrue(mods.size() == 1); 
    }

    @Test
    public void testGetAvailableModulesFiles_WithPublicUser(){
        List<ModulesFile> mods = fileService.getAvailableModulesFiles(new ObjectId("6628696e117fd47726a8116b"));
        assertTrue(mods.size() == 2); 
    }

}
