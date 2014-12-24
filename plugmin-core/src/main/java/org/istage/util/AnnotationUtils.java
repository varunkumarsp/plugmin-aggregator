package org.istage.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AnnotationUtils {

	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T[] getAnnotationsByType(
			AnnotatedElement ae, Class<T> annotationType) {
		/**
		 * JDK8 provides a method like below to achieve the same. But, inorder to provide backwards compatibility,
		 * the following code is commented out. 
		 */
//		T[] anns = metaField.getAnnotationsByType(annotationType);
		
		List<T> anns = new ArrayList<T>();
		
		for (Annotation metaAnn : ae.getDeclaredAnnotations()) {
			if(metaAnn.annotationType().equals(annotationType)) {
				anns.add((T) metaAnn);
			} else {
				T ann = metaAnn.annotationType().getAnnotation(annotationType);
				if (ann != null) {
					anns.add(ann);
				}
			}
		}

		T[] arr = (T[]) Array.newInstance(annotationType, anns.size());
		return anns.toArray(arr);
	}

}
