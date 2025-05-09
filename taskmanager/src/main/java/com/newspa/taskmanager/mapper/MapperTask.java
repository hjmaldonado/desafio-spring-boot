package com.newspa.taskmanager.mapper;
import com.newspa.taskmanager.dto.TaskRequestDTO;
import com.newspa.taskmanager.dto.TaskResponseDTO;
import com.newspa.taskmanager.entity.Task;
import org.modelmapper.ModelMapper;

public class MapperTask {

  private static final ModelMapper mapper = new ModelMapper();


   public static Task dtoRequestToModel(TaskRequestDTO taskRequestDTO){ return  mapper.map(taskRequestDTO, Task.class);}

   public static TaskResponseDTO modelToResponseDTO(Task task){
       System.out.println("jola"+task.getState());
       return mapper.map(task, TaskResponseDTO.class);
   }
}
