package com.kituri.wowrich;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.data.Constants.Constants;
import com.kituri.mediapPlayer.mediaPlayer;
import com.kituri.rich.CustomDialog;
import com.kituri.rich.GameCanvas;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.DataTools;
import com.kituri.tools.EvenOverRequest;
import com.kituri.tools.UIUtils;


public class MIDlet extends Activity implements CustomRequest, EvenOverRequest {
    /** Called when the activity is first created. */

	final static public String BTN_GO = "BTN_GO";
	final static public String BTN_ITEM = "BTN_ITEM";
	final static public String BTN_EQUIP = "BTN_EQUIP";
	final static public String BTN_STATUS = "BTN_STATUS";

	private CustomDialog customDialog;
	private Thread GameThread;
	static public Context context;
	private ImageView iv_go, iv_item, iv_equip;
	//private ScrollView scrollView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Constants.savedInstanceState = savedInstanceState;
        Constants.ActivityMIDlet = this;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Display display = getWindowManager().getDefaultDisplay();
		Constants._gamecanvas = new GameCanvas(this, display.getWidth(), display.getHeight(), this);
		if (Constants.mp == null) {
			Constants.mp = new mediaPlayer(this);
		}
		//Constants.itemsData = new ItemsData();
        customDialog = new CustomDialog(this,this);
        customDialog.show();
        customDialog.dismiss();

        setContentView(R.layout.main_activity);
		RelativeLayout rv_main = (RelativeLayout) findViewById(R.id.rv_main);
		rv_main.addView(Constants._gamecanvas);
//        addContentView(Constants._gamecanvas, 
//        		new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        Constants.animation = AnimationUtils.loadAnimation(this,R.anim.shake);
        
        GameThread = new Thread(Constants._gamecanvas);
        GameThread.start();
		iv_go = (ImageView) findViewById(R.id.iv_go);
		iv_go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Constants._gamecanvas.goClick();
			}
		});

		iv_item = (ImageView) findViewById(R.id.iv_item);
		iv_item.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Constants._gamecanvas.itemClick();
			}
		});

		iv_equip = (ImageView) findViewById(R.id.iv_equip);
		iv_equip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Constants._gamecanvas.equipClick();
			}
		});

		IntentFilter filter= new IntentFilter();
		filter.addAction(BTN_GO);
		filter.addAction(BTN_ITEM);
		filter.addAction(BTN_EQUIP);
		registerReceiver(UIReceiver, filter);

		//Animation animation;  
		///Constants._gamecanvas.startAnimation(animation);
		//animation = AnimationUtils.loadAnimation(context,R.anim.shake);
        
        //Constants.IsPaint = false;
    }
//    private void openOptionsDialog() {
//	    new AlertDialog.Builder(this)
//	         .setTitle("关于")
//	         .setMessage("Android BMI Calc 0.6")
//	         .setPositiveButton("连接：",
//	             new DialogInterface.OnClickListener(){
//	                  public void onClick(
//	                      DialogInterface dialoginterface, int i){
//	                  }
//	              })
//	        .setNegativeButton("首页",
//	           new DialogInterface.OnClickListener(){
//	                    public void onClick(
//	                        DialogInterface dialoginterface, int i){
//	                         //go to url
//	                        Uri uri = Uri.parse("http://androidbmi.googlecode.com/");
//	                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//	                         startActivity(intent);
//	                    }
//	            })
//	         .show();
//	    }
//	public boolean onKeyDown(int keyCode, KeyEvent msg) {
//		return _gamecanvas.onKeyDown(keyCode, msg);
//	}
//	public boolean onKeyUp(int keyCode, KeyEvent msg) {
//		return _gamecanvas.onKeyUp(keyCode, msg);
//	}
    
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			//showDialog(1);
		if(Constants._gamecanvas.CanPlayerAction()){	
			customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL,
					getResources().getText(R.string.app_name).toString(),
					"确定要退出游戏？", R.drawable.icon,-1,0);
			
		}
		return false;
		}
		return super.onKeyDown(keyCode, event);
	}
   

	@Override
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		if(ButtonIndex == CustomDialog.BUTTON_LEFT){
			ExitGame();
			//finish();
		}
		return false;
	}
	
	public void ExitGame(){
		SaveData(this);
		//Constants._gamecanvas.SaveData();
		Constants._gamecanvas.IsRun = false;
		Constants.ActivityMIDlet.finish();
	}

	static public void ExitGame(boolean tt){
		
		//Constants._gamecanvas.SaveData();
		Constants._gamecanvas.IsRun = false;
		Constants.ActivityMIDlet.finish();
	}
	
	public void SaveData(Context context){
		DataTools DataTools1 = new DataTools();
		DataTools1.SaveGame(context);	
	}
	
	public boolean EvenRequest(int TypeID) {
		// TODO Auto-generated method stub
		if(TypeID == -1){
		Intent intent = new Intent(this, GameItemsActivity.class);
		startActivity(intent);
		//Constants.ItemIconCanClick = true;
			UIUtils.sendUIButtonChange(this, MIDlet.BTN_ITEM, true);
		}else if(TypeID == -2){
			Intent intent = new Intent(this, GameEquipActivity.class);
			startActivity(intent);
			//Constants.EquipIconCanClick = true;
			UIUtils.sendUIButtonChange(this, MIDlet.BTN_EQUIP, true);
		}
		return false;
	}

	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(UIReceiver);
	}



	public BroadcastReceiver UIReceiver = new BroadcastReceiver() {


		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(BTN_GO)){
				if(intent.getBooleanExtra("BTN_STATUS", false)){
					iv_go.setImageResource(R.drawable.go_even01);
				}else{
					iv_go.setImageResource(R.drawable.go_even02);
				}
			}else if(intent.getAction().equals(BTN_ITEM)){
				if(intent.getBooleanExtra("BTN_STATUS", false)){
					iv_item.setImageResource(R.drawable.item_even01);
				}else{
					iv_item.setImageResource(R.drawable.item_even02);
				}
			}if(intent.getAction().equals(BTN_EQUIP)){
				if(intent.getBooleanExtra("BTN_STATUS", false)){
					iv_equip.setImageResource(R.drawable.icon_equip_00);
				}else{
					iv_equip.setImageResource(R.drawable.icon_equip_01);
				}
			}

		}
	};

}