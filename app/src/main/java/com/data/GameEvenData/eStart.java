package com.data.GameEvenData;

import android.content.Context;

import com.data.Constants.Constants;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;

public class eStart implements CustomRequest {

	private CustomDialog customDialog;
	//private int Step;
	private EvenOverRequest evenOverRequest;
	// private Context _context;
	private final int LAST_STEP = -1;
	private final int Type = Constants.TYPE_START;
	//private int Index;
	private String title;
	private int DrawableID;
	//private int PlayerType;
	private int turnBonus;
	//
	public eStart(Context _context, EvenOverRequest evenOverRequest) {
		// TODO Auto-generated constructor stub
		customDialog = new CustomDialog(_context, this);
		customDialog.show();
		customDialog.dismiss();
		//customDialog.onCreate(Constants.savedInstanceState);
		//this.Step = 0;
		this.evenOverRequest = evenOverRequest;
	}

	public void ShowEven(String title,int DrawableID){
		//this.Index = Index;
		this.title = title;
		this.DrawableID = DrawableID;
		//this.PlayerType = Constants.player[Constants.playerIndex].getPlayerType();
		this.turnBonus = Constants.player[Constants.playerIndex].getTurnBonus();
		EvenLogic();
	}
	
	private void EvenLogic() {
		// Constants.player[Constants.playerIndex]
			customDialog.Show(CustomDialog.DIG_OK, 
					title, 
					"停留在起点时获得额外奖励" + turnBonus/2 + "G！",
					DrawableID,
					Type, LAST_STEP);
			AddMoney(turnBonus/2);
			eBattleEven.StaminaChange(
					eBattleEven.FormatPer(35, Constants.player[Constants.playerIndex].getStaminaMax())
					);
	}
	
	private void AddMoney(int money){
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
