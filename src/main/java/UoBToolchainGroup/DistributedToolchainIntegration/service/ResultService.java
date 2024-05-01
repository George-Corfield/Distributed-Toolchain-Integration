/*
 * Results service. Handles CRUD Database opertaions.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.ResultRepository;

@Service
public class ResultService {
    
    private final ResultRepository optimisationResultRepository;

    @Autowired
    public ResultService(ResultRepository optimisationResultRepository){
        this.optimisationResultRepository = optimisationResultRepository;
    }

    public Result creatResult(Result optimisationResult){
        return optimisationResultRepository.save(optimisationResult);
    }

    public Result getResultById(ObjectId opResultId){
        return optimisationResultRepository.findById(opResultId).get();
    }

    public List<Result> getResultsByPart(ObjectId partId){
        return optimisationResultRepository.getResultsByPartId(partId);
    }
}
