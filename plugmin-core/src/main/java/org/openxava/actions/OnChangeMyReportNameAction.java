package org.openxava.actions;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.session.MyReport;

/**
 * 
 * @author Javier Paniza 
 */

public class OnChangeMyReportNameAction extends TabBaseAction implements IOnChangePropertyAction  {
	private static Log log = LogFactory.getLog(OnChangeMyReportNameAction.class);
	
	@Inject
	private MyReport myReport;

	private String name;
	
	public void execute() throws Exception {
		myReport = MyReport.find(getTab(), name);
		getView().setModel(myReport);

		getView().removeActionForProperty("name", "MyReport.share");
		if (!name.endsWith(MyReport.SHARED_REPORT)){
			getView().addActionForProperty("name", "MyReport.share");
		}
	}

	public void setChangedProperty(String propertyName) {
	}

	public void setNewValue(Object value) {
		name = (String) value;		
	}
	
}
