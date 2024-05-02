package UoBToolchainGroup.DistributedToolchainIntegration.controllerTests;

import java.io.IOException;
import java.io.InputStream;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import UoBToolchainGroup.DistributedToolchainIntegration.controller.FileController;
import UoBToolchainGroup.DistributedToolchainIntegration.service.FileService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FileController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class FileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileService fileService;

    private MockMultipartFile varsMultFile;
    private MockMultipartFile modsMultFile;

    @BeforeEach
    public void setup()throws IOException{
        //gathers two files before carrying out tests - one for a module and one for a json of variables
        InputStream varsInput = getClass().getClassLoader().getResourceAsStream("VariableFileTestJSON.json");
        InputStream modsInput = getClass().getClassLoader().getResourceAsStream("pseudo-module.py");
        varsMultFile = new MockMultipartFile("file", "test_file.json", MediaType.APPLICATION_JSON_VALUE, varsInput);
        modsMultFile = new MockMultipartFile("file", "test_file.py", MediaType.TEXT_PLAIN_VALUE, modsInput);
    }

    @Test
    public void testSaveFile() throws Exception{
        //test to ensure that /savefile correctly takes a file and saves it and returns saved message
        mvc.perform(multipart("/saveFile").file(varsMultFile))
        .andExpect(status().isOk())
        .andExpect(content().string("Saved"));
    }

    @Test
    public void testSaveModule() throws Exception{
        //test to ensure that saving a module does not throw exceptions and returns user to projects page
        mvc.perform(multipart("/saveModule").file(modsMultFile)
        .param("userId", new ObjectId().toString())
        .param("publicFile", "true"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/projects"));
    }


    
}
