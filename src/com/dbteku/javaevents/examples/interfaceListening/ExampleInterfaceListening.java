package com.dbteku.javaevents.examples.interfaceListening;

class ExampleInterfaceListening {

	private ClassThatDoesCoolThings service;
	
	public ExampleInterfaceListening() {
		this.service = new ClassThatDoesCoolThings();
	}
	
	void example(){
		service.startService();
	}
	
}
