package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;


public interface OptimisationParamsRepository extends MongoRepository<OptimisationParams, String>{

}
