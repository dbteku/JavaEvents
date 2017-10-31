package com.dbteku.javaevents.exceptions;
public class WrongListenerTypeException extends Exception{

	private static final String MESSAGE = "Wrong listener type! Make sure you are subscribing and unsubscribing to events with the same Listener.";
	private static final long serialVersionUID = 1L;

	 public WrongListenerTypeException() {
		 super(MESSAGE);
	}
	
}
