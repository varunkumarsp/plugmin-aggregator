package org.openxava.annotations.parse;

import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyContextFactory implements InitialContextFactory {
	
	@Override
	public Context getInitialContext(Hashtable<?, ?> environment)
			throws NamingException {
		return new MyContext();
	}
}

class MyContext implements Context {
	 // used for the shared feature
    private static final Hashtable TABLE = new Hashtable();
    private static final Hashtable SUB_CONTEXTS = new Hashtable();

    // table is used as a read-write cache which sits
    // above the file-store
    private Hashtable table = new Hashtable();
    private Hashtable subContexts = new Hashtable();
    private Hashtable env = new Hashtable();
    private NameParser nameParser;
    /*
     * The name full name of this context.
     */
    private Name nameInNamespace = null;
    private boolean nameLock = false;
    private boolean closing;
    
    public MyContext() throws NamingException {
    	nameParser = new SimpleNameParser(this);
	}
    
	@Override
	public Object lookup(Name name) throws NamingException {
		return dataSource();
	}

	@Override
	public Object lookup(String name) throws NamingException {
		return dataSource();
	}

	@Override
	public Object addToEnvironment(String propName, Object propVal)
			throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void bind(Name name, Object obj) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void bind(String name, Object obj) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void close() throws NamingException {
	}

	@Override
	public Name composeName(Name name, Name prefix) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public String composeName(String name, String prefix)
			throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public Context createSubcontext(Name name) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public Context createSubcontext(String name) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void destroySubcontext(Name name) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void destroySubcontext(String name) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public Hashtable<?, ?> getEnvironment() throws NamingException {
		return env;
	}

	@Override
	public String getNameInNamespace() throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public NameParser getNameParser(Name name) throws NamingException {
		return nameParser;
	}

	@Override
	public NameParser getNameParser(String name) throws NamingException {
		return nameParser;
	}

	@Override
	public NamingEnumeration<NameClassPair> list(Name name)
			throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public NamingEnumeration<NameClassPair> list(String name)
			throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public NamingEnumeration<Binding> listBindings(Name name)
			throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public NamingEnumeration<Binding> listBindings(String name)
			throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public Object lookupLink(Name name) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public Object lookupLink(String name) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void rebind(Name name, Object obj) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void rebind(String name, Object obj) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public Object removeFromEnvironment(String propName) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void rename(Name oldName, Name newName) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void rename(String oldName, String newName) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void unbind(Name name) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}

	@Override
	public void unbind(String name) throws NamingException {
		throw new IllegalStateException("Not implemented!");
	}
	
	private DataSource dataSource() {
		ComboPooledDataSource ds = null;
		try {
			ds = new ComboPooledDataSource();
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/konakart7?zeroDateTimeBehavior=convertToNull");
			ds.setUser("root");
			ds.setPassword("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
}