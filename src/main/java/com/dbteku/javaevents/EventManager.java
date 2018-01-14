package com.dbteku.javaevents;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;

import com.dbteku.javaevents.exceptions.WrongListenerTypeException;
import com.dbteku.javaevents.interfaces.EventListener;
import com.dbteku.javaevents.interfaces.IEventHandler;
import com.dbteku.javaevents.interfaces.IEventThrower;
import com.dbteku.javaevents.models.JavaEvent;
import com.dbteku.javaevents.models.NullEventHandler;

public class EventManager {


	private static EventManager instance;
	private final Map<Class<?>, LinkedHashSet<IEventThrower<?>>> EVENT_THROWERS;
	private Map<Class<?>, EventListenerCenter> EVENT_LISTENERS;
	private Map<Class<?>, Queue<Object>> QUEUED_LISTENERS;

	private EventManager() {
		this.EVENT_THROWERS = new HashMap<>();
		this.EVENT_LISTENERS = new HashMap<>();
		this.QUEUED_LISTENERS = new HashMap<>();
	}

	public static EventManager getInstance(){
		if(instance == null){
			instance = new EventManager();
		}

		return instance;
	}

	public Iterator<IEventThrower<?>> getEventThrowers(Class<?> interfaceClass){
		synchronized (EVENT_THROWERS) {
			LinkedHashSet<IEventThrower<?>> eventThrower = EVENT_THROWERS.get(interfaceClass);
			return eventThrower.iterator();
		}
	}

	public <T> void registerThrower(Class<T> interfaceClass, IEventThrower<T> thrower){
		synchronized (EVENT_THROWERS) {
			LinkedHashSet<IEventThrower<?>> list = new LinkedHashSet<>();
			if(EVENT_THROWERS.containsKey(interfaceClass)){
				list = EVENT_THROWERS.get(interfaceClass);
				list.add(thrower);
			}else{
				list.add(thrower);
				EVENT_THROWERS.put(interfaceClass, list);
			}
			synchronized (QUEUED_LISTENERS) {
				if(QUEUED_LISTENERS.containsKey(interfaceClass)){
					Queue<Object> queue = QUEUED_LISTENERS.get(interfaceClass);
					LinkedHashSet<IEventThrower<?>> throwers = EVENT_THROWERS.get(interfaceClass);
					for (IEventThrower<?> iEventThrower : throwers) {
						@SuppressWarnings("unchecked")
						IEventThrower<T> convertedThrower = convertObject(thrower.getClass(), iEventThrower);
						for (Object object : queue) {
							convertedThrower.subscribe(convertObject(interfaceClass, object));
						}
					}
					QUEUED_LISTENERS.remove(interfaceClass);
				}	
			}
		}
	}

	private <T> T convertObject(Class<T> cls, Object obj){
		return cls.cast(obj);
	}

	public void unregisterThrower(Class<?> interfaceClass, IEventThrower<?> thrower){
		unregisterThrower(interfaceClass, thrower, false);
	}
	
	public void unregisterThrower(Class<?> interfaceClass, IEventThrower<?> thrower, boolean keepSubscribers){
		synchronized (EVENT_THROWERS) {
			if(EVENT_THROWERS.containsKey(interfaceClass)){
				LinkedHashSet<IEventThrower<?>> list = EVENT_THROWERS.get(interfaceClass);
				if(keepSubscribers){
					Queue<Object> toKeep = new ArrayDeque<>();
					Iterator<?> subscribers = thrower.getSubscribers();
					while(subscribers.hasNext()){
						Object listener = subscribers.next();
						toKeep.add(listener);
					}
					if(!QUEUED_LISTENERS.containsKey(interfaceClass)){
						QUEUED_LISTENERS.put(interfaceClass, toKeep);
					}
				}else{
					thrower.clearSubscribers();
				}
				list.remove(thrower);
				if(list.isEmpty()){
					EVENT_THROWERS.remove(interfaceClass);	
				}
			}
		}
	}

	public <L> void registerToInterface(Class<L> interfaceClass, L listener) throws WrongListenerTypeException{
		synchronized (EVENT_THROWERS) {
			if(EVENT_THROWERS.containsKey(interfaceClass)){
				LinkedHashSet<IEventThrower<?>> list = EVENT_THROWERS.get(interfaceClass);
				for (IEventThrower<?> iEventThrower : list) {
					@SuppressWarnings("unchecked")
					IEventThrower<L> thrower = (IEventThrower<L>) iEventThrower;
					try{
						thrower.subscribe(listener);
					}catch(ClassCastException e){
						throw new WrongListenerTypeException();
					}	
				}
			}else{
				if(QUEUED_LISTENERS.containsKey(interfaceClass)){
					QUEUED_LISTENERS.get(interfaceClass).add(listener);
				}else{
					Queue<Object> queue = new ArrayDeque<>();
					queue.add(listener);
					QUEUED_LISTENERS.put(interfaceClass, queue);
				}
			}
		}
	}

