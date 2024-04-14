package com.example.TaskManagement.controller;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling RESTful API requests related to projects.
 */
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    /**
     * Constructor injecting ProjectService dependency.
     *
     * @param projectService The ProjectService instance to be injected.
     */
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * GET endpoint to retrieve all projects.
     *
     * @return ResponseEntity containing a list of projects and HttpStatus OK.
     */
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /**
     * GET endpoint to retrieve a project by its ID.
     *
     * @param id The ID of the project to retrieve.
     * @return ResponseEntity containing the requested project and HttpStatus OK if found, otherwise HttpStatus NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    /**
     * POST endpoint to create a new project.
     *
     * @param project The project object to be created.
     * @return ResponseEntity containing the created project and HttpStatus CREATED.
     */
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    /**
     * PUT endpoint to update an existing project.
     *
     * @param id      The ID of the project to be updated.
     * @param project The updated project object.
     * @return ResponseEntity containing the updated project and HttpStatus OK if updated successfully, otherwise HttpStatus NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") int id, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    /**
     * DELETE endpoint to delete a project by its ID.
     *
     * @param id The ID of the project to be deleted.
     * @return ResponseEntity with HttpStatus NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}