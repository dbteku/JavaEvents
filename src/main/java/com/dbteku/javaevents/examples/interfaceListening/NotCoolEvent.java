package com.dbteku.javaevents.examples.interfaceListening;

import com.dbteku.javaevents.models.JavaEvent;

public class NotCoolEvent extends JavaEvent{

	public NotCoolEvent() {
		super(NotCoolEvent.class.getSimpleName());
	}
	
}
