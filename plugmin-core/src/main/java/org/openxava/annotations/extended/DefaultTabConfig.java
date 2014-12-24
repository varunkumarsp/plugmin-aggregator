package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.Selectable.MULTIPLE_ROW;
import static org.openxava.annotations.extended.FilterableMode.ROW;
import static org.openxava.annotations.extended.Boolean.TRUE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@TabConfig(
		mobileBool = TRUE,
		navigatable = TRUE,
		reorderable = TRUE,
		resizable = TRUE,
		selectableStr = MULTIPLE_ROW,
		filterable = @Filterable(
			filterable = TRUE, 
			config = @FilterableConfig(mode = ROW)
		),
		columnMenu = @ColumnMenu(isEnabled = TRUE),
		editable = @Editable(editable = TRUE),
		sortable = @Sortable(sortable = TRUE),
		pageable = @Pageable(pageable = TRUE),
		groupable = @Groupable(groupable = TRUE),
		height = "600px",
		toolbarArray = {
			@Toolbar(name = "save", text = "Save"),
			@Toolbar(name = "create", text = "Add new"),
			@Toolbar(name = "cancel", text = "Cancel changes")
		},
		forTabs = {}
)
public @interface DefaultTabConfig {

	String[] forTabs();

}
