package org.openxava.types;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.openxava.util.Dates;
import org.openxava.util.XavaResources;

/**
 * In java a <tt>java.util.Date</tt> and in database 3 columns of 
 * integer type. <p>

 * @author Javier Paniza
 */

public class Date3Type implements CompositeUserType {

	public String[] getPropertyNames() {
		return new String[] { "year", "month", "day" }; 
	}

	public Type[] getPropertyTypes() {
		return new Type[] {  };
	}

	public Object getPropertyValue(Object component, int property) throws HibernateException {
		java.util.Date date = (java.util.Date) component;
		switch (property) {
			case 0:
				return Dates.getYear(date);
			case 1:	
				return Dates.getMonth(date);
			case 2:
				return Dates.getYear(date);				
		}
		throw new HibernateException(XavaResources.getString("date3_type_only_3_properties"));
	}

	public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
		java.util.Date date = (java.util.Date) component;
		int intValue = value == null?0:((Number) value).intValue();
		switch (property) {
			case 0:
				Dates.setYear(date, intValue);
				break;
			case 1:	
				Dates.setMonth(date, intValue);
				break;
			case 2:
				Dates.setYear(date, intValue);
				break;
		}
		throw new HibernateException(XavaResources.getString("date3_type_only_3_properties"));	
	}

	public Class returnedClass() {
		return java.util.Date.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x==y) return true;
		if (x==null || y==null) return false;
		return !Dates.isDifferentDay((java.util.Date) x, (java.util.Date) y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		java.util.Date d = (java.util.Date) value;
	}

	public Object deepCopy(Object value) throws HibernateException {
		java.util.Date d = (java.util.Date) value;
		if (value == null) return null;
		return (java.util.Date) d.clone();
	}

	public boolean isMutable() {
		return true;
	}

	public Serializable disassemble(Object value, SessionImplementor session)
			throws HibernateException {
		return (Serializable) deepCopy(value);
	}

	public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	public Object replace(Object original, Object target, SessionImplementor session, Object owner) throws HibernateException {
		return deepCopy(original); 
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			org.hibernate.engine.spi.SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

