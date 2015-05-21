package person_all;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class PersonBean {

	private String name;
	private String address;
	private int age;
	private int creditLimit;
	
	private List<AgeErrorListener> listeners = new ArrayList<>();

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

	public PersonBean(String name, String address, int age, int creditLimit) {
		this.age = age;
		this.address = address;
		this.name = name;
		this.creditLimit = creditLimit;
	}

	public PersonBean() {
		this("unknown", "none", -1, 0);
	}

	public void setCreditLimit(int creditLimit) throws PropertyVetoException {
		int oldCreditLimt = this.creditLimit;
		vetos.fireVetoableChange("credit limit", oldCreditLimt,
				creditLimit);
		
		this.creditLimit = creditLimit;
		pcs.fireIndexedPropertyChange("credit limit", oldCreditLimt,
				oldCreditLimt, this.creditLimit);
	}

	public int getCreditLimit() {
		return this.creditLimit;
	}

	public void setName(String name) {

		String oldName = this.name;
		this.name = name;
		pcs.firePropertyChange("name", oldName, this.name);

	}

	public void setAddress(String address) {

		String oldAddress = this.address;
		this.address = address;
		pcs.firePropertyChange("address", oldAddress, this.address);
	}

	public void setAge(int age) {
		int oldAge = this.age;
		this.age = age;
		pcs.firePropertyChange("age", oldAge, this.age);
		
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
	
	// -------------------------------------------------------------------------
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

	public void removePrepoertyChangeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}

	// -------------------------------------------------------------------------
	
	public void addVetoableChangeListener(VetoableChangeListener l) {
		vetos.addVetoableChangeListener(l);
	}

	public void removeVetoableChangeListener(VetoableChangeListener l) {
		vetos.removeVetoableChangeListener(l);
	}

}
