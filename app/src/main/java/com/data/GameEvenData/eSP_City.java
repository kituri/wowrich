package com.data.GameEvenData;

import android.content.Context;


import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;

public class eSP_City implements CustomRequest {

	private CustomDialog customDialog;
	//private int Step;
	private EvenOverRequest evenOverRequest;
	// private Context _context;
	private final int STEP_LAST = -1;
	private final int STEP_FRIST = 0;
	private final int STEP_SECOND = 1;
	//private final int STEP_THREE = 2;
	private final int Type = Constants.TYPE_SP_CITY;
	//private final int BattleLV = 1;
	// 20% 30% 40%  16 14 12
	private final int Difficulty = 14;
	private String title;
	private int DrawableID;
	private int PlayerType;
	//private int LV;
	private int atk;
	private int def;
	private int atkMax;
	private String name;
	//
	public eSP_City(Context _context, EvenOverRequest evenOverRequest) {
		// TODO Auto-generated constructor stub
		customDialog = new CustomDialog(_context, this);
		customDialog.show();
		customDialog.dismiss();
		//customDialog.onCreate(Constants.savedInstanceState);
		this.evenOverRequest = evenOverRequest;
	}
	
	public void ShowEven(String title,int DrawableID){
		this.title = title;
		this.DrawableID = DrawableID;
		this.PlayerType = Constants.player[Constants.playerIndex].getPlayerType();
		//this.LV = Constants.player[Constants.playerIndex].getLV();
		this.atk = Constants.player[Constants.playerIndex].getAtkALL();
		this.atkMax = Constants.player[Constants.playerIndex].getAtkMax();
		this.def = atk + Difficulty;
		//this.def = 100;
		//this.name = Constants.player[Constants.playerIndex].getName();
		//12,14,16
		EvenLogic();
	}
	
	private void BattleWin(){
		Constants.gameLayer.setMapAuraPossession(
				Constants.player[Constants.playerIndex].getIndex(), 
				Constants.playerIndex);
		
		int SpCityNum = 0;
		double atkSPCityBuff = 0;
		for(int i = 0;i < Constants.player.length;i++){	
		SpCityNum = Constants.gameLayer.getPlayerSPCityNum(i);
		atkSPCityBuff =  (double)atkMax / 10.00 * (double)SpCityNum;
		//每个战场最低提升1点攻击力的逻辑。
		if(atkSPCityBuff < 1 && atkSPCityBuff > 0){
			atkSPCityBuff = 1.00;
		}
		Constants.player[i].setAtkSPCityBuff((int)atkSPCityBuff);
		}
	}
	
	private void EvenLogic() {
		// Constants.player[Constants.playerIndex]
		
		if(eBattleEven.IsMyArea()){
			customDialog.Show(CustomDialog.DIG_OK,
					title, 
					title + "飘荡着" + name + "的战旗。" ,
					DrawableID, Type, STEP_LAST);
		}else{
			if (PlayerType == 1) {
				customDialog.Show(CustomDialog.DIG_FIGHT,
						CustomDialog.DIG_CANCEL,
						title, 
						"攻下战场！可以获得士气提振！(每个战场增加10%攻击力,最少1点并可叠加)" ,
						DrawableID, Type, STEP_FRIST);
			} else if (PlayerType == 0) {
				// 写AI，AI肯定确定
				//模拟AI按左键
				DigRequest(Type, CustomDialog.BUTTON_LEFT, STEP_FRIST);
			}
		}
	}
	
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		this.name = Constants.player[Constants.playerIndex].getName();
		if (Step == STEP_LAST) {
			evenOverRequest.EvenRequest(Type);
			return false;
		} else if (Step == STEP_FRIST) {
			//
			if (ButtonIndex == 1) {
				//获取战斗随机数
				int BattleRandom = eBattleEven.BattleRandom();
//				if(Constants.DebugMode_BattleWin){
//					BattleRandom = 20;
//				}
				int AtkALL = BattleRandom + atk;
				//进入战斗
				if(eBattleEven.Battle(atk, def,BattleRandom)){
					//胜利
					customDialog.Show(CustomDialog.DIG_OK, 
							title, 
							eBattleEven.getWinString(atk, AtkALL, def, BattleRandom),
							DrawableID,
							Type, STEP_SECOND);
				}else{
					//失败
					customDialog.Show(CustomDialog.DIG_OK, 
							title, 
							eBattleEven.getLostString(atk, AtkALL, def, BattleRandom),
							DrawableID,
							Type, STEP_LAST);
				}
			} else if (ButtonIndex == 2) {
				customDialog.Show(CustomDialog.DIG_OK, 
						title, 
						name + "掂量了一下自己的份量后，静静的离去了",
						DrawableID,
						Type, STEP_LAST);
			}
		}else if (Step == STEP_SECOND) {
			
			customDialog.Show(CustomDialog.DIG_OK, 
					title, 
					name + "获得" + title + "！士气大振！" 
					,
					DrawableID,
					Type, STEP_LAST);
			BattleWin();
		}
		return false;
	}

}
