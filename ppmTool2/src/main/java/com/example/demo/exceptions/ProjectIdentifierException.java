package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//{
//"projectIdentifier": ""
//}

//Custom Exceptional handler

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdentifierException extends RuntimeException{

	public ProjectIdentifierException(String message) {
		super(message);
	}

}
