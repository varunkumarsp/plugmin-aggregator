package org.openxava.ejbx;

import javax.naming.NamingException;

/**
 * Provides a {@link IContext}. <p>
 *
 * Depend on implementation it can make or not cach'
 * of the context that it provides.
 *
 * @author  Javier Paniza
 */

public interface IContextProvider {

  IContext getContext() throws NamingException;
  
}
