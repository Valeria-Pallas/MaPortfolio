package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.NewTaskDto;
import com.example.dto.TaskDTO;
import com.example.models.Task;
import com.example.service.TaskService;
import com.example.util.NewTaskDTOMapper;
import com.example.util.TaskMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private NewTaskDTOMapper newTaskDtoMapper;

    @Operation(description = "Create a new task")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Task created", content = @Content),
        @ApiResponse(responseCode = "400", description = "Task with the same id exists", content = @Content),
        @ApiResponse(responseCode = "500", description = "Business error", content = @Content),
    })

    @PostMapping(value = "/create")
    public ResponseEntity<String> createTask(@RequestBody @Valid NewTaskDto newTaskDto) {
        Task newTask = newTaskDtoMapper.mapToEntity(newTaskDto);
        return new ResponseEntity<>(taskService.addTask(newTask) ? HttpStatus.CREATED
                                                                 : HttpStatus.BAD_REQUEST);
    }

    // ...

    @GetMapping(value = "/all")
    public List<TaskDTO> findAllTasks() {
      return taskMapper.mapToDtoList(taskService.getAllTasks());
    }

    @Operation(description = "Obtain information of a task by task id")
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Task found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
      }),
      @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    @GetMapping(value = "/{id}")
    public NewTaskDto findTaskById(@Parameter(description = "id of task to be searched") @PathVariable int id) {
      return newTaskDtoMapper.mapToDto(taskService.getTaskById(id));
    }

    @Operation(description = "Update information of a task")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Task updated", content = @Content),
        @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateTask(@RequestBody @Valid TaskDTO taskDto) {
        boolean success = taskService.updateTask(taskMapper.mapToEntity(taskDto));
        return new ResponseEntity<>(success ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Delete a task by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Task deleted", content = @Content),
        @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTask(@Parameter(description = "id of task to be deleted") @PathVariable int id) {
        return new ResponseEntity<>(taskService.deleteTask(id) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}
