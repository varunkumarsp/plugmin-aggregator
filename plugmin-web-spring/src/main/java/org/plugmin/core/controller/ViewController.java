package org.plugmin.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openxava.view.meta.MetaCollectionView;
import org.openxava.view.meta.MetaView;
import org.plugmin.core.service.ViewService;
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
@RequestMapping("/view")
public class ViewController {

	private static final Logger logger = LoggerFactory
			.getLogger(ViewController.class);

	@Autowired
	ViewService viewService;

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{entity}", method = RequestMethod.GET)
	public String view(
			@PathVariable String entity,
			@RequestParam(value = "view", defaultValue = "default") String view,
			@RequestParam(value = "entity-id") String entityId, ModelMap model,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		model.addAttribute("entity", entity);
		model.addAttribute("view", view);
		model.addAttribute("entityId", entityId);

		MetaView metaView = viewService.metaView(entity, view);
		model.addAttribute("metaView", metaView);
		
		List<MetaView> metaViews = new ArrayList<MetaView>();
		metaViews.add(metaView);
		
		List<MetaView> sections_ = metaView.getSections();
		if(sections_ != null)
			metaViews.addAll(sections_);
		model.addAttribute("metaViews", metaViews);
		
		Map<String, MetaCollectionView> metaCollectionViews = metaView.getMetaCollectionViews();
		if(metaCollectionViews != null)
			model.addAttribute("metaCollectionViews", metaCollectionViews.values());

		return "view-index";
	}

}