package com.dbteku.javaevents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import com.dbteku.javaevents.exceptions.WrongListenerTypeException;
import com.dbteku.javaevents.interfaces.IEventThrower;

public class EventManager {

	
	private static EventManager instance;
	private final Map<Class<?>, LinkedHashSet<IEventThrower<?>>> EVENTS;

	private EventManager() {
		this.EVENTS = new HashMap<>();
	}
	
	public static EventManager getInstance(){
		if(instance == null){
			instance = new EventManager();
		}
		
		return instance;
	}

	public Iterator<IEventThrower<?>> getEventThrowers(Class<?> interfaceClass){
		LinkedHashSet<IEventThrower<?>> eventThrower = EVENTS.get(interfaceClass);
		return eventThrower.iterator();
	}

	public void registerThrower(Class<?> interfaceClass, IEventThrower<?> thrower){
		LinkedHashSet<IEventThrower<?>> list = new LinkedHashSet<>();
		if(EVENTS.containsKey(interfaceClass)){
			list = EVENTS.get(interfaceClass);
			list.add(thrower);
		}else{
			list.add(thrower);
			EVENTS.put(interfaceClass, list);
		}
	}

	public void unregisterThrower(Class<?> interfaceClass, IEventThrower<?> thrower){
		if(EVENTS.containsKey(interfaceClass)){
			LinkedHashSet<IEventThrower<?>> list = EVENTS.get(interfaceClass);
			list.remove(thrower);
			if(list.isEmpty()){
				EVENTS.remove(interfaceClass);	
			}
		}
	}

	public <L> void registerToInterface(Class<L> interfaceClass, L listener) throws WrongListenerTypeException{
		if(EVENTS.containsKey(interfaceClass)){
			LinkedHashSet<IEventThrower<?>> list = EVENTS.get(interfaceClass);
			for (IEventThrower<?> iEventThrower : list) {
				@SuppressWarnings("unchecked")
				IEventThrower<L> thrower = (IEventThrower<L>) iEventThrower;
				try{
					thrower.subscribe(listener);
				}catch(ClassCastException e){
					throw new WrongListenerTypeException();
				}	
			}
		}
	}

	public <L> void unregisterFromListener(Class<L> interfaceClass, L listener) throws WrongListenerTypeException{
		if(EVENTS.containsKey(interfaceClass)){
			LinkedHashSet<IEventThrower<?>> list = EVENTS.get(interfaceClass);
			for (IEventThrower<?> iEventThrower : list) {
				@SuppressWarnings("unchecked")
				IEventThrower<L> thrower = (IEventThrower<L>) iEventThrower;
				try{
					thrower.unsubscribe(listener);
				}catch(ClassCastException e){
					throw new WrongListenerTypeException();
				}	
			}
		}
	}
	
}