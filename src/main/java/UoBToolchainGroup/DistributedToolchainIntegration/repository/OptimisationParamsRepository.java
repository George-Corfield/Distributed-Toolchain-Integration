/*
 * Optimisation Parameters repository, allows for optimisation parameters to be sotred in the databse.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;


public interface OptimisationParamsRepository extends MongoRepository<OptimisationParams, ObjectId>{

}
