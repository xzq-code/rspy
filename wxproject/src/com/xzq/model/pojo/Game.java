package com.xzq.model.pojo;

public class Game {
	private Integer id;
	private Integer listid;
	private String gamename;
	private String gameinfo;
	private Integer gamenum;
	private String gameprize;
	private Integer prizenum;
	private Integer prizegrantnum;
	private String haobao;
	private String starttime;
	private String endtime;
	
	public Game() {
		super();
	}

	public Game(Integer id, Integer listid, String gamename, String gameinfo, Integer gamenum, String gameprize,
			Integer prizenum, Integer prizegrantnum, String haobao, String starttime, String endtime) {
		super();
		this.id = id;
		this.listid = listid;
		this.gamename = gamename;
		this.gameinfo = gameinfo;
		this.gamenum = gamenum;
		this.gameprize = gameprize;
		this.prizenum = prizenum;
		this.prizegrantnum = prizegrantnum;
		this.haobao = haobao;
		this.starttime = starttime;
		this.endtime = endtime;
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

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getGameinfo() {
		return gameinfo;
	}

	public void setGameinfo(String gameinfo) {
		this.gameinfo = gameinfo;
	}

	public Integer getGamenum() {
		return gamenum;
	}

	public void setGamenum(Integer gamenum) {
		this.gamenum = gamenum;
	}

	public String getGameprize() {
		return gameprize;
	}

	public void setGameprize(String gameprize) {
		this.gameprize = gameprize;
	}

	public Integer getPrizenum() {
		return prizenum;
	}

	public void setPrizenum(Integer prizenum) {
		this.prizenum = prizenum;
	}

	public Integer getPrizegrantnum() {
		return prizegrantnum;
	}

	public void setPrizegrantnum(Integer prizegrantnum) {
		this.prizegrantnum = prizegrantnum;
	}

	public String getHaobao() {
		return haobao;
	}

	public void setHaobao(String haobao) {
		this.haobao = haobao;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", listid=" + listid + ", gamename=" + gamename + ", gameinfo=" + gameinfo
				+ ", gamenum=" + gamenum + ", gameprize=" + gameprize + ", prizenum=" + prizenum + ", prizegrantnum="
				+ prizegrantnum + ", haobao=" + haobao + ", starttime=" + starttime + ", endtime=" + endtime + "]";
	}
	
	
	

}
