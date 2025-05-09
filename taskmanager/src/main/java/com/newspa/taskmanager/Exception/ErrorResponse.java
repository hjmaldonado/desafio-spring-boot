package com.newspa.taskmanager.Exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private  String error;
}
