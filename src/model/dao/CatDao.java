package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnectionUtil;

public class CatDao {
	private Connection connect;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<Category> getItems() {
		ArrayList<Category> listCat = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories ORDER BY sort DESC, id DESC";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("sort"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listCat;
	}

	/**
	 * thêm danh mục
	 * 
	 * @param objCat
	 * @return
	 */
	public int addItem(Category objCat) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO categories (name, sort) VALUES (?, ?)";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			pst.setInt(2, objCat.getSort());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

	/**
	 * lấy tên danh mục dựa vào catId
	 * 
	 * @param catId
	 * @return
	 */
	public Category getItem(int catId) {
		Category objCat = null;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if (rs.next()) {
				objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("sort"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objCat;
	}

	/**
	 * sửa danh mục tin
	 * 
	 * @param objCat
	 * @return
	 */
	public int editItem(Category objCat) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "UPDATE categories SET name = ?, sort = ? WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			pst.setInt(2, objCat.getSort());
			pst.setInt(3, objCat.getCatId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

	/**
	 * xóa danh mục tin
	 * 
	 * @param catId
	 * @return
	 */
	public int deleteItem(int catId) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM categories WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, catId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}
}
