package com.newspa.taskmanager.controller;


import com.newspa.taskmanager.dto.TaskRequestDTO;
import com.newspa.taskmanager.dto.TaskResponseDTO;
import com.newspa.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tasks")
@RestController
@Tag(name="Tasks Crud Operations", description = "API for managing tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Get the list of all taks")
    @ApiResponse(responseCode = "200", description = "Tasks successfully retrieved")
    @ApiResponse(responseCode = "403", description = "needs Authentication for listing tasks")
    public List<TaskResponseDTO> findAllTasks(){
      return taskService.findAllTasks();
    }

    @PostMapping
    @Operation(summary = "Create a new task")
    @ApiResponse(responseCode = "200", description = "Task successfully created")
    @ApiResponse(responseCode = "403", description = "needs Authentication for create task")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO,
                                                      @PathParam("userId") Long userId){
        return taskService.createTask(taskRequestDTO, userId);
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Update an existing task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully updated"),
            @ApiResponse(responseCode = "404", description = "ID not found"),
            @ApiResponse(responseCode = "403", description = "needs Authentication for updating task")
    })
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO,
                                                      @PathVariable Long taskId){
        System.out.println(taskId);
        return taskService.updateTask(taskRequestDTO, taskId);
    }

    @DeleteMapping("/{taskId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ID of the book to be deleted"),
            @ApiResponse(responseCode = "404", description = "ID not found"),
            @ApiResponse(responseCode = "403", description = "needs Authentication for removing task")
    })
    public void deleteTask(@PathVariable("taskId") Long taskId){
         taskService.deleteTask(taskId);
    }


}
