package org.plugmin.core.controller;

import org.openxava.autocomplete.MetaAutocomplete;
import org.plugmin.core.service.AutocompleteService;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rest/autocomplete")
public class AutocompleteRestController {

	@Autowired
	AutocompleteService autocompleteService;
	
	
	@RequestMapping(value = "/read/{entity}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public DataSourceResult manufacturers(@PathVariable String entity, @RequestParam("view") String view, @RequestBody DataSourceRequest req, DataSourceResult res) throws Exception {
		MetaAutocomplete metaAutocomplete = autocompleteService.metaAutocomplete(entity, view);
		return autocompleteService.getList(req, metaAutocomplete);
	}
	

}
