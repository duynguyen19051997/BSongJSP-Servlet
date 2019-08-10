package model.bean;

import java.sql.Timestamp;

public class Contact {
	private int id;
	private String name;
	private String email;
	private String website;
	private String message;
	private int songId;
	private Timestamp dateCreate;
	private String songName;

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Contact(int id, String name, String email, String website, String message) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.website = website;
		this.message = message;
	}

	public Contact(int id, String name, String email, String website, String message, int songId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.website = website;
		this.message = message;
		this.songId = songId;
	}

	public Contact(int id, String name, String email, String website, String message, int songId,
			Timestamp dateCreate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.website = website;
		this.message = message;
		this.songId = songId;
		this.dateCreate = dateCreate;
	}

	public Contact(int id, String name, String email, String website, String message, int songId, Timestamp dateCreate,
			String songName) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.website = website;
		this.message = message;
		this.songId = songId;
		this.dateCreate = dateCreate;
		this.songName = songName;
	}

	public Contact() {
		super();
	}
}
