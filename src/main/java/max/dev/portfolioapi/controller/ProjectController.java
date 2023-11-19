package max.dev.portfolioapi.controller;

import jakarta.validation.Valid;
import max.dev.portfolioapi.model.ProjectModel;
import max.dev.portfolioapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectModel> createProject(@Valid @RequestBody ProjectModel request){
        return ResponseEntity.ok(projectService.createProject(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectModel> updateProject(@PathVariable Long id, @RequestBody ProjectModel request) {
        return ResponseEntity.ok(projectService.updateProject(id, request));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProjectModel> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectModel>> getAllProjectByUser() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }




}
