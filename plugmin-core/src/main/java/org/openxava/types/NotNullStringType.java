package org.openxava.types;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 * Allows to remove null for strings on save and on load from database. <p>
 * 
 * @author Javier Paniza
 */

public class NotNullStringType implements UserType {
	
	private static Log log = LogFactory.getLog(NotNullStringType.class);

	public int[] sqlTypes() {		
		return new int[] { Types.VARCHAR };
	}

	public Class returnedClass() {
		return String.class;
	}

	public boolean equals(Object obj1, Object obj2) throws HibernateException {
		if (obj1 == null) return obj2 == null;
		return obj1.equals(obj2);
	}

	public int hashCode(Object obj) throws HibernateException {
		return obj.hashCode();
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
		Object o = resultSet.getObject(names[0]);
    	return o == null?"":o;        	    
	}

	public void nullSafeSet(PreparedStatement ps, Object value, int index) throws HibernateException, SQLException {
		ps.setString(index, value==null?"":value.toString());
		
	}

	public Object deepCopy(Object obj) throws HibernateException {		
		return obj;
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object obj) throws HibernateException {
		return (Serializable) obj;
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
