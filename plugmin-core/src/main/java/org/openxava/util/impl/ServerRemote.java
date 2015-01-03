package org.openxava.util.impl;

import java.rmi.RemoteException;

import org.openxava.actions.IRemoteAction;
import org.openxava.calculators.ICalculator;


public interface ServerRemote extends javax.ejb.EJBObject {
	
	Object calculate(ICalculator calculator) throws Exception, RemoteException;
	
	Object calculateWithoutTransaction(ICalculator calculator) throws Exception, RemoteException;	

	IRemoteAction execute(IRemoteAction action) throws Exception, RemoteException;
}