package com.dbteku.javaevents;

import com.dbteku.javaevents.models.JavaEventPriority;

class QueuedListener {
	private Object listener;
	private JavaEventPriority priority;
	
	public QueuedListener(Object listener, JavaEventPriority priority) {
		this.listener = listener;
		this.priority = priority;
	}
	
	public Object getListener() {
		return listener;
	}
	
	public JavaEventPriority getPriority() {
		return priority;
	}
}
