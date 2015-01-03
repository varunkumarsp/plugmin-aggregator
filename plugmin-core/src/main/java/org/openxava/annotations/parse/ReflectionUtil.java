package org.openxava.annotations.parse;

import static org.istage.util.AnnotationUtils.getAnnotationsByType;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import org.openxava.tab.meta.MetaTab;
import org.openxava.view.meta.MetaView;

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
	
	public static boolean isCollection(Field field) {
		return Collection.class.isAssignableFrom(field.getType()) || field.getType().isArray();
	}
	
	public static boolean isCollection(String fieldName, Class<?> clazz) throws NoSuchFieldException, SecurityException {
		if(fieldName.contains("."))
			return false;
		Field field = clazz.getDeclaredField(fieldName);
		if(isCollection(field)) {
			return field.getGenericType() instanceof ParameterizedType;
		}
		return false;
	}
	
	public static <T extends Annotation> T findAnnotation(Class<T> annotation, Field metaField, MetaTab metaTab) throws Exception {
		return findAnnotation(annotation, metaField, metaTab.getName(), "forTab", "forTabs");
	}
	
	public static <T extends Annotation> T findAnnotation(Class<T> annotation, Field metaField, MetaView metaView) throws Exception {
		return findAnnotation(annotation, metaField, metaView.getName(), "forView", "forViews");
	}
	
	private static <T extends Annotation> T findAnnotation(Class<T> annotation, Field metaField, String elementName, String singularMethod, String pluralMethod) throws Exception {
		T[] anns = getAnnotationsByType(metaField, annotation);
		for (T ann : anns) {
			Class<? extends Annotation> annotationType = ann.annotationType();
			
			try {
				Method declaredMethod = annotationType.getDeclaredMethod(singularMethod);
				String name = (String) declaredMethod.invoke(ann);
				if(name.equals(elementName) || name.isEmpty()) { //empty means for all tabs or views
					return ann;
				}
			} catch (Exception e) {
			}
			
			try {
				Method declaredMethod = annotationType.getDeclaredMethod(pluralMethod);
				String[] names = (String[]) declaredMethod.invoke(ann);
				if(names.length == 0) //which means for all tabs or views
					return ann;
				
				for (String name : names) {
					if(name.equals(elementName)) {
						return ann;
					}
				}
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T findWidget(Class<? extends Annotation> wrapper,
			Class<T> type, String widgetName, Class<?> entity) throws Exception {
		Annotation ann = entity.getAnnotation(wrapper);
		if(ann != null) {
			Class<? extends Annotation> annotationType = ann.annotationType();
			
			Method declaredMethod = annotationType.getDeclaredMethod("value");
			T[] types = (T[]) declaredMethod.invoke(ann);
			for (T t : types) {
				declaredMethod = t.getClass().getDeclaredMethod("name");
				String name = (String) declaredMethod.invoke(t);
				if(name.equals(widgetName))
					return t;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T findWidgetConfig(Class<? extends Annotation> wrapper,
			Class<T> type, String widgetConfigName, Class<?> entity) throws Exception {
		Annotation ann = entity.getAnnotation(wrapper);
		if(ann != null) {
			Class<? extends Annotation> annotationType = ann.annotationType();
			
			Method declaredMethod = annotationType.getDeclaredMethod("value");
			T[] types = (T[]) declaredMethod.invoke(ann);
			for (T t : types) {
				declaredMethod = t.getClass().getDeclaredMethod("name");
				String name = (String) declaredMethod.invoke(t);
				if(name.equals(widgetConfigName))
					return t;
			}
		}
		return null;
	}
}
