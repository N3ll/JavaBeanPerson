package person_event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class AgeErrorEvent extends EventObject {

	public AgeErrorEvent(Object source){
		super(source);
	}
	
}
