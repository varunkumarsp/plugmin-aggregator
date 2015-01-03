package org.plugmin.core.config.context;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.openxava.annotations.extended.ui.config.vo.PlugminEventListener;
import org.plugmin.core.dao.AutocompleteDao;
import org.plugmin.core.dao.AutocompleteDaoHibernateImpl;
import org.plugmin.core.dao.AutocompleteDaoJpaImpl;
import org.plugmin.core.dao.DropDownBoxDao;
import org.plugmin.core.dao.DropDownBoxDaoHibernateImpl;
import org.plugmin.core.dao.DropDownBoxDaoJpaImpl;
import org.plugmin.core.dao.GridDao;
import org.plugmin.core.dao.GridDaoHibernateImpl;
import org.plugmin.core.dao.GridDaoJpaImpl;
import org.plugmin.core.dao.MetaComponentDao;
import org.plugmin.core.dao.MetaComponentDaoHibernateImpl;
import org.plugmin.core.dao.MetaComponentDaoJpaImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@ComponentScan("org.plugmin.core.dao")
@EnableTransactionManagement
public class PlugminDaoConfiguration implements TransactionManagementConfigurer, BeanFactoryAware {

	@Autowired(required = false)
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired(required = false)
    private SessionFactory sessionFactory;
    
	private BeanFactory parentBeanFactory;
	

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
    	PlatformTransactionManager txnMgr = null;
    	if(entityManagerFactory != null) {
    		txnMgr = new JpaTransactionManager(entityManagerFactory);
    	} else {
    		txnMgr = new HibernateTransactionManager(sessionFactory);
    	}
    	return txnMgr;
    }
    
    @Bean
    public MetaComponentDao metaComponentDao() {
    	MetaComponentDao metaComponentDao;
		if(entityManagerFactory != null) {
			metaComponentDao = new MetaComponentDaoJpaImpl();
		} else {
			metaComponentDao = new MetaComponentDaoHibernateImpl();
		}
        return metaComponentDao;
    }
    
    @Bean
    public DropDownBoxDao dropDownBoxDao() {
		DropDownBoxDao dropDownBoxDao;
		if(entityManagerFactory != null) {
			dropDownBoxDao = new DropDownBoxDaoJpaImpl();
		} else {
			dropDownBoxDao = new DropDownBoxDaoHibernateImpl(sessionFactory);
		}
        return dropDownBoxDao;
    }
    
    @Bean
    public AutocompleteDao autocompleteDao() {
    	AutocompleteDao autocompleteDao;
		if(entityManagerFactory != null) {
			autocompleteDao = new AutocompleteDaoJpaImpl();
		} else {
			autocompleteDao = new AutocompleteDaoHibernateImpl(sessionFactory);
		}
        return autocompleteDao;
    }
	
	@Bean
    public GridDao gridDao() {
		GridDao gridDao;
		if(entityManagerFactory != null) {
			gridDao = new GridDaoJpaImpl();
		} else {
			gridDao = new GridDaoHibernateImpl(sessionFactory);
		}
		
		PlugminEventListener plugminEventListener = null;
		try {
			plugminEventListener = parentBeanFactory.getBean(PlugminEventListener.class);
		} catch (NoSuchBeanDefinitionException e) {
		}
		gridDao.setEventListener(plugminEventListener);
		
        return gridDao;
    }

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		DefaultListableBeanFactory bf = (DefaultListableBeanFactory) beanFactory;
		parentBeanFactory = bf.getParentBeanFactory();
	}
	
}