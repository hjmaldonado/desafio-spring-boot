package com.newspa.taskmanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class TaskRequestDTO {

    @Null(message = "task id  must be null")
    private Long id;

    @NotNull(message = "title id  must not be null")
    @Size(min =1, max=50, message = "Title must be between one and fifty characters")
    private String title;

    @NotNull(message = "state  must not be null")
    private String state;

    @NotNull(message = "description id  must not be null")
    @Size(min =1, max=250, message = "Description must be between one and two hundred fifty characters")
    private String description;

    private LocalDate createdAt;
}
