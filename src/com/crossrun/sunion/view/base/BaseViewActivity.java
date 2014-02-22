package com.crossrun.sunion.view.base;

import java.util.List;

import com.crossrun.sunion.R;
import com.crossrun.sunion.bean.SlideMenuItemInfo;
import com.crossrun.sunion.view.component.MenuAdapter;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.ExpandableListView;

public abstract class BaseViewActivity extends FragmentActivity{

	protected MenuDrawer mMenuDrawer;

	private Position DRAWER_POSITION = Position.LEFT;

	private int DRAG_MODE = MenuDrawer.MENU_DRAG_CONTENT;

	// private View mMenu;
//	private RelativeLayout mMenu;

	// private int mActivePosition = 0;
	
	protected List<SlideMenuItemInfo> partList;

	protected List<String> titles;
	protected List<List<SlideMenuItemInfo>> items;

	protected List<SlideMenuItemInfo> myUnions;
	protected List<SlideMenuItemInfo> allUnions;
	protected List<SlideMenuItemInfo> personalSetting;

	protected ExpandableListView partMenu;
	protected MenuAdapter mAdapter;

	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);

//		setContentView(R.layout.main);
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY,
				DRAWER_POSITION, DRAG_MODE);

		// mMenu = LayoutInflater.from(this).inflate(R.layout.slide_mune, null);
		// partMenu = (ExpandableListView) mMenu.findViewById(R.id.menu_list);
		partMenu = (ExpandableListView) LayoutInflater.from(this).inflate(R.layout.slide_mune, null);
//		mMenu = new RelativeLayout(getMyContext());
//		mMenu.setBackgroundColor(getResources().getColor(R.color.myBlue));
//		partMenu = new ExpandableListView(getMyContext());
//		partMenu.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
//		partMenu.setGroupIndicator(null);
//		mMenu.addView(partMenu);
		
		initmMenu();

		mMenuDrawer.setMenuView(partMenu);

		mMenuDrawer.setMenuSize((int) getResources().getDimension(
				R.dimen.slidingmenu_offset));
		mMenuDrawer.setDropShadow(R.drawable.shadow);
		mMenuDrawer.setDropShadowSize((int) getResources().getDimension(
				R.dimen.shadow_width));
		mMenuDrawer.setHardwareLayerEnabled(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH);
	}

	// public void onActiveViewChanged(View v) {
	// mMenuDrawer.setActiveView(v, mActivePosition);
	// }

	/**
	 * 初始化菜单
	 */
	private void initmMenu() {
		initmMenuData();
		initmMenuListener();
	}

	protected void initActionBar(){
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // 注意顺序 
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,      // 注意顺序 
                R.layout.title); 
	};
	/**
	 * 初始化menu数据
	 */
	protected abstract void initmMenuData();

	/**
	 * 初始化menu监听
	 */
	protected abstract void initmMenuListener();
}
