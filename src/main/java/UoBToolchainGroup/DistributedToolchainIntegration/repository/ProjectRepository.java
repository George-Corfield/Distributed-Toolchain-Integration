/*
 * Project repository, allows for projects to be sotred in the databse.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String>{

    public List<Project> findProjectsByUserId(ObjectId userId);
    //gets all projects with associated userId via query:
    //{ "userId" : { "$oid" : ObjectId }} fields: Document{{}}

    public Project findProjectByProjectName(String projectName);
}
