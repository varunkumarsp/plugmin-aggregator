package org.openxava.annotations.parse;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

public class AddRunTimeAnnotation {


	public static void addMethodLevelAnnotation(String clazz, Class<?> annotation, String methodName) throws Exception {
		// pool creation
		ClassPool pool = ClassPool.getDefault();

		// extracting the class
		CtClass cc = pool.getCtClass(clazz);
		
		// looking for the method to apply the annotation on
		CtMethod method = cc.getDeclaredMethod(methodName);
		
		// create the annotation
		ClassFile ccFile = cc.getClassFile();
		
		ConstPool constpool = ccFile.getConstPool();
		
		AnnotationsAttribute attr = new AnnotationsAttribute(constpool,
				AnnotationsAttribute.visibleTag);
		Annotation annot = new Annotation(annotation.getName(), constpool);
//		annot.addMemberValue("name", new StringMemberValue("World!! (dynamic annotation)", ccFile.getConstPool()));
		attr.addAnnotation(annot);
		
		// add the annotation to the method descriptor
		method.getMethodInfo().addAttribute(attr);

		// transform the ctClass to java class
		Class dynamiqueBeanClass = cc.toClass();
	}
	
	public static Class<?> addClassLevelAnnotation(String clazz, Class<?> annotation) throws Exception {
		// pool creation
		ClassPool pool = ClassPool.getDefault();

		// extracting the class
		CtClass cc = pool.getCtClass(clazz);
		
		// create the annotation
		ClassFile ccFile = cc.getClassFile();
		
		ConstPool constpool = ccFile.getConstPool();
		
		AnnotationsAttribute attr = new AnnotationsAttribute(constpool,
				AnnotationsAttribute.visibleTag);
		Annotation annot = new Annotation(annotation.getCanonicalName(), constpool);
		annot.addMemberValue("name", new StringMemberValue("default", ccFile.getConstPool()));
		attr.addAnnotation(annot);
		
		// add the annotation to the class
		ccFile.addAttribute(attr);

		// transform the ctClass to java class
		Class dynamiqueBeanClass = cc.toClass();
		return dynamiqueBeanClass;
	}
	
	
}