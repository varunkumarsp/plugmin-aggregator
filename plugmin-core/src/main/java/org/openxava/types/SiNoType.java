package org.openxava.types;

import java.io.Serializable;

import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.DiscriminatorType;
import org.hibernate.type.PrimitiveType;
import org.hibernate.type.StringType;
import org.hibernate.type.YesNoType;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

/**
 * Stores a boolean value as 'S' or 'N' in database. <p>
 * 
 * @author Javier Paniza
 */

public class SiNoType 
	extends AbstractSingleColumnStandardBasicType<Boolean>
	implements PrimitiveType<Boolean>, DiscriminatorType<Boolean> 
{
	
	public static final YesNoType INSTANCE = new YesNoType();

	public SiNoType() {
		super( CharTypeDescriptor.INSTANCE, null );
	}

	public String getName() {
		return "yes_no";
	}

	public Class getPrimitiveClass() {
		return boolean.class;
	}

	public Boolean stringToObject(String xml) throws Exception {
		return fromString( xml );
	}

	public Serializable getDefaultValue() {
		return Boolean.FALSE;
	}

	@SuppressWarnings({ "UnnecessaryUnboxing" })
	public String objectToSQLString(Boolean value, Dialect dialect) throws Exception {
		return StringType.INSTANCE.objectToSQLString( value.booleanValue() ? "S" : "N", dialect );
	}
	
}
