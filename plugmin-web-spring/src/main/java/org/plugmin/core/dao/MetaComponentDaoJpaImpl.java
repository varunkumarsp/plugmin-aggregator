package org.plugmin.core.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.ManagedType;

import org.openxava.annotations.parse.AnnotatedClassParser;

public class MetaComponentDaoJpaImpl implements MetaComponentDao {

	@PersistenceContext
	EntityManager entitymanager;

	
	@Override
	public Collection<String> init() {
		Collection<String> managedClasses = managedClasses();
		AnnotatedClassParser.setManagedClassNames(managedClasses);
		return managedClasses;
	}
	
	private Collection<String> managedClasses() {
		Collection<String> managedClassNames = new ArrayList<String>();
		for (ManagedType<?> t: entitymanager.getMetamodel().getManagedTypes()) {
			String className = t.getJavaType().getName();
			managedClassNames.add(className);
		}
		return managedClassNames;
	}
	
}
