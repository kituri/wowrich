package com.kituri.rich.EquipData;





import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.data.Constants.Constants;
import com.kituri.wowrich.R;

//import android.util.Log;

public class EquipItemsAdapter extends BaseAdapter {
	private Context mContext;
	private EquipItemsListData[] GameItems = null;
	
	public EquipItemsAdapter(Context context){
		//getCounts
		this.mContext = context;
		//int counts = Constants.player[Constants.playerIndex].getItemsSize();
		GameItems = Constants.player[Constants.playerIndex].getEquips();
//		imageIcon = new Bitmap[counts];
		
	}



	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}


	public View getView(int position, View convertView, ViewGroup parent) {

		ItemViewCache cache;
		if (convertView == null) {

			cache = new ItemViewCache();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_game_items, null);
			//ItemViewCache viewCache = new ItemViewCache();
			cache.list_Title = (TextView) convertView
					.findViewById(R.id.TextViewTitle);

			cache.list_About = (TextView) convertView
			.findViewById(R.id.TextViewAbout);
			
//			cache.list_Counts = (TextView) convertView
//			.findViewById(R.id.TextViewCounts);
//
//			cache.list_Counts.setVisibility(View.GONE);
			
			cache.list_Image = (ImageView) convertView
			.findViewById(R.id.ImageIcon);
			
			

			convertView.setTag(cache);
		}else{
			cache = (ItemViewCache) convertView.getTag();
		}
		//ItemViewCache cache = (ItemViewCache) convertView.getTag();

		cache.list_Title.setText(GameItems[position].getName());
		switch(GameItems[position].getLV()){
		case 4:
			cache.list_Title.setTextColor(0xffa335ee);
			break;
		case 5:
			cache.list_Title.setTextColor(0xffe48937);
			break;
		}
		
		
		cache.list_About.setText(GameItems[position].getAbout());
		//cache.list_Counts.setText("");
		
		cache.list_Image.setBackgroundResource(GameItems[position].getDrawableID());

		
		return convertView;
	}

	class ItemViewCache {
		public TextView list_Title;
		public TextView list_About;
		//public TextView list_Counts;
		public ImageView list_Image; 
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return GameItems.length;
	}

}
