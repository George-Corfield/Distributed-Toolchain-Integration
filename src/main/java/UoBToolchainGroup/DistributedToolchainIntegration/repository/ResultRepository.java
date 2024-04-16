package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;


public interface ResultRepository extends MongoRepository<Result, ObjectId>{

    List<Result> getResultsByPartId(ObjectId id);
}
