package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.controller.MenuItemController;
import com.cognizant.truyum.model.MenuItem;

@Component("cartDao")
public class CartDaoSqlImpl implements CartDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

	@Override
	public void addCartItem(long userId, long menuItemId) {
		LOGGER.info("Start addCartItem() of CartDaoSqlImpl");
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionHandler.getConnection();
			String query = "insert into cart (user_id, menu_item_id) values (?, ?);";
			ps = con.prepareStatement(query);
			ps.setLong(1, userId);
			ps.setLong(2, menuItemId);
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("End addCartItem() of CartDaoSqlImpl");
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		LOGGER.info("Start getAllCartItems() of CartDaoSqlImpl");
		List<MenuItem> menuItemList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ConnectionHandler.getConnection();
			String query = "select menu_item.id, menu_item.name, price, in_stock, launch_date, category.name, "
					+ "free_delivery from menu_item, category, cart where menu_item.category_id = category.id "
					+ "and menu_item.id = cart.menu_item_id and user_id = ?;";
			ps = con.prepareStatement(query);
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				menuItemList.add(new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4),
						rs.getDate(5), rs.getString(6), rs.getBoolean(7)));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (menuItemList.isEmpty()) {
			throw new CartEmptyException("There are no Items in your cart");
		} else {
			LOGGER.info("End getAllCartItems() of CartDaoSqlImpl");
			return menuItemList;
		}
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		LOGGER.info("Start removeCartItem() of CartDaoSqlImpl");
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionHandler.getConnection();
			String query = "delete from cart where user_id = ? and menu_item_id = ?;";
			ps = con.prepareStatement(query);
			ps.setLong(1, userId);
			ps.setLong(2, menuItemId);
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Start removeCartItem() of CartDaoSqlImpl");
	}
}
