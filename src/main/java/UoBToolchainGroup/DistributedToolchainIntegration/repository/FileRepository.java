package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;

public interface FileRepository extends MongoRepository<File, String>{

    public List<ModulesFile> findByContentType(String type);
    //finds all modules which have a specific content type via query:
    //{ "contentType" : String } fields: Document{{}}

}
