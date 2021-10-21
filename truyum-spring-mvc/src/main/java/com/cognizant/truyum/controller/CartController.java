package com.cognizant.truyum.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);
	
	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam long menuItemId, ModelMap model) {
		LOGGER.info("Start addToCart() of CartController");
		service.addCartItem(1, menuItemId);
		model.addAttribute("addCartStatus", true);
		LOGGER.info("End addToCart() of CartController");
		return "forward:/show-menu-list-customer";
	}
	
	@GetMapping("/show-cart")
	public String showCart(@RequestParam long userId, ModelMap model) {
		LOGGER.info("Start showCart() of CartController");
		model.addAttribute("userId", userId);
		try {
			List<MenuItem> cartItems = service.getAllCartItems(userId);
			model.addAttribute("cart", cartItems);
			float total = 0;
			for(MenuItem item : cartItems) {
				total += item.getPrice();
			}
			model.addAttribute("total", total);
			LOGGER.info("End showCart() of CartController");
			return "cart";
		} catch (CartEmptyException e) {
			LOGGER.info("End showCart() of CartController");
			return "cart-empty";
		}
	}
	
	@GetMapping("/remove-cart")
	public String removeCart(@RequestParam long menuItemId, @RequestParam long userId, ModelMap model) {
		LOGGER.info("Start removeCart() of CartController");
		service.removeCartItem(userId, menuItemId);
		try {
			List<MenuItem> cartItems = service.getAllCartItems(userId);
			model.addAttribute("cart", cartItems);
			float total = 0;
			for(MenuItem item : cartItems) {
				total += item.getPrice();
			}
			model.addAttribute("total", total);
			model.addAttribute("removeCartItemStatus", true);
			LOGGER.info("End removeCart() of CartController");
			return "forward:/show-cart";
		} catch (CartEmptyException e) {
			LOGGER.info("End removeCart() of CartController");
			return "cart-empty";	
		}
	}

}
