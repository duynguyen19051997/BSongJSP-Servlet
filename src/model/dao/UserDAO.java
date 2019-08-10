package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.User;
import util.DBConnectionUtil;
import util.DefineUtil;

public class UserDAO {
	private Connection connect;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<User> getItems() {
		ArrayList<User> listUser = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User objUser = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}

		return listUser;
	}

	public int addItem(User user) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO users (username, password, fullname) VALUES (?,?,?)";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}

	public boolean hasUser(User user) {
		boolean check = false;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, user.getUserName());
			rs = pst.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return check;
	}

	public User getItem(int id) {
		User objUser = null;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return objUser;
	}

	public int editItem(User objUser) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "UPDATE users SET password = ?, fullname = ? WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, objUser.getPassword());
			pst.setString(2, objUser.getFullName());
			pst.setInt(3, objUser.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}

	public int deleteItem(int id) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM users WHERE id  = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}

	public User getItemByUserAndPassword(String user, String password) {
		User objUser = null;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, user);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return objUser;
	}

	public int getNumberOfItem() {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM users";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}

	public ArrayList<User> getItemsPagination(int offset) {
		ArrayList<User> listUser = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				User objUser = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
				listUser.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listUser;
	}
}
