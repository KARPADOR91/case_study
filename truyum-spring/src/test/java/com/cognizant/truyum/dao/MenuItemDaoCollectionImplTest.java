package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImplTest {

	public static void main(String[] args) {
		testGetMenuItemListAdmin();
		System.out.println("");
		testGetMenuItemListCustomer();
		System.out.println("");
		testModifyMenuItem();
	}

	public static void testGetMenuItemListAdmin() {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		for (MenuItem menuItem : menuItemDao.getMenuItemListAdmin()) {
			System.out.println(menuItem.toString());
		}
	}

	public static void testGetMenuItemListCustomer() {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		for (MenuItem menuItem : menuItemDao.getMenuItemListCustomer()) {
			System.out.println(menuItem.toString());
		}
	}

	public static void testModifyMenuItem() {
		MenuItem menuItem = new MenuItem(1, "Sandwich", 109.0f, true, DateUtil.convertToDate("15/03/2017"),
				"Main Course", true);
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		menuItemDao.modifyMenuItem(menuItem);
		System.out.println(menuItemDao.getMenuItem(1).toString());
	}

}
