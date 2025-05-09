package com.newspa.taskmanager.service;

import com.newspa.taskmanager.dto.TaskRequestDTO;
import com.newspa.taskmanager.dto.TaskResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {

    List<TaskResponseDTO> findAllTasks();

    ResponseEntity<TaskResponseDTO> createTask(TaskRequestDTO requestDTO, Long id);

    ResponseEntity<TaskResponseDTO> updateTask(TaskRequestDTO requestDTO,Long id);

    void  deleteTask(Long id);

}
