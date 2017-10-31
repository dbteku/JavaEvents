package com.dbteku.javaevents.examples.handlersAndInterfaces;

class ConcreteExample implements IExampleEventListener{

	public ConcreteExample() {
	}

	@Override
	public void onExampleEvent(ExampleEvent event) {
		System.out.println("Concrete");
		System.out.println(event.getEventName());
		System.out.println(event.getSomeData());
	}
	
}
