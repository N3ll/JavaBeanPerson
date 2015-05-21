package person_event;

import java.util.EventListener;

public interface AgeErrorListener extends EventListener {

	public void ageError(AgeErrorEvent event);
	
}
