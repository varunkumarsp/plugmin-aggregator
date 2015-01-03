package org.openxava.tab.impl;

import javax.ejb.FinderException;
import javax.swing.table.TableModel;


/**
 * A <code>TableModel</code> with a object associated to each row
 *
 * @author  Javier Paniza
 */

public interface IObjectTableModel extends TableModel {

  /**
   * Return the object associated to indicated row. <br>
   */
  Object getObjectAt(int rowIndex) throws FinderException;
  
}
