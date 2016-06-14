package com.data.GameEvenData;

import android.content.Context;


import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.rich.ItemsData.EvenKeyChest;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;

//熔火之心
public class eUS_BT implements CustomRequest {

	private CustomDialog customDialog;
	// private int Step;
	private EvenOverRequest evenOverRequest;
	// private Context _context;
	private final int STEP_LAST = -1;
	private final int STEP_FRIST = 0;
	private final int STEP_SECOND = 1;
	private final int STEP_THREE = 2;
	private final int STEP_4TH = 3;
	private final int STEP_5TH = 4;
	private final int STEP_6TH = 5;
	private final int STEP_7TH = 6;
	private final int STEP_8TH = 7;
	private final int STEP_9TH = 8;

	private final int Type = Constants.TYPE_US_BT;
	private final int BattleLV = 10;
	// 20% 30% 40% 16 14 12
//	private final int Difficulty = 12;
	// private int Index;
	private String title;
	private int DrawableID;
	private int PlayerType;
	private int LV;
	private int atk;
	private String name;
	// private int def;

	private String[] BossMessage = { 
			"【BOSS】塔隆血魔：\n对我来说，死亡之轮已经旋转过许多次……时间已经流逝了许多……有太多事情要做了……", 
			
			"【BOSS】莎赫拉丝：\n你是来办正事，还是找乐子呢？ ",
			
			"【BOSS】伊利丹：\n我被囚禁了一万年 又被逐出了自己的故乡 现在你们竟敢闯入我的领地 真是自寻死路！" };

	private int[] BossDifficulty = { 22, 25, 28  };
	//上下随机变动难度值
	private int BossDifficultyMix = -5;
	private int BossDifficultyMax =  5;
	//battleBonusEven 需要改动(掉物品)

	private final String raidMessage = "散发着浓烈气息的洞穴就是副本入口";

	//
	public eUS_BT(Context _context, EvenOverRequest evenOverRequest) {
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

		//初始化各种难度
		for(int i=0;i<BossMessage.length;i++){
			BossDifficulty[i] += eBattleEven.RandomInt(BossDifficultyMix,BossDifficultyMax);
			//BossMessage[i] = "战斗难度：" + BossDifficulty[i] + "\n" + BossMessage[i]; 
		}
		// 难度调整

		// this.def = atk + Difficulty;
		//this.bossIndex = 0;
		// 12,14,16
		EvenLogic();
	}

	private void EvenLogic() {
		// Constants.player[Constants.playerIndex]
		if (LV < BattleLV) {
			customDialog.Show(CustomDialog.DIG_OK, title, "该副本最低挑战级别为"
					+ BattleLV + "级。", DrawableID, Type, STEP_LAST);
			return;
		}

		if (PlayerType == 1) {
			customDialog.Show(CustomDialog.DIG_FIGHT, CustomDialog.DIG_CANCEL,
					title, raidMessage, DrawableID, Type, STEP_FRIST);
		} else if (PlayerType == 0) {
			// 写AI，AI肯定恢复
			// 模拟AI按左键
			DigRequest(Type, CustomDialog.BUTTON_LEFT, STEP_FRIST);
		}
	}

	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		this.name = Constants.player[Constants.playerIndex].getName();
		if (Step == STEP_LAST) {
			evenOverRequest.EvenRequest(Type);
			return false;
		}
		if (Step == STEP_FRIST && (ButtonIndex == 2)) {
				customDialog.Show(CustomDialog.DIG_OK, title,
						name + "掂量了一下自己的份量后，静静的离去了", DrawableID, Type, STEP_LAST);
			//=========================1===================================//
		} else if (Step == STEP_FRIST) {
			//if (ButtonIndex == 1) {
			bossMessageEven(0, STEP_SECOND);
			//}
		} else if (Step == STEP_SECOND) {
			battleEven(BossDifficulty[0], STEP_THREE);
		} else if (Step == STEP_THREE) {
			battleBonusEven(0, STEP_4TH);
		}
		//============================2=====================================//
		else if (Step == STEP_4TH) {
			bossMessageEven(1, STEP_5TH);
		} else if (Step == STEP_5TH) {
			battleEven(BossDifficulty[1], STEP_6TH);
		} else if (Step == STEP_6TH) {
			battleBonusEven(1, STEP_7TH);
		}
		//============================3===================================//
		else if (Step == STEP_7TH) {
			bossMessageEven(2, STEP_8TH);
		} else if (Step == STEP_8TH) {
			battleEven(BossDifficulty[2], STEP_9TH);
		} else if (Step == STEP_9TH) {
			battleBonusEven(2, STEP_LAST);
		}
		//======================END=====================================//
		return false;
	}

	private void bossMessageEven(int index, int Step) {
		// BossMessage[0]
		customDialog.Show(CustomDialog.DIG_OK, title, BossMessage[index],
				DrawableID, Type, Step);
	}

	private void battleBonusEven(int index, int Step) {
		// 这里是显示和逻辑一起做的。返回一个STRING就是Message了
		switch(index){
		case 0:
			EvenKeyChest.UseOpenChestLV2();
			Constants.ItemMessage = "战胜BOSS后，发现了一个宝箱。" + Constants.ItemMessage;
		customDialog.Show(CustomDialog.DIG_OK, title,
		// 这里就是那个获取逻辑和显示的了,
				Constants.ItemMessage, DrawableID, Type, Step);
		break;
		case 1:
			if(eBattleEven.RandomPer(70)){
				EvenKeyChest.UseOpenChestLV2();
			}else{
				EvenKeyChest.UseOpenChestLV3();
			}
			Constants.ItemMessage = "战胜BOSS后，发现了一个宝箱。" + Constants.ItemMessage;
			customDialog.Show(CustomDialog.DIG_OK, title,
			// 这里就是那个获取逻辑和显示的了,
					Constants.ItemMessage, DrawableID, Type, Step);
			break;
		case 2:
			if(eBattleEven.RandomPer(80)){
				EvenKeyChest.UseOpenRaidChestLV4();
				Constants.ItemMessage = "战胜BOSS后，发现了一个宝箱。" + Constants.ItemMessage;
				}else{
						Constants.player[Constants.playerIndex].addEquips(3);
						Constants.ItemMessage = "打开宝箱，获得了 【" + Constants.player[Constants.playerIndex].getEquipsByID(3)
						.getName() + "】 。";
				}
			
			customDialog.Show(CustomDialog.DIG_OK, title,
			// 这里就是那个获取逻辑和显示的了,
					Constants.ItemMessage, DrawableID, Type, Step);
			break;
		}
	}

	private void battleEven(int battleDifficulty, int winStep) {
		// 本类type为共用type,步骤则需要传递。
		// 获取战斗随机数
		int BattleRandom = eBattleEven.BattleRandom();
		// atk是内类共用变量
		if(Constants.DebugMode_BattleWin){
			BattleRandom = 20;
		}
		int AtkALL = BattleRandom + atk;
		// 进入战斗
		if (eBattleEven.Battle(atk, battleDifficulty, BattleRandom, false,
				false)) {
			// 胜利
			customDialog.Show(CustomDialog.DIG_OK, title, eBattleEven
					.getRaidWinString(atk, AtkALL, battleDifficulty,
							BattleRandom), DrawableID, Type, winStep);
		} else {
			// 失败
			customDialog.Show(CustomDialog.DIG_OK, title, eBattleEven
					.getRaidLostString(atk, AtkALL, battleDifficulty,
							BattleRandom), DrawableID, Type, STEP_LAST);
		}
	}

}
