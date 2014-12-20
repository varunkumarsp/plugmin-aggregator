package org.openxava.hibernate.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
import org.openxava.calculators.IAggregateOidCalculator;
import org.openxava.calculators.ICalculator;
import org.openxava.calculators.IEntityCalculator;
import org.openxava.calculators.IJDBCCalculator;
import org.openxava.calculators.IModelCalculator;
import org.openxava.model.IModel;
import org.openxava.model.meta.MetaCalculator;
import org.openxava.model.meta.MetaModel;
import org.openxava.model.meta.MetaProperty;
import org.openxava.util.DataSourceConnectionProvider;
import org.openxava.util.PropertiesManager;
import org.openxava.util.XavaResources;
import org.openxava.util.meta.MetaSet;

/**
 * Executes the default-value-calculator for key properties.
 * <p>
 * 
 * @author Javier Paniza
 */

public class DefaultValueIdentifierGenerator implements IdentifierGenerator,
		Configurable {

	private static Log log = LogFactory
			.getLog(DefaultValueIdentifierGenerator.class);

	static class AggregateOidInfo {
		public int counter;
		public Object containerKey;
	}

	final private static ThreadLocal currentAggregateOidInfo = new ThreadLocal();

	private String property;

	public Serializable generate(SessionImplementor implementor, Object object)
			throws HibernateException {
		String modelName = "unknow";
		try {
			IModel model = (IModel) object;
			MetaModel metaModel = model.getMetaModel();
			modelName = metaModel.getName();
			MetaProperty pr = (MetaProperty) metaModel
					.getMetaProperty(getProperty());
			PropertiesManager pm = new PropertiesManager(model);
			MetaCalculator metaCalculator = pr.getMetaCalculatorDefaultValue();
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
			if (calculator instanceof IAggregateOidCalculator) {
				((IAggregateOidCalculator) calculator)
						.setContainer(getCurrentContainerKey());
				((IAggregateOidCalculator) calculator)
						.setCounter(getCurrentCounter());
			}
			resetAggregateOidInfo();
			return (Serializable) calculator.calculate();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new HibernateException(XavaResources.getString(
					"entity_create_error", modelName, ex.getLocalizedMessage()));
		}
	}

	static void resetAggregateOidInfo() {
		currentAggregateOidInfo.set(null);
	}

	public String getProperty() {
		return property;
	}

	public void configure(Type type, Properties params, Dialect dialect)
			throws MappingException {
		property = params.getProperty("property");
	}

	static Object getCurrentContainerKey() {
		AggregateOidInfo info = (AggregateOidInfo) currentAggregateOidInfo
				.get();
		if (info == null)
			return null;
		return info.containerKey;
	}

	public static void setCurrentContainerKey(Object containerKey) {
		AggregateOidInfo info = (AggregateOidInfo) currentAggregateOidInfo
				.get();
		if (info == null) {
			info = new AggregateOidInfo();
			currentAggregateOidInfo.set(info);
		}
		info.containerKey = containerKey;
	}

	static int getCurrentCounter() {
		AggregateOidInfo info = (AggregateOidInfo) currentAggregateOidInfo
				.get();
		if (info == null)
			return 0;
		return info.counter;
	}

	public static void setCurrentCounter(int counter) {
		AggregateOidInfo info = (AggregateOidInfo) currentAggregateOidInfo
				.get();
		if (info == null) {
			info = new AggregateOidInfo();
			currentAggregateOidInfo.set(info);
		}
		info.counter = counter;
	}

}
