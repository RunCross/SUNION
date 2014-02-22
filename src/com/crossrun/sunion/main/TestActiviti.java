package com.crossrun.sunion.main;

import net.simonvt.menudrawer.MenuDrawer;

import com.crossrun.sunion.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestActiviti extends Activity{

	private MenuDrawer mDrawer;
	private TextView mContentTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testt);
		 mDrawer = (MenuDrawer) findViewById(R.id.drawer);
	        mDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);

	        mContentTextView = (TextView) findViewById(R.id.contentText);

	}
	public void onClick(View v) {
        String tag = (String) v.getTag();
        mContentTextView.setText(String.format("%s clicked.", tag));
        mDrawer.setActiveView(v);
    }
}
