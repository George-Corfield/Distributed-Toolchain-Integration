package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Part;

import java.util.List;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.PartRepository;

@Service
public class PartService {

    private final PartRepository partRepository;
    
    @Autowired
    public PartService(PartRepository partRepository){
        this.partRepository = partRepository;
    }

    public Part createPart(Part part){
        return partRepository.save(part);
    }

    public Part updatePart(Part part){
        return partRepository.save(part);
    }

    public List<Part> getPartsByProjectId(ObjectId id){
        //retrieves all parts with same project id
        return partRepository.findPartsByProjectId(id);
    }


    public Part getPartbyId(ObjectId id){
        return partRepository.findById(id.toString()).get();
    }

    public List<Part> getAllParts(){
        return partRepository.findAll();
    }
}

