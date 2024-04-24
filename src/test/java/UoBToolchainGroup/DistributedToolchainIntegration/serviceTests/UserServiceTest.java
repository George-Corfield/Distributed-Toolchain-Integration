package UoBToolchainGroup.DistributedToolchainIntegration.serviceTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest  extends ContainerTest{

    @Autowired
    private UserRespository userRespository;
    private UserService userService;

    private User USER;

    @Before
    public void setUp(){
        userService = new UserService(userRespository);
        USER = new User(new ObjectId("6627c5044bdbcf0634346abc"), "testUser", new byte[0], "testUser@test.com", 10, new byte[0]);
    }

    @Test
    @Order(1)
    public void testCreateUser(){
        User savedUser = userService.createUser(USER);
        assertEquals(USER, savedUser);
        System.out.println(userService.getAllUsers());
        System.out.println(USER.getUserId());
        System.out.println(savedUser.getUserId());
    }

    @Test 
    @Order(2)
    public void testGetUserById(){
        User expectedUser = userService.getUserById(USER.getUserId());
        assertEquals(USER.getUserId(), expectedUser.getUserId());
        assertEquals(USER.getEmail(), expectedUser.getEmail());
        assertArrayEquals(USER.getPassword(), expectedUser.getPassword());
        assertEquals(USER.getRole(), expectedUser.getRole());
        assertArrayEquals(USER.getSalt(), expectedUser.getSalt());
        assertEquals(USER.getUsername(), expectedUser.getUsername());
    }
    
}
