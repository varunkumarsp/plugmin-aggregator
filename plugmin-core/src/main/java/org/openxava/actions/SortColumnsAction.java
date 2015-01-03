package org.openxava.actions;



import javax.inject.Inject;
import javax.inject.Named;

import org.openxava.tab.Tab;

/**
 * @author Javier Paniza
 */
public class SortColumnsAction extends BaseAction {
	
	@Inject @Named("xava_customizingTab")
	private Tab tab;
	

	public void execute() throws Exception {
		// In reality, sort and unsort
		getTab().setSortRemainingProperties(!getTab().isSortRemainingProperties());
	}

	public Tab getTab() {
		return tab;
	}
	public void setTab(Tab tab) {
		this.tab = tab;
	}
}
