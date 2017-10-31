JavaEvents
===================


The purpose of  **JavaEvents** is to make events a little easier for you when you are programming.

----------


Features
-------------

There are three types of listening that you can do in **JavaEvents!**

> **Listening Types:**

> - Interface listening (Usefull for classes that have unique events)
> - Event listening with custom handlers short acronym <strong>ELI</strong> (Useful for listening to an event with a respected interface)
> - Reflective Event Listening (Useful for quick event handling)

#### <i class="icon-code"></i> Code Examples
**InterfaceListening:**

> **Example Custom Service Or Class**
> <code>public class SomeClass implements &lt;IEventThrower&lt;ISomeInterface>>{
>  &nbsp;&nbsp;&nbsp; private LinkedList&lt;ISomeInterface> listeners;
>	&nbsp;&nbsp;&nbsp;&nbsp;public SomeClass(){
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this.listeners = new LinkedList<>();
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// Register the thrower
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EventManager.getInstance().registerThrower(ISomeInterface.class, this);
> &nbsp;&nbsp;&nbsp;&nbsp;}
> &nbsp;&nbsp;&nbsp;&nbsp;void subscribe(ISomeInterface listener){
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; listeners.add(listener);
> &nbsp;&nbsp;&nbsp;&nbsp;}
> &nbsp;&nbsp;&nbsp;&nbsp;void unsubscribe(ISomeInterface listener){
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; listeners.remove(listener);
> &nbsp;&nbsp;&nbsp;&nbsp;}
>  &nbsp;&nbsp;&nbsp;&nbsp;void clearSubscribers(){
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; listeners.clear();
> &nbsp;&nbsp;&nbsp;&nbsp;}
>  &nbsp;&nbsp;&nbsp;&nbsp;void somethingHappens(){
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (ISomeInterface iSomeInterface: listeners) {
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iSomeInterface.onCustomMethod();
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
> &nbsp;&nbsp;&nbsp;&nbsp;}
>  }
>  </code>
> **Example Interface for that class**
> <code>public interface ISomeInterface{
> // You obviously can create any methods in an interface with any return type and parameters
> // but here is an example interface.
>	&nbsp;&nbsp;&nbsp;&nbsp;void onCustomMethod(){};
>  
> </code>
> **Example Class that listens on that interface**
> <code>public class SomeListenerClass implements ISomeInterface{
>	&nbsp;&nbsp;&nbsp;&nbsp;public SomeListenerClass(){
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// Register listener.
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EventManager.getInstance().registerToInterface(ISomeInterface.class, this);
> &nbsp;&nbsp;&nbsp;&nbsp;}
>	&nbsp;&nbsp;&nbsp;&nbsp;void onCustomMethod(){
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; System.out.println("We got a call!");
> &nbsp;&nbsp;&nbsp;&nbsp;}
>  
> </code>
> </code>
> **Example Calling Code**
> <code>
> public static void main(String[] args){
>  &nbsp;&nbsp;&nbsp;&nbsp;SomeClass someClass = new SomeClass();
>  &nbsp;&nbsp;&nbsp;&nbsp;SomeListenerClass listener = new SomeListenerClass();
>  &nbsp;&nbsp;&nbsp;&nbsp;someClass.somethingHappens();
> }
> </code>
> **Expected output:**
> > <i class="icon-terminal"></i>We got a call!

**ELI:**

> **Example Event**
> <code>
> public class ExampleEvent extends Event{
> &nbsp;&nbsp;&nbsp;&nbsp;	private String someData;
> &nbsp;&nbsp;&nbsp;&nbsp;	public ExampleEvent(String data) {
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this.someData = someData;
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
> &nbsp;&nbsp;&nbsp;&nbsp; public String getSomeData(){
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return someData;
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
> }
> </code>
