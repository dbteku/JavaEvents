package com.dbteku.javaevents.interfaces;

import com.dbteku.javaevents.models.Event;

public interface IEventHandler<T> {

	void handle(Event event, Object listener);
	
}
