package com.newspa.taskmanager.service;

import com.newspa.taskmanager.Exception.ApiException;
import com.newspa.taskmanager.dto.TaskRequestDTO;
import com.newspa.taskmanager.dto.TaskResponseDTO;
import com.newspa.taskmanager.entity.State;
import com.newspa.taskmanager.entity.Task;
import com.newspa.taskmanager.entity.UserEntity;
import com.newspa.taskmanager.mapper.MapperTask;
import com.newspa.taskmanager.repository.TaskRepository;
import com.newspa.taskmanager.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements  TaskService{

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    @Override
    public List<TaskResponseDTO> findAllTasks() {
        return taskRepository.findAll().stream()
                .map(MapperTask::modelToResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public ResponseEntity<TaskResponseDTO> createTask(TaskRequestDTO requestDTO, Long id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new ApiException("Not Found By id" + id, HttpStatus.NOT_FOUND));

        Task taskModel = MapperTask.dtoRequestToModel(requestDTO);
        System.out.println("ss"+taskModel);

        Optional<State>  state = taskRepository.findStateByStatus(requestDTO.getState());

        if(!state.isPresent()){
            throw new ApiException("State Not Found By id" + id, HttpStatus.NOT_FOUND);
        }

        //taskModel.setStatus(Collections.singletonList(State.valueOf(requestDTO.getState().toUpperCase())));
        taskModel.setState(state.get());
        taskModel.setCreatedAt(LocalDateTime.now());
        taskModel.setUserEntity(userEntity);
        System.out.println(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MapperTask.modelToResponseDTO(taskRepository.save(taskModel)));


    }

    @Override
    @Transactional
    public ResponseEntity<TaskResponseDTO> updateTask(TaskRequestDTO requestDTO, Long id) {
        Task taskWithRepository = taskRepository.findById(Long.valueOf(id)).orElseThrow(() ->
                new ApiException("Task Not Found By id" + id, HttpStatus.NOT_FOUND));

        taskWithRepository.setUpdateAt(LocalDateTime.now());
        taskWithRepository.setTitle(requestDTO.getTitle());
        taskWithRepository.setDescription(requestDTO.getDescription());
        //List<State> lstStatus = new ArrayList<>();
        //lstStatus.add(State.valueOf(requestDTO.getState().toUpperCase()));
         //taskWithRepository.setState(null);

        Optional<State>  state = taskRepository.findStateByStatus(requestDTO.getState());

        if(!state.isPresent()){
            throw new ApiException("State Not Found By id" + id, HttpStatus.NOT_FOUND);
        }


         taskWithRepository.setState(state.get());
         System.out.println(requestDTO.getState());
        return ResponseEntity.status(HttpStatus.OK).body(
                MapperTask.modelToResponseDTO(taskRepository.save(taskWithRepository)));

    }

    @Override
    public void deleteTask(Long taskId) {
        if(!taskRepository.existsById(taskId)) {
            throw new ApiException("Not found by id: " + taskId, HttpStatus.NOT_FOUND);
        }
         taskRepository.deleteById(taskId);
    }
}
