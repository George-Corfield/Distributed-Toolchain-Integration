package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String>{

    public List<Project> findProjectByUserId(ObjectId userId);

    public Project findProjectByProjectName(String projectName);
}
