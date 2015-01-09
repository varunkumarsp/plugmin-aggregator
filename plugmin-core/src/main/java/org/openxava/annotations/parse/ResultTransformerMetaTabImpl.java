package org.openxava.annotations.parse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

public class ResultTransformerMetaTabImpl implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	
	private FieldResolver fieldResolver;
	

	public ResultTransformerMetaTabImpl(FieldResolver fieldResolver) {
		this.fieldResolver = fieldResolver;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map result = new HashMap(tuple.length);
		for ( int i = 0; i < tuple.length; i++ ) {
			String alias = aliases[i];
			if ( alias!=null ) {
				result.put( alias, tuple[i] );
			}
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List transformList(List collection) {
		List<Object> _data = new LinkedList<Object>();
		
		for (Object item : collection) {
			Map<String, Object> rootEntity = (Map<String, Object>) item;
			Map<String, Object> _rootEntity = new HashMap<String, Object>();

			for(String key : rootEntity.keySet()) {
				if(fieldResolver.isAssociatedEntity(key)) {
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
				} else {
					_rootEntity.put(key, rootEntity.get(key));
				}
			}
			
			_data.add(_rootEntity);
		}
		return _data;
	}

}
