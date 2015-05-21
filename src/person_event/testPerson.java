package person_event;


public class testPerson {

	public static void main(String[] args) {

  		PersonBean p1 = new PersonBean("Bob", "Horsens", 20);

		System.out.println(p1);
		System.out.println();

		Listener listener = new Listener();

		p1.addAgeErrorEventListener(listener);

		p1.setAge(150);

	}
}

class Listener implements AgeErrorListener {

	@Override
	public void ageError(AgeErrorEvent event) {
		PersonBean person = (PersonBean) event.getSource();
		System.out.println("Listener has caught event.");
		System.out.println("Event source: " + person.getName()
				+ "tried to change their age to " + person.getAge());
	}
}
