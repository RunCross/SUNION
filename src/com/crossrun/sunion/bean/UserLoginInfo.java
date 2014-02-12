package com.crossrun.sunion.bean;

public class UserLoginInfo {
	public int id;
	public String name;
	public String pwd;
	
	/**
	 * @param id
	 * @param name
	 * @param pwd
	 */
	public UserLoginInfo(int id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}

	public UserLoginInfo(String name,String pwd){
		this(-1,name,pwd);
	}
	
}
