package edu.neu.careerfoster.exception;

// Custom Exception Class
public class JobsPostedException extends Exception {
	
	public JobsPostedException(String message)
	{
		super("JobsPostedException-"+message);
	}
	
	public JobsPostedException(String message, Throwable cause)
	{
		super("JobsPostedException-"+message,cause);
	}
}
