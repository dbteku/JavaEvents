package com.dbteku.javaevents.examples.handlersAndInterfaces;

import com.dbteku.javaevents.interfaces.IEventHandler;
import com.dbteku.javaevents.models.Event;

class ExampleHandler implements IEventHandler<IExampleEventListener>{

	public ExampleHandler() {
	}

	// Handlers do all the conversion because java has type erasure :(
	// Converts the event and throws it to the listener.
	@Override
	public void handle(Event event, Object listener) {
		if(listener instanceof IExampleEventListener){
			IExampleEventListener eventListener = (IExampleEventListener) listener;
			eventListener.onExampleEvent((ExampleEvent)event);	
		}
	}
	
}