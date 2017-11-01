package com.dbteku.javaevents.interfaces;

import java.util.Iterator;

public interface IEventThrower<T> {
	
	/**
	 * Classes that handle specific events defined by you.
	 * @param object The listener you want to subscribe.
	 */
	void subscribe(T object);
	/**
	 * Classes that handle specific events defined by you.
	 * @param listener The listener you want to unsubscribe.
	 */
	void unsubscribe(T listener);
	
	void clearSubscribers();
	
	boolean isAnyoneListening();
	
	Iterator<T> getSubscribers();
	
}

