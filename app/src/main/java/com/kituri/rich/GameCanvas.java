package com.kituri.rich;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.data.Constants.Constants;
import com.data.GameEvenData.eBattleEven;
import com.data.mapdata.MapLayer;
import com.data.playerdata.PlayerData;
import com.kituri.wowrich.MIDlet;
import com.kituri.wowrich.R;
import com.kituri.tools.DataTools;
import com.kituri.tools.EvenOverRequest;
import com.kituri.tools.UIUtils;

public class GameCanvas extends View implements Runnable, OnClickListener,
		EvenOverRequest {

	// public static final int W = 320;
	// public static final int H = 480;

	
	final private int screenWidth;
	final private int screenHeight;

	private int GameCanvasWidth;
	private int GameCanvasHeight;

	//private Paint PaintBlack;
	private Paint PaintWhite;
	private Paint PaintWhiteFont;
	// private Paint PaintWhiteBig;

	private double MotionEventX;
	private double MotionEventY;
	// Animation animation;
	// /GameCavas.startAnimation(animation);
	Context _context;
	// public LayerManager lm = null;
	//
	// public TiledLayer bgLayer = null;

	// public Sprite spriteLayer = null;

	// public Sprite spriteLayer1 = null;

	private Handler mHandler;

	// 地图
	public Bitmap bgImage = null;

	// public Bitmap spriteImage = null;
	//
	// public Bitmap spriteImage1 = null;

	// public int[][] frameIndex = { { 0, 1, 2, 3 }, { 4, 5, 6, 7 },
	// { 8, 9, 10, 11 }, { 12, 13, 14, 15 } };

	public int viewX = 0;

	public int viewY = 0;

	public int viewX1 = 0;

	public int viewY1 = 0;

	public int moveStep = -1;
	//
	// public int viewX2 = 0;
	//
	// public int viewY2 = 0;

	private GameEven gameEven;

	// private Random random;

	// public PlayerData player01;
	// public PlayerData player02;

	// Constants.DebugMode_BattleWin = true;

	/** 游戏速度控制参数(单位豪秒) FPS = 1000 / C_FRAME_INTERVAL； */
	static private final int C_FRAME_INTERVAL = 30;

	public int PlayerDataFrame;
	public int PlayerMoveSpeed = 0;
	public final int FrameSpeed = 16;

	int textX = 0;

	public boolean IsRun;

	private Bitmap[] BLPossessionImg;

	private Bitmap[] LMPossessionImg;

	private Bitmap BLsp_city_img;
	private Bitmap LMsp_city_img;

	private Bitmap GoIcon;
	private Bitmap ItemIcon;
	private Bitmap EquipIcon;

	private Bitmap GoIconFalse;
	private Bitmap ItemIconFalse;
	private Bitmap EquipIconFalse;

//	private int GoIconX;
//	private int GoIconY;
//
//	private int ItemIconX;
//	private int ItemIconY;
//
//	private int EquipIconX;
//	private int EquipIconY;

	private EvenOverRequest evenOverRequest;

	private Bitmap MapBarriersImg;

	//private GameSaveData gameSaveData;
	
	// private Bitmap gameBG;
	public GameCanvas(Context context, int screenWidth, int screenHeight,
			EvenOverRequest evenOverRequest) {
		super(context);
		this.evenOverRequest = evenOverRequest;

		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		this.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));

		_context = context;

		InitPaint(_context);
