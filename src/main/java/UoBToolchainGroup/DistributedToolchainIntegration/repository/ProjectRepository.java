package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;


public interface ProjectRepository extends MongoRepository<Project, String>{

}
