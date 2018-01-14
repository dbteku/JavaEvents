package com.dbteku.javaevents.tests;

import org.junit.Test;

import com.dbteku.javaevents.EventManager;
import com.dbteku.javaevents.interfaces.EventListener;

public class EventTests {

	@Test
	public void test() {
		EventManager.getInstance().registerEventListener(TestEvent.class, this);
		EventManager.getInstance().registerEvent(TestEvent.class);
		EventManager.getInstance().throwEvent(new TestEvent());
	}

	@EventListener
	public void onEvent(TestEvent event){
		System.out.println(event.getEventName());
	}
	
}
