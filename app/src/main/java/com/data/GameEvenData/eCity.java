package com.data.GameEvenData;

import android.content.Context;


import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;
//制作城市
public class eCity implements CustomRequest {

	private CustomDialog customDialog;
	//private int Step;
	private EvenOverRequest evenOverRequest;
	// private Context _context;
	private final int STEP_LAST = -1;
	private final int STEP_BUY = 0;
	
	
	private final int STEP_LVUP = 1;
	
	private final int STEP_FIGHT = 2;
	
//	private final int STEP_PAY = 3;
	
	//private final int STEP_FIVE = 4;
	
	private final int STEP_FIGHT_WIN = 5;

	private final int STEP_FIGHT_LOSE = 6;

	
	private final int Type = Constants.TYPE_CITY;
	//private final int BattleLV = 1;
	// 20% 30% 40%  16 14 12
	//private final int Difficulty = 14;
	//private int Index;
	private String title;
	private int DrawableID;
	private int PlayerType;
	private int LV;
	private int atk;
	private int def;
	private int payMoney;
	private int LvUpMoney;
	private int BuyMoney;
	private int index;
	private String name;
	//private int atkMax;
	//
	public eCity(Context _context, EvenOverRequest evenOverRequest) {
		// TODO Auto-generated constructor stub
		customDialog = new CustomDialog(_context, this);
		customDialog.show();
		customDialog.dismiss();
		//customDialog.onCreate(Constants.savedInstanceState);
		this.evenOverRequest = evenOverRequest;
	}
	
	public void ShowEven(String title,int DrawableID){
		//this.Index = Index;
		this.index = Constants.player[Constants.playerIndex].getIndex();
		this.title = title;
		this.DrawableID = DrawableID;
		this.PlayerType = Constants.player[Constants.playerIndex].getPlayerType();

		this.LV = Constants.gameLayer.getMapAura(index).getLV();
		this.atk = Constants.player[Constants.playerIndex].getAtkALL();
		this.def = Constants.gameLayer.getMapAura(index).getDef();
		this.payMoney = Constants.gameLayer.getMapAura(index).getCostPay();
		this.LvUpMoney = Constants.gameLayer.getMapAura(index).getCostLvUp();
		this.BuyMoney = Constants.gameLayer.getMapAura(index).getCostBuy();
		this.name = Constants.player[Constants.playerIndex].getName();
		//this.atkMax = Constants.player[Constants.playerIndex].getAtkMax();
		//this.def = atk + Difficulty;
		
		//12,14,16
		EvenLogic();
	}
	
	private void BattleWin(){
		//战斗胜利 - 1点LV
		Constants.gameLayer.setMapAuraPossession(
				Constants.player[Constants.playerIndex].getIndex(), 
				Constants.playerIndex);
		eBattleEven.BulidLVChange(-1);
	}
	
	private void BattleLose(){

		eBattleEven.PayMoney(payMoney*2);
		
		eBattleEven.BulidLVChange(-1);
	}
	
	private void EvenLogic() {
		// Constants.player[Constants.playerIndex]
		
		
		if(eBattleEven.CanBuy()){
			if (PlayerType == 1) {
			customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL, 
					title, "该地价值 " + BuyMoney + " G" +
							",是否要买下该地？", DrawableID, 
					Type, STEP_BUY);
			}else if(PlayerType == 0) {
				//AI
				if(eBattleEven.AI_BuyOrNoBuy()){
				DigRequest(Type, CustomDialog.BUTTON_LEFT, STEP_BUY);
				}else{
				DigRequest(Type, CustomDialog.BUTTON_RIGHT, STEP_BUY);
				}
			}
		}else{
			//是否是我的领地
			if(eBattleEven.IsMyArea()){
				if(IsLvNoMAX()){
					if (PlayerType == 1) {
				customDialog.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL, 
						title, "该地升级费用为 " + LvUpMoney 
						+ " G，是否要升级此地？", DrawableID, 
						Type, STEP_LVUP);}
					else if (PlayerType == 0) {
						if(eBattleEven.AI_LvupOrNoLvup()){
							DigRequest(Type, CustomDialog.BUTTON_LEFT, STEP_LVUP);
							}else{
								DigRequest(Type, CustomDialog.BUTTON_RIGHT, STEP_LVUP);
							}
						}
				}else{
					customDialog.Show(CustomDialog.DIG_OK, 
							title, "该地不能再升级了！", DrawableID, 
							Type, STEP_LAST);
				}
			}else{
				if (PlayerType == 1) {
				customDialog.Show(CustomDialog.DIG_FIGHT,
						CustomDialog.DIG_PAY,
						title, 
						"战斗难度： " + def + "\n" +
						//atk
						"当前攻击： " + atk + "(随机浮动1-20)\n" +
						"这是敌人的城市！\n" +
						"可以进行战斗或支付过路费 " + payMoney + " G" ,
						DrawableID, Type, STEP_FIGHT);
			} else if (PlayerType == 0) {
				// 写AI，AI肯定确定
				//模拟AI按左键
				if(eBattleEven.AI_FightOrPay(
						Constants.player[Constants.playerIndex].getAtk(),
						def)){
				DigRequest(Type, CustomDialog.BUTTON_LEFT, STEP_FIGHT);
				}else{
					DigRequest(Type, CustomDialog.BUTTON_RIGHT, STEP_FIGHT);
				}
			}
			}
		}
	}

	//IsNoMoney
	
	private void NoMoney(){
		customDialog.Show(CustomDialog.DIG_OK, 
				title, name + "没有足够的金钱！", DrawableID, 
				Type, STEP_LAST);
	}
	
	private void NoStamina(){
		customDialog.Show(CustomDialog.DIG_OK, 
				title, name + "没有足够的体力！", DrawableID, 
				Type, STEP_LAST);
	}
	
	private boolean IsLvNoMAX(){
		if(LV < 4){
		return true;
		}else{
			return false;
		}
	}
	
