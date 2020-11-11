package com.xzq.model.pojo;

public class Group {
	private Integer id;
	private String groupname;
	private String openid;
	private String officalid;

	public Group() {
		super();
	}

	public Group(Integer id, String groupname, String openid, String officalid) {
		super();
		this.id = id;
		this.groupname = groupname;
		this.openid = openid;
		this.officalid = officalid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOfficalid() {
		return officalid;
	}

	public void setOfficalid(String officalid) {
		this.officalid = officalid;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", groupname=" + groupname + ", openid=" + openid + ", officalid=" + officalid + "]";
	}

	
}
