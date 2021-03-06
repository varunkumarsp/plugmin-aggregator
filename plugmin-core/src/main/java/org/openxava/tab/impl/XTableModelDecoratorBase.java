package org.openxava.tab.impl;

import java.rmi.RemoteException;
import java.util.Map;

import javax.ejb.FinderException;
import javax.swing.event.TableModelListener;

import org.openxava.util.Assert;


/**
 * Base class to create {@link IXTableModel} decorators. <p>
 * 
 * A decorator as describe in GoF book.
 * By default simply redirect the call to <tt>IXTableModel</tt> original. <br>
 *
 * @author  Javier Paniza
 */

public class XTableModelDecoratorBase
	implements IXTableModel, java.io.Serializable {

	private IXTableModel impl;
	
	

	/**
	 * @param toDecorate <tt>IXTableModel</tt> to decorate.
	 * @exception IllegalArgumentException If <tt>toDecorate == null</tt>.
	 */
	public XTableModelDecoratorBase(IXTableModel toDecorate) {
		Assert.arg(toDecorate);
		impl = toDecorate;
	}
	
	public void addTableModelListener(TableModelListener l) {
		impl.addTableModelListener(l);
	}
	
	public Class getColumnClass(int columnIndex) {
		return impl.getColumnClass(columnIndex);
	}
	
	public int getColumnCount() {
		return impl.getColumnCount();
	}
	
	public String getColumnName(int columnIndex) {
		return impl.getColumnName(columnIndex);
	}
	
	public Object getObjectAt(int rowIndex) throws FinderException {
		return impl.getObjectAt(rowIndex);
	}
	
	public int getRowCount() {
		return impl.getRowCount();
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		return impl.getValueAt(rowIndex, columnIndex);
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return impl.isCellEditable(rowIndex, columnIndex);
	}
	
	public void refresh() throws TabException {
		impl.refresh();
	}
	
	public void removeAllRows() {
		impl.removeAllRows();
	}
	
	public void removeTableModelListener(TableModelListener l) {
		impl.removeTableModelListener(l);
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		impl.setValueAt(aValue, rowIndex, columnIndex);
	}

	public int getTotalSize() throws RemoteException {
		return impl.getTotalSize();		
	}

	public Number getSum(String property) throws RemoteException { 
		return impl.getSum(property);		
	}
		
	public void removeRow(Map keyValues) throws FinderException { 
		impl.removeRow(keyValues);	
	}
	
}
