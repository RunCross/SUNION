package com.crossrun.sunion.bean;

/**
 * 组织信息
 * @author Cross.Run
 *
 */
public class UnionInfo {

	public int id;
	/**
	 * 上级组织id
	 */
	public int superior;
	public String name;
	public String address;
	public String contact;
	public String introduction;
	
	public UnionInfo(){
		superior = -1;
	}
}
