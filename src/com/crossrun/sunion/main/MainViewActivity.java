package com.crossrun.sunion.main;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import com.crossrun.sunion.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;

public class MainViewActivity extends FragmentActivity {

	private MenuDrawer mMenuDrawer;
	private int width = 20;
	private boolean hasMeasured = false;
	private View layout;
	private ListView menuList;
	
	
	private Fragment fragment ;
	private FragmentTransaction manager ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		
		fragment = new PersonInfoView();
//		manager.attach(fragment);
		
		layout = LayoutInflater.from(this).inflate(R.layout.slide_mune, null);
		initMenu();
		
		
		manager = getSupportFragmentManager().beginTransaction();
		manager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//		mMenuDrawer.getContentContainer().getId();
		manager.add(mMenuDrawer.getContentContainer().getId(), fragment);
		manager.commit();

	}

	private void initListView() {
		menuList = new ListView(MainViewActivity.this);
		// menuList
	}

	private void initMenu() {
		// mMenuDrawer = MenuDrawer.attach(this, Position.LEFT);
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_BEZEL);
//		mMenuDrawer.setContentView(R.layout.main);
		mMenuDrawer.setActiveView(layout, 0);
		mMenuDrawer.setMenuView(layout);
		mMenuDrawer.setDropShadow(R.drawable.shadow);
		mMenuDrawer.setDropShadowSize((int) getResources().getDimension(
				R.dimen.shadow_width));
		// mMenuDrawer.setDropShadowSize(0);
		mMenuDrawer.setMaxAnimationDuration(3000);
		mMenuDrawer.setHardwareLayerEnabled(true);
		mMenuDrawer.setMenuSize((int) getResources().getDimension(
				R.dimen.slidingmenu_offset));
		// mMenuDrawer.setMenuSize(width);
		
		mMenuDrawer.setOnDrawerStateChangeListener(new MenuDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == MenuDrawer.STATE_CLOSED) {
//                    commitTransactions();
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                // Do nothing
            }
        });
		
	}

	protected void commitTransactions() {
        if (manager != null && !manager.isEmpty()) {
            manager.commit();
            manager = null;
        }
    }
}
