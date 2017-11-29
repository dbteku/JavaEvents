package com.dbteku.javaevents.examples.handlersAndInterfaces;

import com.dbteku.javaevents.models.JavaEvent;

class ExampleEvent extends JavaEvent{

	private String someData;
	
	public ExampleEvent(String data) {
		super(ExampleEvent.class.getSimpleName());
		this.someData = data;
	}

	public String getSomeData() {
		return someData;
	}
	
}
