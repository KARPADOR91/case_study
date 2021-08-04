package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {
	private List<MenuItem> menuItemList;

	public MenuItemDaoCollectionImpl() {
		if (menuItemList == null) {
			menuItemList = new ArrayList<MenuItem>();
			menuItemList.add(new MenuItem(1, "Sandwich", 99.0f, true, DateUtil.convertToDate("15/03/2017"),
					"Main Course", true));
			menuItemList.add(new MenuItem(2, "Burger", 129.0f, true, DateUtil.convertToDate("23/12/2017"),
					"Main Course", false));
			menuItemList.add(
					new MenuItem(3, "Pizza", 149.0f, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			menuItemList.add(new MenuItem(4, "French Fries", 57.0f, false, DateUtil.convertToDate("02/07/2017"),
					"Starters", true));
			menuItemList.add(new MenuItem(5, "Chocolate Brownie", 32.0f, true, DateUtil.convertToDate("02/11/2022"),
					"Dessert", true));
		}
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
