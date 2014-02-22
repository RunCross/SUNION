package com.crossrun.sunion.bean;

/**
 * 侧滑菜单栏单项信息
 * @author gjyuan
 *
 */
public class SlideMenuItemInfo {

	/**
	 * 菜单项名称
	 */
	public String tag ;
	/**
	 * 菜单项对应URL
	 */
	public String url;
	
	public SlideMenuItemInfo(String tag, String url) {
		super();
		this.tag = tag;
		this.url = url;
	}
	

}
