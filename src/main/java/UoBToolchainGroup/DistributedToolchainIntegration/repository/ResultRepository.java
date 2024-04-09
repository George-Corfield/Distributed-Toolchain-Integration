package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;


public interface ResultRepository extends MongoRepository<Result, ObjectId>{

}
