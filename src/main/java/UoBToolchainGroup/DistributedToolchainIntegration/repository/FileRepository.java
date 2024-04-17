package UoBToolchainGroup.DistributedToolchainIntegration.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;

public interface FileRepository extends MongoRepository<File, String>{

    public List<ModulesFile> findModulesFilesByUserIdOrPublicFile(ObjectId userId, boolean publicFile, String contentType);
    //finds all modules which are either of the user or are public files
    //find using query: { "$or" : [{ "userId" : { "$oid" : "$userId"}}, { "publicFile" : true}]} fields: Document{{}} for class: class UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile in collection: Files

}
