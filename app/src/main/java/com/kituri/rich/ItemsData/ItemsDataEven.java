package com.kituri.rich.ItemsData;




public class ItemsDataEven{

//	 "大地图腾",
//	 "寒冬号角",
//	 "屠龙者的咆哮",
//	 "艾露恩的恩赐",
//	 "地狱咆哮的挽歌"
//	
    static public void Even(int ID,String name){
    	 //evenOverRequest
 	 switch(ID){
		case 0:
			EvenPoint.UsePoint(EvenPoint.TYPE_S_POINT,name);
			break;
		case 1:
			EvenPoint.UsePoint(EvenPoint.TYPE_M_POINT,name);
			break;
		case 2:
			EvenPoint.UsePoint(EvenPoint.TYPE_L_POINT, name);
			break;
		case 3:
			EvenPoint.UsePoint(EvenPoint.TYPE_MAX_POINT, name);
			break;
		case 4:
			EvenAtkBuff.UseAtkBuff(GameItemsBUFF.TYPE_ATK_LV1, name);
			break;
		case 5:
			EvenAtkBuff.UseAtkBuff(GameItemsBUFF.TYPE_ATK_LV2, name);
			break;
		case 6:
			EvenAtkBuff.UseAtkBuff(GameItemsBUFF.TYPE_ATK_LV3, name);
			break;
		case 7:
			EvenAtkBuff.UseAtkBuff(GameItemsBUFF.TYPE_ATK_LV4, name);
			break;
		case 8:
			EvenAtkBuff.UseAtkBuff(GameItemsBUFF.TYPE_ATK_LV5, name);
			break;
		case 9:
			EvenRecoveryBuff.UseAtkBuff(GameItemsBUFF.TYPE_RECOVERY_LV1, name);
			break;
		case 10:
			EvenRecoveryBuff.UseAtkBuff(GameItemsBUFF.TYPE_RECOVERY_LV2, name);
			break;
		case 11:
			EvenRecoveryBuff.UseAtkBuff(GameItemsBUFF.TYPE_RECOVERY_LV3, name);
			break;
		case 12:
			EvenRecoveryBuff.UseAtkBuff(GameItemsBUFF.TYPE_RECOVERY_LV4, name);
			break;
		case 13:
			EvenRecoveryBuff.UseAtkBuff(GameItemsBUFF.TYPE_RECOVERY_LV5, name);
			break;
		}
 	 
 	 
 	 
	}

    static public void Even(int keyID,int chestID){
    	if(keyID == 21 && chestID == 25){
    		EvenKeyChest.UseOpenChestLV4();	
    	}else     	if(keyID == 20 && chestID == 24){
    		EvenKeyChest.UseOpenChestLV3();	
    	}else     	if(keyID == 19 && chestID == 23){
    		EvenKeyChest.UseOpenChestLV2();	
    	}else     	if(keyID == 18 && chestID == 22){
    		EvenKeyChest.UseOpenChestLV1();	
    	}
    }
}
