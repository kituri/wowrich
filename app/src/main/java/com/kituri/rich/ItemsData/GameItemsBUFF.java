package com.kituri.rich.ItemsData;

import java.io.Serializable;

import com.data.GameEvenData.eBattleEven;

public class GameItemsBUFF implements Serializable{
	private static final long serialVersionUID = 1L;

	//所有延迟类BUFF都在这里体现。
	
//	 "大地图腾",
//	 "寒冬号角",
//	 "屠龙者的咆哮",
//	 "艾露恩的恩赐",
//	 "地狱咆哮的挽歌"
//	
	static public final int TYPE_ATK_LV1 = 1;
	static public final int TYPE_ATK_LV2 = 2;
	static public final int TYPE_ATK_LV3 = 3;
	static public final int TYPE_ATK_LV4 = 4;
	static public final int TYPE_ATK_LV5 = 5;
	
	static public final int TYPE_RECOVERY_LV1 = 1;
	static public final int TYPE_RECOVERY_LV2 = 2;
	static public final int TYPE_RECOVERY_LV3 = 3;
	static public final int TYPE_RECOVERY_LV4 = 4;
	static public final int TYPE_RECOVERY_LV5 = 5;
	
	
	  private int BUFF_ATK_TURN_01 = 0;
	  private int BUFF_ATK_TURN_02 = 0;
	  private int BUFF_ATK_TURN_03 = 0;
	  private int BUFF_ATK_TURN_04 = 0;
	  private int BUFF_ATK_TURN_05 = 0;
	 
	  private int BUFF_RECOVERY_TURN_01 = 0;
	  private int BUFF_RECOVERY_TURN_02 = 0;
	  private int BUFF_RECOVERY_TURN_03 = 0;
	  private int BUFF_RECOVERY_TURN_04 = 0;
	  private int BUFF_RECOVERY_TURN_05 = 0;
	  
	  public void setAtkBuff(int type){
		  switch(type){
		  case TYPE_ATK_LV1:
			  BUFF_ATK_TURN_01 = 8;
			  break;
		  case TYPE_ATK_LV2:
			  BUFF_ATK_TURN_02 = 4;
			  break;
		  case TYPE_ATK_LV3:
			  BUFF_ATK_TURN_03 = 10;
			  break;
		  case TYPE_ATK_LV4:
			  BUFF_ATK_TURN_04 = 8;
			  break;
		  case TYPE_ATK_LV5:
			  BUFF_ATK_TURN_05 = 4;
			  break;
		  }
	  }
	  
	  public void setRecoveryBuff(int type){
		  switch(type){
		  case TYPE_RECOVERY_LV1:
			  BUFF_RECOVERY_TURN_01 = 8;
			  break;
		  case TYPE_RECOVERY_LV2:
			  BUFF_RECOVERY_TURN_02 = 6;
			  break;
		  case TYPE_RECOVERY_LV3:
			  BUFF_RECOVERY_TURN_03 = 6;
			  break;
		  case TYPE_RECOVERY_LV4:
			  BUFF_RECOVERY_TURN_04 = 6;
			  break;
		  case TYPE_RECOVERY_LV5:
			  BUFF_RECOVERY_TURN_05 = 4;
			  break;
		  }
	  }
	  
	  public void nextTurn(){
		 
		 if(BUFF_ATK_TURN_01 > 0){
			 BUFF_ATK_TURN_01--;
		 }
		 if(BUFF_ATK_TURN_02 > 0){
			 BUFF_ATK_TURN_02--;
		 }
		 if(BUFF_ATK_TURN_03 > 0){
			 BUFF_ATK_TURN_03--;
		 }
		 if(BUFF_ATK_TURN_04 > 0){
			 BUFF_ATK_TURN_04--;
			 }
		 if(BUFF_ATK_TURN_05 > 0){
			 BUFF_ATK_TURN_05--;
		 }
		 
		 if(BUFF_RECOVERY_TURN_01 > 0){
			 BUFF_RECOVERY_TURN_01--;
		 }
		 if(BUFF_RECOVERY_TURN_02 > 0){
			 BUFF_RECOVERY_TURN_02--;
		 }
		 if(BUFF_RECOVERY_TURN_03 > 0){
			 BUFF_RECOVERY_TURN_03--;
		 }
		 if(BUFF_RECOVERY_TURN_04 > 0){
			 BUFF_RECOVERY_TURN_04--;
			 }
		 if(BUFF_RECOVERY_TURN_05 > 0){
			 BUFF_RECOVERY_TURN_05--;
		 }
		 
	 }

	  public int getAtkBuff(int atk){
		 int atkBuff = 0;
		 if(BUFF_ATK_TURN_01 > 0){
			 if(eBattleEven.FormatPer(atk, 10) < 1){
				 atkBuff+= 1;
			 }else{
			 atkBuff+=eBattleEven.FormatPer(atk, 10);
			 }
		 }
		 if(BUFF_ATK_TURN_02 > 0){
			 if(eBattleEven.FormatPer(atk, 20) < 2){
				 atkBuff+= 2;
			 }else{
			 atkBuff+=eBattleEven.FormatPer(atk, 20);
			 }
		 }
		 if(BUFF_ATK_TURN_03 > 0){
			 if(eBattleEven.FormatPer(atk, 20) < 2){
				 atkBuff+= 2;
			 }else{
			 atkBuff+=eBattleEven.FormatPer(atk, 20);
			 }
		 }
		 if(BUFF_ATK_TURN_04 > 0){
			 if(eBattleEven.FormatPer(atk, 25) < 3){
				 atkBuff+= 3;
			 }else{
			 atkBuff+=eBattleEven.FormatPer(atk, 25);
			 }
		 }
		 if(BUFF_ATK_TURN_05 > 0){
			 if(eBattleEven.FormatPer(atk, 30) < 3){
				 atkBuff+= 4;
			 }else{
			 atkBuff+=eBattleEven.FormatPer(atk, 30);
			 }
		 }
	 return atkBuff;
	 }
	  
	  public int getRecoveryBuff(){
			 int recoveryBuff = 0;
			 //BUFF_RECOVERY_TURN_01
			 
			 if(BUFF_RECOVERY_TURN_01 > 0){
				 recoveryBuff+=12;
			 }
			 if(BUFF_RECOVERY_TURN_02 > 0){
				 recoveryBuff+=10;
			 }
			 if(BUFF_RECOVERY_TURN_03 > 0){
				 recoveryBuff+=35;
			 }
			 if(BUFF_RECOVERY_TURN_04 > 0){
				 recoveryBuff+=50;
			 }
			 if(BUFF_RECOVERY_TURN_05 > 0){
				 recoveryBuff+=80;
			 }
			 
		 return recoveryBuff;
		 }
	  
}

