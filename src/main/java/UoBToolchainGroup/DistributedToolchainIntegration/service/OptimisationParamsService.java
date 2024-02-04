package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.repository.OptimisationParamsRepository;
import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationParams;


@Service
public class OptimisationParamsService {
    
    private final OptimisationParamsRepository optimisationParamsRepository;

    @Autowired
    public OptimisationParamsService(OptimisationParamsRepository optimisationParamsRepository){
        this.optimisationParamsRepository = optimisationParamsRepository;
    }

    public OptimisationParams createOptimisationParams(OptimisationParams optimisationParams){
        return optimisationParamsRepository.save(optimisationParams);
    }

    public OptimisationParams getOptimisationParamsById(String opParamsId){
        return optimisationParamsRepository.findById(opParamsId).get();
    }
}
