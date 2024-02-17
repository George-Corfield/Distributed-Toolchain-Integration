package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.User;


public interface UserRespository extends MongoRepository<User, String>{

    public User findUserByUsername(String username);
    //Method to retrieve users in the database by username

}