//NO SAVE



		Constants.gameLayer = new MapLayer(context, screenWidth, screenHeight);

		if(Constants.LoadSaveMode){
			LoadData();
		}else{
			InitData(_context);
		}

		bgImage = Constants.gameLayer.getBitmap();

		GameCanvasWidth = bgImage.getWidth();
		GameCanvasHeight = bgImage.getHeight();

		this.setOnClickListener(this);

		// mHandler.sendEmptyMessage(UPDATE_TEXT);

		Constants.updata = true;
		Constants.IsPaint = true;

		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				// switch (msg.what) {
				gameEven.TypeEven(msg.what);
				// }
				super.handleMessage(msg);
			}
		};

		if(Constants.DebugMode){
		//Constants.player[0].setMoneyChange(money);
		//Constants.player[1].setMoneyChange(-4999);
			for (int i = 0; i < 26; i++) {
				Constants.player[0].addItems(i);
			}

			for (int i = 0; i < 9; i++) {
				Constants.player[0].addEquips(i);
			}
		}
		this.IsRun = true;
		// new Thread(this).start();
	}

	// private Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
	//
	// int src_w = bitmap.getWidth();
	// int src_h = bitmap.getHeight();
	// float scale_w = ((float) dst_w) / src_w;
	// float scale_h = ((float) dst_h) / src_h;
	// Matrix matrix = new Matrix();
	// matrix.postScale(scale_w, scale_h);
	// Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
	// true);
	//
	// return dstbmp;
	// }

