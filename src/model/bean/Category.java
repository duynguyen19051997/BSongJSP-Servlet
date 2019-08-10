package model.bean;

public class Category {
	private int catId;
	private String catName;
	private int sort;
	private int totalOfSong;
	
	public int getTotalOfSong() {
		return totalOfSong;
	}

	public void setTotalOfSong(int totalOfSong) {
		this.totalOfSong = totalOfSong;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Category(int catId, String catName) {
		super();
		this.catId = catId;
		this.catName = catName;
	}

	public Category(int catId, String catName, int sort) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.sort = sort;
	}
	
	public Category(int catId, String catName, int sort, int totalOfSong) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.sort = sort;
		this.totalOfSong = totalOfSong;
	}

	public Category() {
		super();
	}
}
