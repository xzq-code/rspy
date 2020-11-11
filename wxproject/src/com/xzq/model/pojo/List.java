package com.xzq.model.pojo;

public class List {
	private Integer id;
	private String listname;
	private String info;

	public List() {
		super();
	}

	public List(Integer id, String listname, String info) {
		super();
		this.id = id;
		this.listname = listname;
		this.info = info;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getListname() {
		return listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "List [id=" + id + ", listname=" + listname + ", info=" + info + "]";
	}

	
}
