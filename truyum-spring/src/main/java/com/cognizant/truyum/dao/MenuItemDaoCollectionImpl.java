package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

@Component
@ImportResource("spring-config.xml")
public class MenuItemDaoCollectionImpl implements MenuItemDao {

	@Autowired
	private List<MenuItem> menuItemList;

	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemListCustomer = new ArrayList<>();
		Date now = new Date();
		menuItemListCustomer = menuItemList.stream().filter(menuItem -> now.after(menuItem.getDateOfLaunch()))
				.filter(menuItem -> menuItem.isActive() == true).collect(Collectors.toList());
		return menuItemListCustomer;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		for (MenuItem menuItemInList : menuItemList) {
			if (menuItemInList.equals(menuItem)) {
				menuItemInList.setName(menuItem.getName());
				menuItemInList.setPrice(menuItem.getPrice());
				menuItemInList.setActive(menuItem.isActive());
				menuItemInList.setDateOfLaunch(menuItem.getDateOfLaunch());
				menuItemInList.setCategory(menuItem.getCategory());
				menuItemInList.setFreeDelivery(menuItem.isFreeDelivery());
			}
		}

	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == menuItemId) {
				return menuItem;
			}
		}
		return null;
	}

}
