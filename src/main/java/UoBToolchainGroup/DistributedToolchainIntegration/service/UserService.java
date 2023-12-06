package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.UserRespository;

import java.util.List;

@Service
public class UserService {
    
    private final UserRespository userRespository;

    @Autowired
    public UserService(UserRespository userRespository){
        this.userRespository = userRespository;
    }

    public User createUser(User user){
        return userRespository.save(user);
    }

    public List<User> getAllUsers(){
        return userRespository.findAll();
    }

    public User getUserById(String id){
        return userRespository.findById(id).get();
    }




}
