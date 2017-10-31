package com.dbteku.javaevents.models;

public abstract class Event {

	private final String NAME;
	private boolean cancelled;
	
	public Event(String name) {
		this.NAME = name;
	}
	
	public String getEventName() {
		return NAME;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean isNull(){
		return NAME != null && NAME.length() > 0;
	}
	
}
