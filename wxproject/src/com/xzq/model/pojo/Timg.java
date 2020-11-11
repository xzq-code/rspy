package com.xzq.model.pojo;

public class Timg {
	private Integer id;
	private Integer listid;
	private String image;
	public Timg() {
		super();
	}
	public Timg(Integer id, Integer listid, String image) {
		super();
		this.id = id;
		this.listid = listid;
		this.image = image;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getListid() {
		return listid;
	}
	public void setListid(Integer listid) {
		this.listid = listid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Timg [id=" + id + ", listid=" + listid + ", image=" + image + "]";
	}
	
	
}
