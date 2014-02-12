package com.crossrun.sunion.main.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.crossrun.sunion.R;
import com.crossrun.sunion.bean.ResultDes;
import com.crossrun.sunion.engine.AppEngine;
import com.crossrun.sunion.engine.IManager;
import com.crossrun.sunion.engine.manager.NetworkManager;
import com.crossrun.sunion.hand.HttpResponseHand;
import com.crossrun.sunion.mess.ActivityMessage;
import com.crossrun.sunion.util.PreferencesUtil;
import com.crossrun.sunion.util.StringCheck;
import com.crossrun.sunion.view.base.BaseActivity;
import com.crossrun.sunion.view.base.LoadingDialog;

/**
 * 登录界面
 * @author gjyuan
 *
 */
public class LoginActivity extends BaseActivity implements Callback{

//	private Button login;
//	private Button forget;
//	private Button register; 
	private EditText loginEmail;
	private EditText loginPwd;

	private LoadingDialog mDialog;
	
	private Handler hand;
	
	private static final int EMAIL_ERROR = 1;
	private static final int PWD_ERROR = 2;
	private static final int HTTP_ERROR = 3;
	
	private ResultDes loginResult;
	
	private boolean passShow = false;
	private ImageView passImg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		init();		
		
		mDialog = new LoadingDialog(this);
		
		hand = new Handler(this);
	}

	/**
	 * 初始化组件
	 */
	private void init() {

		loginEmail = (EditText) findViewById(R.id.login_email);
		loginPwd = (EditText) findViewById(R.id.login_pwd);
		passImg = (ImageView) findViewById(R.id.pwd_show);
		
		
		passShow = PreferencesUtil.getBoolean(PreferencesUtil.KEY_PWD_SHOW, false);
		
		if(passShow){
			loginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			passImg.setImageResource(R.drawable.passwor_show);
		}else{
			loginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
			passImg.setImageResource(R.drawable.passwor_hidden);
		}
	}

	public void onClick(View view) {
		switch (view.getId()) {
		// 登录
		case R.id.login_go:
			
			login();
			
			break;

		// 忘记密码
		case R.id.login_forget:
			break;

		// 注册
		case R.id.login_register:
			break;
			
		case R.id.pwd_show:
			passShow = !passShow;
			if(passShow){
				loginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				passImg.setImageResource(R.drawable.passwor_show);
			}else{
				loginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
				passImg.setImageResource(R.drawable.passwor_hidden);
			}
			loginPwd.setSelection(loginPwd.getText().length());
			break;
		}
	}
	
	@Override
	public boolean handleMessage(Message message) {
		switch(message.what){
		case EMAIL_ERROR:
			Toast.makeText(LoginActivity.this, ActivityMessage.EMAIL_ERROR, Toast.LENGTH_SHORT).show();
			break;
		case PWD_ERROR:
			Toast.makeText(LoginActivity.this, ActivityMessage.PWD_ERROR, Toast.LENGTH_SHORT).show();
			break;
		case HTTP_ERROR:
			Toast.makeText(LoginActivity.this, loginResult.des, Toast.LENGTH_SHORT).show();
			break;
		}
		return false;
	}

	/**
	 * 登录
	 */
	private void login(){
//		final String email = loginEmail.getText().toString();
		final String email = "rujiang_g@qq.com";
		final String pwd = loginPwd.getText().toString();
//		pwd = "123456";
		if (!StringCheck.checkEmail(email)) {
			hand.sendEmptyMessage(EMAIL_ERROR);
			return ;
		}
		if(!StringCheck.checkPwd(pwd)){
			hand.sendEmptyMessage(PWD_ERROR);
			return ;
		}
		mDialog.showDialog(ActivityMessage.Loading);
		((NetworkManager) AppEngine.getInstance().getManager(
				IManager.NETWOTK_ID)).login(email, pwd,
				new HttpResponseHand() {

					@Override
					public void onSuccess(ResultDes result) {
						mDialog.dismissDialog();
						saveUserLoginInfo(email, pwd);
					}

					@Override
					public void onFailed(ResultDes result) {
						mDialog.dismissDialog();
						loginResult = result;
						hand.sendEmptyMessage(HTTP_ERROR);
					}
				});
	}
	private void saveUserLoginInfo(String email,String pwd){
		PreferencesUtil.commit(PreferencesUtil.KEY_USER_EMAIL, email);
		PreferencesUtil.commit(PreferencesUtil.KEY_USER_PWD, pwd);
		PreferencesUtil.commit(PreferencesUtil.KEY_PWD_SHOW, passShow);
	}
	@Override
	public byte activityId() {
		return LOGIN_ID;
	}


}
