package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.OptimisationResult;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.OptimisationResultRepository;

@Service
public class OptimisationResultService {
    
    private final OptimisationResultRepository optimisationResultRepository;

    @Autowired
    public OptimisationResultService(OptimisationResultRepository optimisationResultRepository){
        this.optimisationResultRepository = optimisationResultRepository;
    }

    public OptimisationResult createOptimisationResult(OptimisationResult optimisationResult){
        return optimisationResultRepository.save(optimisationResult);
    }

    public OptimisationResult getOptimisationResultById(String opResultId){
        return optimisationResultRepository.findById(opResultId).get();
    }
}
