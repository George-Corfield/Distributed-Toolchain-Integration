/*
 * Optimisation parameters service. Handles CRUD Database opertaions.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.bson.types.ObjectId;
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

    public OptimisationParams getOptimisationParamsById(ObjectId opParamsId){
        return optimisationParamsRepository.findById(opParamsId).get();
    }

    public OptimisationParams updateOptimisationParams(OptimisationParams op){
        return optimisationParamsRepository.save(op);
    }
}
