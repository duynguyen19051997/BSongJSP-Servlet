package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Contact;
import util.DBConnectionUtil;
import util.DefineUtil;

public class ContactDAO {
	private Connection connect;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<Contact> getItems() {
		ArrayList<Contact> listCt = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM contacts ORDER BY id DESC";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Contact objCon = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("website"), rs.getString("message"));
				listCt.add(objCon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listCt;
	}
	
	public ArrayList<Contact> getItemsBySongID(int id) {
		ArrayList<Contact> listCt = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM contacts WHERE songId = ? ORDER BY date_create DESC";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Contact objCon = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("website"), rs.getString("message"), rs.getInt("songId"), rs.getTimestamp("date_create"));
				listCt.add(objCon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listCt;
	}

	public int deleteItem(int id) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM contacts WHERE id  = ?";
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

	public int sendContact(Contact contact) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO contacts (name, email, website, message) VALUES (?, ?, ?, ?)";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getWebsite());
			pst.setString(4, contact.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

	public int getNumberOfItem() {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM contacts";
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

	public ArrayList<Contact> getItemsPagination(int offset) {
		ArrayList<Contact> listCt = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM contacts ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Contact objCon = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("website"), rs.getString("message"));
				listCt.add(objCon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listCt;
	}

	public int addItem(Contact contact) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO contacts (name, email, website, message, songId) VALUES (?, ?, ?, ?, ?)";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getWebsite());
			pst.setString(4, contact.getMessage());
			pst.setInt(5, contact.getSongId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

}
