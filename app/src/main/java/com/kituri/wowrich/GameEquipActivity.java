package com.kituri.wowrich;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.data.Constants.Constants;
import com.kituri.rich.EquipData.EquipItemsAdapter;

public class GameEquipActivity extends Activity implements OnItemClickListener {
	// private final int TYPE_USE_ITEM = 0;
	// //private final int TYPE_USE_ITEM = 0;
	// private final int STEP_FRIST = 300;
	// private final int STEP_LAST = 301;
	private ListView GameItemsList;
	private EquipItemsAdapter giAdapter;
	private ImageView equipIcon;
	private TextView equipName;
	private TextView equipAbout;

	private Toast toast;

	// private ItemsDataEven itemsDataEven;
	// private int ID = -1;
	// private String title = "装备仓库";

	public void onCreate(Bundle icicle) {
		if(Constants.EquipActivityIsRun){
			this.finish();
		}else{
			Constants.EquipActivityIsRun = true;
		}
		super.onCreate(icicle);
		

		
		setContentView(R.layout.activity_game_equip);
		GameItemsList();

		// 设置Toast试图显示的位置屏幕居中显示
		toast = Toast.makeText(this,"", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);

		equipIcon = (ImageView) findViewById(R.id.EquipIcon);
		equipName = (TextView) findViewById(R.id.Equip_Title);
		equipAbout = (TextView) findViewById(R.id.EquipAbout);

		int index = Constants.player[Constants.playerIndex].getEquipItemsWP();
		if (index != -1) {
			equipIcon.setImageResource(Constants.player[Constants.playerIndex]
					.getEquipsByID(index).getDrawableID());
			equipName.setText(Constants.player[Constants.playerIndex]
					.getEquipsByID(index).getName());
			equipAbout.setText(Constants.player[Constants.playerIndex]
					.getEquipsByID(index).getAbout());
			switch(Constants.player[Constants.playerIndex]
			    					.getEquipsByID(index).getLV()){
			case 4:
				equipName.setTextColor(0xffa335ee);
				break;
			case 5:
				equipName.setTextColor(0xffe48937);
				break;
			}
		}

	}
	
	protected void onDestroy() {  
        // TODO Auto-generated method stub  
        //System.out.println("SecondActivity--->onDestory");  
		Constants.EquipActivityIsRun = false;
		//Constants.EquipActivityIsActiton = false;
        super.onDestroy();  
    }  
	

	private void GameItemsList() {
		GameItemsList = (ListView) findViewById(R.id.ListViewEquip);
		giAdapter = new EquipItemsAdapter(this);
		GameItemsList.setAdapter(giAdapter);
		GameItemsList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		// TODO Auto-generated method stub
		// index

		
		equipIcon.setImageResource(Constants.player[Constants.playerIndex]
				.getEquips(index).getDrawableID());

		equipName.setText(Constants.player[Constants.playerIndex].getEquips(
				index).getName());

		equipAbout.setText(Constants.player[Constants.playerIndex].getEquips(
				index).getAbout());

		switch(Constants.player[Constants.playerIndex]
		        				.getEquips(index).getLV()){
		case 4:
			equipName.setTextColor(0xffa335ee);
			break;
		case 5:
			equipName.setTextColor(0xffe48937);
			break;
		}
		
		// equipItemsBUFF
		Constants.player[Constants.playerIndex]
				.setEquipItemsWP(Constants.player[Constants.playerIndex]
						.getEquips(index).getID());

		toast = Toast.makeText(this, "已装备　" + Constants.player[Constants.playerIndex]
				.getEquips(index).getName() + "　", Toast.LENGTH_SHORT);
		toast.show();

		// Toast.makeText(this,
		// Constants.player[Constants.playerIndex]
		// .getEquips(index).getDrawableID(),
		// Toast.LENGTH_SHORT);

		// Constants.player[Constants.playerIndex].setEquipAtk(Constants.player[
		// Constants.playerIndex].getEquips(index).getID());
		// Constants.player[Constants.playerIndex].getItems(index).getID();
		// 14的情况下是设置路障
		// Log.i("Items", "index:" + index);
		// this.ID =
		// Constants.player[Constants.playerIndex].getItems(index).getID();
		// customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL,
		// title, "是否要使用 " +
		// Constants.player[Constants.playerIndex].getItems(index).getName()
		// +" ？"
		// ,
		// Constants.player[Constants.playerIndex].getItems(index).getDrawableID
		// ()
		// , TYPE_USE_ITEM , STEP_FRIST);
	}

	// public void tipMessage(int ID) {
	// //Log.i("removeItems index:", ""+index);
	// customDialog.Show(CustomDialog.DIG_OK,
	// title, Constants.ItemMessage
	// ,
	// Constants.player[Constants.playerIndex].getItemsByID(ID).getDrawableID()
	// , TYPE_USE_ITEM , STEP_LAST);
	// // TODO Auto-generated method stub
	// }

}
