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
        //sets up test user object before each test so previous tests do not interfere

        byte[] salt = generateSalt();
        byte[] password = generatePasswordHash( "password".getBytes(), salt);
        testUser = new User(new ObjectId(), "test_user",password , "test@test.com" , 10, salt);

        //tells the service to return the testUser object when query carried out
        when(userService.getUserByUsername("test_user")).thenReturn(testUser);
    }

    @Test
    public void testLoginGetRequest() throws Exception{
        //test to check that login produces the correct page
        mvc.perform(get("/login"))
        .andExpect(MockMvcResultMatchers.view().name("login"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRegisterGetRequest() throws Exception{
        //test to check register produces correct page
        mvc.perform(get("/register"))
        .andExpect(MockMvcResultMatchers.view().name("register"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private byte[] generatePasswordHash(byte[] plaintext, byte[] salt) throws NoSuchAlgorithmException {
        //copied method to produce password hash
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(plaintext);
        return hashedPassword;
    }

    private byte[] generateSalt(){
        //copied module to generate salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }



    @Test
    public void testLoginValidUsername() throws Exception, NoSuchAlgorithmException{
        //test to check login performs correct redirect when valid login
        mvc.perform(post("/login")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "test_user")
        .param("password", new String("password".getBytes()))).andExpect(MockMvcResultMatchers.redirectedUrl("/projects"));
    }


    @Test
    public void testLoginValidUsername_IncorrectPassword() throws Exception {
        //test to check login performs correct redirect with invalid password but valid username
        mvc.perform(post("/login")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "test_user")
        .param("password", "wrong_password")).andExpect(MockMvcResultMatchers.redirectedUrl("/login?fail=true"));
    }
    
    @Test
    public void testLoginIncorrectUsername() throws Exception {
        //test to check login performs correct redirect with invalid username
        mvc.perform(post("/login")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "wrong_username")
        .param("password", "password")).andExpect(MockMvcResultMatchers.redirectedUrl("/login?fail=true"));
    }


    @Test
    public void testRegisterNewUser() throws Exception {
        //test to check register correctly redirects when user is valid
        mvc.perform(post("/register")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "new_user")
        .param("password","password")
        .param("email","test@test.com")).andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    @Test
    public void testRegisterExistingUser() throws Exception {
        //test tp check register correctly doesn't register user with same username as another user
        mvc.perform(post("/register")
        .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("username", "test_user")
        .param("password","password")
        .param("email","test@test.com")).andExpect(MockMvcResultMatchers.redirectedUrl("/register?fail=true"));
    }    

}
