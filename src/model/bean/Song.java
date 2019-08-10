package model.bean;

import java.sql.Timestamp;

public class Song {
	private int songId;
	private int songCouter;
	private String songName;
	private String songPreview;
	private String songDetail;
	private String songPicture;
	private Timestamp songDateCreate;
	private Category songCat;

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public int getSongCouter() {
		return songCouter;
	}

	public void setSongCouter(int songCouter) {
		this.songCouter = songCouter;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSongPreview() {
		return songPreview;
	}

	public void setSongPreview(String songPreview) {
		this.songPreview = songPreview;
	}

	public String getSongDetail() {
		return songDetail;
	}

	public void setSongDetail(String songDetail) {
		this.songDetail = songDetail;
	}

	public String getSongPicture() {
		return songPicture;
	}

	public void setSongPicture(String songPicture) {
		this.songPicture = songPicture;
	}

	public Timestamp getSongDateCreate() {
		return songDateCreate;
	}

	public void setSongDateCreate(Timestamp songDateCreate) {
		this.songDateCreate = songDateCreate;
	}

	public Category getSongCat() {
		return songCat;
	}

	public void setSongCat(Category songCat) {
		this.songCat = songCat;
	}

	public Song(int songId, int songCouter, String songName, String songPreview, String songDetail, String songPicture,
			Timestamp songDateCreate, Category songCat) {
		super();
		this.songId = songId;
		this.songCouter = songCouter;
		this.songName = songName;
		this.songPreview = songPreview;
		this.songDetail = songDetail;
		this.songPicture = songPicture;
		this.songDateCreate = songDateCreate;
		this.songCat = songCat;
	}

	public Song(int songId, int songCouter, String songName, String songPreview, String songDetail, String songPicture,
			Timestamp songDateCreate) {
		super();
		this.songId = songId;
		this.songCouter = songCouter;
		this.songName = songName;
		this.songPreview = songPreview;
		this.songDetail = songDetail;
		this.songPicture = songPicture;
		this.songDateCreate = songDateCreate;
	}

	public Song(String songName, String songPreview, String songDetail, String songPicture, Category songCat) {
		super();
		this.songName = songName;
		this.songPreview = songPreview;
		this.songDetail = songDetail;
		this.songPicture = songPicture;
		this.songCat = songCat;
	}

	public Song(int songId, String songName, String songPreview, String songDetail, String songPicture,
			Category songCat) {
		super();
		this.songId = songId;
		this.songName = songName;
		this.songPreview = songPreview;
		this.songDetail = songDetail;
		this.songPicture = songPicture;
		this.songCat = songCat;
	}

	public Song() {
		super();
	}
}
