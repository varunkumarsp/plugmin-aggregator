package org.openxava.actions;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.session.MyReport;

/**
 * 
 * @author Javier Paniza
 */
public class CreateNewMyReportAction extends TabBaseAction {
	private static Log log = LogFactory.getLog(CreateNewMyReportAction.class);
	
	@Inject
	private MyReport myReport; 

	public void execute() throws Exception {
		myReport = MyReport.create(getTab());
		myReport.setShared(false);
		getView().setModel(myReport);		
		getView().setEditable("name", true);			
		getView().removeActionForProperty("name", "MyReport.createNew");
		getView().removeActionForProperty("name", "MyReport.remove");
		getView().removeActionForProperty("name", "MyReport.share");
	}

}
