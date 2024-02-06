package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationVar;


public interface OptimisationVarRepository extends MongoRepository<OptimisationVar, String>{

}
