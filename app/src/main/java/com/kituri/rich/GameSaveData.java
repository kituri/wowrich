package com.kituri.rich;


import com.data.Constants.Constants;
import com.data.playerdata.PlayerData;

import java.io.Serializable;

//import android.util.Log;


public class GameSaveData  implements Serializable {

	public int BarriersIndex; 
	public int[] possessionInt;
    //public GameSavePlayerData[] playerData;
	public PlayerData playerData[];

	private static final long serialVersionUID = 1L;

	public void InitData(){

			//Log.i("Save", "InitData()");

				
		//this.playerIndex = 10;

		 BarriersIndex = Constants.gameLayer.getBarriersIndex();

		 possessionInt = Constants.gameLayer.getMapAuraPossession();

		 
		 playerData = new PlayerData[Constants.player.length];
		 for(int i = 0;i < playerData.length;i++){
		 playerData[i] = Constants.player[i];
	 }
		
		 //Log.i("Save", "playerData.length:" + playerData.length);
		 //Log.i("Save", "InitData() over");
		 
	}

	public void LoadData(){

		//Log.i("Save", "LoadData()");
		
		//Log.i("Save", "BarriersIndex:" + BarriersIndex);
		//Log.i("Save", "possessionInt.length" + possessionInt.length);
		//Log.i("Save", "possessionInt[0]" + possessionInt[0]);
		
		if(BarriersIndex != -1){
		int BarriersX = Constants.gameLayer.getMapAura(BarriersIndex).getX();
		int BarriersY = Constants.gameLayer.getMapAura(BarriersIndex).getY();
		//Log.i("Save", "BarriersX and BarriersY get is over");
		Constants.gameLayer.setBarriers(true, BarriersX, BarriersY, BarriersIndex);
		}

		//Log.i("Save", "setBarriers over");
		
		Constants.gameLayer.setMapAuraPossession(possessionInt);
			
		Constants.player = new PlayerData[playerData.length];
		
		//Log.i("Save", "Constants.player = new reday");
		//Log.i("Save", "Constants.player.length:" + Constants.player.length);

		
		for(int i = 0;i<playerData.length;i++){
			Constants.player[i] = playerData[i];
			Constants.player[i].setMapAura(Constants.gameLayer.getMapAuraArray());
		}
		Constants.playerIndex = 0;
		//Log.i("Save", "Constants.player = new over");
		
	}
}
