package com.kituri.wowrich;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;


public class GameMapViewActivity extends Activity implements CustomRequest {

	private ImageView ImageViewBarriers;
	private TextView TextViewTitle;
	private TextView TextViewAbout;
	private int MotionEventX;
	private int MotionEventY;
	private CustomDialog customDialog;
	private double singleLength;

	private final int STEP_FRIST = 0;
	private final int STEP_LAST = -1;
	private final int TYPE_BARRIERS = 14;// 和ID号一样
	private final int TYPE_FIRE_STORM = 15;// 和ID号一样
	private final int TYPE_GOBLIN_ENGINERR = 16;// 和ID号一样
	private final int TYPE_EARTH_QUAKE = 17;// 和ID号一样
	
	private int type;
	private String name;
	private int index;
	private String title;
	private String about;

	private Bitmap[] BLPossessionImg;

	private Bitmap[] LMPossessionImg;
	
	private Bitmap BLsp_city_img;
	private Bitmap LMsp_city_img;
	
	private int[] playerIndex;
	public void onCreate(Bundle icicle) {
		
		if(Constants.GameMapViewActivityRun){
			this.finish();
		}else{
			Constants.EquipActivityIsRun = true;
		}
		
		super.onCreate(icicle);
		setContentView(R.layout.activity_set_barriers);
		
		singleLength = Constants._gamecanvas.getScreenWidth() / 13;
		customDialog = new CustomDialog(this, this);
		
		Bundle bunde = getIntent().getExtras();
		this.type = bunde.getInt("type");
		this.name = bunde.getString("name");
		this.about = bunde.getString("about");
		this.title = "地图范围";
		
		TextViewTitle = (TextView) findViewById(R.id.TextViewMapView);
		TextViewTitle.setText("请选择使用 " + name + " 的地点");
		TextViewAbout = (TextView)findViewById(R.id.TextViewMapViewAbout);
		TextViewAbout.setText(name + "：" + about);
		//			Constants.gameLayer.getBitmap();
		Bitmap oldBitmap = Bitmap.createBitmap(
				Constants.gameLayer.getSingleLength() * Constants.gameLayer.getRow(),
				Constants.gameLayer.getSingleLength() * Constants.gameLayer.getColumn(), Config.ARGB_8888);
		
		BLPossessionImg = new Bitmap[5];
		LMPossessionImg = new Bitmap[5];

		BLPossessionImg[0] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_red_00);
		BLPossessionImg[1] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_red_00);
		BLPossessionImg[2] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_red_01);
		BLPossessionImg[3] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_red_02);
		BLPossessionImg[4] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_red_03);

		LMPossessionImg[0] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_blue_00);
		LMPossessionImg[1] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_blue_00);
		LMPossessionImg[2] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_blue_01);
		LMPossessionImg[3] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_blue_02);
		LMPossessionImg[4] = BitmapFactory.decodeResource(
				getResources(), R.drawable.city_lv_blue_03);

		BLsp_city_img = BitmapFactory.decodeResource(
				getResources(), R.drawable.icon_bl);
		
		LMsp_city_img = BitmapFactory.decodeResource(
				getResources(), R.drawable.icon_lm);
		
		Canvas cv = new Canvas(oldBitmap);
		cv.drawBitmap(Constants.gameLayer.getBitmap(),
				0,0,null);
		printPossessionUI(cv);
		
		for (int i = 0; i < Constants.player.length; i++) {
			Constants.player[i].spriteLayer.setPosition(Constants.player[i]
					.getX(), Constants.player[i].getY());
			Constants.player[i].spriteLayer.paint(cv);
		}
		
		cv.save(Canvas.ALL_SAVE_FLAG);// 保存
		// //store
		cv.restore();// 存储
		ImageViewBarriers = (ImageView) findViewById(R.id.ImageViewbarriers);
		ImageViewBarriers.setImageBitmap(imageScale(oldBitmap, 
				Constants._gamecanvas.getScreenWidth(),
				Constants._gamecanvas.getScreenWidth()));

		playerIndex = new int[Constants.player.length];
		for(int i = 0;i<Constants.player.length;i++){
			playerIndex[i] = Constants.player[i].getIndex();
		}
	}
	
	protected void onDestroy() {  
        // TODO Auto-generated method stub  
        //System.out.println("SecondActivity--->onDestory");  
		Constants.GameMapViewActivityRun = false;
        super.onDestroy();  
    }  
	
	private void printPossessionUI(Canvas g) {
		//LMPossessionImg[Constants.gameLayer.getMapAura(i).getLV()
				for (int i = 0; i < Constants.gameLayer.getMapAuraCounts(); i++) {
					if (Constants.gameLayer.getMapAura(i).getPossession() > -1) {
						// LMPossessionImg
						if(Constants.gameLayer.getMapAura(i).getType() == 2){
						if (Constants.gameLayer.getMapAura(i).getPossession() == 0) {
							g.drawBitmap(BLsp_city_img,
									Constants.gameLayer.getPossessionPosX(i),
									Constants.gameLayer.getPossessionPosY(i), null);
						} else if (Constants.gameLayer.getMapAura(i).getPossession() == 1) {
							g.drawBitmap(LMsp_city_img,
									Constants.gameLayer.getPossessionPosX(i),
									Constants.gameLayer.getPossessionPosY(i), null);
						}
						}else 
							if(Constants.gameLayer.getMapAura(i).getType() == 1){
							if (Constants.gameLayer.getMapAura(i).getPossession() == 0) {
								g.drawBitmap(BLPossessionImg[Constants.gameLayer
										.getMapAura(i).getLV()],
										Constants.gameLayer.getPossessionPosX(i),
										Constants.gameLayer.getPossessionPosY(i), null);
							} else if (Constants.gameLayer.getMapAura(i).getPossession() == 1) {
								g.drawBitmap(LMPossessionImg[Constants.gameLayer
								 							.getMapAura(i).getLV()],
										Constants.gameLayer.getPossessionPosX(i),
										Constants.gameLayer.getPossessionPosY(i), null);
							}
							}
					}
				}
			}

	private Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {

		int src_w = bitmap.getWidth();
		int src_h = bitmap.getHeight();
		float scale_w = ((float) dst_w) / src_w;
		float scale_h = ((float) dst_h) / src_h;
		Matrix matrix = new Matrix();
		matrix.postScale(scale_w, scale_h);
		Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
				true);

		return dstbmp;
	}

	public boolean onTouchEvent(MotionEvent event) {

		// ImageViewBarriers.getLocationInWindow(location);
		// ImageViewBarriers.getLocationOnScreen(location);
		MotionEventX = (int) (event.getX() / singleLength);

		MotionEventY = (int) (event.getY() / singleLength);

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:

			UseEven(type,name);

			break;

		case MotionEvent.ACTION_UP:

			// showNote("MotionEventX:" + MotionEventX + "MotionEventY:" +
			// MotionEventY);

			break;

		case MotionEvent.ACTION_MOVE:

			break;

		}

		return super.onTouchEvent(event);
	}

	private boolean IsPlayerIndex(int index){
		for(int i = 0;i < playerIndex.length;i++){
			if(playerIndex[i] == index){
				return true;
			}
		}
		return false;
	}
	
	private void UseEven(int type,String name) {
		// TODO Auto-generated method stub
		if (Constants.gameLayer.IsMapAura(MotionEventX, MotionEventY)) {
		this.index = Constants.gameLayer.getIndex(MotionEventX, MotionEventY);
		int possession= Constants.gameLayer.getPossession(index);
		int lv= Constants.gameLayer.getMapAura(index).getLV();
		int maxlv= Constants.gameLayer.getMapAura(index).getMaxLV();

		
		switch (type) {
		case TYPE_BARRIERS:
			if(IsPlayerIndex(index) == false){
				customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL,
						title, "是否要在  "
								+ Constants.gameLayer.getName(MotionEventX,
										MotionEventY) + " 使用 " + name + " ？",
						Constants.gameLayer.getDrawableID(MotionEventX,
								MotionEventY), TYPE_BARRIERS, STEP_FRIST);
			}
			break;
		case TYPE_FIRE_STORM:
			if(possession != Constants.playerIndex){
				//才可以使用
				if(lv > 1){
				customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL,
						title, "是否要在  "
								+ Constants.gameLayer.getName(MotionEventX,
										MotionEventY) + " 使用 " + name + " ？",
						Constants.gameLayer.getDrawableID(MotionEventX,
								MotionEventY), TYPE_FIRE_STORM, STEP_FRIST);
				}else{
					customDialog.Show(CustomDialog.DIG_OK,
							title,"该建筑物级别为最低级别！无法使用 " + name + " ！",
							Constants.gameLayer.getDrawableID(MotionEventX,
									MotionEventY), TYPE_FIRE_STORM, STEP_FRIST);
				}
			}
			break;
		case TYPE_GOBLIN_ENGINERR:
			if(possession == Constants.playerIndex){
				//才可以使用
				if(lv < maxlv){
				customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL,
						title, "是否要在  "
								+ Constants.gameLayer.getName(MotionEventX,
										MotionEventY) + " 使用 " + name + " ？",
						Constants.gameLayer.getDrawableID(MotionEventX,
								MotionEventY), TYPE_GOBLIN_ENGINERR, STEP_FRIST);
				}else{
					customDialog.Show(CustomDialog.DIG_OK,
							title,"该建筑物级别已经是最高级别！无法使用 " + name + " ！",
							Constants.gameLayer.getDrawableID(MotionEventX,
									MotionEventY), TYPE_FIRE_STORM, STEP_FRIST);
				}
			}
			break;
		case TYPE_EARTH_QUAKE:
			if(possession != Constants.playerIndex && possession != -1){
				//才可以使用
				customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL,
						title, "是否要在  "
								+ Constants.gameLayer.getName(MotionEventX,
										MotionEventY) + " 使用 " + name + " ？",
						Constants.gameLayer.getDrawableID(MotionEventX,
								MotionEventY), TYPE_EARTH_QUAKE, STEP_FRIST);
			}
			break;
		}
		}
	}
	
	private void EffectBarriers(){
		Constants.gameLayer.setBarriers(true, MotionEventX,
				MotionEventY, Constants.gameLayer.getIndex(
						MotionEventX, MotionEventY));
//		Constants.gameLayer.setBarriers
//		EvenOther.useBarriers(name, index);
		Constants.player[Constants.playerIndex].removeItems(TYPE_BARRIERS);
		//Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";
		customDialog.Show(CustomDialog.BUTTON_CENTER,
				title, "使用了 " + name + "。道路上出现了一个冰冻陷阱！", 
				Constants.gameLayer.getDrawableID(MotionEventX,
						MotionEventY), TYPE_BARRIERS, STEP_LAST);
	}

	private void EffectFireStorm(){
		Constants.gameLayer.setMapAuraLVChange(index, -1);
		Constants.player[Constants.playerIndex].removeItems(TYPE_FIRE_STORM);
		//Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";
		customDialog.Show(CustomDialog.BUTTON_CENTER,
				title, "火焰吞噬着敌人的领地 ！" + 
				Constants.gameLayer.getName(index) + "等级下降！", 
				Constants.gameLayer.getDrawableID(MotionEventX,
						MotionEventY), TYPE_FIRE_STORM, STEP_LAST);
	}
	private void EffectGoblinEnginerr(){
		Constants.gameLayer.setMapAuraLVChange(index, 1);
		Constants.player[Constants.playerIndex].removeItems(TYPE_GOBLIN_ENGINERR);
		//Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";
		customDialog.Show(CustomDialog.BUTTON_CENTER,
				title,"在一阵吵杂的机器轰鸣声中，您的领地 " + 
				Constants.gameLayer.getName(index) + "等级上升一级！", 
				Constants.gameLayer.getDrawableID(MotionEventX,
						MotionEventY), TYPE_GOBLIN_ENGINERR, STEP_LAST);
	}
	private void EffectEarthQuake(){
		//Constants.gameLayer.
		Constants.gameLayer.setMapAuraPossession( index, - 1);
		Constants.gameLayer.setMapAuraLVChange(index, -4);
		Constants.player[Constants.playerIndex].removeItems(TYPE_EARTH_QUAKE);
		//Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";
		customDialog.Show(CustomDialog.BUTTON_CENTER,
				title, "一阵天崩地裂后，"
				+ Constants.gameLayer.getName(index) + "已被夷为平地。", 
				Constants.gameLayer.getDrawableID(MotionEventX,
						MotionEventY), TYPE_EARTH_QUAKE, STEP_LAST);
	}
	@Override
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		if(STEP_LAST == Step){
			this.finish();
		}
		
		if(ButtonIndex == CustomDialog.BUTTON_RIGHT){
			return false;
		}
		
		switch (TypeID) {
		case TYPE_BARRIERS:
			if (ButtonIndex == CustomDialog.BUTTON_LEFT) {
				EffectBarriers();
			}
			break;
		case TYPE_FIRE_STORM:
			if (ButtonIndex == CustomDialog.BUTTON_LEFT) {
				EffectFireStorm();
			}
			break;
		case TYPE_GOBLIN_ENGINERR:
			if (ButtonIndex == CustomDialog.BUTTON_LEFT) {
				EffectGoblinEnginerr();
			}
			break;
		case TYPE_EARTH_QUAKE:
			if (ButtonIndex == CustomDialog.BUTTON_LEFT) {
				EffectEarthQuake();
			}
			break;
			
		}

		//this.finish();
		return false;
	}

//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		switch (keyCode) {
//		case KeyEvent.KEYCODE_BACK:
//		{
//			Intent intent = new Intent(this, GameItemsActivity.class);
//			startActivity(intent);
//		}
//			return false;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
	
}
