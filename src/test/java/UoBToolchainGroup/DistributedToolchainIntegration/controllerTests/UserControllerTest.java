package UoBToolchainGroup.DistributedToolchainIntegration.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import UoBToolchainGroup.DistributedToolchainIntegration.controller.UserController;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    private User testUser;


    @BeforeEach
    public void setup() throws NoSuchAlgorithmException{
        byte[] salt = generateSalt();
        byte[] password = generatePasswordHash( "password".getBytes(), salt);
        testUser = new User(new ObjectId(), "test_user",password , "test@test.com" , 10, salt);
        when(userService.getUserByUsername("test_user")).thenReturn(testUser);
    }

    @Test
    public void testLoginGetRequest() throws Exception{
        mvc.perform(get("/login")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRegisterGetRequest() throws Exception{
        mvc.perform(get("/register")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private byte[] generatePasswordHash(byte[] plaintext, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(plaintext);
        return hashedPassword;
    }

    //create a generate salt function
    private byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }



    @Test
    public void testLoginValidUsername() throws Exception, NoSuchAlgorithmException{
        mvc.perform(post("/login")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "test_user")
        .param("password", new String("password".getBytes()))).andExpect(MockMvcResultMatchers.redirectedUrl("/projects"));
    }


    @Test
    public void testLoginValidUsername_IncorrectPassword() throws Exception {
        mvc.perform(post("/login")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "test_user")
        .param("password", "wrong_password")).andExpect(MockMvcResultMatchers.redirectedUrl("/login?fail=true"));
    }
    
    @Test
    public void testLoginIncorrectUsername() throws Exception {
        mvc.perform(post("/login")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "wrong_username")
        .param("password", "password")).andExpect(MockMvcResultMatchers.redirectedUrl("/login?fail=true"));
    }


    @Test
    public void testRegisterNewUser() throws Exception {
        mvc.perform(post("/register")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "new_user")
        .param("password","password")
        .param("email","test@test.com")).andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    @Test
    public void testRegisterExistingUser() throws Exception {
        mvc.perform(post("/register")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "test_user")
        .param("password","password")
        .param("email","test@test.com")).andExpect(MockMvcResultMatchers.redirectedUrl("/register?fail=true"));
    }    

}
