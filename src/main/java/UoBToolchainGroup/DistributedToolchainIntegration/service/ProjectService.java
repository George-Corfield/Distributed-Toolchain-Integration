/*
 * Project service. Handles CRUD Database opertaions.
 */
package UoBToolchainGroup.DistributedToolchainIntegration.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Project getProjectById(String projectId){
        return projectRepository.findById(projectId).get();
    }

    // not functioning correctly, error #500
    //public void deleteProject(String projectId) {
        //Project project = getProjectById(projectId);
        //projectRepository.delete(project);
    //}

    public List<Project> getProjectsByUser(ObjectId userId){
        //retrieves all projects with the same userId
        return projectRepository.findProjectsByUserId(userId);
    }

    public Project getProjectByName(String projectName){
        return projectRepository.findProjectByProjectName(projectName);
    }

    public Project updateProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getPartsbyId(String projectId){
        String[] id = {projectId};
        ArrayList<String> ids = new ArrayList<>(Arrays.asList(id));
        return projectRepository.findAllById(ids);
    }
}
