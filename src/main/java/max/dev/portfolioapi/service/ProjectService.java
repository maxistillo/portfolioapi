package max.dev.portfolioapi.service;


import max.dev.portfolioapi.exceptions.GlobalException;
import max.dev.portfolioapi.model.ProjectModel;
import max.dev.portfolioapi.repository.ProjectRepository;
import max.dev.portfolioapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

//  -------------------------------------------- CREATE PROJECT SERVICE ----------------------------------------------

    public ProjectModel createProject(ProjectModel request){

        ProjectModel project = ProjectModel.builder()
                .name(request.getName())
                .description(request.getDescription())
                .additionalInfo(request.getAdditionalInfo())
                .githubUrl(request.getGithubUrl())
                .projectUrl(request.getProjectUrl())
                .imgUrl(request.getImgUrl())
                .imgUrl2(request.getImgUrl2())
                .build();
        projectRepository.save(project);

        return project;
    }

//  ------------------------------------------ UPDATE PROJECT SERVICE ------------------------------------------------

    public ProjectModel updateProject(Long id, ProjectModel request) {
        ProjectModel project = projectRepository.findById(id).orElseThrow(()-> new GlobalException("Project with id " + id + " not found"));

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setAdditionalInfo(request.getAdditionalInfo());
        project.setGithubUrl(request.getGithubUrl());
        project.setProjectUrl(request.getProjectUrl());
        project.setImgUrl(request.getImgUrl());
        project.setImgUrl2(request.getImgUrl2());

        projectRepository.save(project);

        return project;
    }

//  ------------------------------------------------------- READ PROJECT SERVICE -----------------------------------------------------

    public ProjectModel getProject(Long id) {
        var project = projectRepository.findById(id).orElseThrow(() -> new GlobalException("Project not found"));
        return project;
    }

    public List<ProjectModel> getAllProjects() {
        List<ProjectModel> allProjects = projectRepository.findAll();
        return allProjects;
    }



//  ------------------------------------------------- DELETE SERVICE ---------------------------------------------

    public String deleteProject(Long id) {
        ProjectModel project = projectRepository.findById(id).orElseThrow(() -> new GlobalException("Project with id " + id + " not found"));
        projectRepository.deleteById(id);
        return "Project with id " + id + " deleted";
    }

}
