package com.crossrun.sunion.view.component;

import java.util.List;

import com.crossrun.sunion.R;
import com.crossrun.sunion.bean.SlideMenuItemInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseExpandableListAdapter {

	private List<String> titles;
	private List<List<SlideMenuItemInfo>> items;
	private Context context;	
	
	public MenuAdapter(List<String> titles,
			List<List<SlideMenuItemInfo>> items, Context context) {
		super();
		this.titles = titles;
		this.items = items;
		this.context = context;
	}

	static class TitleViewHolder{
		TextView title;
	}
	
	static class ItemViewHolder{
		TextView item;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return items.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TitleViewHolder titleView = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.menu_title, null);
			titleView = new TitleViewHolder();
			titleView.title = (TextView) convertView.findViewById(R.id.menu_title);
			convertView.setTag(titleView);
		}else{
			titleView = (TitleViewHolder) convertView.getTag();
		}
		titleView.title.setText(titles.get(groupPosition));
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ItemViewHolder itemView = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.menu_item, null);
			itemView = new ItemViewHolder();
			itemView.item = (TextView) convertView.findViewById(R.id.menu_item);
			convertView.setTag(itemView);
		}else{
			itemView = (ItemViewHolder) convertView.getTag();
		}
		itemView.item.setText(items.get(groupPosition).get(childPosition).tag);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return items.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return titles.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return titles.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}


	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
