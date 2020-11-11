package com.xzq.model.pojo;

public class User {
	private Integer id;
	private String name;
	private String password;
	private String role;
	private String createtime;
	private String nickname;

	public User() {

	}

	public User(Integer id, String name, String password, String role, String createtime, String nickname) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.createtime = createtime;
		this.nickname = nickname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + ", createtime="
				+ createtime + ", nickname=" + nickname + "]";
	}
	
	
}
