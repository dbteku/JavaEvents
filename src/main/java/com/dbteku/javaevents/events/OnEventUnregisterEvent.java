package com.dbteku.javaevents.events;

import com.dbteku.javaevents.models.JavaEvent;

public class OnEventUnregisterEvent extends JavaEvent{
	
	private final Class<?> EVENT_CLASS;
	
	public OnEventUnregisterEvent(Class<?> eventClass) {
		super(OnEventUnregisterEvent.class.getSimpleName());
		this.EVENT_CLASS = eventClass;
	}
	
	public Class<?> getEventClass() {
		return EVENT_CLASS;
	}


}
