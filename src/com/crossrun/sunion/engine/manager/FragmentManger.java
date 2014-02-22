package com.crossrun.sunion.engine.manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.crossrun.sunion.engine.IManager;
import com.crossrun.sunion.main.PersonInfoView;

public class FragmentManger implements IManager {
	
	public static final String MESSAGE_TO_FRAGEMENT = "message_fragement";
	
	public static final int FRAGeMENT_PERSON = 1;
	public static final int FRAGEMENT_NEWS = 2; 
	
	
	public Fragment getFragment(int tag,String json){
		Fragment fragment = null;
		switch(tag){
		case FRAGeMENT_PERSON :
			fragment = new PersonInfoView();
			break;
		case FRAGEMENT_NEWS :
//			fragment =  new 
		}
		if(fragment !=null ){
			Bundle arg = new Bundle();
			arg.putString(MESSAGE_TO_FRAGEMENT, json);
			fragment.setArguments(arg);
//			getArguments().getString(MESSAGE_TO_FRAGEMENT);
		}
		return fragment;
	} 

	@Override
	public byte managerId() {
		return FRAGMENT;
	}

	
}
