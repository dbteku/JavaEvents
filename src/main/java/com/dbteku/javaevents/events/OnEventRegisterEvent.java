package com.dbteku.javaevents.events;

import com.dbteku.javaevents.models.Event;

public class OnEventRegisterEvent extends Event{
	
	private final Class<?> EVENT_CLASS;
	
	public OnEventRegisterEvent(Class<?> eventClass) {
		super(OnEventRegisterEvent.class.getSimpleName());
		this.EVENT_CLASS = eventClass;
	}
	
	public Class<?> getEventClass() {
		return EVENT_CLASS;
	}


}
