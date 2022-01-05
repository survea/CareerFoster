package edu.neu.careerfoster.exception;

//Custom exception class
public class JobApplicationException extends Exception {
	
	public JobApplicationException(String message)
	{
		super("JobApplicationException-"+message);
	}
	
	public JobApplicationException(String message, Throwable cause)
	{
		super("JobApplicationException-"+message,cause);
	}
}
