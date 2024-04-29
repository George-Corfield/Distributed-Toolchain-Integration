package UoBToolchainGroup.DistributedToolchainIntegration;

import java.time.Duration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

//Base class for docker image of mongodb for testing purposes
//Each service test must inherit this container
public class ContainerTest {
    
    //upon tests being initialised, docker image of latest mongo container runs
    //init-script.js populates each collection with data
    //username and password set to give tests access to database
    static GenericContainer<?> MONGO_TEST_CONTAINER = new GenericContainer<>(DockerImageName.parse("mongo:7.0.3"))
        .withExposedPorts(27017)
        .withEnv("MONGO_INITDB_ROOT_USERNAME", "test_admin")
        .withEnv("MONGO_INITDB_ROOT_PASSWORD","test_admin")
        .withCopyFileToContainer(MountableFile.forClasspathResource("init-script.js"), "/docker-entrypoint-initdb.d/init-script.js")
        .withStartupTimeout(Duration.ofSeconds(50));

    //starts the mongo container and logs that init-script.js has been successfully read in
    //occurs prior to tests being run
    static {
        MONGO_TEST_CONTAINER.start();
        try {
            ExecResult result = MONGO_TEST_CONTAINER.execInContainer("ls", "/docker-entrypoint-initdb.d");
            System.out.println("Contents of /docker-entrypoint-initdb.d");
            System.out.println(result.getStdout());
        } catch (Exception e){
            System.out.println(e);
        }
            
    }


    //Adds to registry temporary values for host, port, username etc
    //Similar to applicaiton.properties file for main application
    @DynamicPropertySource
    static void setMongoProperties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.host", MONGO_TEST_CONTAINER::getHost);
        registry.add("spring.data.mongodb.port", MONGO_TEST_CONTAINER::getFirstMappedPort);
        registry.add("spring.data.mongodb.username", () -> "test_admin");
        registry.add("spring.data.mongodb.password", () -> "test_admin");
        registry.add("spring.data.mongodb.database", () -> "service_test");
    }
}
