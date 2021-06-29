package com.hm.rules.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


class ApiError {
    private static final long serialVersionUID = 6877490277661133451L;

    public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public Integer getError() {
		return error;
	}


	public void setError(Integer error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	private HttpStatus status;
    private Integer error;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private ApiError() {
        this.timestamp = LocalDateTime.now();
    }


    ApiError(HttpStatus status, String message, Integer error) {
        this();
        this.status = status;
        this.message = message;
        this.error = error;
    }
}