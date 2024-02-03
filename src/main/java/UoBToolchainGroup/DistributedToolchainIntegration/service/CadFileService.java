package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.CadFile;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.CadFileRepository;

@Service
public class CadFileService {

    private final CadFileRepository cadFileRepository;

    @Autowired
    public CadFileService(CadFileRepository cadFileRepository){
        this.cadFileRepository = cadFileRepository;
    }

    public CadFile createCadFile(CadFile cadFile){
        return cadFileRepository.save(cadFile);
    }

    public CadFile getCadFileByPart(String partId){
        return cadFileRepository.findById(partId).get();
    }

}
