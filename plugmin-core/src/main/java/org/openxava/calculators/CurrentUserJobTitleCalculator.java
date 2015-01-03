package org.openxava.calculators;

import org.openxava.util.UserInfo;
import org.openxava.util.Users;

/**
 * Job title of the current user. <p>
 * 
 * Outside of a portal it will be an empty string. <br>
 * 
 * @author Javier Paniza
 */

public class CurrentUserJobTitleCalculator implements ICalculator {

	public Object calculate() throws Exception {
		UserInfo user = Users.getCurrentUserInfo();
		return user.getJobTitle();
	}
	
}
