package com.newspa.taskmanager.dto;

import com.newspa.taskmanager.entity.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {

    private String id;

    private String title;

    private State state;

    private String description;

    private LocalDateTime createdAt;
}
