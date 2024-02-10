package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.VariableRepository;


@Service
public class VariableService {

    private final VariableRepository optimisationVarRepository;

    @Autowired
    public VariableService(VariableRepository optimisationVarRepository){
        this.optimisationVarRepository = optimisationVarRepository;
    }

    public Variable createOptimisationVar(Variable optimisationVar){
        return optimisationVarRepository.save(optimisationVar);
    }

    public Variable getOptimisationVarById(String opVarId){
        return optimisationVarRepository.findById(opVarId).get();
    }

}