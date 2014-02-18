package com.crossrun.sunion.main.login;

import android.content.Intent;
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
import com.crossrun.sunion.mess.ActivityHandFlag;
import com.crossrun.sunion.mess.ActivityMessage;
import com.crossrun.sunion.util.Session;
import com.crossrun.sunion.util.StringCheck;
import com.crossrun.sunion.view.base.BaseActivity;
import com.crossrun.sunion.view.base.LoadingDialog;

/**
 * 注册
 * @author gjyuan
 *
 */
public class RegisterActivity extends BaseActivity implements Callback{

	private EditText registerEmail;
	private EditText registerPwd;
	private EditText registerPwdConfirm;
	
	private boolean passShow = false;
	private ImageView passImg;
	
	private Handler hand;
	
	private ResultDes registerResult;
	
	private LoadingDialog mDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		hand = new Handler(this);
		init();
		mDialog = new LoadingDialog(this);
	}
	
	/**
	 * 初始化组件
	 */
	private void init(){
		registerEmail = (EditText) findViewById(R.id.register_email);
		registerPwd = (EditText) findViewById(R.id.register_pwd);
		registerPwdConfirm = (EditText) findViewById(R.id.register_pwd_confirm);
		
		passImg = (ImageView) findViewById(R.id.pwd_show);
	}
	
	/**
	 * 组件响应
	 * @param view
	 */
	public void onClick(View view){
		switch(view.getId()){
		case R.id.pwd_show:
			passShow = !passShow;
			if(passShow){
				registerPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				registerPwdConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				passImg.setImageResource(R.drawable.passwor_show);
			}else{
				registerPwdConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
				registerPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
				passImg.setImageResource(R.drawable.passwor_hidden);
			}
			break;
		case R.id.register_go:
			register();
			break;
		}
	}
	
	/**
	 * 注册
	 */
	private void register(){
		final String email = registerEmail.getText().toString();
		final String pwd = registerPwd.getText().toString();
		String pwdConfirm = registerPwdConfirm.getText().toString();
		if(!checkParam(email, pwd, pwdConfirm)){
		    return ;	
		}
		mDialog.showDialog(ActivityMessage.RegisterLoading);
		((NetworkManager) AppEngine.getInstance().getManager(
				IManager.NETWOTK_ID)).register(email, pwd, new HttpResponseHand() {
					
					@Override
					public void onSuccess(ResultDes result) {
						mDialog.dismissDialog();
						Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); 
						intent.putExtra(Session.USER_EMAIL, email);
						intent.putExtra(Session.USER_PWD, pwd);
						setResult(0, intent);
						finish();
					}
					
					@Override
					public void onFailed(ResultDes result) {
						mDialog.dismissDialog();
						registerResult = result;
						hand.sendEmptyMessage(ActivityHandFlag.HTTP_ERROR);
					}
				});
		
	}
	
	/**
	 * 输入参数检查
	 * @param email
	 * @param pwd
	 * @param pwdConfirm
	 * @param pick
	 * @return true 符合格式  false 不符合格式
	 */
	private boolean checkParam(String email,String pwd,String pwdConfirm){
		if(!StringCheck.isEmail(email)){
			System.out.println(email);
			hand.sendEmptyMessage(ActivityHandFlag.EMAIL_ERROR);
			return false;
		}
		
		if(!StringCheck.isPwd(pwd) && !StringCheck.isPwd(pwdConfirm) ){
			hand.sendEmptyMessage(ActivityHandFlag.PWD_ERROR);
			return false;
		}
		if(!pwd.equals(pwdConfirm)){
			hand.sendEmptyMessage(ActivityHandFlag.PWD_CONFIRM_ERROR);
			return false; 
		}
		return true;
	}
	
	@Override
	public boolean handleMessage(Message message) {
		switch(message.what){
		case ActivityHandFlag.EMAIL_ERROR:
			Toast.makeText(RegisterActivity.this, ActivityMessage.EMAIL_ERROR, Toast.LENGTH_SHORT).show();
			break;
		case ActivityHandFlag.PWD_ERROR:
			Toast.makeText(RegisterActivity.this, ActivityMessage.PWD_ERROR, Toast.LENGTH_SHORT).show();
			break;
		case ActivityHandFlag.PWD_CONFIRM_ERROR:
			Toast.makeText(RegisterActivity.this, ActivityMessage.PWD_CONFIRM_ERROR, Toast.LENGTH_SHORT).show();
			break;
		case ActivityHandFlag.HTTP_ERROR:
			Toast.makeText(RegisterActivity.this, registerResult.des, Toast.LENGTH_SHORT).show();
			break;
		}
		return false;
	}
	
	
	@Override
	public byte activityId() {
		return REGISTER_ID;
	}


}
