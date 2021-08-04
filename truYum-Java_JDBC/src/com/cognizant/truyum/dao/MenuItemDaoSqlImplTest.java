package com.cognizant.truyum.dao;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {

	public static void main(String[] args) {
		testGetMenuItemListAdmin();
		System.out.println("");
		testGetMenuItemListCustomer();
		System.out.println("");
		testModifyMenuItem();
		System.out.println("");
		testGetMenuItemListAdmin();
		System.out.println("");
		testGetMenuItem();
	}
	
	public static void testGetMenuItemListAdmin() {
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		for (MenuItem menuItem : menuItemDao.getMenuItemListAdmin()) {
			System.out.println(menuItem.toString());
		}
	}
	
	public static void testGetMenuItemListCustomer() {
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		for (MenuItem menuItem : menuItemDao.getMenuItemListCustomer()) {
			System.out.println(menuItem.toString());
		}
	}
	
	public static void testModifyMenuItem() {
		MenuItem menuItem = new MenuItem(1, "Sandwich", 109.0f, true, DateUtil.convertToDate("15/03/2017"),
				"Snack", true);
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		menuItemDao.modifyMenuItem(menuItem);
		System.out.println(menuItemDao.getMenuItem(1).toString());
	}
	
	public static void testGetMenuItem() {
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		menuItemDao.getMenuItem(1);
		System.out.println(menuItemDao.getMenuItem(1).toString());
	}

}
