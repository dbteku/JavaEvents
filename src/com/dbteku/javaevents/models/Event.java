package com.dbteku.javaevents.models;

public abstract class Event {

	private final String NAME;
	
	public Event(String name) {
		this.NAME = name;
	}
	
	
	public String getEventName() {
		return NAME;
	}
	
	public boolean isNull(){
		return NAME != null && NAME.length() > 0;
	}
	
}
