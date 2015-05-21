package person_event;

import java.util.ArrayList;
import java.util.List;

public class PersonBean {

	private String name;
	private String address;
	private int age;
	private List<AgeErrorListener> listeners = new ArrayList<>();

	public PersonBean(String name, String address, int age) {
		this.age = age;
		this.address = address;
		this.name = name;
	}

	public PersonBean() {
		this("unknown", "none", -1);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(int age) {
		this.age = age;
		if (age < 0 || age > 141)
			this.fireAgeErrorEvent(new AgeErrorEvent(this));
	
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public int getAge() {
		return this.age;
	}

	@Override
	public String toString() {

		return String.format("%s, %d, %s", this.name, this.age, this.address);
	}

	// -------------------------------------------------------------------------

	public synchronized void addAgeErrorEventListener(AgeErrorListener listener) {
		listeners.add(listener);
	}

	public synchronized void removeAgeErrorListener(AgeErrorEvent listener) {
		listeners.remove(listener);
	}

	private void fireAgeErrorEvent(AgeErrorEvent event) {
		List<AgeErrorListener> copy;
		synchronized (this) {
			copy = new ArrayList<AgeErrorListener>(listeners);
		}
		for (AgeErrorListener listener: copy) {
			listener.ageError(event);
		}
	}

}
