package com.newspa.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspa.taskmanager.entity.State;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
