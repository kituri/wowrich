package com.data.GameEvenData;


import android.content.Context;

import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;

public class eChest implements CustomRequest {

	private CustomDialog customDialog;

	private EvenOverRequest evenOverRequest;
	// private Context _context;
	private final int LAST_STEP = -1;
	// 这里是类型必须改动
	private final int Type = Constants.TYPE_CHEST;
	// private int Index;
	private String title;
	private int DrawableID;
	private String name;
	//
	public eChest(Context _context, EvenOverRequest evenOverRequest) {
		// TODO Auto-generated constructor stub
		customDialog = new CustomDialog(_context, this);
		customDialog.show();
		customDialog.dismiss();
		// customDialog.onCreate(Constants.savedInstanceState);

		this.evenOverRequest = evenOverRequest;
		
	}

	public void ShowEven(String title, int DrawableID) {
		// this.Index = Index;
		this.title = title;
		this.DrawableID = DrawableID;
		// this.PlayerType =
		// Constants.player[Constants.playerIndex].getPlayerType();
		EvenLogic();
	}

	private void EvenLogic() {
		this.name = Constants.player[Constants.playerIndex].getName();
		// 掉落物品 掉率
		// 青铜宝箱 10%
		// 白银宝箱 10%
		// 小血瓶 10%
		// 中血瓶 10%

		// 烈焰风暴 10%
		// 法力屏障 10%
		// 空的 10%

		// 大血瓶 5%
		// 地精工程师 5%
		// 愈合祷言 5%
		// 圣疗 5%
		// 大地图腾 5%
		// 寒冬号角 5%

		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);
		if (roll >= 1 && roll <= 10) {
			ID = 22;
		} else if (roll >= 11 && roll <= 20) {
			ID = 23;
		} else if (roll >= 21 && roll <= 30) {
			ID = 0;
		} else if (roll >= 31 && roll <= 40) {
			ID = 1;
		} else if (roll >= 41 && roll <= 50) {
			ID = 15;
		} else if (roll >= 51 && roll <= 60) {
			ID = 14;
		} else if (roll >= 61 && roll <= 70) {
			ID = -1;
		} else if (roll >= 71 && roll <= 75) {
			ID = 2;
		} else if (roll >= 76 && roll <= 80) {
			ID = 16;
		} else if (roll >= 81 && roll <= 85) {
			ID = 9;
		} else if (roll >= 86 && roll <= 90) {
			ID = 10;
		} else if (roll >= 91 && roll <= 95) {
			ID = 04;
		} else if (roll >= 96 && roll <= 100) {
			ID = 05;
		}
		if (ID > -1) {
			Constants.player[Constants.playerIndex].addItems(ID);
			Constants.ItemMessage = name + "从宝箱中获得了 【"
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
		} else {
			Constants.ItemMessage = name + "打开了宝箱，不过似乎是个空箱子…………尼玛！坑爹啊！到底有木有啊！";
		}

		customDialog.Show(CustomDialog.DIG_OK, title, Constants.ItemMessage,
				DrawableID, Type, LAST_STEP);
	}

	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		if (Step == LAST_STEP) {
			evenOverRequest.EvenRequest(Type);
			return false;
		}
		return false;
	}

}
