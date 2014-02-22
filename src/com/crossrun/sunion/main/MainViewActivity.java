package com.crossrun.sunion.main;

import java.util.ArrayList;
import java.util.List;

import net.simonvt.menudrawer.MenuDrawer;
import com.crossrun.sunion.R;
import com.crossrun.sunion.bean.SlideMenuItemInfo;
import com.crossrun.sunion.view.base.BaseViewActivity;
import com.crossrun.sunion.view.component.MenuAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class MainViewActivity extends BaseViewActivity {

	private FragmentManager mFragmentManager;
	private FragmentTransaction mFragmentTransaction;

	private String mCurrentFragmentTag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mFragmentManager = getSupportFragmentManager();

		mCurrentFragmentTag = "test";
		attachFragment(mMenuDrawer.getContentContainer().getId(),
				getFragment(mCurrentFragmentTag), mCurrentFragmentTag);
		commitTransactions();

		mMenuDrawer
				.setOnDrawerStateChangeListener(new MenuDrawer.OnDrawerStateChangeListener() {
					@Override
					public void onDrawerStateChange(int oldState, int newState) {
						if (newState == MenuDrawer.STATE_CLOSED) {
							commitTransactions();
						}
					}

					@Override
					public void onDrawerSlide(float openRatio, int offsetPixels) {
						// Do nothing
					}
				});

	}

	protected FragmentTransaction ensureTransaction() {
		if (mFragmentTransaction == null) {
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		}

		return mFragmentTransaction;
	}

	private Fragment getFragment(String tag) {
		Fragment f = mFragmentManager.findFragmentByTag(tag);

		if (f == null) {
			f = SampleFragment.newInstance(tag);
		}

		return f;
	}

	protected void attachFragment(int layout, Fragment f, String tag) {
		if (f != null) {
			if (f.isDetached()) {
				ensureTransaction();
				mFragmentTransaction.attach(f);
			} else if (!f.isAdded()) {
				ensureTransaction();
				mFragmentTransaction.add(layout, f, tag);
			}
//			ensureTransaction();
//			mFragmentTransaction.replace(R.id.main_view, f);
		} else {
			System.out.println("fragment == null");
		}
	}

	protected void detachFragment(Fragment f) {
		if (f != null && !f.isDetached()) {
			ensureTransaction();
			mFragmentTransaction.detach(f);
		}
	}

	protected void commitTransactions() {
		if (mFragmentTransaction != null && !mFragmentTransaction.isEmpty()) {
			mFragmentTransaction.addToBackStack(mCurrentFragmentTag);
			mFragmentTransaction.commit();
			mFragmentTransaction = null;
		}
	}

	public static class SampleFragment extends Fragment {

		private static final String ARG_TEXT = "net.simonvt.menudrawer.samples.SampleFragment.text";

		public static SampleFragment newInstance(String text) {
			SampleFragment f = new SampleFragment();

			Bundle args = new Bundle();
			args.putString(ARG_TEXT, text);
			f.setArguments(args);

			return f;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.activity_main, container, false);

			return v;
		}
	}

	/**
	 * 初始化菜单数据
	 */
	@Override
	protected void initmMenuData() {
		titles = new ArrayList<String>();
		items = new ArrayList<List<SlideMenuItemInfo>>();
		titles.add(new String("我的组织"));
		titles.add(new String("所有组织"));
		titles.add(new String("个人设置"));
		myUnions = new ArrayList<SlideMenuItemInfo>();
		myUnions.add(new SlideMenuItemInfo("我的组织====", ""));
		allUnions = new ArrayList<SlideMenuItemInfo>();
		allUnions.add(new SlideMenuItemInfo("所有组织===", ""));
		personalSetting = new ArrayList<SlideMenuItemInfo>();
		personalSetting.add(new SlideMenuItemInfo("个人设置===", ""));
		items.add(myUnions);
		items.add(allUnions);
		items.add(personalSetting);

		mAdapter = new MenuAdapter(titles, items, MainViewActivity.this);
		partMenu.setAdapter(mAdapter);
	}

	/**
	 * 初始化菜单监听
	 */
	@Override
	protected void initmMenuListener() {
		partMenu.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				for (int i = 0; i < titles.size(); i++) {
					if (groupPosition != i) {
						partMenu.collapseGroup(i);
					}
				}
			}
		});
	}

	/**
	 * 重写回退事件
	 */
	@Override
	public void onBackPressed() {
		if (mMenuDrawer != null && mMenuDrawer.isMenuVisible()) {
			mMenuDrawer.closeMenu();
		} else {
			super.onBackPressed();
		}
	}

	
	
	
}
