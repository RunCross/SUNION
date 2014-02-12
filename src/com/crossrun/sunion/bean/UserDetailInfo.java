package com.crossrun.sunion.bean;

/**
 * 用户详细信息
 * @author Cross.Run
 *
 */
public class UserDetailInfo {

	public int id;
	public String name;
	public String gender;
	public String email;
	public UnionInfo union;
	public String unionHis;
	public String address;
	public String contact;
	public String photo;
	
	public UserDetailInfo(){
		id = -1;
	}
}