	public <L> void unregisterFromListener(Class<L> interfaceClass, L listener) throws WrongListenerTypeException{
		synchronized (EVENT_THROWERS) {
			if(EVENT_THROWERS.containsKey(interfaceClass)){
				LinkedHashSet<IEventThrower<?>> list = EVENT_THROWERS.get(interfaceClass);
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

	public <E> void registerEvent(Class<E> eventClass){
		synchronized (EVENT_LISTENERS) {
			if(!EVENT_LISTENERS.containsKey(eventClass)){
				EVENT_LISTENERS.put(eventClass, new EventListenerCenter(new IEventHandler<NullEventHandler>() {

					@Override
					public void handle(JavaEvent event, Object listener) {
						final int FIRST = 0;
						final int PARAM = 1;

						Method[] methods = listener.getClass().getMethods();
						for (int i = 0; i < methods.length; ++i) {
							EventListener eventHandler = methods[i].getAnnotation(EventListener.class);
							if (eventHandler != null) {
								Class<?>[] methodParams = methods[i].getParameterTypes();
								if(methodParams.length >= PARAM && event.getClass().getSimpleName().equals(methodParams[FIRST].getSimpleName())){
									try {
										methods[i].invoke(listener, event);
									} catch (IllegalAccessException e) {
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				}, eventClass));
				synchronized (QUEUED_LISTENERS) {
					if(QUEUED_LISTENERS.containsKey(eventClass)){
						Queue<?> queue = QUEUED_LISTENERS.get(eventClass);
						for (Object object : queue) {
							EVENT_LISTENERS.get(eventClass).add(object);
						}
						QUEUED_LISTENERS.remove(eventClass);
					}	
				}	
			}	
		}
	}
	
	public <E> void unregisterEvent(Class<E> eventClass) {
		synchronized (EVENT_LISTENERS) {
			if(EVENT_LISTENERS.containsKey(eventClass)) {
				EVENT_LISTENERS.remove(eventClass);
			}
		}
	}

	public <E> void registerEventWithHandler(Class<E> eventClass, IEventHandler<?> handler){
		synchronized (EVENT_LISTENERS) {
			if(!EVENT_LISTENERS.containsKey(eventClass)){
				EVENT_LISTENERS.put(eventClass, new EventListenerCenter(handler, eventClass));
				synchronized (QUEUED_LISTENERS) {
					if(QUEUED_LISTENERS.containsKey(eventClass)){
						Queue<?> queue = QUEUED_LISTENERS.get(eventClass);
						for (Object object : queue) {
							EVENT_LISTENERS.get(eventClass).add(object);
						}
						QUEUED_LISTENERS.remove(eventClass);
					}	
				}
			}	
		}
	}

	public <E, I, L> void registerEventListener(Class<E> eventClass, L listener){
		synchronized (EVENT_LISTENERS) {
			if(EVENT_LISTENERS.containsKey(eventClass)){
				EventListenerCenter center = EVENT_LISTENERS.get(eventClass);
				center.add(listener);
			}else{
				if(QUEUED_LISTENERS.containsKey(eventClass)){
					QUEUED_LISTENERS.get(eventClass).add(listener);
				}else{
					Queue<Object> queue = new ArrayDeque<>();
					queue.add(listener);
					QUEUED_LISTENERS.put(eventClass, queue);
				}
			}
		}
	}
	
	public <E, L> void unregisterEventListener(Class<E> eventClass, L listener){
		synchronized (EVENT_LISTENERS) {
			if(EVENT_LISTENERS.containsKey(eventClass)) {
				EventListenerCenter center = EVENT_LISTENERS.get(eventClass);
				center.remove(listener);
			}
		}
	}

	public <E> void throwEvent(final JavaEvent event){
		synchronized (EVENT_LISTENERS) {
			if(EVENT_LISTENERS.containsKey(event.getClass())){
				EventListenerCenter center = EVENT_LISTENERS.get(event.getClass());
				center.throwEvent(event);
			}	
		}
	}

	private class EventListenerCenter{
		private final IEventHandler<?> HANDLER;
		private final LinkedHashSet<Object> LISTENERS;
		private final Class<?> EVENT_CLASS;

		private EventListenerCenter(IEventHandler<?> handler, Class<?> eventClass) {
			this.HANDLER = handler;
			this.LISTENERS = new LinkedHashSet<>();
			this.EVENT_CLASS = eventClass;
		}

		private void throwEvent(JavaEvent event){
			synchronized (LISTENERS) {
				if(event.getClass().equals(EVENT_CLASS)){
					Iterator<?> listeners = LISTENERS.iterator();
					while(listeners.hasNext()){
						Object obj = listeners.next();
						HANDLER.handle(event, obj);
					}
				}	
			}
		}

		private<L> void add(L listener){
			synchronized (LISTENERS) {
				if(!LISTENERS.contains(listener)){
					LISTENERS.add(listener);
				}	
			}
		}
		
		private <L> void remove(L listener){
			LISTENERS.remove(listener);
		}
		
	}

}