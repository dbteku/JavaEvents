package com.dbteku.javaevents.models;

import com.dbteku.javaevents.interfaces.IEventHandler;
import com.dbteku.javaevents.interfaces.INullEventListener;

public class NullEventHandler implements IEventHandler<INullEventListener>{

	public NullEventHandler() {
	}

	// Handlers do all the conversion because java has type erasure :(
	// Converts the event and throws it to the listener.
	@Override
	public void handle(Event event, Object listener) {
		if(listener instanceof INullEventListener){
			INullEventListener eventListener = (INullEventListener) listener;
			eventListener.onNullEvent((NullEvent)event);	
		}
	}
	
}