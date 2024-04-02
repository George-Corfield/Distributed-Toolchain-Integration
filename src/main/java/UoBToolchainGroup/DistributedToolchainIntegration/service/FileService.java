package UoBToolchainGroup.DistributedToolchainIntegration.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.File;
import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.FileRepository;


@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public File createFile(File file){
        return fileRepository.save(file);
    }

    public List<ModulesFile> getAllModulesFiles(){
        return fileRepository.findByContentType("Python");
    } 

    public File getFileById(ObjectId id){
        return fileRepository.findById(id.toString()).get();
    }
}
