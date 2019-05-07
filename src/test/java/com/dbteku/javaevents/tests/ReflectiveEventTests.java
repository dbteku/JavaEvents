package com.dbteku.javaevents.tests;

import org.junit.Test;

import com.dbteku.javaevents.EventManager;
import com.dbteku.javaevents.interfaces.EventListener;
import com.dbteku.javaevents.models.JavaEventPriority;

public class ReflectiveEventTests {

	@Test
	public void test() {
		EventManager.getInstance().registerEventListener(TestEvent.class, this);
		FirstEventTest test = new FirstEventTest();
		EventManager.getInstance().registerEvent(TestEvent.class);
		EventManager.getInstance().throwEvent(new TestEvent());
		test.getClass();
	}

	@EventListener
	public void onEvent(TestEvent event){
		System.out.println(event.getEventName());
	}
	
	class FirstEventTest{
		public FirstEventTest() {
			EventManager.getInstance().registerEventListener(TestEvent.class, this, JavaEventPriority.HIGHEST);
		}
		
		@EventListener
		public void onEvent(TestEvent event) {
			System.out.println("HIGHEST: " + event.getEventName());
		}
	}
	
}