//	customDialog.Show(CustomDialog.DIG_OK
//	title, 
//	title + "飘荡着" + name + "的战旗。" ,
//	DrawableID, Type, STEP_LAST);	
	
//	if (PlayerType == 1) {
//		customDialog.Show(CustomDialog.DIG_FIGHT,
//				CustomDialog.DIG_CANCEL,
//				title, 
//				"攻下战场！可以获得士气提振！(每个战场能增加%10攻击力,可叠加)" ,
//				DrawableID, Type, STEP_FRIST);
//	} else if (PlayerType == 0) {
//		// 写AI，AI肯定确定
//		//模拟AI按左键
//		DigRequest(Type, CustomDialog.BUTTON_LEFT, STEP_FRIST);
//	}

	private void BuyCity(){
		customDialog.Show(CustomDialog.DIG_OK, 
				title, 
				name + "支付相关费用后，获得了" + title + "的支配权！\n"+ 
				"获得 1 点经验，失去 "+def + " 点体力。",
				DrawableID,
				Type, STEP_LAST);
		eBattleEven.MoneyChange(-BuyMoney);
		Constants.gameLayer.setMapAuraPossession(
				Constants.player[Constants.playerIndex].getIndex(), 
				Constants.playerIndex);
		eBattleEven.ExpChange(1);
		eBattleEven.StaminaChange(-def);
	}
	
	private void LvUpCity(){
		int oldLV = eBattleEven.getBulidLV();
		int newLV = eBattleEven.getBulidLV() + 1 ;
		customDialog.Show(CustomDialog.DIG_OK, 
				title, 
				name + "支付了相关费用后，"+ title + "的等级上升！\n" + 
				"LV： " + oldLV + " → " + newLV + " 。\n" + 
				"获得 2 点经验，失去 "+def + " 点体力。",
				DrawableID,
				Type, STEP_LAST);
		eBattleEven.BulidLVChange(1);
		eBattleEven.MoneyChange(-LvUpMoney);
		eBattleEven.ExpChange(2);
		eBattleEven.StaminaChange(-def);
	}
	
	private void NoPayMoney(){
		//DigRequest(Type,0,STEP_LAST);
		customDialog.Show(CustomDialog.DIG_OK, 
				title, 
				name + "果断离开了……",
				DrawableID,
				Type, STEP_LAST);
	}
	
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		
		if (Step == STEP_LAST) {
			evenOverRequest.EvenRequest(Type);
			return false;			
		}else if(Step == STEP_BUY){
			if(ButtonIndex == 1){
				//买地皮
				if(eBattleEven.IsNoMoney(BuyMoney) == false ){
					if(eBattleEven.CanStamina(def)){
					BuyCity();
					}else{
						NoStamina();
					}
				}else{
					NoMoney();
				}
			}else if(ButtonIndex == 2){
				//不买地皮
				NoPayMoney();
			}
		} else if(Step == STEP_LVUP){
			if(ButtonIndex == 1){
				//升级地皮
				if(eBattleEven.IsNoMoney(BuyMoney) == false ){
					if(eBattleEven.CanStamina(def)){
					LvUpCity();
					}else{
						NoStamina();
					}
				}else{
					NoMoney();
				}
			}else if(ButtonIndex == 2){
				//不升级地皮
				NoPayMoney();
			}
		}else if (Step == STEP_FIGHT) {
			//
			if (ButtonIndex == 1) {
				//获取战斗随机数
				int BattleRandom = eBattleEven.BattleRandom();
				int AtkALL = BattleRandom + atk;
				//进入战斗
				if(eBattleEven.Battle(atk, def,BattleRandom)){
					//胜利
					customDialog.Show(CustomDialog.DIG_OK, 
							title, 
							eBattleEven.getWinString(atk, AtkALL, def, BattleRandom),
							DrawableID,
							Type, STEP_FIGHT_WIN);
				}else{
					//失败
					customDialog.Show(CustomDialog.DIG_OK, 
							title, 
							eBattleEven.getLostString(atk, AtkALL, def, BattleRandom),
							DrawableID,
							Type, STEP_FIGHT_LOSE);
				}
			} else if (ButtonIndex == 2) {
				//付钱
				customDialog.Show(CustomDialog.DIG_OK, 
						title, 
						name + "支付了过路费后，从对方领地迅速得撤离了。",
						DrawableID,
						Type, STEP_LAST);
				eBattleEven.PayMoney(payMoney*2);
			}
		}else if (Step == STEP_FIGHT_WIN) {
			
			customDialog.Show(CustomDialog.DIG_OK, 
					title, 
					"一次辉煌的胜利！" + name + "获得了" + title + "的支配权！" ,
					DrawableID,
					Type, STEP_LAST);
			BattleWin();
		}else if(Step == STEP_FIGHT_LOSE){
			customDialog.Show(CustomDialog.DIG_OK, 
					title, 
					name + "在" + title + "的战役中失败。向对方领主支付了双倍的过路费，总计 " +
							payMoney*2 + " 元。" 
					,
					DrawableID,
					Type, STEP_LAST);
			BattleLose();
		}
		return false;
	}

}
