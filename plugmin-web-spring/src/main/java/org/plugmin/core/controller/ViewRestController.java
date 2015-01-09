package org.plugmin.core.controller;

import java.util.Map;

import org.openxava.view.meta.MetaView;
import org.plugmin.core.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/view")
public class ViewRestController {

	@Autowired
	ViewService viewService;

	@Autowired
	Environment env;

	@RequestMapping(value = "/read/{entity}", method = RequestMethod.POST)
	@ResponseBody
	public Object read(@PathVariable String entity,
			@RequestParam("view") String view,
			@RequestParam("entity-id") String entityId) throws Exception {
		MetaView metaView = viewService.metaView(entity, view);
		Map<String, Object> result = viewService.read(entityId, metaView);
		return result;
	}

	@RequestMapping(value = "/create/{entity}", method = RequestMethod.POST)
	@ResponseBody
	public Object create(@PathVariable String entity,
			@RequestParam("view") String view,
			@RequestBody Map<String, Object> model) throws Exception {
		MetaView metaView = viewService.metaView(entity, view);
		Map<String, Object> result = viewService.create(model, metaView);
		return result;
	}

	@RequestMapping(value = "/update/{entity}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String entity,
			@RequestParam("view") String view,
			@RequestParam("entity-id") String entityId,
			@RequestBody Map<String, Object> model) throws Exception {
		MetaView metaView = viewService.metaView(entity, view);
		Map<String, Object> result = viewService.update(entityId, model,
				metaView);
		return result;
	}

}
