JavaEvents
===================


The purpose of  **JavaEvents** is to make events a little easier for you when you are programming.

----------


Features
-------------

There are three types of listening that you can do in **JavaEvents!**

> **Listening Types:**

> - Interface listening (Usefull for classes that have unique events)
> - Event listening with custom handlers short acronym **ELH** (Useful for listening to an event with a respected interface)
> - Reflective Event Listening (Useful for quick event handling)

Code Examples

**InterfaceListening:**

**Example Custom Service Or Class**

```java
    public class SomeClass implements IEventThrower<ISomeInterface>{
        private LinkedList<ISomeInterface> listeners;
        public SomeClass(){
            this.listeners = new LinkedList<>();
            EventManager.getInstance().registerThrower(ISomeInterface.class, this);
        }
        @Override
	    public void subscribe(IGameDaemonListener listener) {
    		synchronized (listeners) {
    			if(!listeners.contains(listener)){
    				listeners.add(listener);
    			}	
    		}
	    }
	    @Override
	    public void unsubscribe(IGameDaemonListener listener) {
    		synchronized (listeners) {
    			listeners.remove(listener);	
    		}
	    }
    	@Override
    	public void clearSubscribers() {
    		listeners.clear();
    	}
        
        public void somethingHappened(){
			for (ISomeInterface iSomeInterface : listeners) {
				iSomeInterface.onCustomEvent();
			}
        }
        
    }
```
> **Example Interface for that class**
```java
    // You can declare any method with any return type or parameters this is just an example.
    public interface ISomeInterface{
        void onCustomMethod();
    }
```
> **Example Class that listens on that interface**
```java
    public SomeListenerClass implements ISomeInterface{
        SomeListenerClass(){
            EventManager.getInstance().registerToInterace(ISomeInterface.class,this);
        }
        
        @Override
        public void onCustomMethod(){
            System.out.println("We got a call!");
        }
        
    }
```
> **Example Calling Code**
```java

    public static void main(String[] args){ 
        SomeClass someClass = new SomeClass(); 
        SomeListenerClass listener = new SomeListenerClass(); 
        someClass.somethingHappens(); 
    } 
```
> **Expected output:**
> > We got a call!

**Event Interfacing With Handlers:**

**Example Event**
```java
    public class ExampleEvent extends Event{
    
    	private String someData;
    	
    	public ExampleEvent(String data) {
    		super(ExampleEvent.class.getSimpleName());
    		this.someData = data;
    	}
    
    	public String getSomeData() {
    		return someData;
    	}
    	
    }
```
**Example Interface**
```java
    public interface IExampleEventListener
```

