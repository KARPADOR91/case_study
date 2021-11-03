package com.cognizant.truyum.controller;

import java.util.List;

import javax.validation.Valid;

import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.model.Category;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.CategoryService;
import com.cognizant.truyum.service.MenuItemService;

@Controller
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
	
	@Autowired
	private CategoryService categoryService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

	@GetMapping(value = "/show-menu-list-admin")
	public String showMenuItemListAdmin(ModelMap model) throws SystemException {
		LOGGER.info("Start showMenuItemListAdmin() of MenuItemController");
		model.addAttribute("menuItemList", menuItemService.getMenuItemListAdmin());
		LOGGER.info("End showMenuItemListAdmin() of MenuItemController");		
		return "menu-item-list-admin";
	}
	
	@GetMapping(value = "/show-menu-list-customer")
	public String showMenuItemListCustomer(ModelMap model) throws SystemException {
		LOGGER.info("Start showMenuItemListCustomer() of MenuItemController");
		model.addAttribute("menuItemList", menuItemService.getMenuItemListCustomer());
		LOGGER.info("End showMenuItemListCustomer() of MenuItemController");
		return "menu-item-list-customer";
	}
	
	@GetMapping(value = "/show-edit-menu-item")
	public String showEditMenuItem(@RequestParam int menuItemId, ModelMap model) {
		LOGGER.info("Start showEditMenuItem() of MenuItemController");
		MenuItem menuItem = menuItemService.getMenuItem(menuItemId);
		model.addAttribute("menuItem", menuItem);
		List<Category> categories = categoryService.getCategoryList();
		model.addAttribute("categories", categories);
		LOGGER.info("End showEditMenuItem() of MenuItemController");
		return "edit-menu-item";
	}
	
	@PostMapping(value = "/edit-menu-item")
	public String editMenuItem(@Valid @ModelAttribute("menuItem") MenuItem menuItem, BindingResult bindingResult) {
		LOGGER.info("Start editMenuItem() of MenuItemController");
		if(bindingResult.hasErrors()) {
			return "edit-menu-item";
		}
		menuItemService.modifyMenuItem(menuItem);
		LOGGER.info("End editMenuItem() of MenuItemController");		
		return "edit-menu-item-status";
	}

}
