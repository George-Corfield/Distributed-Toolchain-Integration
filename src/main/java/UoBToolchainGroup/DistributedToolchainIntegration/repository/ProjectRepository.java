package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;

public interface ProjectRepository extends MongoRepository<Project, String>{

    public List<Project> findProjecByUser(User user);
}
