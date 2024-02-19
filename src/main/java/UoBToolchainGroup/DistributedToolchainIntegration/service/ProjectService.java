package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UoBToolchainGroup.DistributedToolchainIntegration.model.User;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Project;
import UoBToolchainGroup.DistributedToolchainIntegration.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {
    
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public Project getPartbyId(String projectId){
        return projectRepository.findById(projectId).get();
    }

    public Project getProjectByUser(User user){
        return projectRepository.findProjecByUser(user);
    }

    public List<Project> getPartsbyId(String projectId){
        String[] id = {projectId};
        ArrayList<String> ids = new ArrayList<>(Arrays.asList(id));
        return projectRepository.findAllById(ids);
    }
}
