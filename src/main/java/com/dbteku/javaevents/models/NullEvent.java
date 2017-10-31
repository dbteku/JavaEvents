package com.dbteku.javaevents.models;

public class NullEvent extends Event{

	public NullEvent() {
		super(NullEvent.class.getSimpleName());
	}

	@Override
	public boolean isNull() {
		return true;
	}
	
}
