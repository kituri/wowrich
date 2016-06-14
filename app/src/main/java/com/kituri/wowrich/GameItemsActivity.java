package com.kituri.wowrich;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.rich.ItemsData.GameItemsAdapter;
import com.kituri.rich.ItemsData.ItemsDataEven;
import com.kituri.tools.CustomRequest;

public class GameItemsActivity extends ListActivity implements OnItemClickListener, CustomRequest{
	private final int TYPE_USE_ITEM = 0;
	private final int TYPE_NOTHING = 1;
	private final int TYPE_CHEST_OPEN = 2;
	//private final int TYPE_USE_ITEM = 0;
	private final int STEP_FRIST = 300;
	private final int STEP_LAST = 301;
	//private final int STEP_NOTHING = 302;
	private ListView GameItemsList;
	private GameItemsAdapter giAdapter;
	private CustomDialog customDialog;
	//private ItemsDataEven itemsDataEven;
	private int ID = -1;
	private final String title = "道具";
	private int keyID;
	private int chestID;
	
	public void onCreate(Bundle icicle) {
		
		if(Constants.ItemActivityIsRun){
			this.finish();
		}else{
			Constants.ItemActivityIsRun = true;
		}
		
		super.onCreate(icicle);

		setContentView(R.layout.activity_game_items);
		GameItemsList();
		customDialog = new CustomDialog(this,this);

	}
	
	protected void onDestroy() {  
        // TODO Auto-generated method stub  
        //System.out.println("SecondActivity--->onDestory");  
		Constants.ItemActivityIsRun = false;
		//Constants.ItemActivityIsActiton = false;
        super.onDestroy();  
    }  
	
	private void GameItemsList(){
		GameItemsList = getListView();
		giAdapter = new GameItemsAdapter(this);
		GameItemsList.setAdapter(giAdapter);
		GameItemsList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		// TODO Auto-generated method stub
		//index
		
		//Constants.player[Constants.playerIndex].getItems(index).getID();
		//14的情况下是设置路障
		//Log.i("Items", "index:" + index);

		  this.ID = Constants.player[Constants.playerIndex].getItems(index).getID();
		if(ID >= 14 && ID <= 17){
			jumpSetBarriers(ID,
					Constants.player[Constants.playerIndex].getItems(index).getName(),
					Constants.player[Constants.playerIndex].getItems(index).getAbout());
		}else 
			if(ID >= 18 && ID <= 21){
				keyEven(index);
			}else
				
				if(ID >= 22 && ID <= 25){
					chestEven(index);
				}else{
		//this.ID = Constants.player[Constants.playerIndex].getItems(index).getID();
		customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL,
				title, "是否要使用 " +  Constants.player[Constants.playerIndex].getItems(index).getName()
				+" ？"
				, Constants.player[Constants.playerIndex].getItems(index).getDrawableID()
, TYPE_USE_ITEM , STEP_FRIST);
		}
	}

	private void keyEven(int index){
		
		if(Constants.player[Constants.playerIndex].getItemsByID(ID+4).getCounts() == 0){
			//有钥匙，没箱子
			customDialog.Show(CustomDialog.DIG_OK,title,
					"这是一把打开对应宝箱的钥匙", Constants.player[Constants.playerIndex].getItems(index).getDrawableID()
					, TYPE_NOTHING , STEP_FRIST);
		}else{
			//有钥匙，有箱子
			//EVEN事件。
			this.keyID = ID ;
			this.chestID = ID + 4;
			ItemsDataEven.Even(keyID,chestID);
			OpenChest();
		}
	}

	private void chestEven(int index){
		//如果有箱子
		if(Constants.player[Constants.playerIndex].getItemsByID(ID-4).getCounts() == 0){
			//有箱子，没钥匙
			customDialog.Show(CustomDialog.DIG_OK,title,
					"打开该宝箱需要对应的钥匙", Constants.player[Constants.playerIndex].getItems(index).getDrawableID()
					, TYPE_NOTHING , STEP_FRIST);
		}else{
			this.keyID = ID - 4;
			this.chestID = ID;
			//有箱子，有钥匙
			//EVEN事件。
			ItemsDataEven.Even(keyID,chestID);
			OpenChest();
		}
	}

	private void OpenChest(){
		customDialog.Show(CustomDialog.DIG_OK,
				title, Constants.ItemMessage
				, Constants.player[Constants.playerIndex].getItemsByID(ID).getDrawableID()
, TYPE_CHEST_OPEN, STEP_LAST);
	}
	
	@Override
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub

		if(TypeID == TYPE_USE_ITEM){
			if(Step == STEP_FRIST){
			if(ButtonIndex == CustomDialog.BUTTON_LEFT){
				ItemsDataEven.Even(ID,
						Constants.player[Constants.playerIndex].getItemsByID(ID).getName());
				tipMessage(ID);
			}
			}else if(Step == STEP_LAST){
				Constants.player[Constants.playerIndex].removeItems(ID);
				this.finish();
			}
		}else if(TypeID == TYPE_CHEST_OPEN){
			Constants.player[Constants.playerIndex].removeItems(keyID);
			Constants.player[Constants.playerIndex].removeItems(chestID);
			this.finish();
		}
		return false;
	}

	public void tipMessage(int ID) {
		//Log.i("removeItems index:", ""+index);
		customDialog.Show(CustomDialog.DIG_OK,
				title, Constants.ItemMessage
				, Constants.player[Constants.playerIndex].getItemsByID(ID).getDrawableID()
, TYPE_USE_ITEM , STEP_LAST);
		// TODO Auto-generated method stub
	}
	
	private void jumpSetBarriers(int type,String name,String about){
		Intent intent = new Intent(this, GameMapViewActivity.class);
		Bundle bundle = new Bundle();  
		bundle.putInt("type", type);  
		bundle.putString("name", name);  
		bundle.putString("about", about);  
		intent.putExtras(bundle);  
		//context.startActivity(intent); 或 context.startService(intent);  
		startActivity(intent);
		this.finish();
	}
}
