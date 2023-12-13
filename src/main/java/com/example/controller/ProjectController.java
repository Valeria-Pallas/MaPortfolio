package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.NewProjectDto;
import com.example.dto.ProjectDTO;
import com.example.models.Project;
import com.example.service.ProjectService;
import com.example.util.NewProjectDTOMapper;
import com.example.util.ProjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
// @CrossOrigin(origins = "localhost")
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private NewProjectDTOMapper newProjectMapper;

    @Operation(description = "Create a new project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Project with the same id exists", content = @Content),
            @ApiResponse(responseCode = "500", description = "Business error", content = @Content),
    })
    @PostMapping(value = "/create")
    public ResponseEntity<String> createProject(@RequestBody @Valid NewProjectDto newProjectDto) {
        Project newProject = newProjectMapper.mapToEntity(newProjectDto);
        return new ResponseEntity<>(projectService.addProject(newProject) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

  @Operation(description = "Obtain information of all projects")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Found list of all projects", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ProjectDTO.class))
    })
  })
  @GetMapping(value = "/all")
  public List<ProjectDTO> findAll() {
    return projectMapper.mapToDtoList(projectService.getAllProjects());
  }

  @Operation(description = "Obtain information of a project by project id")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "project found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ProjectDTO.class))
    }),
    @ApiResponse(responseCode = "404", description = "project not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ProjectDTO.class))
    })
  })
  @GetMapping(value = "/{id}")
  public ProjectDTO findById(@Parameter(description = "id of project to be searched") @PathVariable int id) {
    return projectMapper.mapToDto(projectService.getProjectById(id));
  }

  @Operation(description = "Update information of a project")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Project updated", content = @Content),
    @ApiResponse(responseCode = "404", description = "Project not found", content = @Content)
  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<String> updateProject(@RequestBody @Valid ProjectDTO projectDTO) {
    boolean success = projectService.updateProject(projectMapper.mapToEntity(projectDTO));
    return new ResponseEntity<>(success ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
  }

  @Operation(description = "Delete a project by id")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Project deleted", content = @Content),
    @ApiResponse(responseCode = "404", description = "Project not found", content = @Content)
  })
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteProject(@Parameter(description = "id of project to be deleted") @PathVariable int id) {
    return new ResponseEntity<>(projectService.deleteProjectById(id) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND); 
  }
}
