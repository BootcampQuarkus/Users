package com.quarkus.bootcamp.nttdata.infraestructure.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseDto<T> {
	private int code;
	private String message;
	private String errorMessage;
	private String description;
	private T data;
	private List<T> lista;

	public ResponseDto(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResponseDto(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
