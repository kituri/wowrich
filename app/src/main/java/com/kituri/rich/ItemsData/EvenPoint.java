package com.kituri.rich.ItemsData;



import com.data.Constants.Constants;


public class EvenPoint{

static public final int TYPE_S_POINT = 1310;
static public final int TYPE_M_POINT = 514;
static public final int TYPE_L_POINT = 124142;
static public final int TYPE_MAX_POINT = 1411113;

static	public void UsePoint(int TypeID,String name){
		int Stamina30  = Constants.player[Constants.playerIndex].getStaminaMax() / 10* 3;
		int Stamina40  = Constants.player[Constants.playerIndex].getStaminaMax() / 5 * 2;
		int Stamina50  = Constants.player[Constants.playerIndex].getStaminaMax() / 2;
		int Stamina100 = Constants.player[Constants.playerIndex].getStaminaMax();
		switch(TypeID){
		case TYPE_S_POINT:
			Constants.player[Constants.playerIndex].setStaminaChange(Stamina30);
			
			break;
		case TYPE_M_POINT:
			Constants.player[Constants.playerIndex].setStaminaChange(Stamina40);
			
			break;
		case TYPE_L_POINT:
			Constants.player[Constants.playerIndex].setStaminaChange(Stamina50);
			
			break;
		case TYPE_MAX_POINT:
			Constants.player[Constants.playerIndex].setStaminaChange(Stamina100);
			
			break;
		}	
		Constants.ItemMessage =  "使用 " + name +" ，恢复了体力。";
	}
}
