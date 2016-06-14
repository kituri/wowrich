package com.kituri.rich.EquipData;

import com.data.GameEvenData.eBattleEven;

import java.io.Serializable;

public class EquipItemsBUFF implements Serializable{
	private static final long serialVersionUID = 1L;

	static public final int TYPE_Equip_00 = 0;
	static public final int TYPE_Equip_01 = 1;
	static public final int TYPE_Equip_02 = 2;
	static public final int TYPE_Equip_03 = 3;
	static public final int TYPE_Equip_04 = 4;
	static public final int TYPE_Equip_05=  5;
	static public final int TYPE_Equip_06 = 6;
	static public final int TYPE_Equip_07 = 7;
	static public final int TYPE_Equip_08 = 8;	


	  public int getEquipAtk(int type) {
		  int EQUIP_ATK = 0;
		  switch(type){
		  case TYPE_Equip_00:
			  EQUIP_ATK = 7;
			  break;
		  case TYPE_Equip_01:
			  EQUIP_ATK = 5;
			  break;
		  case TYPE_Equip_02:
			  EQUIP_ATK = 8;
			  break;
		  case TYPE_Equip_03:
			  EQUIP_ATK = 6;
			  break;
		  case TYPE_Equip_04:
			  EQUIP_ATK = 4;
			  break;
			  
		  case TYPE_Equip_05:
			  EQUIP_ATK = 1;
			  break;
		  case TYPE_Equip_06:
			  EQUIP_ATK = 2;
			  break;
		  case TYPE_Equip_07:
			  EQUIP_ATK = 3;
			  break;
		  case TYPE_Equip_08:
			  EQUIP_ATK = 4;
			  break;
		  }
		  return EQUIP_ATK;
		}

	  public int getExtraMoney(int type , int money){
		  if(TYPE_Equip_01 == type){
			  return eBattleEven.FormatPer(money, 20);
		  }
		  return 0;
	  }

	  public int getExtraStamina(int type , int staminaCost){
		  if(TYPE_Equip_02 == type){
			  return  - eBattleEven.FormatPer(staminaCost, 25);
		  }
		  return 0;
	  }
	  
	  public int getExtraAC(int type , int AC){
		  if(TYPE_Equip_03 == type){
			  return  - eBattleEven.FormatPer(AC, 20);
		  }else 
			  if(TYPE_Equip_04 == type){
				  return  - eBattleEven.FormatPer(AC, 30);
		  }
		  return 0;
	  }

	  
}

