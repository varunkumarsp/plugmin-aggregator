package org.openxava.calculators;



import org.openxava.util.Users;

public class CurrentUserCalculator implements ICalculator {

	
	
	public Object calculate() throws Exception {		
		return Users.getCurrent();
	}

}
