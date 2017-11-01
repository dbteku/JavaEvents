package com.dbteku.javaevents.events;

import com.dbteku.javaevents.interfaces.IEventThrower;
import com.dbteku.javaevents.models.Event;

public class OnThrowerUnregisterEvent extends Event{
	
	private final IEventThrower<?> THROWER;
	
	public OnThrowerUnregisterEvent(IEventThrower<?> thrower) {
		super(OnThrowerUnregisterEvent.class.getSimpleName());
		this.THROWER = thrower;
	}
	
	public IEventThrower<?> getThrower() {
		return THROWER;
	}
	
	public Class<?> getThrowerClass(){
		return THROWER.getClass();
	}

}
