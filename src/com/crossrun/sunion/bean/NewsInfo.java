package com.crossrun.sunion.bean;

/**
 * 新闻类
 * @author Cross.Run
 *
 */
public class NewsInfo {

	private int id;
	private String author;
	private String title;
	private String date;
	private String content;
	private int visits;
	private char flag;
	private char discuss;
	
	public NewsInfo(){
		// 默认不存在
		id = -1;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public char getFlag() {
		return flag;
	}
	public void setFlag(char flag) {
		this.flag = flag;
	}
	public char getDiscuss() {
		return discuss;
	}
	public void setDiscuss(char discuss) {
		this.discuss = discuss;
	}
	
	
}
