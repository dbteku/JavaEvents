package com.dbteku.javaevents.interfaces;

import com.dbteku.javaevents.models.JavaEvent;

public interface IEventHandler<T> {

	void handle(JavaEvent event, Object listener);
	
}
