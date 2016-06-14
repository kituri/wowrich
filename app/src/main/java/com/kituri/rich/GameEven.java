package com.kituri.rich;

import android.content.Context;
import android.util.Log;

import com.data.Constants.Constants;

import com.data.GameEvenData.*;

import com.kituri.rich.ItemsData.ItemsAI;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;
import com.kituri.wowrich.MIDlet;
import com.kituri.wowrich.R;
import com.kituri.tools.UIUtils;

public class GameEven implements EvenOverRequest, CustomRequest {
	// private Context _context;
	// private AlertDialog.Builder builder;
	// private int DialogType;
	// private CustomDialog customDialog;
	// private Random random;
	// private int RandomIndex;

	private eTavern ETavern;
	private eChest EChest;
	private eChange EChange;
	private eStart EStart;
	private eUS_MC EUS_MC;
	private eUS_BT EUS_BT;
	private eUS_SW EUS_SW;
	private eSP_City ESP_City;
	private eCity ECity;
	private eArena EArena;
	private CustomDialog customDialog;
	private ItemsAI itemsAI;
	private final int TYPE_LVUP = 101;
	private final int STEP_LAST = 201;
	private final int STEP_LVUP = 202;
	private final int STEP_WIN = 203;

	// private AlertDialog alertDig;
	// private final int DIG_YES = 0;

	// AlertDialog.Builder builder = new AlertDialog.Builder(this);
	// builder.setTitle("Pick a color");
	// builder.setItems(items, new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int item) {
	// Toast.makeText(getApplicationContext(), items[item],
	// Toast.LENGTH_SHORT).show();
	// }
	// });
	// AlertDialog alert = builder.create();

	private Context mContext;

	public GameEven(Context _context) {
		// this._context = _context;
		// InitDig();
		// random = new Random();
		this.mContext = _context;
		ETavern = new eTavern(_context, this);
		EChest = new eChest(_context, this);
		EChange = new eChange(_context, this);
		EStart = new eStart(_context, this);
		EUS_MC = new eUS_MC(_context, this);
		EUS_BT = new eUS_BT(_context, this);
		EUS_SW = new eUS_SW(_context, this);
		ESP_City = new eSP_City(_context, this);
		ECity = new eCity(_context, this);
		EArena = new eArena(_context, this);
		customDialog = new CustomDialog(_context, this);
		itemsAI = new ItemsAI(_context, this);
	}

	private void EvenGO() {
		int turnBonus = Constants.gameLayer
				.getPlayerCityLVNum(Constants.playerIndex) * 5 + 200;
		Constants.player[Constants.playerIndex].setTurnBonus(turnBonus);
		Constants.player[Constants.playerIndex].setMoneyChange(turnBonus);
		Constants.player[Constants.playerIndex]
				.setStaminaChange(Constants.player[Constants.playerIndex]
						.getStaminaMax() / 5);
		Constants.player[Constants.playerIndex].addTurnCounts();
	}

	public void TypeEven(int type) {
		// logicThread = false;
		// 事件列表 宏定义
		// static public final int TYPE_NULL = 0;
		// static public final int TYPE_CITY = 1;
		// static public final int TYPE_SP_CITY = 2;
		// static public final int TYPE_START = 3;
		// static public final int TYPE_CHANGE = 4;
		// static public final int TYPE_CHEST = 5;
		// static public final int TYPE_TAVERN = 6;
		// static public final int TYPE_US_MC = 7;
		// static public final int TYPE_US_BT = 8;
		// static public final int TYPE_US_SW = 9;
		// static public final int TYPE_ARENA = 10;
		// 判断是不是经过起点的事件

		Log.i("TypeEven", "type:" + type);
		// getMapAura
		if (Constants.player[Constants.playerIndex].isGo()) {
			EvenGO();
			// getPlayerCityLVNum
			Constants.player[Constants.playerIndex].setGo(false);
		}

		// TextEven();
		int Index = Constants.player[Constants.playerIndex].getIndex();
		String title = Constants.gameLayer.getMapAura(Index).getName();
		int DrawableID = Constants.gameLayer.getMapAura(Index).getDrawableID();

		switch (type) {
		case Constants.TYPE_CITY:
			ECity.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_SP_CITY:
			ESP_City.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_START:
			EStart.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_CHANGE:
			EChange.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_CHEST:
			EChest.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_TAVERN:
			ETavern.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_US_MC:
			EUS_MC.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_US_BT:
			EUS_BT.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_US_SW:
			EUS_SW.ShowEven(title, DrawableID);
			break;
		case Constants.TYPE_ARENA:
			EArena.ShowEven(title, DrawableID);
			break;

		}

	}

