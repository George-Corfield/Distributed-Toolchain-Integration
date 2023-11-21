package UoBToolchainGroup.DistributedToolchainIntegration;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.User;


public interface UserRespository extends MongoRepository<User, String>{

}
