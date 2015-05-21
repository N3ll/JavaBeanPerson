package person_all;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class testPerson {

	public static void main(String[] args) {

		PersonBean p1 = new PersonBean("Bob", "Horsens", 20, 60000);

		System.out.println(p1);
		System.out.println();

		Listener listener = new Listener();

		p1.addAgeErrorEventListener(listener);
		p1.addVetoableChangeListener(listener);
		p1.addPropertyChangeListener(listener);

		p1.setAge(150);

		try {
			p1.setCreditLimit(2);
		} catch (PropertyVetoException e) {
			System.out.println("Cannot");
		}

	}
}

class Listener implements AgeErrorListener, PropertyChangeListener,
		VetoableChangeListener {

	@Override
	public void ageError(AgeErrorEvent event) {
		PersonBean person = (PersonBean) event.getSource();
		System.out.println("Listener has caught event.");
		System.out.println("Event source: " + person.getName());
		System.out.println();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Person changes his " + evt.getPropertyName()
				+ " tried to change their age to " + evt.getNewValue());
		System.out.println("From " + evt.getOldValue());
		System.out.println("To " + evt.getNewValue());
		System.out.println();
	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		int oldCreditLimit = (Integer) evt.getOldValue();
		int newCreditLimit = (Integer) evt.getNewValue();
		System.out.println("From " + oldCreditLimit + " to " + newCreditLimit);

		if (newCreditLimit < 50) {
			throw new PropertyVetoException("Cannot do the change", evt);
		}

	}
}
