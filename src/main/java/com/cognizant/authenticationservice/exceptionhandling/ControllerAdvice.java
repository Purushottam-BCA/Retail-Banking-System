package com.cognizant.authenticationservice.exceptionhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.authenticationservice.errorhandling.ErrorMessage;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class ControllerAdvice 
{
	//Exception Method for APPUSER not found
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage userNotFoundException(UsernameNotFoundException userNotFoundException) 
	{
		return new ErrorMessage(HttpStatus.NOT_FOUND,LocalDateTime.now(),userNotFoundException.getMessage());
	}
	
	
	//Exception for jwt malfunctioned error
	@ExceptionHandler(MalformedJwtException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorMessage tokenMalformedException() 
	{
		return new ErrorMessage(HttpStatus.UNAUTHORIZED,LocalDateTime.now(),"Not Authorized --> Token is Invalid..");
	}

	
	// Exception for JWT Signature unauthorized error
	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorMessage tokenSignatureException() 
	{
		return new ErrorMessage(HttpStatus.UNAUTHORIZED,LocalDateTime.now(),"Not Authorized --> Token is Invalid..");
	}


}