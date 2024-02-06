package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationVar;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.OptimisationVarRepository;


@Service
public class OptimisationVarService {

    private final OptimisationVarRepository optimisationVarRepository;

    @Autowired
    public OptimisationVarService(OptimisationVarRepository optimisationVarRepository){
        this.optimisationVarRepository = optimisationVarRepository;
    }

    public OptimisationVar createOptimisationVar(OptimisationVar optimisationVar){
        return optimisationVarRepository.save(optimisationVar);
    }

    public OptimisationVar getOptimisationVarById(String opVarId){
        return optimisationVarRepository.findById(opVarId).get();
    }

}