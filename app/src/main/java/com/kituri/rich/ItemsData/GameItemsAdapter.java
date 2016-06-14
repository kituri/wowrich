package com.kituri.rich.ItemsData;





import com.data.Constants.Constants;
import com.kituri.wowrich.R;
import android.content.Context;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GameItemsAdapter extends BaseAdapter {
	private Context mContext;
	private GameItemsListData[] GameItems = null;
	// 展示的文字
//	private Bitmap[] imageIcon;
	
	public GameItemsAdapter(Context context){
		//getCounts
		this.mContext = context;
		//int counts = Constants.player[Constants.playerIndex].getItemsSize();
		GameItems = Constants.player[Constants.playerIndex].getItems();
//		imageIcon = new Bitmap[counts];
		
	}
	
	
	/**
	 * 元素的个数
	 */
	


	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// 用以生成在ListView中展示的一个个元素View
	public View getView(int position, View convertView, ViewGroup parent) {
		// 优化ListView
		ItemViewCache cache;
		if (convertView == null) {
			// 这里是item(单个的)
			cache = new ItemViewCache();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_game_items, null);
			//ItemViewCache viewCache = new ItemViewCache();
			cache.list_Title = (TextView) convertView
					.findViewById(R.id.TextViewTitle);

			cache.list_About = (TextView) convertView
			.findViewById(R.id.TextViewAbout);
			
			cache.list_Counts = (TextView) convertView
			.findViewById(R.id.TextViewCounts);
			
			cache.list_Image = (ImageView) convertView
			.findViewById(R.id.ImageIcon);

			convertView.setTag(cache);
		}else{
			cache = (ItemViewCache) convertView.getTag();
		}
		//ItemViewCache cache = (ItemViewCache) convertView.getTag();
		// 设置文本和图片，然后返回这个View，用于ListView的Item的展示
		cache.list_Title.setText(GameItems[position].getName());
		cache.list_About.setText(GameItems[position].getAbout());
		cache.list_Counts.setText("　X " + GameItems[position].getCounts());		
		cache.list_Image.setBackgroundResource(GameItems[position].getDrawableID());

		return convertView;
	}

	class ItemViewCache {
		public TextView list_Title;
		public TextView list_About;
		public TextView list_Counts;
		public ImageView list_Image; 
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return GameItems.length;
	}

}
