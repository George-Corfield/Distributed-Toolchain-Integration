package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import org.bson.types.ObjectId;

public interface UserRespository extends MongoRepository<User, ObjectId>{

    public User findUserByUsername(String username);
    //Method to retrieve users in the database by username

}
