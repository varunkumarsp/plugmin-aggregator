package org.openxava.annotations.parse;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class ReflectionUtil {

	// This method traverses the object graph to find the read method
	public static Method getReadMethod(String field, Class<?> clazz)
			throws IntrospectionException {
		Class<?> visitClass = clazz;
		String[] split = field.split("\\.");
		for (int i = 0; i < split.length - 1; i++) {
			PropertyDescriptor prop = new PropertyDescriptor(split[i], visitClass);
			visitClass = prop.getPropertyType();
		}
		Method accessor = new PropertyDescriptor(split[split.length - 1],
				visitClass).getReadMethod();
		return accessor;
	}
	
	// This method traverses the object graph to find the write method
	public static Method getWriteMethod(String field, Class<?> clazz) throws IntrospectionException {
		Class<?> visitClass = clazz;
		String[] split = field.split("\\.");
		for (int i = 0; i < split.length - 1; i++) {
			PropertyDescriptor prop = new PropertyDescriptor(split[i], visitClass);
			visitClass = prop.getPropertyType();
		}
		Method accessor = new PropertyDescriptor(split[split.length - 1],
				visitClass).getWriteMethod();
		return accessor;
	}

	// Recursively finds a field type
	public static Class<?> fieldType(String field, Class<?> clazz)
			throws IntrospectionException {
		Class<?> visitClass = clazz;
		String[] split = field.split("\\.");
		for (String token : split) {
			PropertyDescriptor property = new PropertyDescriptor(token, visitClass);
			visitClass = property.getPropertyType();
		}
		return visitClass;
	}
	
	public static Field fieldReflection(String field, Class<?> clazz)
			throws IntrospectionException, NoSuchFieldException, SecurityException {
		Class<?> visitClass = clazz;
		String[] split = field.split("\\.");
		for (int i = 0; i < split.length - 1; i++) {
			PropertyDescriptor prop = new PropertyDescriptor(split[i], visitClass);
			visitClass = prop.getPropertyType();
		}
		Field field2 = visitClass.getDeclaredField(split[split.length - 1]);
		return field2;
	}

	public static boolean calledFrom(Class<?> clazz) {
		boolean valid = false;
				
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		StackTraceElement methodCaller = stackTraceElements[3];
		try {
			Class<?> callerClass = Class.forName(methodCaller.getClassName());
			String callerMethod = methodCaller.getMethodName();
			
			if(callerClass.equals(clazz) && callerMethod.startsWith("get") && callerMethod.endsWith("_"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}
	
	public static boolean isCollection(String fieldName, Class<?> clazz) throws NoSuchFieldException, SecurityException {
		if(fieldName.contains("."))
			return false;
		Field field = clazz.getDeclaredField(fieldName);
		Class<?> type = field.getType();
		if(type.isArray() || Collection.class.isAssignableFrom(type)) {
			return field.getGenericType() instanceof ParameterizedType;
		}
		return false;
	}
}