private void LoadData(){
	DataTools DataTools1 = new DataTools();
	GameSaveData gameSaveData = DataTools1.LoadGame(_context);
	gameSaveData.LoadData();
	InitSprite(_context);
	//Constants.gameLayer = gameSaveData.gameLayer;
	//Constants.player = gameSaveData.player;
	//Constants.playerIndex = gameSaveData.playerIndex;
	
}


	
	public void onDraw(Canvas g) {
		// super.onDraw(canvas)
		clearScreen(g);
		if (Constants.IsPaint) {
			// g.drawBitmap(gameBG, 0, 0, null);
			g.drawBitmap(bgImage, -viewX, -viewY, null);
			printMapBarriers(g);
			printDataUI(g);
			printShowUI(g);
			printPossessionUI(g);
			//printOnClickIconUI(g);
			for (int i = 0; i < Constants.player.length; i++) {
				Constants.player[i].spriteLayer.setPosition(Constants.player[i]
						.getX()
						- viewX, Constants.player[i].getY() - viewY);
				Constants.player[i].spriteLayer.paint(g);
			}
		}
	}

	private void AutoCamera() {
		// TODO Auto-generated method stub
		// 自动移动的速度 数值越大越快

		int AutoSpeed = 1;
		int _viewX_MIX = 0;
		int _viewX_MAX = GameCanvasWidth - screenWidth;
		int _viewY_MIX = 0;
		int _viewY_MAX = GameCanvasHeight - screenHeight;

		viewX1 = Constants.player[Constants.playerIndex].getX() - screenWidth
				/ 2;
		viewY1 = Constants.player[Constants.playerIndex].getY() - screenHeight
				/ 2;

		if (viewX < viewX1) {
			viewX += AutoSpeed;

		} else if (viewX > viewX1) {
			viewX -= AutoSpeed;
		}

		if (viewY < viewY1) {
			viewY += AutoSpeed;

		} else if (viewY > viewY1) {
			viewY -= AutoSpeed;
		}
		// 屏幕高 480 我的游戏 高 200
		// 研究滚屏算法

		if (viewX > _viewX_MAX) {
			viewX = _viewX_MAX;
		} else if (viewX < _viewX_MIX) {
			viewX = _viewX_MIX;
		}

		if (viewY > _viewY_MAX) {
			viewY = _viewY_MAX;
		} else if (viewY < _viewY_MIX) {
			viewY = _viewY_MIX;
		}

		if (_viewX_MAX <= _viewX_MIX) {
			viewX = 0;
		}
		if (_viewY_MAX <= _viewY_MIX) {
			viewY = 0;
		}
	}

	private void updata() {
		if (Constants.updata) {
			setDown();
			OnClickIconUILogic();
			if (Constants.player[Constants.playerIndex].isTurn()) {
				// 测试改步数实例：
				LogicMoveSetp();
			}
		}

		// BattleEffect();
		for (int i = 0; i < 6; i++) {
			AutoCamera();
		}

	}

	private void OnClickIconUILogic() {
		if (Constants.player[Constants.playerIndex].getItemsSize() > 0) {
			//Constants.ItemIconCanClick = true;
			UIUtils.sendUIButtonChange(getContext(), MIDlet.BTN_ITEM, true);
		} else {
			//Constants.ItemIconCanClick = false;
			UIUtils.sendUIButtonChange(getContext(), MIDlet.BTN_ITEM, false);
		}

		if (Constants.player[Constants.playerIndex].getEquipsSize() > 0) {
			//Constants.EquipIconCanClick = true;
			UIUtils.sendUIButtonChange(getContext(), MIDlet.BTN_EQUIP, true);
		} else {
			//Constants.EquipIconCanClick = false;
			UIUtils.sendUIButtonChange(getContext(), MIDlet.BTN_EQUIP, false);
		}
	}

	public void play() {
	}

	public void stop() {
	}

	/**
	 * 游戏线程处理函数 作用：主要用来控制游戏的速度
	 * */
	public void run() {

		long frameElapse, frameTick;
		try {
			frameTick = System.currentTimeMillis();
			while (IsRun) {
				frameElapse = System.currentTimeMillis() - frameTick;
				if (frameElapse < C_FRAME_INTERVAL) {
					Thread.sleep((C_FRAME_INTERVAL - frameElapse));
				}
				// 更新逻辑
				updata();
				// 刷新渲染
				postInvalidate();
				frameTick = System.currentTimeMillis();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// flushGraphics();
		// OnDrawGai(graphics);
	}

	public void printShowUI(Canvas g) {
		// 对话框长和宽，默认居中
		// screenHeight

		// int _viewX_MIX = 0;
		// int _viewX_MAX = GameCanvasWidth - screenWidth;
		// int _viewY_MIX = 0;
		// int _viewY_MAX = GameCanvasHeight - screenHeight;

		int DigX = GameCanvasWidth / 2;
		int DigY = Constants.gameLayer.getSingleLength() * 3;
		if (screenWidth < GameCanvasWidth) {
			DigX = screenWidth / 2;
		}
		// if (screenHeight < GameCanvasHeight) {
		// DigY = screenHeight / 2;
		// }

		// 路过了GO点的状态
		if (Constants.player[Constants.playerIndex].isGo()) {
			g.drawText(Constants.player[Constants.playerIndex].getName()
					+ "经过起点。得到一些奖励！", DigX, DigY, PaintWhiteFont);
		} else {
			// 准备掷骰子状态
			if (Constants.player[Constants.playerIndex].Status == -1) {

				g.drawText("轮到了 "
						+ Constants.player[Constants.playerIndex].getName()
						+ " 的回合", DigX, DigY, PaintWhiteFont);
			}
			// //移动中状态
			if (Constants.player[Constants.playerIndex].Status == 1) {
				if (moveStep != -1) {
					g.drawText(Constants.player[Constants.playerIndex]
							.getName()
							+ "投出了" + moveStep, DigX, DigY, PaintWhiteFont);
				}
			}
		}

		// "player投出了： " + moveStep, getViewWidth() / 2 - 40,
		// getViewHeight() / 2, _paint
	}

	public void printDataUI(Canvas g) {
		// int tempX = GameCanvasWidth;
		// int tempY = GameCanvasHeight;
		int LineHeight = 36;
		int playerWidth = screenWidth / 2
				- Constants.gameLayer.getSingleLength() * 2;

		// int Height = GameCanvasHeight;
		int Height = Constants.gameLayer.getSingleLength() * 4;
		int Height1 = Constants.gameLayer.getSingleLength() * 7;

		g.drawText("======" + Constants.player[0].getName() + "======",
				screenWidth / 2, Height, PaintWhiteFont);
		g.drawText("　金钱：　" + Constants.player[0].getMoney() + " G",
				playerWidth, Height + LineHeight, PaintWhite);
		g.drawText("　等级：　" + Constants.player[0].getLV(), playerWidth, Height
				+ LineHeight * 2, PaintWhite);
		g.drawText("　经验：　" + Constants.player[0].getExp() + "　/　"
				+ Constants.player[0].NextLVExp(), playerWidth, Height
				+ LineHeight * 3, PaintWhite);
		g.drawText("　攻击：　" + Constants.player[0].getAtk() + "　/　"
				+ Constants.player[0].getAtkMax() + " + "
				+ Constants.player[0].getAtkBuff(), playerWidth, Height
				+ LineHeight * 4, PaintWhite);
		g.drawText("　体力：　" + Constants.player[0].getStamina() + " ("
				+ Constants.player[0].getPerStamina() + "%)", playerWidth,
				Height + LineHeight * 5, PaintWhite);
		int index = Constants.player[0].getEquipItemsWP();
		String equipName = "无";
		if (index != -1) {
			equipName = Constants.player[0].getEquipsByID(index).getName();
		}
		g.drawText("　装备：　" + equipName, playerWidth,
				Height + LineHeight * 6, PaintWhite);

		g.drawText("======" + Constants.player[1].getName() + "======",
				screenWidth / 2, Height1, PaintWhiteFont);
		g.drawText("　金钱：　" + Constants.player[1].getMoney() + " G",
				playerWidth, Height1 + LineHeight, PaintWhite);
		g.drawText("　等级：　" + Constants.player[1].getLV(), playerWidth, Height1
				+ LineHeight * 2, PaintWhite);
		g.drawText("　经验：　" + Constants.player[1].getExp() + "　/　"
				+ Constants.player[1].NextLVExp(), playerWidth, Height1
				+ LineHeight * 3, PaintWhite);
		g.drawText("　攻击：　" + Constants.player[1].getAtk() + "　/　"
				+ Constants.player[1].getAtkMax() + " + "
				+ Constants.player[1].getAtkBuff(), playerWidth, Height1
				+ LineHeight * 4, PaintWhite);
		g.drawText("　体力：　" + Constants.player[1].getStamina() + " ("
				+ Constants.player[1].getPerStamina() + "%)", playerWidth,
				Height1 + LineHeight * 5, PaintWhite);
		index = Constants.player[1].getEquipItemsWP();
		equipName = "无";
		if (index != -1) {
			equipName = Constants.player[1].getEquipsByID(index).getName();
		}
		g.drawText("　装备：　" + equipName, playerWidth,
				Height1 + LineHeight * 6, PaintWhite);
	}

	public void printPossessionUI(Canvas g) {
		// LMPossessionImg[Constants.gameLayer.getMapAura(i).getLV()
		for (int i = 0; i < Constants.gameLayer.getMapAuraCounts(); i++) {
			if (Constants.gameLayer.getMapAura(i).getPossession() > -1) {
				// LMPossessionImg
				if (Constants.gameLayer.getMapAura(i).getType() == 2) {
					if (Constants.gameLayer.getMapAura(i).getPossession() == 0) {
						g.drawBitmap(BLsp_city_img, Constants.gameLayer
								.getPossessionPosX(i)
								- viewX, Constants.gameLayer
								.getPossessionPosY(i)
								- viewY, null);
					} else if (Constants.gameLayer.getMapAura(i)
							.getPossession() == 1) {
						g.drawBitmap(LMsp_city_img, Constants.gameLayer
								.getPossessionPosX(i)
								- viewX, Constants.gameLayer
								.getPossessionPosY(i)
								- viewY, null);
					}
				} else if (Constants.gameLayer.getMapAura(i).getType() == 1) {
					if (Constants.gameLayer.getMapAura(i).getPossession() == 0) {
						g.drawBitmap(BLPossessionImg[Constants.gameLayer
								.getMapAura(i).getLV()], Constants.gameLayer
								.getPossessionPosX(i)
								- viewX, Constants.gameLayer
								.getPossessionPosY(i)
								- viewY, null);
					} else if (Constants.gameLayer.getMapAura(i)
							.getPossession() == 1) {
						g.drawBitmap(LMPossessionImg[Constants.gameLayer
								.getMapAura(i).getLV()], Constants.gameLayer
								.getPossessionPosX(i)
								- viewX, Constants.gameLayer
								.getPossessionPosY(i)
								- viewY, null);
					}
				}
			}
		}
	}

//	public void printOnClickIconUI(Canvas g) {
//		if (Constants.GoIconCanClick) {
//			g.drawBitmap(GoIcon, GoIconX, GoIconY, null);
//		} else {
//			g.drawBitmap(GoIconFalse, GoIconX, GoIconY, null);
//		}
//		g.drawText("移动", GoIconX + 32, GoIconY - 5, PaintWhiteFont);
//
//		if (Constants.ItemIconCanClick) {
//			g.drawBitmap(ItemIcon, ItemIconX, ItemIconY, null);
//		} else {
//			g.drawBitmap(ItemIconFalse, ItemIconX, ItemIconY, null);
//		}
//		g.drawText("道具", ItemIconX + 32, ItemIconY - 5, PaintWhiteFont);
//
//		if (Constants.EquipIconCanClick) {
//			g.drawBitmap(EquipIcon, EquipIconX, EquipIconY, null);
//		} else {
//			g.drawBitmap(EquipIconFalse, EquipIconX, EquipIconY, null);
//		}
//		g.drawText("装备", EquipIconX + 32, EquipIconY - 5, PaintWhiteFont);
//	}

	public void printMapBarriers(Canvas g) {
		if (Constants.gameLayer.isBarriersEnabled()) {
			// -viewX, -viewY
			g.drawBitmap(MapBarriersImg, Constants.gameLayer.getBarriersX()
					* Constants.gameLayer.getSingleLength() - viewX,
					Constants.gameLayer.getBarriersY()
							* Constants.gameLayer.getSingleLength() - viewY,
					null);
		}
		// item_even_14_map
	}

	private void clearScreen(Canvas g) {
		// g.setClip(0, 0, this.getViewWidth(), this.getViewHeight());
		// g.setColor(0xffffff);
		// g.fillRect(0, 0, this.getViewWidth(), this.getViewHeight());
		g.drawColor(Color.BLACK);
	}

	private void setDown() {

		// 一直往下的动画
		// index1和index控制帧速率
		// player01.drawableFrame++;
		// Frame为0到3
		// Log.i("Move", "setDown()");
		// spriteLayer.move(2, 0);
		PlayerDataFrame++;

		if (PlayerDataFrame > FrameSpeed) {
			PlayerDataFrame = 0;
		}
		for (int i = 0; i < Constants.player.length; i++) {
			switch (PlayerDataFrame / 4) {
			case 0:
				Constants.player[i].spriteLayer.setFrame(0);
				break;
			case 1:
				// Constants.player[i].spriteLayer.setFrame(1);
				break;
			case 2:
				Constants.player[i].spriteLayer.setFrame(1);
				break;
			case 3:
				// Constants.player[i].spriteLayer.setFrame(1);
				break;

			default:
				break;
			}
		}
	}

	// 人物移动的逻辑
	public void LogicMoveSetp() {
		// 移动的步数
		// setStatus
		// 0 ： 准备移动
		// 1: 正在移动
		// 2: 移动完毕
		PlayerMoveSpeed++;
		if (PlayerMoveSpeed > 10) {
			PlayerMoveSpeed = 0;
		}

		if (PlayerMoveSpeed == 0
				&& Constants.player[Constants.playerIndex].Status == 1) {
			// 1: 正在移动
			if (Constants.player[Constants.playerIndex].moveStep > 0) {
				// player[playerIndex].MoveSetp();
				Constants.player[Constants.playerIndex]
						.MoveNextSetp(Constants.gameLayer.getBarriersIndex());
			} else {
				Constants.player[Constants.playerIndex].Status = 2;
			}
			// Log.i("Move", "Status = 1" + "moveStep :" +
			// player[playerIndex].moveStep);
		}
		switch (Constants.player[Constants.playerIndex].Status) {
		case 0: {
			// 0 ： 准备移动
			// Log.i("Move", "Status = 0");
			int moveStep = getMoveStep();
			Constants.player[Constants.playerIndex].Status = 1;
			Constants.player[Constants.playerIndex].moveStep = moveStep;
			this.moveStep = moveStep;
		}
			break;
		// case 1:
		//				
		// break;
		case 2:
			// 2: 移动完毕
			Constants.player[Constants.playerIndex].Status = 3;
			// 发送消息更新UI线程来显示对话框

			mHandler.sendEmptyMessage(Constants.gameLayer.getMapAura(
					Constants.player[Constants.playerIndex].getIndex())
					.getType());

			this.moveStep = -1;
			// showNote("移动完了！");// 发生移动完毕的地点事件
			// player01[playerIndex].setTurn(true);

			// 这里的NextPlayerTurn()需要放到对话框事件里去

			break;
		}
	}

	private int getMoveStep() {
		if (Constants.DebugMode && Constants.DebugModeStep > 0) {
			return Constants.DebugModeStep;
		} else {
			return eBattleEven.RandomInt(
					Constants.player[Constants.playerIndex].getMoveStepMix(),
					Constants.player[Constants.playerIndex].getMoveStepMax());
		}
	}

	// getViewWidth(), this.getViewHeight() ViewHeight ViewWidth
	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public boolean CanPlayerAction(){
		if (Constants.player[Constants.playerIndex].Status == -1) {
		return true;
		}
		return false;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// keyPressed(VK_FIRE);

		// GoIconX

	}

	public boolean onTouchEvent(MotionEvent event) {

		MotionEventX = event.getX();

		MotionEventY = event.getY();

//		if (CanPlayerAction()) {
//			if ((MotionEventX > GoIconX && MotionEventX < GoIconX + 64)
//					&& (MotionEventY > GoIconY && MotionEventY < GoIconY + 64)) {
//				// 点中了
//
//				Constants.player[Constants.playerIndex].Status = 0;
//				Constants.GoIconCanClick = false;
//
//			} else if ((MotionEventX > ItemIconX && MotionEventX < ItemIconX + 64)
//					&& (MotionEventY > ItemIconY && MotionEventY < ItemIconY + 64)) {
//
//				if (Constants.player[Constants.playerIndex].getItemsSize() > 0) {
//					evenOverRequest.EvenRequest(-1);
//				}
//			} else if ((MotionEventX > EquipIconX && MotionEventX < EquipIconX + 64)
//					&& (MotionEventY > EquipIconY && MotionEventY < EquipIconY + 64)) {
//
//				if (Constants.player[Constants.playerIndex].getEquipsSize() > 0) {
//					evenOverRequest.EvenRequest(-2);
//				}
//			}
//		}
		
		// switch (event.getAction()) {
		//
		// case MotionEvent.ACTION_DOWN:
		//
		// break;
		//
		// case MotionEvent.ACTION_UP:
		//
		// break;
		//
		// case MotionEvent.ACTION_MOVE:
		//
		// break;
		//
		// }

		return super.onTouchEvent(event);
	}

	@Override
	public boolean EvenRequest(int TypeID) {
		// TODO Auto-generated method stub
		if (TypeID == Constants.AI_TYPE_ITEM_USE) {
			Constants.player[Constants.playerIndex].Status = 1;
		}
		return false;
	}
	
	private void InitPaint(Context context){
		gameEven = new GameEven(_context);
		Typeface font = Typeface.create("宋体", Typeface.BOLD);
//		PaintBlack = new Paint();
//		PaintBlack.setColor(Color.BLACK);
		PaintWhite = new Paint();
		PaintWhite.setColor(Color.WHITE);
		PaintWhite.setTextSize(28);
		
		PaintWhiteFont = new Paint();
		PaintWhiteFont.setColor(Color.YELLOW);
		PaintWhiteFont.setTextAlign(Paint.Align.CENTER);
		PaintWhiteFont.setTextSize(28);

		PaintWhiteFont.setTypeface(font);

		PaintWhiteFont = new Paint();
		PaintWhiteFont.setColor(Color.WHITE);
		PaintWhiteFont.setTextAlign(Paint.Align.CENTER);
		PaintWhiteFont.setTextSize(36);

		PaintWhiteFont.setTypeface(font);
		
		BLPossessionImg = new Bitmap[5];
		LMPossessionImg = new Bitmap[5];

		BLPossessionImg[0] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_red_00);
		BLPossessionImg[1] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_red_00);
		BLPossessionImg[2] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_red_01);
		BLPossessionImg[3] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_red_02);
		BLPossessionImg[4] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_red_03);

		LMPossessionImg[0] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_blue_00);
		LMPossessionImg[1] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_blue_00);
		LMPossessionImg[2] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_blue_01);
		LMPossessionImg[3] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_blue_02);
		LMPossessionImg[4] = BitmapFactory.decodeResource(context
				.getResources(), R.drawable.city_lv_blue_03);

		BLsp_city_img = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.icon_bl);

		LMsp_city_img = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.icon_lm);

		GoIcon = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.go_even01);

		ItemIcon = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.item_even01);

		EquipIcon = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.icon_equip_00);

		GoIconFalse = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.go_even02);
		ItemIconFalse = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.item_even02);
		EquipIconFalse = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.icon_equip_01);

		MapBarriersImg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.item_even_14_map);
		
