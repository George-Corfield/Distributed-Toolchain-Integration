package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;


public interface VariableRepository extends MongoRepository<Variable, ObjectId>{

    public List<Variable> getVariablesByPartId(ObjectId partId);
}
