package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		List<MenuItem> menuItemList = new ArrayList<>();
		Connection con = null;
		Statement stmnt = null;
		ResultSet rs = null;
		try {
			con = ConnectionHandler.getConnection();
			String query = "select menu_item.id, menu_item.name, price, in_stock, launch_date, category.name, "
					+ "free_delivery from menu_item, category where menu_item.category_id = category.id;";
			stmnt = con.createStatement();
			rs = stmnt.executeQuery(query);
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
				if (stmnt != null) {
					stmnt.close();
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
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> menuItemList = new ArrayList<>();
		Connection con = null;
		Statement stmnt = null;
		ResultSet rs = null;
		try {
			con = ConnectionHandler.getConnection();
			String query = "select menu_item.id, menu_item.name, price, in_stock, launch_date, category.name, "
					+ "free_delivery from menu_item, category where menu_item.category_id = category.id "
					+ "and in_stock is true and launch_date <= sysdate();";
			stmnt = con.createStatement();
			rs = stmnt.executeQuery(query);
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
				if (stmnt != null) {
					stmnt.close();
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
		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		Connection con = null;
		PreparedStatement psGetCategory = null;
		PreparedStatement psCreateCategory = null;
		PreparedStatement psUpdate = null;
		ResultSet rsGetCategory = null;
		try {
			con = ConnectionHandler.getConnection();

			// Get the category ID
			String queryGetCategory = "select id from category where upper(name) = upper(?);";
			psGetCategory = con.prepareStatement(queryGetCategory);
			psGetCategory.setString(1, menuItem.getCategory());
			rsGetCategory = psGetCategory.executeQuery();
			int categoryId = 0;
			if (rsGetCategory.next()) {
				categoryId = rsGetCategory.getInt(1);				
			}			
			// If the Category doesn't exist, create a new one
			if (categoryId == 0) {
				String queryCreateCategory = "insert into category (name) values (?);";
				psCreateCategory = con.prepareStatement(queryCreateCategory);
				psCreateCategory.setString(1, menuItem.getCategory());
				psCreateCategory.execute();
				// Get the ID of the new category
				rsGetCategory = psGetCategory.executeQuery();
				rsGetCategory.next();
				categoryId = rsGetCategory.getInt(1);
			}
			// Update menu item
			String queryUpdate = "update menu_item set name = ?, price = ?, in_stock = ?, launch_date = ?, "
					+ "category_id = ?, free_delivery = ? where id = ?;";
			psUpdate = con.prepareStatement(queryUpdate);
			psUpdate.setString(1, menuItem.getName());
			psUpdate.setFloat(2, menuItem.getPrice());
			psUpdate.setBoolean(3, menuItem.isActive());
			psUpdate.setDate(4, new java.sql.Date(menuItem.getDateOfLaunch().getTime()));
			psUpdate.setInt(5, categoryId);
			psUpdate.setBoolean(6, menuItem.isFreeDelivery());
			psUpdate.setLong(7, menuItem.getId());
			psUpdate.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rsGetCategory != null) {
					rsGetCategory.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (psGetCategory != null) {
					psGetCategory.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (psCreateCategory != null) {
					psCreateCategory.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (psUpdate != null) {
					psUpdate.close();
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
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ConnectionHandler.getConnection();
			String query = "select menu_item.name, price, in_stock, launch_date, category.name, free_delivery "
					+ "from menu_item, category where menu_item.category_id = category.id and menu_item.id = ?;";
			ps = con.prepareStatement(query);
			ps.setLong(1, menuItemId);
			rs = ps.executeQuery();
			rs.next();
			String name = rs.getString(1);
			float price = rs.getFloat(2);
			boolean active = rs.getBoolean(3);
			Date dateOfLaunch = rs.getDate(4);
			String category = rs.getString(5);
			boolean freeDelivery = rs.getBoolean(6);
			MenuItem menuItem = new MenuItem(menuItemId, name, price, active, dateOfLaunch, category, freeDelivery);
			return menuItem;
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
		return null;
	}

}
