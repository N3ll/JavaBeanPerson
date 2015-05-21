package person_bound_pr;

import java.beans.PropertyVetoException;

public class testPerson {

	public static void main(String[] args) {
	
		PersonBean p1 = new PersonBean("Bob", "Horsens", 20);
		PersonBean p2 = new PersonBean("An", "Aarhus", 23);
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println();
		
		PersonListener pl = new PersonListener(1);
		PersonListener pl2 = new PersonListener(2);
		
		
		p1.addPropertyChangeListener(pl);
		p2.addPropertyChangeListener(pl2);
		
		p1.addVetoableChangeListener(pl);
		
		p1.setName("Bobob");
		try {
			p1.setCreditLimit(1);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

