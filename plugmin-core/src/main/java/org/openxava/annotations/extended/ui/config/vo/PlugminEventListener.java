package org.openxava.annotations.extended.ui.config.vo;

import org.hibernate.Session;

public interface PlugminEventListener {

	public void prePersist(Object entity, String view, Session session);

	public void postPersist(Object entity, String view, Session session);
	
	
	public void preUpdate(Object entity, String view, Session session);

	public void postUpdate(Object entity, String view, Session session);
	
	
	public void preDelete(Object entity, String view, Session session);
	
	public void postDelete(Object entity, String view, Session session);
	
}
