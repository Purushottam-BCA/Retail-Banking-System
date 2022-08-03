package com.cognizant.authenticationservice.exceptionhandling;

//Class for APPUSER is not found in DB
public class AppUserNotFoundException extends Exception 
{
	/**
	*
	*
	* @author Authentication MS
	*/
	private static final long serialVersionUID = 1L;
	
	public AppUserNotFoundException() 
	{
		super();
		//Empty Constructor
	}

	public AppUserNotFoundException(final String message) 
	{
		//Constructor for AppUserNotFoundException
		super(message);
	}
}