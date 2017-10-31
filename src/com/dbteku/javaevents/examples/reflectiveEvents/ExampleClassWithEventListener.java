package com.dbteku.javaevents.examples.reflectiveEvents;

import com.dbteku.javaevents.EventManager;
import com.dbteku.javaevents.interfaces.EventListener;
import com.dbteku.javaevents.models.NullEvent;

class ExampleClassWithEventListener {

	/*
	 * Imagine this class needs to listen to one or more events but you don't
	 * want to go through the trouble of creating handlers and interfaces for each event.
	 */
	
	public ExampleClassWithEventListener() {
		
	}
	
	void testThrow(){
		// Rule 1 is always register events before you try to listen to them.
		EventManager.getInstance().registerEvent(NullEvent.class);
		
		//Listen to that event.
		EventManager.getInstance().registerListener(NullEvent.class, this);
		
		EventManager.getInstance().throwEvent(new NullEvent());
	}
	
	//Declare this method an event listener with the @EventListener annotation.
	//The name of the method does not matter as long as the first parameter is the correct event.
	@EventListener
	public void onWhateverEvent(NullEvent event){
		System.out.println(event.getEventName());
	}
	
}
