package UoBToolchainGroup.DistributedToolchainIntegration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import UoBToolchainGroup.DistributedToolchainIntegration.ContainerTest;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;


@SpringBootTest
@Testcontainers
public class UserServiceTest  extends ContainerTest{

    @Autowired
    private UserService userService;

    private User USER = new User(new ObjectId(), "testUser", new byte[0], "testUser@test.com", 10, new byte[0]);

    @Test
    public void testCreateUser(){
        assertEquals(USER, userService.createUser(USER));
    }

    @Test 
    public void testGetUserById(){
        assertEquals(USER, userService.getUserById(USER.getUserId()));
    }
    
}