//		GoIconX = screenWidth / 2 - 32;
//		GoIconY = screenHeight - 64;
//
//		ItemIconX = 0;
//		ItemIconY = screenHeight - 64;
//
//		EquipIconX = screenWidth - 64;
//		EquipIconY = screenHeight - 64;
	}
	
	private void InitSprite(Context context){
		Constants.player[0].InitSprite(BitmapFactory.decodeResource(context
				.getResources(), R.drawable.bl_act01));
		Constants.player[1].InitSprite(BitmapFactory.decodeResource(context
				.getResources(), R.drawable.lm_act01));
	}
	
	private void InitData(Context context){
		


		Constants.player = new PlayerData[2];

		Constants.player[0] = new PlayerData(Constants.gameLayer
				.getSingleLength(), Constants.gameLayer.getMapAuraCounts(),
				Constants.gameLayer.getMapAuraArray());


		Constants.player[0].setName("部落");

		Constants.player[0].setPlayerType(1);

		Constants.player[1] = new PlayerData(Constants.gameLayer
				.getSingleLength(), Constants.gameLayer.getMapAuraCounts(),
				Constants.gameLayer.getMapAuraArray());


		Constants.player[1].setName("联盟");

		Constants.player[0].setTurn(true);
		Constants.player[1].setTurn(false);
		Constants.player[0].Status = -1;
		Constants.playerIndex = 0;

		Constants.player[0].setLV(Constants.LV);
		Constants.player[1].setLV(Constants.LV);

		Constants.player[0].setLVMAX(Constants.LVMAX);
		Constants.player[1].setLVMAX(Constants.LVMAX);

		Constants.player[1].setMoneyChange(Constants.INITIAL_MONEY);

		InitSprite(context);
	}

