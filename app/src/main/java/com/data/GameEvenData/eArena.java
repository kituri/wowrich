package com.data.GameEvenData;

import android.content.Context;

import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.rich.ItemsData.EvenKeyChest;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;


public class eArena implements CustomRequest {

	private CustomDialog customDialog;
	// private int Step;
	private EvenOverRequest evenOverRequest;
	// private Context _context;
	private final int STEP_LAST = -1;
	private final int STEP_FRIST = 0;
	private final int STEP_SECOND = 1;
	
	private final int Type = Constants.TYPE_ARENA;
	private final int BattleLV = 3;
	// 20% 30% 40% 16 14 12
	private final int Difficulty = 14;
	// private int Index;
	private String title;
	private int DrawableID;
	private int PlayerType;
	private int LV;
	private int atk;
	private int def;
	private String name;
	//
	public eArena(Context _context, EvenOverRequest evenOverRequest) {
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
		this.PlayerType = Constants.player[Constants.playerIndex]
				.getPlayerType();
		this.LV = Constants.player[Constants.playerIndex].getLV();
		this.atk = Constants.player[Constants.playerIndex].getAtkALL();
		this.def = atk + Difficulty;
		
		// 12,14,16
		EvenLogic();
	}

	private void EvenLogic() {
		// Constants.player[Constants.playerIndex]
		if (LV < BattleLV) {
			customDialog.Show(CustomDialog.DIG_OK, title, "竞技场最低挑战级别为"
					+ BattleLV + "级。", DrawableID, Type, STEP_LAST);
			return;
		}
		if (PlayerType == 1) {
			customDialog.Show(CustomDialog.DIG_FIGHT, CustomDialog.DIG_CANCEL,
					title, "在竞技场的搏斗中获胜将会获得额外的奖励！ ", DrawableID, Type,
					STEP_FRIST);
		} else if (PlayerType == 0) {
			// 写AI，AI肯定恢复
			// 模拟AI按左键
			DigRequest(Type, CustomDialog.BUTTON_LEFT, STEP_FRIST);
		}

	}

	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		this.name = Constants.player[Constants.playerIndex].getName();
		// TODO Auto-generated method stub
		if (Step == STEP_LAST) {
			evenOverRequest.EvenRequest(Type);
			return false;
		} else if (Step == STEP_FRIST) {
			//
			if (ButtonIndex == 1) {
				// 获取战斗随机数
				int BattleRandom = eBattleEven.BattleRandom();
				if(Constants.DebugMode_BattleWin == true){
					BattleRandom = 20;
				}
				int AtkALL = BattleRandom + atk;
				// 进入战斗
				if (eBattleEven.Battle(atk, def, BattleRandom)) {
					// 胜利
					customDialog.Show(CustomDialog.DIG_OK, title, eBattleEven
							.getWinString(atk, AtkALL, def, BattleRandom),
							DrawableID, Type, STEP_SECOND);
				} else {
					// 失败
					customDialog.Show(CustomDialog.DIG_OK, title, eBattleEven
							.getLostString(atk, AtkALL, def, BattleRandom),
							DrawableID, Type, STEP_LAST);
				}
			} else if (ButtonIndex == 2) {
				
				customDialog.Show(CustomDialog.DIG_OK, title,
						name + "掂量了一下自己的份量后，静静的离去了", DrawableID, Type, STEP_LAST);
			}
		} else if (Step == STEP_SECOND) {
			// customDialog.Show(CustomDialog.DIG_OK,
			// title,
			// "获得竞技场奖励：XX点经验。获得金钱：XXXXG！"
			// ,
			// DrawableID,
			// Type, STEP_THREE);
			battleBonusEven(LV);
		} 
//		else if (Step == STEP_THREE) {
//			customDialog.Show(CustomDialog.DIG_OK, title, "获得XXX道具",
//					DrawableID, Type, STEP_LAST);
//		}

		return false;
	}

	private void battleBonusEven(int lv) {
		if (lv >= 12) {
			//12以上
			EvenKeyChest.UseOpenArenaChestLV4();
			customDialog.Show(CustomDialog.DIG_OK, title,
					Constants.ItemMessage, DrawableID, Type, STEP_LAST);
		}else if (lv >= 9 && lv <= 11) {
			// 9- 11级
			EvenKeyChest.UseOpenArenaChestLV3();
			customDialog.Show(CustomDialog.DIG_OK, title,
					Constants.ItemMessage, DrawableID, Type, STEP_LAST);
		} else if (lv >= 6  && lv <= 8) {
			// 6 - 8级
			EvenKeyChest.UseOpenArenaChestLV2();
			customDialog.Show(CustomDialog.DIG_OK, title,
					Constants.ItemMessage, DrawableID, Type, STEP_LAST);
		} else if (lv >= 3  && lv <= 5){
			// 3 - 5级
			EvenKeyChest.UseOpenArenaChestLV1();
			customDialog.Show(CustomDialog.DIG_OK, title,
					Constants.ItemMessage, DrawableID, Type, STEP_LAST);
		}  
	}

}
