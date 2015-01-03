package org.openxava.annotations.parse;

import org.openxava.annotations.extended.ui.config.vo.ColumnVo;

public class ColumnBuilder {

	private ColumnVo column;

	public ColumnBuilder(ColumnVo columnVo) {
		this.column = columnVo;
	}

	public void build() {
//		MetaEntity metaEntity = column.getMetaElement().getMetaComponent().getMetaEntity();
//		String field = column.getField();
//		
//		Field metaField;
//		if(isForeignEntity_(field, metaEntity)) {
//			if(isManyToOne(field, metaEntity.getPOJOClass())) {
//				column.setTitle(field);
//				column.setField(field);
//				if(field.contains(".")) {
//					metaField = fieldReflection(field, metaEntity.getPOJOClass());
//					init(metaField, metaComponents);
//					column.setEditable(false);
//				} else {
//					String nameField = nameField(metaEntity.getPOJOClass().getDeclaredField(field).getType());
//					metaField = fieldReflection(field + "." + nameField, metaEntity.getPOJOClass());
//					column.setMetaField(metaField);
//					column.setForeignEntity(true);
//					configureManyToOneColumn(field, metaElement, metaComponents);
//				}
//			} else if(isOneToOne(field, metaEntity.getPOJOClass())){
//				if(field.contains(".")) {
//					column.setTitle(field);
//					column.setField(field);
//					init(metaField, metaComponents);
//					configureOneToOneColumn(field, metaElement, metaComponents);
//				} else {
//					String nameField = nameField(metaEntity.getPOJOClass().getDeclaredField(field).getType());
//					column.metaField = fieldReflection(field + "." + nameField, metaEntity.getPOJOClass());
//					column.setTitle(field);
//					field = field + "." + nameField;
//					column.setField(field);
//					init(metaField, metaComponents);
//					configureOneToOneColumn(field, metaElement, metaComponents);
//				}
//			} else if(isOneToMany(field, metaEntity.getPOJOClass())){
////				configureOneToManyColumn(field, metaElement, metaComponents);
//			} else if(isManyToMany(field, metaEntity.getPOJOClass())){
////				configureManyToManyColumn(field, metaElement, metaComponents);
//			}
//		} else {
//			column.setTitle(field);
//			column.setField(field);
//			init(metaField, metaComponents);
//			if(_isIdField(metaField, metaEntity))
//				configureIdColumn();
//		}
	}

	public ColumnVo getColumn() {
		return column;
	}

	public void setColumn(ColumnVo column) {
		this.column = column;
	}

}
