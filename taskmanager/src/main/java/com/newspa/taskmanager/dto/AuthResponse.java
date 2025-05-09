package com.newspa.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "token"})
public record AuthResponse(String username,
                           String message,
                           String jwt,
                           boolean status) {
}
