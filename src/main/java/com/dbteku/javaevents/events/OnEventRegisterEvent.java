package com.dbteku.javaevents.events;

import com.dbteku.javaevents.models.JavaEvent;

public class OnEventRegisterEvent extends JavaEvent{
	
	private final Class<?> EVENT_CLASS;
	
	public OnEventRegisterEvent(Class<?> eventClass) {
		super(OnEventRegisterEvent.class.getSimpleName());
		this.EVENT_CLASS = eventClass;
	}
	
	public Class<?> getEventClass() {
		return EVENT_CLASS;
	}


}
