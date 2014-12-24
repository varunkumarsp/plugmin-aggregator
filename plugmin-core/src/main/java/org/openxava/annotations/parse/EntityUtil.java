package org.openxava.annotations.parse;

import static org.openxava.annotations.parse.FieldDefaultValueResolver.isSimpleType;
import static org.springframework.core.annotation.AnnotationUtils.getAnnotation;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.sql.JoinType;
import org.openxava.model.meta.MetaEntity;

public class EntityUtil {

	public static boolean isForeignEntity_(String field, MetaEntity metaEntity) {
		field = field.split("\\.")[0];
		if(metaEntity.getReferencesNames().contains(field))
			return true;
		if(metaEntity.getColectionsNames().contains(field))
			return true;
		return false;
	}

	/**
	 * Returns true if the field has @Id annotation. Returns false otherwise. No
	 * support for fields with @EmbeddedId, @IdClass or any other id related
	 * annotations.
	 * 
	 * @param metaField
	 * @param metaEntity
	 * @return
	 */
	public static boolean _isIdField(Field metaField, MetaEntity metaEntity) {
		Id id = metaField.getAnnotation(Id.class);
		if (id != null)
			return true;
		return false;
	}

	public static boolean isIdField(String field, Class<?> entity) throws NoSuchFieldException, SecurityException {
		if(!field.contains(".")){
			Field field_ = entity.getDeclaredField(field);
			return field_.isAnnotationPresent(Id.class);
		}
		return false;
	}
	
