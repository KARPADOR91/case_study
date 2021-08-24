package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

@Component
@ImportResource("spring-config.xml")
public class CartDaoCollectionImpl implements CartDao {

	@Autowired
	private HashMap<Long, Cart> userCarts;

	@Autowired
	private MenuItemDao menuItemDao;

	public HashMap<Long, Cart> getUserCarts() {
		return userCarts;
	}

	public void setUserCarts(HashMap<Long, Cart> userCarts) {
		this.userCarts = userCarts;
	}

	@Override
	public void addCartItem(long userId, long menuItemId) {
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		if (userCarts.containsKey(userId)) {
			Cart cart = userCarts.get(userId);
			List<MenuItem> menuItemList = cart.getMenuItemList();
			menuItemList.add(menuItem);
			cart.setMenuItemList(menuItemList);
			cart.setTotal(cart.getTotal() + menuItem.getPrice());
			userCarts.put(userId, cart);
		} else {
			List<MenuItem> menuItemListCart = new ArrayList<>();
			menuItemListCart.add(menuItem);
			userCarts.put(userId, new Cart(menuItemListCart, menuItem.getPrice()));
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
		if (menuItemList.isEmpty()) {
			throw new CartEmptyException("There are no Items in your cart");
		} else {
			/*
			 * We are supposed to calculate the total here. But it makes more
			 * sense to me to update the total when an item is added to the
			 * cart. This way the total is always updated instead of just being
			 * updated when getAllCartItems is called for the specific cart.
			 */
			return menuItemList;
		}
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
		ListIterator<MenuItem> itr = menuItemList.listIterator();
		while (itr.hasNext()) {
			if (itr.next().getId() == menuItemId) {
				itr.remove();
			}
		}
	}

}
