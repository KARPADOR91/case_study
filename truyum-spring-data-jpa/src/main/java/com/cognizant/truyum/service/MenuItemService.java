package com.cognizant.truyum.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.repository.MenuItemRepository;

@Service("menuItemService")
public class MenuItemService {

	@Autowired
	private MenuItemRepository menuItemRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemService.class);

	@Transactional
	public List<MenuItem> getMenuItemListAdmin() {
		LOGGER.info("Start");
		return menuItemRepository.findAll();
	}

	@Transactional
	public List<MenuItem> getMenuItemListCustomer() {
		LOGGER.info("Start");
		return menuItemRepository.getMenuItemListCustomer();
	}

	public void modifyMenuItem(MenuItem menuItem) {
		LOGGER.info("Start");
		menuItemRepository.save(menuItem);
		LOGGER.info("End");
	}

	public MenuItem getMenuItem(int menuItemId) {
		LOGGER.info("Start");
		return menuItemRepository.getOne(menuItemId);
	}

}
