package com.dbteku.javaevents.events;

import com.dbteku.javaevents.models.Event;

public class OnEventUnregisterEvent extends Event{
	
	private final Class<?> EVENT_CLASS;
	
	public OnEventUnregisterEvent(Class<?> eventClass) {
		super(OnEventUnregisterEvent.class.getSimpleName());
		this.EVENT_CLASS = eventClass;
	}
	
	public Class<?> getEventClass() {
		return EVENT_CLASS;
	}


}
