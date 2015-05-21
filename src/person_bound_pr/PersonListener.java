package person_bound_pr;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;

public class PersonListener implements PropertyChangeListener, VetoableChangeListener {

	private int number;

	public  PersonListener(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	@Override
	public String toString(){
		return Integer.toString(number);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Person changes his "+ evt.getPropertyName());
		System.out.println("From "+evt.getOldValue());
		System.out.println("To "+ evt.getNewValue());
	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt) {
		System.out.println("Hello");
		
	}

}
