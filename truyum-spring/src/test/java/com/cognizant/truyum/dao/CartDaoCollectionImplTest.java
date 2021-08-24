package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {

	public static void main(String[] args) {
		try {
			testAddCartItem();
		} catch (CartEmptyException e) {
			System.out.println(e.getMessage());
		}
		testRemoveCartItem();
	}

	public static void testAddCartItem() throws CartEmptyException {
		CartDao cartDao = new CartDaoCollectionImpl();
		cartDao.addCartItem(1, 2);
		List<MenuItem> menuItemList = cartDao.getAllCartItems(1);
		System.out.println(menuItemList.toString());
	}

	/*
	 * I don't know what this method is for since this method is never called
	 * and it's functionality already exists in testAddCartItem()
	 */
	public static void testGetAllCartItems() throws CartEmptyException {
		CartDao cartDao = new CartDaoCollectionImpl();
		List<MenuItem> menuItemList = cartDao.getAllCartItems(1);
		System.out.println(menuItemList.toString());
	}

	public static void testRemoveCartItem() {
		CartDao cartDao = new CartDaoCollectionImpl();

		// Add Burger and Pizza to cart
		cartDao.addCartItem(1, 2);
		cartDao.addCartItem(1, 3);

		// Print Items in Cart
		try {
			List<MenuItem> menuItemList = cartDao.getAllCartItems(1);
			System.out.println(menuItemList.toString());
		} catch (CartEmptyException e) {
			System.out.println(e.getMessage());
		}

		// remove Burger
		cartDao.removeCartItem(1, 2);

		// Print Items in Cart
		try {
			List<MenuItem> menuItemList = cartDao.getAllCartItems(1);
			System.out.println(menuItemList.toString());
		} catch (CartEmptyException e) {
			System.out.println(e.getMessage());
		}
	}

}
