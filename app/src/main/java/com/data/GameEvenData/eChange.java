package com.data.GameEvenData;



import android.content.Context;

import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;

public class eChange implements CustomRequest {

	private CustomDialog customDialog;
	//private int Step;
	private EvenOverRequest evenOverRequest;
	// private Context _context;
	private final int LAST_STEP = -1;
	//这里是类型必须改动
	private final int Type = Constants.TYPE_CHANGE;
	//private int Index;
	private String title;
	private int DrawableID;
	//private int PlayerType;
	
//	private int payMoney;
	//private int money;
	//private Random random;
	//
	public eChange(Context _context, EvenOverRequest evenOverRequest) {
		// TODO Auto-generated constructor stub
		customDialog = new CustomDialog(_context, this);
		customDialog.show();
		customDialog.dismiss();
		//customDialog.onCreate(Constants.savedInstanceState);
		//this.Step = 0;
		this.evenOverRequest = evenOverRequest;
		//random = new Random();
	}

	public void ShowEven(String title,int DrawableID){
		//this.Index = Index;
		this.title = title;
		this.DrawableID = DrawableID;
		//this.PlayerType = Constants.player[Constants.playerIndex].getPlayerType();
		EvenLogic();
	}
	
	private void EvenLogic() {
		int randomInt = eBattleEven.RandomInt(0,13);
		
		customDialog.Show(
				CustomDialog.DIG_OK,
				title, 
				message[randomInt],
				DrawableID, Type, LAST_STEP);
		chestEven(randomInt);
	}
	

//	 "鲜血宝箱",      //22
//	 "翡翠宝箱",      //23
//	 "暗月宝箱",      //24
//	 "黄金宝箱"       //25
	
	private String[] message = {
			"春节:获得500金钱",												
			"复活节:获得【黄金宝箱】一个",							
			"儿童节:获得100金钱，获得【体力恢复药(小)】",								
			"仲夏火焰节:获得200金钱，获得【鲜血钥匙】一条",								
			"收获节:获得100金钱，获得【鲜血钥匙】一条",		
			
			"美酒节:减少200金钱",															
			"冬幕节:减少150金钱，并获得【翡翠钥匙】一条",								
			"遇见神棍D，被骗取200G，购买了一件“银鳞胸甲”，其实是破铜烂铁",								
			"遇见傻曼，获得【暗月宝箱】一个",								
			"遇见法丝，获得【暗月宝箱】一个",								
			
			"遇见MT，被牵累进MT与暗夜男的战斗，损失30%体力，获得5点经验",								
			"流连于暗夜马戏团，失去金币300G",								
			"参加荆棘谷钓鱼大赛获胜，获得【翡翠宝箱】一个",								
			"在古拉巴什竞技场发现【翡翠宝箱】一个"							
	};
	
	private void chestEven(int index){
		switch(index){
		case 0:
			ChangeMoney(500);
			break;
		case 1:
			Constants.player[Constants.playerIndex].addItems(25);
			break;
		case 2:
			ChangeMoney(100);
			Constants.player[Constants.playerIndex].addItems(0);
			break;
		case 3:
			ChangeMoney(200);
			Constants.player[Constants.playerIndex].addItems(18);
			break;
		case 4:
			ChangeMoney(100);
			Constants.player[Constants.playerIndex].addItems(18);
			break;
		case 5:
			ChangeMoney(-200);
			break;
		case 6:
			ChangeMoney(-150);
			Constants.player[Constants.playerIndex].addItems(19);
			break;
		case 7:
			ChangeMoney(-200);
			break;
		case 8:
			Constants.player[Constants.playerIndex].addItems(24);
			break;
		case 9:
			Constants.player[Constants.playerIndex].addItems(24);
			break;
		case 10:
			staminaChange(0 - eBattleEven.FormatPer(
					Constants.player[Constants.playerIndex].getStaminaMax(),
					30));
			eBattleEven.ExpChange(5);
			break;
		case 11:
			ChangeMoney(-300);
			break;
		case 12:
			Constants.player[Constants.playerIndex].addItems(23);
			break;
		case 13:
			Constants.player[Constants.playerIndex].addItems(23);
			break;
		}
	}

	private void staminaChange(int sChange){
		Constants.player[Constants.playerIndex].setStaminaChange(sChange);
	}
	
	private void ChangeMoney(int money){
		Constants.player[Constants.playerIndex].setMoneyChange(money);
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
