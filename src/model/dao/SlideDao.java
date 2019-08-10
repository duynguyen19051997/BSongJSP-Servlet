package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Slide;
import util.DBConnectionUtil;

public class SlideDao {
	private static Connection connect;
	private static PreparedStatement pst;
	private static Statement st;
	private static ResultSet rs;

	public ArrayList<Slide> getItems() {
		ArrayList<Slide> listSlide = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM slides ORDER BY sort DESC";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Slide objSlide = new Slide(rs.getInt("id"), rs.getString("image"), rs.getInt("sort"));
				listSlide.add(objSlide);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSlide;
	}

	public Slide getItem(int slideId) {
		Slide objSlide = null;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM slides WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, slideId);
			rs = pst.executeQuery();
			if(rs.next()) {
				objSlide = new Slide(rs.getInt("id"), rs.getString("image"), rs.getInt("sort"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return objSlide;
	}

	public int deleteItem(int slideId) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM slides WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, slideId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

	public int editSong(Slide objSlide) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "UPDATE slides SET sort = ? WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, objSlide.getSort());
			pst.setInt(2, objSlide.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

}
