package com.dbteku.javaevents.examples.handlersAndInterfaces;

import com.dbteku.javaevents.EventManager;

class ExampleEventCalling {

	public ExampleEventCalling() {
	}
	
	public void throwingEvents(){
		EventManager manager = EventManager.getInstance();
		// Register an event with its handler.
		manager.registerEventWithHandler(ExampleEvent.class, new ExampleHandler());
		
		// Register a listener (Can be a concrete class or an anonymous inner class)
		// Anonymous example
		manager.registerListener(ExampleEvent.class, new IExampleEventListener() {
			
			@Override
			public void onExampleEvent(ExampleEvent event) {
				System.out.println("Anonymous");
				System.out.println(event.getEventName());
				System.out.println(event.getSomeData());
			}
		});
		
		// Concrete Example
		manager.registerListener(ExampleEvent.class, new ConcreteExample());
		
		//Throw Event
		manager.throwEvent(new ExampleEvent("This is an example event"));
		
		// Thats it!
	}
	
}
