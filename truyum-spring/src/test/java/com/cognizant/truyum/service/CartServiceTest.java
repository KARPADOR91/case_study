package com.cognizant.truyum.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cognizant.truyum.dao.CartEmptyException;

public class CartServiceTest {
	private CartService cartService;

	@Before
	public void initializeService() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.cognizant.truyum");
		context.refresh();
		cartService = (CartService) context.getBean("cartService");
	}

	@Test
	public void testGetAllCartItems() throws CartEmptyException {
		assertEquals(2, cartService.getAllCartItems(1).size());
	}

	@Test(expected = CartEmptyException.class)
	public void testGetAllCartItemsCartEmptyException() throws CartEmptyException {
		cartService.getAllCartItems(2);
	}

	@Test
	public void testAddCartItem() throws CartEmptyException {
		assertEquals(2, cartService.getAllCartItems(1).size());
		cartService.addCartItem(1, 3);
		assertEquals(3, cartService.getAllCartItems(1).size());
	}

	@Test
	public void testRemoveCartItem() throws CartEmptyException {
		assertEquals(2, cartService.getAllCartItems(1).size());
		cartService.removeCartItem(1, 2);
		assertEquals(1, cartService.getAllCartItems(1).size());
	}

}