package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationResult;


public interface OptimisationResultRepository extends MongoRepository<OptimisationResult, String>{

}
