package model.bean;

public class Slide {
	private int id;
	private String image;
	private int sort;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Slide(int id, String image, int sort) {
		super();
		this.id = id;
		this.image = image;
		this.sort = sort;
	}

	public Slide() {
		super();
	}
}
