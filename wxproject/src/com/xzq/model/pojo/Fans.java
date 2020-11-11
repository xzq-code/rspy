package com.xzq.model.pojo;

public class Fans {
	private Integer id;
	private String subscribe;
	private String openid;
	private String nickname;
	private String sex;
	private String city;
	private String province;
	private String country;
	private String language;
	private String headimgurl;
	private String subscribe_time;
	private String remark;
	private Integer groupid;
	private Integer tagid_list;
	private String subscribe_scene;
	private String qr_scene;
	private String qr_scene_str;
	private String unionid;
	private String parentid;
	private String childid;
	private Integer fansnum;

	public Fans() {
		super();
	}

	public Fans(Integer id, String subscribe, String openid, String nickname, String sex, String city, String province,
			String country, String language, String headimgurl, String subscribe_time, String remark, Integer groupid,
			Integer tagid_list, String subscribe_scene, String qr_scene, String qr_scene_str, String unionid,
			String parentid, String childid, Integer fansnum) {
		super();
		this.id = id;
		this.subscribe = subscribe;
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.city = city;
		this.province = province;
		this.country = country;
		this.language = language;
		this.headimgurl = headimgurl;
		this.subscribe_time = subscribe_time;
		this.remark = remark;
		this.groupid = groupid;
		this.tagid_list = tagid_list;
		this.subscribe_scene = subscribe_scene;
		this.qr_scene = qr_scene;
		this.qr_scene_str = qr_scene_str;
		this.unionid = unionid;
		this.parentid = parentid;
		this.childid = childid;
		this.fansnum = fansnum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(Integer tagid_list) {
		this.tagid_list = tagid_list;
	}

	public String getSubscribe_scene() {
		return subscribe_scene;
	}

	public void setSubscribe_scene(String subscribe_scene) {
		this.subscribe_scene = subscribe_scene;
	}

	public String getQr_scene() {
		return qr_scene;
	}

	public void setQr_scene(String qr_scene) {
		this.qr_scene = qr_scene;
	}

	public String getQr_scene_str() {
		return qr_scene_str;
	}

	public void setQr_scene_str(String qr_scene_str) {
		this.qr_scene_str = qr_scene_str;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getChildid() {
		return childid;
	}

	public void setChildid(String childid) {
		this.childid = childid;
	}

	public Integer getFansnum() {
		return fansnum;
	}

	public void setFansnum(Integer fansnum) {
		this.fansnum = fansnum;
	}

	@Override
	public String toString() {
		return "Fans [id=" + id + ", subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname
				+ ", sex=" + sex + ", city=" + city + ", province=" + province + ", country=" + country + ", language="
				+ language + ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", remark=" + remark
				+ ", groupid=" + groupid + ", tagid_list=" + tagid_list + ", subscribe_scene=" + subscribe_scene
				+ ", qr_scene=" + qr_scene + ", qr_scene_str=" + qr_scene_str + ", unionid=" + unionid + ", parentid="
				+ parentid + ", childid=" + childid + ", fansnum=" + fansnum + "]";
	}
	
	

}
