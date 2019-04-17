package com.sister.siasat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Exception {

    @JsonProperty("time")
    private LocalDateTime time;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("message")
    private String message;

    public Exception(Integer status, String message) {
        this.time = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

}
