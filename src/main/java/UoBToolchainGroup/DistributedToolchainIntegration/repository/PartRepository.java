package UoBToolchainGroup.DistributedToolchainIntegration.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;


public interface PartRepository extends MongoRepository<Part, String>{


}
