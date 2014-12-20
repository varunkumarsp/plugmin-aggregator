package org.openxava.hibernate.impl;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.openxava.calculators.ICalculator;
import org.openxava.calculators.IEntityCalculator;
import org.openxava.calculators.IJDBCCalculator;
import org.openxava.calculators.IModelCalculator;
import org.openxava.model.IModel;
import org.openxava.model.meta.MetaCalculator;
import org.openxava.model.meta.MetaModel;
import org.openxava.util.DataSourceConnectionProvider;
import org.openxava.util.PropertiesManager;
import org.openxava.util.XavaResources;
import org.openxava.util.meta.MetaSet;

public class CalculatorsListener implements PreInsertEventListener,
		PreUpdateEventListener, PreDeleteEventListener, PostLoadEventListener {

	private static Log log = LogFactory.getLog(CalculatorsListener.class);
	private static CalculatorsListener instance = new CalculatorsListener();
	final private static ThreadLocal setOffForCurrentThread = new ThreadLocal();

	public static CalculatorsListener getInstance() {
		return instance;
	}

	public CalculatorsListener() {
	}

	public void onPostLoad(PostLoadEvent ev) {
		if (isOff())
			return;
		String modelName = "unknow";
		try {
			Object entity = ev.getEntity();
			if (!(entity instanceof IModel))
				return;
			IModel model = (IModel) entity;
			executeCalculators(model, model.getMetaModel()
					.getMetaCalculatorsPostLoad());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new HibernateException(XavaResources.getString(
					"entity_load_error", modelName, ex.getLocalizedMessage()));
		}
	}

	public boolean onPreInsert(PreInsertEvent ev) {
		String modelName = "unknow";
		try {
			Object entity = ev.getEntity();
			if (!(entity instanceof IModel))
				return false;
			IModel model = (IModel) entity;
			executeCalculators(model, model.getMetaModel()
					.getMetaCalculatorsPostCreate());
			return false;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new HibernateException(XavaResources.getString(
					"entity_create_error", modelName, ex.getLocalizedMessage()));
		}
	}

	public boolean onPreUpdate(PreUpdateEvent ev) {
		String modelName = "unknow";
		try {
			Object entity = ev.getEntity();
			if (!(entity instanceof IModel))
				return false;
			IModel model = (IModel) entity;
			executeCalculators(model, model.getMetaModel()
					.getMetaCalculatorsPostModify());
			return false;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new HibernateException(XavaResources.getString(
					"entity_modify_error", modelName, ex.getLocalizedMessage()));
		}
	}

	public boolean onPreDelete(PreDeleteEvent ev) {
		String modelName = "unknow";
		try {
			Object entity = ev.getEntity();
			if (!(entity instanceof IModel))
				return false;
			IModel model = (IModel) entity;
			executeCalculators(model, model.getMetaModel()
					.getMetaCalculatorsPreRemove());
			return false;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new HibernateException(XavaResources.getString(
					"entity_remove_error", modelName, ex.getLocalizedMessage()));
		}
	}

	private void executeCalculators(IModel model, Collection calculators)
			throws Exception {
		if (isOff())
			return;
		MetaModel metaModel = model.getMetaModel();
		if (calculators.isEmpty())
			return;
		PropertiesManager pm = new PropertiesManager(model);
		for (Iterator it = calculators.iterator(); it.hasNext();) {
			MetaCalculator metaCalculator = (MetaCalculator) it.next();
			ICalculator calculator = metaCalculator.createCalculator();
			PropertiesManager pmCalculator = new PropertiesManager(calculator);
			for (Iterator itSets = metaCalculator.getMetaSetsWithoutValue()
					.iterator(); itSets.hasNext();) {
				MetaSet set = (MetaSet) itSets.next();
				pmCalculator.executeSet(set.getPropertyName(),
						pm.executeGet(set.getPropertyNameFrom()));
			}
			if (calculator instanceof IJDBCCalculator) {
				((IJDBCCalculator) calculator)
						.setConnectionProvider(DataSourceConnectionProvider
								.getByComponent(metaModel.getMetaComponent()
										.getName()));
			}
			if (calculator instanceof IModelCalculator) {
				((IModelCalculator) calculator).setModel(model);
			}
			if (calculator instanceof IEntityCalculator) {
				((IEntityCalculator) calculator).setEntity(model);
			}
			calculator.calculate();
		}
	}

	public static void setOffForCurrentThread() {
		setOffForCurrentThread.set(Boolean.TRUE);
	}

	public static void setOnForCurrentThread() {
		setOffForCurrentThread.set(null);
	}

	private static boolean isOff() {
		return setOffForCurrentThread.get() != null;
	}

}
