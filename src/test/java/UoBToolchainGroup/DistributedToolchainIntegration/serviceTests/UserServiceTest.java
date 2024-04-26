package UoBToolchainGroup.DistributedToolchainIntegration.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import UoBToolchainGroup.DistributedToolchainIntegration.ContainerTest;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.UserRespository;
import UoBToolchainGroup.DistributedToolchainIntegration.service.UserService;


@SpringBootTest
@Testcontainers
@RunWith(SpringRunner.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest  extends ContainerTest{

    @Autowired
    private UserRespository userRespository;
    private UserService userService;

    private User USER;

    @BeforeAll
    public void setUp(){
        userService = new UserService(userRespository);
        USER = new User(new ObjectId("6627c5044bdbcf0634346abc"), "testUser", new byte[0], "testUser@test.com", 10, new byte[0]);
    }

    public boolean equalUsers(User user1, User user2){
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
        User savedUser = userService.createUser(USER);
        assertEquals(USER, savedUser);
    }

    @ParameterizedTest 
    @Order(2)
    @CsvSource({"6628696e117fd47726a8116e,John","6628696e117fd47726a8116b,Jack","6628696e117fd47726a8116c,Jason"})
    public void testGetUserById(String id, String expectedUsername){
        User expectedUser = userService.getUserById(new ObjectId(id));
        assertEquals(expectedUser.getUsername(),expectedUsername);
    }
    
}
