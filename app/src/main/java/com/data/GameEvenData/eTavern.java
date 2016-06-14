package com.data.GameEvenData;

import android.content.Context;

import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;

public class eTavern implements CustomRequest {

	private CustomDialog customDialog;
	private int Step;
	private EvenOverRequest evenOverRequest;
	// private Context _context;
	private final int LAST_STEP = -1;
	private final int Type = Constants.TYPE_TAVERN;
	//private int Index;
	private String title;
	private int DrawableID;
	private int PlayerType;
	
	private int payMoney;
	private int money;
	//
	public eTavern(Context _context, EvenOverRequest evenOverRequest) {
		// TODO Auto-generated constructor stub
		customDialog = new CustomDialog(_context, this);
		customDialog.show();
		customDialog.dismiss();
		//customDialog.onCreate(Constants.savedInstanceState);
		this.Step = 0;
		this.evenOverRequest = evenOverRequest;
	}

	public void ShowEven(String title,int DrawableID){
		//this.Index = Index;
		this.title = title;
		this.DrawableID = DrawableID;
		this.PlayerType = Constants.player[Constants.playerIndex].getPlayerType();
		
		this.payMoney = Constants.player[Constants.playerIndex].getStaminaMax()
		- Constants.player[Constants.playerIndex].getStamina();
		this.money = Constants.player[Constants.playerIndex].getMoney();
		EvenLogic();
	}
	
	private void EvenLogic() {
		// Constants.player[Constants.playerIndex]
		if (money < payMoney) {
			customDialog.Show(CustomDialog.DIG_OK, 
					title, 
					"又一个付不起钱的穷鬼！",
					DrawableID,
					Type, LAST_STEP);
			return;
		}
		
		if (IsStaminaMax()) {
			customDialog.Show(CustomDialog.DIG_OK, 
					title, 
					"体力已是MAX状态！",
					DrawableID,
					Type, LAST_STEP);
			return;
		}

		switch (Step) {
		case 0: {
			if (PlayerType == 1) {
				customDialog.Show(CustomDialog.DIG_PAY,
						CustomDialog.DIG_CANCEL,
						title, 
						"恢复全部体力需要 " + payMoney + " G",
						DrawableID, Type, Step);
			} else if (PlayerType == 0) {
				// 写AI，AI肯定恢复
				//模拟AI按左键
				DigRequest(Type, CustomDialog.BUTTON_LEFT, Step);
			}

		}
			break;
		}

	}

	private boolean IsStaminaMax(){
		if(Constants.player[Constants.playerIndex].getStaminaMax() ==
			Constants.player[Constants.playerIndex].getStamina()){
			return true;
		}
		return false;
		
	}
	
	private void PayMoney(int money){
		Constants.player[Constants.playerIndex].setMoneyChange(money);
	}
	
	private void StaminaRecovery(){
		Constants.player[Constants.playerIndex].setStaminaChange(
				Constants.player[Constants.playerIndex].getStaminaMax());
	}
	
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		if (Step == LAST_STEP) {
			evenOverRequest.EvenRequest(Type);
			return false;
		} else if (Step == 0) {
			// 付钱界面按左键右键
			if (ButtonIndex == 1) {
				customDialog.Show(CustomDialog.DIG_OK, 
						title, 
						"在酒馆睡了一晚后，体力恢复至MAX状态！",
						DrawableID,
						Type, LAST_STEP);
				//恢复体力，扣掉金钱
				PayMoney(payMoney);
				StaminaRecovery();
			} else if (ButtonIndex == 2) {
				customDialog.Show(CustomDialog.DIG_OK, 
						title, 
						"又一个付不起钱的穷鬼！",
						DrawableID,
						Type, LAST_STEP);
			}
		}

		return false;
	}

}
