package UoBToolchainGroup.DistributedToolchainIntegration.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import UoBToolchainGroup.DistributedToolchainIntegration.ContainerTest;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.UserRespository;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;


@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
//Ensures that all tests are run with one container and in JUnit5
public class UserServiceTest  extends ContainerTest{
    //Test class to test UserService methods

    @Autowired
    private UserRespository userRespository;
    private UserService userService;

    private User USER;

    @BeforeAll
    public void setUp(){
        //Sets user service prior to starting all tests
        userService = new UserService(userRespository);
        USER = new User(new ObjectId("6627c5044bdbcf0634346abc"), "testUser", new byte[0], "testUser@test.com", 10, new byte[0]);
    }

   
    public boolean equalUsers(User user1, User user2){
        //Used to check that the contents of 2 user objects are equal
        //Consider moving this to User class
        return (user1.getUserId().equals(user2.getUserId()) 
        && user1.getEmail().equals(user2.getEmail()) 
        && Arrays.equals(user1.getPassword(), user2.getPassword())
        && user1.getUsername().equals(user2.getUsername()) 
        && Arrays.equals(user1.getSalt(), user2.getSalt())
        && user1.getRole() == user2.getRole());
    }

    @Test
    @Order(1)
    public void testCreateUser(){
        //Test to ensure create user returns the same user
        User savedUser = userService.createUser(USER);
        userRespository.delete(savedUser);
        assertEquals(USER, savedUser);
    }

    @ParameterizedTest 
    @Order(2)
    @CsvSource({"6628696e117fd47726a8116e,John","6628696e117fd47726a8116b,Jack","6628696e117fd47726a8116c,Jason"})
    public void testGetUserById(ObjectId id, String expectedUsername){
        //Test to check getUserById() returns expected user
        User expectedUser = userService.getUserById(id);
        assertEquals(expectedUser.getUsername(),expectedUsername);
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource({"6628696e117fd47726a8116e,John","6628696e117fd47726a8116b,Jack","6628696e117fd47726a8116c,Jason"})
    public void testGetUserByUsername(String expectedId, String name){
        //Test to check getUserByUsername() returns expected user
        User user = userService.getUserByUsername(name);
        assertEquals(user.getUserId().toString(), expectedId);
    }

    @Test
    @Order(4)
    public void testGetAllUsers(){
        //Test to check getAllUsers() retrieves all users in database
        List<User> users = userService.getAllUsers();
        System.out.println(userService.getAllUsers());
        assertTrue(users.size() == 3);
    }
    
}
