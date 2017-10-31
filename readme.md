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
    	        if(!listeners.contains(listener)){
    		    listeners.add(listener);
    		}	
	    }
	    
	@Override
	public void unsubscribe(IGameDaemonListener listener) {
    	    listeners.remove(listener);	
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
    public interface IExampleEventListener{
        // Interface for event
        void onExampleEvent(ExampleEvent event);
    }
```
**Example Handler**
```java
    class ExampleHandler implements IEventHandler<IExampleEventListener>{
    
    	public ExampleHandler() {
    	}
    
    	// Handlers do all the conversion because java has type erasure :(
    	// Converts the event and throws it to the listener.
    	@Override
    	public void handle(Event event, Object listener) {
    		if(listener instanceof IExampleEventListener){
    			IExampleEventListener eventListener = (IExampleEventListener) listener;
    			eventListener.onExampleEvent((ExampleEvent)event);	
    		}
    	}
    }
```
**Concrete Class Example**
```java
    public class ConcreteClass implements IExampleEventListener{
        public ConcreteClass(){
            // You can call this code anywhere it doesn't have to be in the class itself though its preferred.
            EventManager.getInstance().registerListener(ExampleEvent.class, this);
        }
        
        @Override
        void onExampleEvent(ExampleEvent event){
            System.out.println("Concrete got a call!");
        }
        
    }
```
**Calling Code**
```java
    public static void main(String[] args){
            //Register Event
            EventManager.getInstance().registerEventWithHandler(ExampleEvent.class, new ExampleHandler());
            //Listen to event anonymous inner class
            EventManager.getInstance().registerListener(ExampleEvent.class, new IExampleEventListener() {
			
			@Override
			public void onExampleEvent(ExampleEvent event) {
				System.out.println("Anonymous got a call!");
			}
			//Concrete class listener
			ConcreteClass concrete = new ConcreteClass();
			//Throw event.
			EventManager.getInstance().throwEvent(new ExampleEvent());
		}); 
    }
```

> **Expected output:**
> Anonymous got a call!
> Concrete got a call!

**Reflective Events:**
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
**Example Listener Class**
```java
    public class ConcreteClass{
        public ConcreteClass(){
            EventManager.getInstance().registerListener(ExampleEvent.class, this);
        }
        
        //Add the EventListener annotation
        //Note: It does not matter what the method name is called as long as the first parameter matches the event
        // you subscribed to.
        @EventListener
        void onWhateverEvent(ExampleEvent event){
            System.out.println("Reflective got a call!");
        }
        
    }
```
**Calling Code**
```java
    public static void main(String[] args){
            //Register Event
            EventManager.getInstance().registerEvent(ExampleEvent.class);
            //Have an instance of this class or any other class.
			ConcreteClass concrete = new ConcreteClass();
			//Throw event.
			EventManager.getInstance().throwEvent(new ExampleEvent());
			//Thats it! Easy
		}); 
    }
```

> **Expected output:**
> > Reflective got a call!


