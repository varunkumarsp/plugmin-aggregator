package org.openxava.types;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.openxava.util.Is;
import org.openxava.util.XavaResources;

/**
 * In java an Enum and in database a letter corresponding
 * to the position in the string that it's in property 'letters'. <p>
 * 
 * @author Javier Paniza
 */

public class EnumLetterType implements UserType, ParameterizedType {
	
	private static Log log = LogFactory.getLog(EnumLetterType.class);
		
	private String letters;
	private String enumType;

	public int[] sqlTypes() {		
		return new int[] { Types.VARCHAR };
	}

	public Class returnedClass() {
		return Enum.class;		
	}

	public boolean equals(Object obj1, Object obj2) throws HibernateException {
		if (obj1 == obj2) return true;
		if (obj1 == null) return false;
		return obj1.equals(obj2);
	}

	public int hashCode(Object obj) throws HibernateException {
		return obj.hashCode();
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
		Object o = resultSet.getObject(names[0]);
		if (o == null) return null;
		if (!(o instanceof String)) { 
			throw new HibernateException(XavaResources.getString("conversion_java_string_expected"));
		}
		assertParameters();
		String value  = (String) o;
		if (Is.emptyString(value)) return null;
		int idx = letters.indexOf(value);
		if (idx < 0) {
			throw new HibernateException(XavaResources.getString("conversion_java_valid_values", value,  letters));
		}
		try {
			Object values = Class.forName(enumType).getMethod("values", null).invoke(null, null);
			return ((Object []) values)[idx];
		} 
		catch (Exception ex) {
			String message = XavaResources.getString("hibernate_type_enum_error", enumType, getClass()); 
			log.error(message, ex);
			throw new HibernateException(message);
		} 		
	}

	public void nullSafeSet(PreparedStatement ps, Object value, int index) throws HibernateException, SQLException {		
		if (value == null) {
			if (log.isTraceEnabled()) {
				log.trace( "binding '' to parameter: " + index );
			}			
			ps.setString(index, "");
			return;
		}
		if (!(value instanceof Enum)) {		
			throw new HibernateException(XavaResources.getString("conversion_db_enum_expected"));
		}
		assertParameters();
		int ivalue = ((Enum) value).ordinal();
		try {
			String letter = String.valueOf(letters.charAt(ivalue));
			if (log.isTraceEnabled()) {
				log.trace( "binding '" + letter + "' to parameter: " + index );
			}			
			ps.setString(index, letter);		
		}
		catch (IndexOutOfBoundsException ex) {
			throw new HibernateException(XavaResources.getString("conversion_db_valid_values", value, letters)); 
		}
	}
	
	public void setParameterValues(Properties parameters) {
		if (parameters == null) return;
		letters = parameters.getProperty("letters");
		enumType = parameters.getProperty("enumType");
	}
	
	private void assertParameters() throws HibernateException {
		if (Is.emptyString(letters)) {
			throw new HibernateException(XavaResources.getString("conversion_valid_values_letters_required", getClass().getName())); 
		}
		if (Is.emptyString(enumType)) {
			throw new HibernateException(XavaResources.getString("hibernate_type_parameter_required", "enumType", getClass().getName())); 
		}		
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

	/**
	 * Full qualified class for the Enum. <p>
	 * 
	 * For example: "org.openxava.test.model.Delivery$Distance"
	 */
	public String getEnumType() {
		return enumType;
	}
	/**
	 * Full qualified class for the Enum. <p>
	 * 
	 * For example: "org.openxava.test.model.Delivery$Distance"
	 */
	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}

	/**
	 * Letters string that corresponds with the valid values for this enum. <p>
	 * 
	 * For example, "AEI", means:
	 * <ul>
	 * <li> ordinal 0 in Java Enum for 'A' in DB
	 * <li> ordinal 1 in Java Enum for 'E' in DB
	 * <li> ordinal 2 in Java Enum for 'I' in DB
	 * <li>
	 * </ul>
	 */
	public String getLetters() {
		return letters;
	}
	/**
	 * Letters string that corresponds with the valid values for this enum. <p>
	 * 
	 * For example, "AEI", means:
	 * <ul>
	 * <li> ordinal 0 in Java Enum for 'A' in DB
	 * <li> ordinal 1 in Java Enum for 'E' in DB
	 * <li> ordinal 2 in Java Enum for 'I' in DB
	 * <li>
	 * </ul>
	 */
	public void setLetters(String letters) {
		this.letters = letters;
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
