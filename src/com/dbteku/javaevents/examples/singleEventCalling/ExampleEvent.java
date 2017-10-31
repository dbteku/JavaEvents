package com.dbteku.javaevents.examples.singleEventCalling;

import com.dbteku.javaevents.models.Event;

class ExampleEvent extends Event{

	private String someData;
	
	public ExampleEvent(String data) {
		super(ExampleEvent.class.getSimpleName());
		this.someData = data;
	}

	public String getSomeData() {
		return someData;
	}
	
}
