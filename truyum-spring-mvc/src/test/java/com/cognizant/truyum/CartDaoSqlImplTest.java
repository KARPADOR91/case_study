package com.cognizant.truyum;

import java.util.EmptyStackException;
import java.util.List;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartDaoSqlImpl;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {

	public static void main(String[] args) {
		try {
			testGetAllCartItems();
		} catch (CartEmptyException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("");
		testAddCartItem();
		try {
			testGetAllCartItems();
		} catch (CartEmptyException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("");
		testRemoveCartItem();
		try {
			testGetAllCartItems();
		} catch (CartEmptyException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void testAddCartItem() {
		// User has already been created in the database
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem(2, 3);
	}

	public static void testGetAllCartItems() throws CartEmptyException {
		CartDao cartDao = new CartDaoSqlImpl();
		List<MenuItem> menuItemList = cartDao.getAllCartItems(2);
		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem.toString());
		}
	}

	public static void testRemoveCartItem() {
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.removeCartItem(2, 3);
	}

}
