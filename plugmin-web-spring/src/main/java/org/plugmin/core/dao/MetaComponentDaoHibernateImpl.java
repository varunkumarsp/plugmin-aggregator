package org.plugmin.core.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.openxava.annotations.parse.AnnotatedClassParser;
import org.springframework.beans.factory.annotation.Autowired;

public class MetaComponentDaoHibernateImpl implements MetaComponentDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public Collection<String> init() {
		Collection<String> managedClasses = managedClasses();
		AnnotatedClassParser.setManagedClassNames(managedClasses);
		return managedClasses;
	}
	
	private Collection<String> managedClasses() {
		Collection<String> managedClassNames = new ArrayList<String>();
		Map<String, ClassMetadata> allClassMetadata = sessionFactory.getAllClassMetadata();
		for (String entity : allClassMetadata.keySet()) {
			ClassMetadata metadata = allClassMetadata.get(entity);
			String className = metadata.getMappedClass().getName();
			managedClassNames.add(className);
		}
		return managedClassNames;
	}
	
}
