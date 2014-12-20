package org.plugmin.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openxava.tab.meta.MetaTab;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;
import org.plugmin.core.service.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/grid")
public class GridRestController {

	@Autowired
	GridService gridService;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	Environment env;
	
	
	@RequestMapping(value = "/create/{entity}", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> create(@PathVariable String entity, @RequestParam("view") String view, @RequestBody Map<String, Object> model) throws Exception {
		MetaTab metaTab = gridService.metaTab(entity, view);
		List<Map<String, Object>> models = new ArrayList<Map<String,Object>>();
		models.add(model);
		gridService.create(models, metaTab);
		return models;
	}
	
	@RequestMapping(value = "/batch/create/{entity}", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> batchCreate(@PathVariable String entity, @RequestParam("view") String view, @RequestBody List<Map<String, Object>> models) throws Exception {
		MetaTab metaTab = gridService.metaTab(entity, view);
		gridService.create(models, metaTab);
		return models;
	}
	
	@RequestMapping(value = "/read/{entity}", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public DataSourceResult read(@PathVariable String entity, @RequestParam("view") String view, @RequestBody DataSourceRequest req) throws Exception {
		MetaTab metaTab = gridService.metaTab(entity, view);
		DataSourceResult res = gridService.read(req, metaTab);
		return res;
	}
	
	@RequestMapping(value = "/update/{entity}", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> update(@PathVariable String entity, @RequestParam("view") String view, @RequestBody Map<String, Object> model) throws Exception {
		MetaTab metaTab = gridService.metaTab(entity, view);
		List<Map<String, Object>> models = new ArrayList<Map<String,Object>>();
		models.add(model);
		gridService.update(models, metaTab);
		return models;
	}
	
	@RequestMapping(value = "/batch/update/{entity}", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> batchUpdate(@PathVariable String entity, @RequestParam("view") String view, @RequestBody List<Map<String, Object>> models) throws Exception {
		MetaTab metaTab = gridService.metaTab(entity, view);
		gridService.update(models, metaTab);
		return models;
	}
	
	@RequestMapping(value = "/remove/{entity}", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> delete(@PathVariable String entity, @RequestParam("view") String view, @RequestBody Map<String, Object> model) throws Exception {
		MetaTab metaTab = gridService.metaTab(entity, view);
		List<Map<String, Object>> models = new ArrayList<Map<String,Object>>();
		models.add(model);
		gridService.delete(models, metaTab);
		return models;
	}
	
	@RequestMapping(value = "/batch/remove/{entity}", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> batchDelete(@PathVariable String entity, @RequestParam("view") String view, @RequestBody List<Map<String, Object>> models) throws Exception {
		MetaTab metaTab = gridService.metaTab(entity, view);
		gridService.delete(models, metaTab);
		return models;
	}

}
