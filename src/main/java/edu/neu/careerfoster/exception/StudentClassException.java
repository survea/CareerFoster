package edu.neu.careerfoster.exception;

public class StudentClassException extends Exception {
	
	public StudentClassException(String message)
	{
		super("EmployerClassException-"+message);
	}
	
	public StudentClassException(String message, Throwable cause)
	{
		super("EmployerClassException-"+message,cause);
	}
}
