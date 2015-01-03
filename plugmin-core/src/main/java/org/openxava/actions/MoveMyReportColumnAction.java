package org.openxava.actions;

import java.util.Collections;

import javax.inject.Inject;

import org.openxava.session.MyReport;

/**
 * 
 * @author Javier Paniza
 */
public class MoveMyReportColumnAction extends CollectionBaseAction {
	
	@Inject
	private MyReport myReport;
	private int increment;
	
	public void execute() throws Exception {
		int otherRow = getRow() + increment;
		if (otherRow < 0 || otherRow >= getMapValues().size()) return;
		Collections.swap(myReport.getColumns(), getRow(), otherRow);	
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

}
