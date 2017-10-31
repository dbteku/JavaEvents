package com.dbteku.javaevents.examples.interfaceListening;

import com.dbteku.javaevents.models.Event;

public class CoolThingEvent extends Event{

	public CoolThingEvent() {
		super(CoolThingEvent.class.getSimpleName());
	}
	
}
