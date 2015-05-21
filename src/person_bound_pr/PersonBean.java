package person_bound_pr;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public class PersonBean {

	private String name;
	private String address;
	private int age;
	private int creditLimit;

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

	
	public PersonBean(String name, String address, int age) {
		this.age = age;
		this.address = address;
		this.name = name;
	}

	public PersonBean() {
		this("unknown", "none", -1);
	}
	
	public void setCreditLimit(int creditLimit) throws PropertyVetoException {
		int oldCreditLimt = creditLimit;
		vetos.fireVetoableChange("credit limit", oldCreditLimt,
				this.creditLimit);
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

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

	public void removePrepoertyChangeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}

	public void addVetoableChangeListener(VetoableChangeListener l) {
		vetos.addVetoableChangeListener(l);
	}

	public void removeVetoableChangeListener(VetoableChangeListener l) {
		vetos.removeVetoableChangeListener(l);
	}

}
