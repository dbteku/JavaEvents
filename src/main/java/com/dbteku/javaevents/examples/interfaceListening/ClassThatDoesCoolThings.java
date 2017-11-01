package com.dbteku.javaevents.examples.interfaceListening;

import java.util.Iterator;
import java.util.LinkedList;

import com.dbteku.javaevents.interfaces.IEventThrower;

public class ClassThatDoesCoolThings implements IEventThrower<ICoolThingsListener>{

	/* Pretend this class is a service in your system.
	 * Lets say you have other services that would like to listen to this one.
	 * Usually you would have to give it access by an instance or some reference.
	 * In this model this service will hold all of its listeners and notify them whenever needed.
	 * You can also imagine this being thread safe with synchronized blocks but none are shown.
	 */
	
	private final LinkedList<ICoolThingsListener> LISTENERS;
	
	public ClassThatDoesCoolThings() {
		this.LISTENERS = new LinkedList<>();
	}
	
	public void startService(){
		doACoolThing();
		doANotCoolThing();
	}
	
	private void doACoolThing(){
		CoolThingEvent event = new CoolThingEvent();
		for (ICoolThingsListener iCoolThingsListener : LISTENERS) {
			iCoolThingsListener.onCoolThingEvent(event);
		}
	}
	
	private void doANotCoolThing(){
		NotCoolEvent event = new NotCoolEvent();
		for (ICoolThingsListener iCoolThingsListener : LISTENERS) {
			iCoolThingsListener.onNotSoCoolEvent(event);
		}
	}
	
	@Override
	public void subscribe(ICoolThingsListener listener) {
		if(!LISTENERS.contains(listener)){
			LISTENERS.add(listener);
		}
	}

	@Override
	public void unsubscribe(ICoolThingsListener listener) {
		LISTENERS.remove(listener);
	}

	@Override
	public void clearSubscribers() {
		LISTENERS.clear();
	}

	@Override
	public boolean isAnyoneListening() {
		return !LISTENERS.isEmpty();
	}

	@Override
	public Iterator<ICoolThingsListener> getSubscribers() {
		return LISTENERS.iterator();
	}

}
