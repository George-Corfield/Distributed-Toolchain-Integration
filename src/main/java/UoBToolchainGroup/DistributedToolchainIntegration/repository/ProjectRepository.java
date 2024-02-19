package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;
import UoBToolchainGroup.DistributedToolchainIntegration.model.User;

public interface ProjectRepository extends MongoRepository<Project, String>{

    public Project findProjecByUser(User user);
}
