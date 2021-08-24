package com.cognizant.truyum.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemServiceTest {
	private MenuItemService menuItemService;

	@Before
	public void initializeService() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.cognizant.truyum");
		context.refresh();
		menuItemService = (MenuItemService) context.getBean("menuItemService");
	}

	@Test
	public void testGetMenuItemListAdminSize() {
		assertEquals(5, menuItemService.getMenuItemListAdmin().size());
	}

	@Test
	public void testGetMenuItemListAdminContainsSandwich() {
		boolean containsSandwich = false;
		for (MenuItem item : menuItemService.getMenuItemListAdmin()) {
			if (item.getName().equalsIgnoreCase("Sandwich")) {
				containsSandwich = true;
				break;
			}
		}
		assertTrue(containsSandwich);
	}

	@Test
	public void testGetMenuItemListCustomerSize() {
		assertEquals(3, menuItemService.getMenuItemListCustomer().size());
	}

	@Test
	public void testGetMenuItemListCustomerNotContainsFrenchFries() {
		boolean containsFries = false;
		for (MenuItem item : menuItemService.getMenuItemListCustomer()) {
			if (item.getName().equalsIgnoreCase("French Fries")) {
				containsFries = true;
				break;
			}
		}
		assertFalse(containsFries);
	}

	@Test
	public void testGetMenuItem() {
		assertEquals(1, menuItemService.getMenuItem(1).getId());
	}

	@Test
	public void testModifyMenuItem() {
		float oldPrice = menuItemService.getMenuItem(1).getPrice();
		MenuItem menuItem = new MenuItem(1, "Sandwich", 119.0f, true, new Date(), "Main Course", true);
		menuItemService.modifyMenuItem(menuItem);
		assertEquals(99.0, oldPrice, 0.001);
		assertEquals(119.0, menuItemService.getMenuItem(1).getPrice(), 0.001);
	}

}
