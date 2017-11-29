package com.dbteku.javaevents.examples.interfaceListening;

import com.dbteku.javaevents.models.JavaEvent;

public class CoolThingEvent extends JavaEvent{

	public CoolThingEvent() {
		super(CoolThingEvent.class.getSimpleName());
	}
	
}
