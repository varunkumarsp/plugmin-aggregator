package org.plugmin.core.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openxava.annotations.parse.EntityUtil;
import org.openxava.model.meta.MetaProperty;
import org.openxava.tab.meta.MetaTab;
import org.plugmin.core.service.GridService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/grid")
public class GridController {

	private static final Logger logger = LoggerFactory.getLogger(GridController.class);
	
	@Autowired
	GridService gridService;
	
	private static final List<String> HTML_CLASS_ATTRS = new LinkedList<String>();
	
	static {
		HTML_CLASS_ATTRS.add("admin-moonlight");
		HTML_CLASS_ATTRS.add("admin-blueopal");
		HTML_CLASS_ATTRS.add("admin-black");
		HTML_CLASS_ATTRS.add("admin-uniform");
		HTML_CLASS_ATTRS.add("admin-metroblack");
	}
	
	@RequestMapping(value = "/{entity}", method = RequestMethod.GET)
	public String grid(@PathVariable String entity, @RequestParam(value = "view", defaultValue = "default") String view,	ModelMap model, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		model.addAttribute("entity", entity);
		model.addAttribute("view", view);
		
		MetaTab metaTab = gridService.metaTab(entity, view);
		model.addAttribute("metaTab", metaTab);
		
		List<MetaProperty> metaProperties = gridService.metaProperties(entity, view);
		model.addAttribute("metaProperties", metaProperties);
		
//		return "indianstage/grid-view/index-new";
		return "grid-index";
	}

	@RequestMapping(value = "/detail-template/{entity}", method = RequestMethod.GET)
	public String detailTemplate(@PathVariable String entity, @RequestParam(value = "view", defaultValue = "default") String view, @RequestParam("drill-depth") int drillDepth,	ModelMap model, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		model.addAttribute("entity", entity);
		model.addAttribute("view", view);
		
		MetaTab metaTab = gridService.metaTab(entity, view);
		model.addAttribute("metaTab", metaTab);
		model.addAttribute("entityId", EntityUtil.idField(metaTab.getMetaModel().getPOJOClass()));
		
		List<MetaProperty> metaProperties = gridService.metaProperties(entity, view);
		model.addAttribute("metaProperties", metaProperties);

		model.addAttribute("htmlClassAttribute", HTML_CLASS_ATTRS.get(drillDepth));
		model.addAttribute("drillDepth", drillDepth + 1);
		
		gridService.configureViewSection(metaTab);

		return "grid-detail-template";
	}

}