package org.openxava.annotations.extended.ui.config.defaults;

import static org.openxava.annotations.extended.ui.config.enums.SelectableMode.MULTIPLE_ROW;
import static org.openxava.annotations.extended.ui.config.enums.Boolean.TRUE;
import static org.openxava.annotations.extended.ui.config.enums.FilterableMode.ROW;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ColumnMenu;
import org.openxava.annotations.extended.ui.config.TabConfig;
import org.openxava.annotations.extended.ui.config.grid.Editable;
import org.openxava.annotations.extended.ui.config.grid.Filterable;
import org.openxava.annotations.extended.ui.config.grid.FilterableConfig;
import org.openxava.annotations.extended.ui.config.grid.Groupable;
import org.openxava.annotations.extended.ui.config.grid.Pageable;
import org.openxava.annotations.extended.ui.config.grid.Sortable;
import org.openxava.annotations.extended.ui.config.grid.Toolbar;

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
		}
)
public @interface DefaultTabConfig {

}
