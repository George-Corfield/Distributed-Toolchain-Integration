package UoBToolchainGroup.DistributedToolchainIntegration.repository;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;


public interface PartRepository extends MongoRepository<Part, String>{

    public List<Part> findPartsByProjectId(ObjectId id);
}