//	if (CanPlayerAction()) {
//		if ((MotionEventX > GoIconX && MotionEventX < GoIconX + 64)
//				&& (MotionEventY > GoIconY && MotionEventY < GoIconY + 64)) {
//			// 点中了
//
//			Constants.player[Constants.playerIndex].Status = 0;
//			Constants.GoIconCanClick = false;
//
//		} else if ((MotionEventX > ItemIconX && MotionEventX < ItemIconX + 64)
//				&& (MotionEventY > ItemIconY && MotionEventY < ItemIconY + 64)) {
//
//			if (Constants.player[Constants.playerIndex].getItemsSize() > 0) {
//				evenOverRequest.EvenRequest(-1);
//			}
//		} else if ((MotionEventX > EquipIconX && MotionEventX < EquipIconX + 64)
//				&& (MotionEventY > EquipIconY && MotionEventY < EquipIconY + 64)) {
//
//			if (Constants.player[Constants.playerIndex].getEquipsSize() > 0) {
//				evenOverRequest.EvenRequest(-2);
//			}
//		}
//	}

	public void itemClick(){
		if (!CanPlayerAction()) return;
		if (Constants.player[Constants.playerIndex].getItemsSize() > 0) {
			evenOverRequest.EvenRequest(-1);
		}
	}

	public void goClick(){
		if (!CanPlayerAction()) return;

		Constants.player[Constants.playerIndex].Status = 0;
		//Constants.GoIconCanClick = false;
		UIUtils.sendUIButtonChange(getContext(), MIDlet.BTN_GO, false);
	}

	public void equipClick(){
		if (!CanPlayerAction()) return;
			if (Constants.player[Constants.playerIndex].getEquipsSize() > 0) {
				evenOverRequest.EvenRequest(-2);
			}
	}

}