package com.dbteku.javaevents.events;

import com.dbteku.javaevents.interfaces.IEventThrower;
import com.dbteku.javaevents.models.JavaEvent;

public class OnThrowerRegisterEvent extends JavaEvent{
	
	private final IEventThrower<?> THROWER;
	
	public OnThrowerRegisterEvent(IEventThrower<?> thrower) {
		super(OnThrowerRegisterEvent.class.getSimpleName());
		this.THROWER = thrower;
	}
	
	public IEventThrower<?> getThrower() {
		return THROWER;
	}
	
	public Class<?> getThrowerClass(){
		return THROWER.getClass();
	}

}
