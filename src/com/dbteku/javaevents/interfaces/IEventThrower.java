package com.dbteku.javaevents.interfaces;

public interface IEventThrower<T> {
	
	/**
	 * Classes that handle specific events defined by you.
	 * @param listener The listener you want to subscribe.
	 */
	void subscribe(T listener);
	/**
	 * Classes that handle specific events defined by you.
	 * @param listener The listener you want to unsubscribe.
	 */
	void unsubscribe(T listener);
	
	void clearSubscribers();
	
}

