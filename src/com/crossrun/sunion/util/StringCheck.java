package com.crossrun.sunion.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCheck {

	/**
	 * 密码规定长度
	 */
	public static final int PASSWORD_LENGTH = 6;
	
	/**
	 * 检查Email格式
	 * @param email
	 * @return true 符合Email false 不符合
	 */
	public static boolean checkEmail(String email) {
		if(email == null ){
			return false;
		}
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查密码格式
	 * @param str
	 * @return true 符合格式 false 不符合格式
	 */
	public static boolean checkPwd(String str){
		if(str == null || str.length()<PASSWORD_LENGTH){
			return false;
		}		
		return true;
	}
}
