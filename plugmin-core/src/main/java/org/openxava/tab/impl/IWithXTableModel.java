package org.openxava.tab.impl;

import java.rmi.RemoteException;

/**
 * Something with a {@link IXTableModel}. <p>
 *
 * @author  Javier Paniza
 */

public interface IWithXTableModel {

  IXTableModel getTable() throws RemoteException;
  
}
