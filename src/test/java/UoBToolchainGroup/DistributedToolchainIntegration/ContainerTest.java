package UoBToolchainGroup.DistributedToolchainIntegration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;
import org.testcontainers.junit.jupiter.Container;

@Testcontainers
public class ContainerTest {
    

    @Container
    public static GenericContainer<?> MONGO_TEST_CONTAINER = new GenericContainer<>(DockerImageName.parse("mongo:7.0.3"))
        .withExposedPorts(27017)
        .withEnv("MONGO_INITDB_ROOT_USERNAME", "test_admin")
        .withEnv("MONGO_INITDB_ROOT_PASSWORD","test_admin")
        .withCopyFileToContainer(MountableFile.forClasspathResource("init-script.js"), "/docker-entrypoint-initdb.d/init-script.js");

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

    @DynamicPropertySource
    static void setMongoProperties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.host", MONGO_TEST_CONTAINER::getHost);
        registry.add("spring.data.mongodb.port", MONGO_TEST_CONTAINER::getFirstMappedPort);
        registry.add("spring.data.mongodb.username", () -> "test_admin");
        registry.add("spring.data.mongodb.password", () -> "test_admin");
        registry.add("spring.data.mongodb.database", () -> "service_test");
    }
}
