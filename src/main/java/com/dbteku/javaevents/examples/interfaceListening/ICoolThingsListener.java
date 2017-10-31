package com.dbteku.javaevents.examples.interfaceListening;

public interface ICoolThingsListener {

	void onCoolThingEvent(CoolThingEvent event);
	void onNotSoCoolEvent(NotCoolEvent event);
	
}