	private void TypeEvenEnd() {
		if (Constants.player[Constants.playerIndex].getPlayerType() == 1) {
			NextPlayerTurn();

			if (Constants.player[Constants.playerIndex].getPlayerType() == 0) {
				Constants.player[Constants.playerIndex].Status = 0;
			}
		} else if (Constants.player[Constants.playerIndex].getPlayerType() == 0) {
			NextPlayerTurn();
			//Constants.GoIconCanClick = true;
			UIUtils.sendUIButtonChange(mContext, MIDlet.BTN_GO, true);
			if (Constants.player[Constants.playerIndex].getPlayerType() == 0) {
				Constants.player[Constants.playerIndex].Status = 0;
			}
		}

	}

	private void NextPlayerTurn() {
		// int playerIndex = 0;
		Constants.player[Constants.playerIndex].NextTurn();
		Constants.player[Constants.playerIndex].setTurn(false);
		if (Constants.playerIndex + 2 > Constants.player.length) {
			Constants.player[0].setTurn(true);
			Constants.player[0].Status = -1;
			Constants.playerIndex = 0;
		} else {
			Constants.player[Constants.playerIndex + 1].setTurn(true);
			Constants.player[Constants.playerIndex + 1].Status = -1;
			Constants.playerIndex++;
		}
	}

	@Override
	public boolean EvenRequest(int TypeID) {

		if (eBattleEven.IsLost()) {

			Log.i("IsLost()", "Now IsLost()");

			int index = Constants.playerIndex;
			if (index == 0) {
				index = 1;
			} else {
				index = 0;
			}

			customDialog.Show(CustomDialog.BUTTON_CENTER, "胜利！",
					Constants.player[index].getName() + "战胜了对手！获得了胜利！",
					R.drawable.icon_atk, STEP_WIN, STEP_WIN);
			return false;
		}

		// 看看会不会升级 需要升级的 此处增加升级事件
		if (Constants.AI_TYPE_ITEM_USE == TypeID) {
			// DigRequest(TYPE_LVUP,0,STEP_LAST);
			Log.i("AI", "if(Constants.AI_TYPE_ITEM_USE == TypeID)");
			TypeEvenEnd();
			return false;
		}

		if (Constants.player[Constants.playerIndex].CanLVUP()) {
			// 可以升级的情况。
			eBattleEven.LVUP();
			customDialog.Show(CustomDialog.BUTTON_CENTER, "等级提升", eBattleEven
					.getLVUP_Message(), R.drawable.icon_lvup02, TYPE_LVUP,
					STEP_LVUP);
		} else {
			// TypeEvenEnd();
			if (Constants.player[Constants.playerIndex].getPlayerType() == 1) {
				TypeEvenEnd();
			} else {
				Log.i("AI", "EvenRequest itemsAI.AI_UseItem()");
				itemsAI.AI_UseItem();
			}
		}
		return false;
	}

	@Override
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub

		if (STEP_WIN == Step) {
			MIDlet.ExitGame(true);
			return false;
		}

		Log.i("IsLost()", "DigRequest");

		if (STEP_LAST == Step) {
			// TypeEvenEnd();
			if (Constants.player[Constants.playerIndex].getPlayerType() == 1) {
				TypeEvenEnd();
			} else {
				Log.i("AI", "DigRequest itemsAI.AI_UseItem()");
				itemsAI.AI_UseItem();
			}
			return false;
		}
		switch (Step) {
		case STEP_LVUP:
			DigRequest(TYPE_LVUP, 0, STEP_LAST);
			break;
		}
		return false;
	}
}
