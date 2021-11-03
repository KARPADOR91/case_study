package com.cognizant.truyum.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.model.User;
import com.cognizant.truyum.service.MenuItemService;
import com.cognizant.truyum.service.UserService;

@Controller
public class CartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuItemService menuItemService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
	
	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam int menuItemId, ModelMap model) {
		LOGGER.info("Start addToCart() of CartController");
		User user = userService.get(1);
		Set<MenuItem> cartItems = user.getCartItems();
		cartItems.add(menuItemService.getMenuItem(menuItemId));
		user.setCartItems(cartItems);
		userService.save(user);
		model.addAttribute("addCartStatus", true);
		LOGGER.info("End addToCart() of CartController");
		return "forward:/show-menu-list-customer";
	}
	
	@GetMapping("/show-cart")
	public String showCart(@RequestParam int userId, ModelMap model) {
		LOGGER.info("Start showCart() of CartController");
		model.addAttribute("userId", userId);
		Set<MenuItem> cartItems = userService.get(userId).getCartItems();
		if(cartItems.isEmpty()) {
			LOGGER.info("End showCart() of CartController");
			return "cart-empty";			
		}
		model.addAttribute("cart", cartItems);
		float total = 0;		
		for(MenuItem item : cartItems) {
			total += item.getPrice();
		}
		model.addAttribute("total", total);
		LOGGER.info("End showCart() of CartController");
		return "cart";
	}
	
	@GetMapping("/remove-cart")
	public String removeCart(@RequestParam int menuItemId, @RequestParam int userId, ModelMap model) {
		LOGGER.info("Start removeCart() of CartController");
		model.addAttribute("userId", userId);
		User user = userService.get(userId);
		Set<MenuItem> cartItems = user.getCartItems();
		cartItems.remove(menuItemService.getMenuItem(menuItemId));
		user.setCartItems(cartItems);
		userService.save(user);
		if(cartItems.isEmpty()) {
			LOGGER.info("End removeCart() of CartController");
			return "cart-empty";				
		}
		model.addAttribute("cart", cartItems);
		float total = 0;
		for(MenuItem item : cartItems) {
			total += item.getPrice();
		}
		model.addAttribute("total", total);
		model.addAttribute("removeCartItemStatus", true);
		LOGGER.info("End removeCart() of CartController");
		return "forward:/show-cart";
	}

}
