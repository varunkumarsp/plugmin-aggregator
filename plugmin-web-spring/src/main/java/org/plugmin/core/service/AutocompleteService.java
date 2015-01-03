package org.plugmin.core.service;

import org.openxava.autocomplete.MetaAutocomplete;

public interface AutocompleteService {


	DataSourceResult getList(DataSourceRequest req, MetaAutocomplete metaAutocomplete);

	MetaAutocomplete metaAutocomplete(String entity, String view) throws Exception;

}