	/**
	 * Attempts to find a field with @Id annotation. Returns null otherwise. No
	 * support for fields with @EmbeddedId, @IdClass or any other id related
	 * annotations.
	 * 
	 * @param entity
	 * @return id field
	 */
	public static String idField(Class<?> entity) {
		Field[] declaredFields = entity.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getAnnotation(Id.class) != null)
				return field.getName();
		}
		return null;
	}
	
	public static Field idField2(Class<?> entity) {
		Field[] declaredFields = entity.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getAnnotation(Id.class) != null)
				return field;
		}
		return null;
	}

	/**
	 * Attempts to find a field with name equals or endswith or contains the
	 * word "name". If it finds one, then returns that field. Otherwise returns
	 * the id field of that entity. Collection fields and reference fields are
	 * not inspected.
	 * 
	 * @param entity
	 * @return name field
	 */
	public static String nameField(Class<?> entity) {
		Field[] declaredFields = entity.getDeclaredFields();
		for (Field field : declaredFields) {
			if (isSimpleType(field.getType())) {
				if (field.getName().toLowerCase().equals("name")) {
					return field.getName();
				}
			}
		}
		for (Field field : declaredFields) {
			if (isSimpleType(field.getType())) {
				if (field.getName().toLowerCase().endsWith("name")) {
					return field.getName();
				}
			}
		}
		for (Field field : declaredFields) {
			if (isSimpleType(field.getType())) {
				if (field.getName().toLowerCase().contains("name")) {
					return field.getName();
				}
			}
		}
		return idField(entity);
	}

	public static boolean hasCompositePrimaryKey(Class<?> entity) {
		Field[] declaredFields = entity.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getAnnotation(Id.class) != null)
				return false;
		}
		return true;
	}

	public static boolean isManyToMany(String field, Class<?> rootClass)
			throws NoSuchFieldException, SecurityException {
		field = field.split("\\.")[0];
		Field _field = rootClass.getDeclaredField(field);
		ManyToMany manyToMany = _field.getAnnotation(ManyToMany.class);
		if (manyToMany != null)
			return true;
		return false;
	}

	public static boolean isOneToMany(String field, Class<?> rootClass)
			throws NoSuchFieldException, SecurityException {
		field = field.split("\\.")[0];
		Field _field = rootClass.getDeclaredField(field);
		OneToMany oneToMany = _field.getAnnotation(OneToMany.class);
		if (oneToMany != null)
			return true;
		return false;
	}

	public static boolean isManyToOne(String field, Class<?> rootClass)
			throws NoSuchFieldException, SecurityException {
		field = field.split("\\.")[0];
		Field _field = rootClass.getDeclaredField(field);
		ManyToOne manyToOne = _field.getAnnotation(ManyToOne.class);
		if (manyToOne != null)
			return true;
		return false;
	}

	public static boolean isOneToOne(String field, Class<?> rootClass)
			throws NoSuchFieldException, SecurityException {
		field = field.split("\\.")[0];
		Field _field = rootClass.getDeclaredField(field);
		OneToOne oneToOne = _field.getAnnotation(OneToOne.class);
		if (oneToOne != null)
			return true;
		return false;
	}

	public static String createAlias(Criteria criteria, String associationPath) {
		return createAlias(criteria, criteria, criteria.getAlias(),
				associationPath, JoinType.LEFT_OUTER_JOIN.getJoinTypeValue());
	}

	public static String createAlias(Criteria criteria, String associationPath,
			int joinType) {
		return createAlias(criteria, criteria, criteria.getAlias(),
				associationPath, joinType);
	}

	@SuppressWarnings("deprecation")
	private static String createAlias(Criteria rootCriteria,
			Criteria currentSubCriteria, String alias, String associationPath,
			int joinType) {
		StringTokenizer st = new StringTokenizer(associationPath, ".");
		if (st.countTokens() == 1) {
			return alias + "." + associationPath;
		} else {
			String associationPathToken = st.nextToken();
			String newAlias = alias + "_" + associationPathToken;
			Criteria criteriaForAlias = findCriteriaForAlias(rootCriteria,
					newAlias);
			if (criteriaForAlias == null) {
				currentSubCriteria = currentSubCriteria.createCriteria(
						associationPathToken, newAlias, joinType);
			} else {
				currentSubCriteria = criteriaForAlias;
			}

			String newKey = associationPath
					.substring(associationPathToken.length() + 1,
							associationPath.length());
			return createAlias(rootCriteria, currentSubCriteria, newAlias,
					newKey, joinType);
		}
	}

	@SuppressWarnings("rawtypes")
	public static Criteria findCriteriaForAlias(Criteria criteria, String alias) {
		Iterator subcriterias = ((CriteriaImpl) criteria).iterateSubcriteria();
		while (subcriterias.hasNext()) {
			Criteria subcriteria = (Criteria) subcriterias.next();
			if (subcriteria.getAlias().equals(alias)) {
				return subcriteria;
			}
		}
		return null;
	}

	public static String getAssociationPath(String alias) {

		// this_manufacturers2.city
		alias = alias.substring(5).replaceAll("_", ".");
		return alias;
	}

	public static int randomInt() {
		Random r = new Random(System.currentTimeMillis());
		return (1 + r.nextInt(2)) * 10000 + r.nextInt(10000);
	}

	@SuppressWarnings("unchecked")
	public static Object modelToEntityOld(Map<String, Object> model, Class<?> entity) throws Exception {
		Object instance = entity.newInstance();
		
		for(String field : model.keySet()) {
			if(field.contains("_") ) {
				String[] split = field.split("_");
				
				if(split.length == 2) { // > 2 => non-editable, no need to persist. 
					Field declaredField = entity.getDeclaredField(split[0]);
					Object value = model.get(field);
					
					Class<?> foreignEntity = declaredField.getType();
					
					Map<String, Object> map = new HashMap<String, Object>(1);
					map.put(split[1], value);
					
					value = modelToEntity(map, foreignEntity);
					
					declaredField.set(instance, value);
				}
			} else {
				Field declaredField = entity.getDeclaredField(field);
				Object value = model.get(field);
				
				if(value instanceof Map) {
					Class<?> foreignEntity = declaredField.getType();
					value = modelToEntity((Map<String, Object>) value, foreignEntity);
				}
				
				declaredField.set(instance, cast(value, declaredField.getType()));
			}
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public static Object modelToEntity(Map<String, Object> model, Class<?> entity) throws Exception {
		Object instance = entity.newInstance();
		
		model = reConfigure(model);
		
		for(String field : model.keySet()) {
			Field declaredField = entity.getDeclaredField(field);
			Object value = model.get(field);
			
			if(value instanceof Map) {
				Class<?> foreignEntity = declaredField.getType();
				value = modelToEntity((Map<String, Object>) value, foreignEntity);
			}
			
//			declaredField.set(instance, cast(value, declaredField.getType()));
			BeanUtils.setProperty(instance, declaredField.getName(), cast(value, declaredField.getType())); //invokes setter to set the value. So that logic within setter method could be executed.
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public static void copyProperties(Map<String, Object> rootEntity, Object entity, Session session) throws Exception {
		for(String key : rootEntity.keySet()) {
			String[] split = key.split("_");
			
			Object foreignEntity = entity;
			Class<?> foreignEntityClz = entity.getClass();
			for (int i = 0; i < split.length - 1; i++) {
				Field field = foreignEntityClz.getDeclaredField(split[i]);
				Class<?> foreignEntityClz_ = field.getType();
				Object foreignEntity_ = field.get(foreignEntity);
				
				Object unproxiedEntity = initializeAndUnproxy(foreignEntity_);
				field.set(foreignEntity, unproxiedEntity);

				foreignEntityClz = foreignEntityClz_;
				foreignEntity = unproxiedEntity;
			}
			
			Field field = foreignEntityClz.getDeclaredField(split[split.length - 1]);
			
			Object value = rootEntity.get(key);
			if(value instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) value;
				
				Class<?> foreignEntityClass = field.getType();
				
				String idField = idField(foreignEntityClass);
				value = map.get(idField);
				
				if(value == null) { // => null reference
					entity.getClass().getDeclaredField(key).set(entity, null);
					continue;
				}

				foreignEntity = foreignEntityClass.newInstance();
				entity.getClass().getDeclaredField(key).set(entity, foreignEntity);
				
				field = foreignEntityClass.getDeclaredField(idField);
			}
			value = cast(value, field.getType());
			if(value != null) {
//				field.set(foreignEntity, value);
				BeanUtils.setProperty(foreignEntity, field.getName(), value); //invokes setter to set the value. So that logic within setter method could be executed.
			}
				
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T initializeAndUnproxy(T entity) {
		if (entity == null) {
			return null;
		}

		if (entity instanceof HibernateProxy) {
			Hibernate.initialize(entity);
			entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> reConfigure(Map<String, Object> rootEntity) {
		Map<String, Object> _rootEntity = new HashMap<String, Object>();

		for(String key : rootEntity.keySet()) {
			String[] split = key.split("_");

			Map<String, Object> entity = _rootEntity;
			
			for (int i = 0; i < split.length - 1; i++) {
				Map<String, Object> _entity = (Map<String, Object>) entity.get(split[i]);
				if(_entity == null) {
					_entity = new HashMap<String, Object>();
					entity.put(split[i], _entity);
				}
				entity = _entity;
			}
			
			entity.put(split[split.length - 1], rootEntity.get(key));
		}
		return _rootEntity;
	}

	public static Object cast(Object value, Class<?> type) throws ParseException {
		if(value instanceof String && StringUtils.isEmpty((String) value))
			value = null;
		if(value != null) {
			if (type == double.class || type == Double.class) {
	            value = Double.parseDouble(value.toString());
	        } else if (type == float.class || type == Float.class) {
	            value = Float.parseFloat(value.toString());
	        } else if (type == long.class || type == Long.class) {
	            value = Long.parseLong(value.toString());
	        } else if (type == int.class || type == Integer.class) {
	            value = Integer.parseInt(value.toString());
	        } else if (type == short.class || type == Short.class) {
	            value = Short.parseShort(value.toString());
	        } else if (type == boolean.class || type == Boolean.class) {
	            value = Boolean.parseBoolean(value.toString());
	        } else if (type == Date.class){
	            SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" );
	            String input = value.toString();
	            value = df.parse(input);
	        } else if (type == Calendar.class){
	            SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" );
	            String input = value.toString();
	            Date date = df.parse(input);
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(date);
	            value = cal;
	        } else if (type == BigDecimal.class){
	            value = BigDecimal.valueOf(Double.parseDouble(value.toString()));
	        } else if (type == BigInteger.class){
	            value = BigInteger.valueOf(Integer.parseInt(value.toString()));
	        }
		}
		return value;
	}


	public static boolean isNullable(String field, Class<?> entity) throws NoSuchFieldException, SecurityException {
		Field metaField = entity.getDeclaredField(field);
		
		Column column = getAnnotation(metaField, Column.class);
		if(column != null) {
			return column.nullable();
		}
		
		ManyToOne manyToOne = getAnnotation(metaField, ManyToOne.class);
		if(manyToOne != null) {
			return manyToOne.optional();
		}
		
		return true;
	}
}
