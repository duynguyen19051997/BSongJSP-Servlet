package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Song;
import util.DBConnectionUtil;
import util.DefineUtil;

public class SongDao {
	private Connection connect;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ArrayList<Song> getItemsPop() {
		ArrayList<Song> listSong = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.counter DESC LIMIT 5";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSong;
	}
	
	public ArrayList<Song> getItemsLatest() {
		ArrayList<Song> listSong = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC LIMIT 6";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSong;
	}

	public ArrayList<Song> getItems() {
		ArrayList<Song> listSong = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSong;
	}

	public ArrayList<Song> getItems(int number) {
		ArrayList<Song> listSong = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC LIMIT ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, number);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSong;
	}

	/**
	 * edit song
	 * 
	 * @param objSong
	 * @return
	 */
	public int editSong(Song objSong) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET name = ?, preview_text = ?, detail_text = ?, picture = ?, cat_id = ? WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, objSong.getSongName());
			pst.setString(2, objSong.getSongPreview());
			pst.setString(3, objSong.getSongDetail());
			pst.setString(4, objSong.getSongPicture());
			pst.setInt(5, objSong.getSongCat().getCatId());
			pst.setInt(6, objSong.getSongId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

	/**
	 * validate String by Java
	 * 
	 * @param x
	 * @return
	 */
	public String validString(String x) {
		if ("".equals(x) || x.isEmpty()) {
			return "Vui lòng không để trống!";
		}
		return "";
	}

	/**
	 * delete song
	 * 
	 * @param songId
	 * @return
	 */
	public int deleteItem(int songId) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM songs WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, songId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

	public Song getItem(int id) {
		Song objSong = null;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE s.id = ? ";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				objSong = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return objSong;
	}
	public Song getItemsMostPop() {
		Song song = null;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.counter DESC LIMIT 1";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return song;
	}
	
	public Song getItemMostPopByCatId(int catId) {
		Song song = null;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE s.cat_id = ? ORDER BY s.counter DESC LIMIT 1";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if (rs.next()) {
				song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return song;
	}

	public int addSong(Song objSong) {	
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO songs (name, preview_text, detail_text, picture, cat_id, counter) VALUES (?, ?, ?, ?, ?,?)";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, objSong.getSongName());
			pst.setString(2, objSong.getSongPreview());
			pst.setString(3, objSong.getSongDetail());
			pst.setString(4, objSong.getSongPicture());
			pst.setInt(5, objSong.getSongCat().getCatId());
			pst.setInt(6, 0);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

	public ArrayList<Song> getItemsByCatIdAndIdSong(int id, int idSong) {
		ArrayList<Song> listSong = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE s.cat_id = ? AND s.id != ? ORDER BY s.id DESC LIMIT ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, idSong);
			pst.setInt(3, 4);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSong;
	}

	public ArrayList<Song> getItemsByCatId(int id) {
		ArrayList<Song> listSong = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE s.cat_id = ? ORDER BY s.id DESC";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSong;
	}
	
	public int getIdCatByIdSong(int id) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT cat_id FROM songs WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = Integer.valueOf(rs.getInt("cat_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}
	
	public int getTotalItemsById(int id) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM songs WHERE cat_id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = Integer.valueOf(rs.getInt("COUNT(*)"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}

	public int getNumberOfItem() {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs";
		try {
			st = connect.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}

	public ArrayList<Song> getItemsPagination(int offset) {
		ArrayList<Song> listSong = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC LIMIT ?, ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSong;
	}

	public int deleteItemsByCatId(int catId) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM songs WHERE cat_id = ?";
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

	public int getNumberOfItem(int id) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE cat_id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}
	
	public int getNumberOfItemBySearch(String search) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE name LIKE ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setString(1, "%" + search + "%");
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return result;
	}

	public ArrayList<Song> getItemsByCatIdPagination(int catId, int offset) {
		ArrayList<Song> listSong = new ArrayList<>();
		connect = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE s.cat_id = ? ORDER BY s.id DESC LIMIT ?, ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, catId);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connect);
		}
		return listSong;
	}
	
	public ArrayList<Song> searchSong(String search, int offset) {
		ArrayList<Song> listSong = new ArrayList<>();
		Connection connection = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id,s.name,s.preview_text,s.detail_text,s.date_create,s.picture,s.counter,s.cat_id, c.name  FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE s.name LIKE ? ORDER BY s.id DESC LIMIT ?, ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, "%" + search + "%");
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("s.id"), rs.getInt("s.counter"), rs.getString("s.name"),
						rs.getString("s.preview_text"), rs.getString("s.detail_text"), rs.getString("s.picture"),
						rs.getTimestamp("s.date_create"), new Category(rs.getInt("s.cat_id"), rs.getString("c.name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connection);
		}
		return listSong;
	}

	public ArrayList<Song> searchSong(String search) {
		ArrayList<Song> listSong = new ArrayList<>();
		Connection connection = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM songs WHERE name LIKE ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, "%" + search + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("name"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getString("picture"),
						rs.getTimestamp("date_create"), new Category(rs.getInt("cat_id"), rs.getString("name")));
				listSong.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, connection);
		}
		return listSong;
	}


	public int updateCounter(int id, int songCouter) {
		int result = 0;
		connect = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET counter = ?  WHERE id = ?";
		try {
			pst = connect.prepareStatement(sql);
			pst.setInt(1, songCouter);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, connect);
		}
		return result;
	}

}
