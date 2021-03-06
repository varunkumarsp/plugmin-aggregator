package org.openxava.view.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.openxava.util.meta.MetaElement;




/**
 * A little minimum common denominator for 
 * <code>MetaPropertyView</code>, <code>MetaReferenceView</code> and 
 * <code>MetaCollectionView</code>. <p>
 * 
 * @author Javier Paniza
 */

public class MetaMemberView extends MetaElement {
	
	private Collection actionsNames;
	private Collection alwaysEnabledActionsNames;
	private String editor;
	private String searchListCondition;
	private boolean collapsed;
	
	
	
	public void addActionName(String actionName) {
		if (actionsNames == null) actionsNames = new ArrayList();
		actionsNames.add(actionName);
	}
	public Collection getActionsNames() {		
		return actionsNames==null?Collections.EMPTY_LIST:actionsNames;
	}
	
	public void addAlwaysEnabledActionName(String actionName) {
		if (alwaysEnabledActionsNames == null) alwaysEnabledActionsNames = new ArrayList();
		alwaysEnabledActionsNames.add(actionName);
	}
	public Collection getAlwaysEnabledActionsNames() {		
		return alwaysEnabledActionsNames==null?Collections.EMPTY_LIST:alwaysEnabledActionsNames;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public void setSearchListCondition(String searchListCondition) {
		this.searchListCondition = searchListCondition;
	}
	public String getSearchListCondition() {
		return searchListCondition;
	}		
	
	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
