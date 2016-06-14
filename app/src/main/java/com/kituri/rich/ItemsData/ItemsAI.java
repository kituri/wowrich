package com.kituri.rich.ItemsData;

import android.content.Context;



import com.data.Constants.Constants;
import com.data.GameEvenData.eBattleEven;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.EvenOverRequest;

public class ItemsAI implements CustomRequest {

	private CustomDialog customDialog;
	private final int STEP_FRIST = 0;
	
	private final int STEP_LAST = -1;
	private final int TYPE_USE_ITEM = 91;
	private final String title = "";
	private EvenOverRequest evenOverRequest;
	
	public ItemsAI(Context context,EvenOverRequest evenOverRequest){
		customDialog = new CustomDialog(context,this);
		this.evenOverRequest = evenOverRequest;
	}

	public void AI_UseItem(){
		//boolean itemUsed = false;

		if(WP_USE()){
			return;
		}
		if(KEY_USE()){
			return;
		}
		if(staminaRecovery_USE()){
			return;
		}
		if(item_USE()){
			return;
		}
		if(MAP_ITEM_USE()){
			return;
		}

		
		DigRequest(TYPE_USE_ITEM,0,STEP_LAST);
		//DigRequest
	}
	private boolean MAP_ITEM_USE(){
		//搜索符合条件的房子
		int randomInt = eBattleEven.RandomInt(1, 3);
		int index = -1;
		
		//测一下 1和3
		
		randomInt = 1;
		//Log.i("AI", "MAP_ITEM_USE");
		//Log.i("AI", "randomInt:" + randomInt);
		if(eBattleEven.RandomPer(20)){
		//if(true){//仅测试用
			if(randomInt == 1){
				index = Use_EffectFireStorm();
				if(CanUseItem(15) && index != -1){
				EffectFireStorm(index);
				return true;
				}
				randomInt = 2;
			}
			if(randomInt == 2){
				index = Use_EffectGoblinEnginerr();
				//Log.i("AI", "index:" + index);
				if(CanUseItem(16) && index != -1){
				EffectGoblinEnginerr(index);
				return true;
				}
				randomInt = 3;
			}
			if(randomInt == 3){
				index = Use_EffectEarthQuake();
				if(CanUseItem(17) && index != -1){
				EffectEarthQuake(index);
				return true;
				}
			}
		}
		
		
		return false;
	}
	
	private int Use_EffectFireStorm(){
		//获取所有对方城市，获取所有对方LV > 1的城市。的index.
		//int playerIndex = Constants.playerIndex;
		int possessionSize = 0;
		//int lv= Constants.gameLayer.getMapAura(index).getLV();
		//int maxlv= Constants.gameLayer.getMapAura(index).getMaxLV();
		for(int i=0;i < Constants.gameLayer.getMapAuraCounts();i++){
			if(Constants.gameLayer.getPossession(i) != Constants.playerIndex){
				if(Constants.gameLayer.getMapAura(i).getType() == Constants.TYPE_CITY){
				if(Constants.gameLayer.getMapAura(i).getLV() > 1){
					possessionSize++;
				}
				}
			}
		}
		
		if(possessionSize == 0){
			return -1;
		}
		
		int[] index = new int[possessionSize];
		int   arrIndex = 0;
		for(int i=0;i < Constants.gameLayer.getMapAuraCounts();i++){
			if(Constants.gameLayer.getPossession(i) != Constants.playerIndex){
				if(Constants.gameLayer.getMapAura(i).getType() == Constants.TYPE_CITY){
				if(Constants.gameLayer.getMapAura(i).getLV() > 1){
					index[arrIndex] = i;
					arrIndex++;
				}
				}
			}
		}
		
		int lvTop = 0;
		int selectIndex = index.length - 1;
		for(int i = 0;i<index.length;i++){
			if(lvTop <= index[i]){
				lvTop = index[i];
				selectIndex = i;
			}
		}
		return index[selectIndex];
	}

	private int Use_EffectGoblinEnginerr(){

		//获取所有对方城市，获取所有对方LV > 1的城市。的index.
		int possessionSize = 0;
		//int lv= Constants.gameLayer.getMapAura(index).getLV();
		//int maxlv= Constants.gameLayer.getMapAura(index).getMaxLV();
		for(int i=0;i < Constants.gameLayer.getMapAuraCounts();i++){
			if(Constants.gameLayer.getPossession(i) == Constants.playerIndex){
				if(Constants.gameLayer.getMapAura(i).getType() == Constants.TYPE_CITY){
				if(Constants.gameLayer.getMapAura(i).getLV() < 4){
					possessionSize++;
				}
				}
			}
		}
		
		if(possessionSize == 0){
			return -1;
		}
		
		int[] index = new int[possessionSize];
		int   arrIndex = 0;
		for(int i=0;i < Constants.gameLayer.getMapAuraCounts();i++){
			if(Constants.gameLayer.getPossession(i) == Constants.playerIndex){
				if(Constants.gameLayer.getMapAura(i).getType() == Constants.TYPE_CITY){
				if(Constants.gameLayer.getMapAura(i).getLV() < 4){
					index[arrIndex] = i;
					arrIndex++;
				}
				}
			}
		}
		int lvTop = 0;
		int selectIndex = index.length - 1;
		for(int i = 0;i<index.length;i++){
			if(lvTop <= index[i]){
				lvTop = index[i];
				selectIndex = i;
			}
		}
		return index[selectIndex];
		//int randomInt = eBattleEven.RandomInt(0, possessionSize - 1);
		//return index[randomInt];
	}

	private int Use_EffectEarthQuake(){
		//获取所有对方城市。的index.
		int possessionSize = 0;
		//int lv= Constants.gameLayer.getMapAura(index).getLV();
		//int maxlv= Constants.gameLayer.getMapAura(index).getMaxLV();
		for(int i=0;i < Constants.gameLayer.getMapAuraCounts();i++){
			if(Constants.gameLayer.getPossession(i) != Constants.playerIndex){
				if(Constants.gameLayer.getMapAura(i).getType() == Constants.TYPE_CITY){
					possessionSize++;
				}
			}
		}
		
		if(possessionSize == 0){
			return -1;
		}
		
		int[] index = new int[possessionSize];
		int   arrIndex = 0;
		for(int i=0;i < Constants.gameLayer.getMapAuraCounts();i++){
			if(Constants.gameLayer.getPossession(i) != Constants.playerIndex){
				if(Constants.gameLayer.getMapAura(i).getType() == Constants.TYPE_CITY){
					index[arrIndex] = i;
					arrIndex++;
				}
			}
		}
		
		int lvTop = 0;
		int selectIndex = index.length - 1;
		for(int i = 0;i<index.length;i++){
			if(lvTop <= index[i]){
				lvTop = index[i];
				selectIndex = i;
			}
		}
		return index[selectIndex];
	}

	
	private void EffectFireStorm(int index){
//		int PosX = Constants.gameLayer.getMapAura(index).getX();
//		int PosY = Constants.gameLayer.getMapAura(index).getY();
		Constants.gameLayer.setMapAuraLVChange(index, -1);
		Constants.player[Constants.playerIndex].removeItems(15);
		//Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";
		customDialog.Show(CustomDialog.BUTTON_CENTER,
				title, 
				Constants.player[Constants.playerIndex].getName() + "使用了" 
				+ Constants.player[Constants.playerIndex].getItemsByID(15).getName() +
				"！" +
				"火焰吞噬着敌人的领地 ！" + 
				Constants.gameLayer.getName(index) + "等级下降！", 
				Constants.player[Constants.playerIndex].getItemsByID(15).getDrawableID(),
				TYPE_USE_ITEM, STEP_LAST);
	}
	private void EffectGoblinEnginerr(int index){
		Constants.gameLayer.setMapAuraLVChange(index, 1);
		Constants.player[Constants.playerIndex].removeItems(16);
		//Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";
		customDialog.Show(CustomDialog.BUTTON_CENTER,
				title,
				Constants.player[Constants.playerIndex].getName() + "使用了" 
				+ Constants.player[Constants.playerIndex].getItemsByID(16).getName() +
				"！" +
				Constants.gameLayer.getName(index) + "等级上升一级！", 
				Constants.player[Constants.playerIndex].getItemsByID(16).getDrawableID(),
				TYPE_USE_ITEM, STEP_LAST);
	}
	private void EffectEarthQuake(int index){
		//Constants.gameLayer.
		Constants.gameLayer.setMapAuraPossession( index, - 1);
		Constants.gameLayer.setMapAuraLVChange(index, -4);
		Constants.player[Constants.playerIndex].removeItems(17);
		//Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";
		customDialog.Show(CustomDialog.BUTTON_CENTER,
				title, 				Constants.player[Constants.playerIndex].getName() + "使用了" 
				+ Constants.player[Constants.playerIndex].getItemsByID(17).getName() +
				"！" +
				"一阵天崩地裂后，"
				+ Constants.gameLayer.getName(index) + "已被夷为平地。", 
				Constants.player[Constants.playerIndex].getItemsByID(17).getDrawableID(),
				TYPE_USE_ITEM, STEP_LAST);
	}
	
	private boolean KEY_USE(){
//		 //18
//		 "鲜血钥匙",      //18
//		 "翡翠钥匙",      //19
//		 "暗月钥匙",      //20
//		 "黄金钥匙",      //21
//		 //22
//		 "鲜血宝箱",      //22
//		 "翡翠宝箱",      //23
//		 "暗月宝箱",      //24
//		 "黄金宝箱"       //25
		int keyID = getUseKeyID();
		int chestID = keyID + 4;
		if(keyID != -1){
			Constants.player[Constants.playerIndex].removeItems(keyID);
			Constants.player[Constants.playerIndex].removeItems(chestID);
			ItemsDataEven.Even(keyID,chestID);
			OpenChest(chestID);
			return true;
		}
		return false;
	}
	
	private void OpenChest(int chestID){
		customDialog.Show(CustomDialog.DIG_OK,
				title, Constants.ItemMessage
				, Constants.player[Constants.playerIndex].getItemsByID(chestID).getDrawableID()
, TYPE_USE_ITEM, STEP_LAST);
	}
	
	private boolean WP_USE(){
		//getEquipItemsTopLV
		int wp = Constants.player[Constants.playerIndex].getEquipItemsWP();
		if(Constants.player[Constants.playerIndex].getEquipItemsTopLV_ID() != wp){
		Constants.player[Constants.playerIndex].setEquipItemsWP(
				Constants.player[Constants.playerIndex].getEquipItemsTopLV_ID());
		}
		if(wp != Constants.player[Constants.playerIndex].getEquipItemsWP()){
			wp = Constants.player[Constants.playerIndex].getEquipItemsWP();
			customDialog.Show(CustomDialog.DIG_OK,
					title,Constants.player[Constants.playerIndex].getName() + " 装备上了\n【" + 
					Constants.player[Constants.playerIndex].getEquipsByID(wp).getName() + "】。"
					, Constants.player[Constants.playerIndex].getEquipsByID(wp).getDrawableID()
	, TYPE_USE_ITEM , STEP_FRIST);
			return true;
		}
		return false;
	}

	//物品类别
	/*
	 * -1 ： 不可使用
	 *  0:  恢复体力的药水
	 *  1:  增加攻击力的延时卷轴
	 *  2:  恢复体力的延时卷轴
	 *  3:  需要打开地图选择类的物品
	 *  4:  钥匙
	 *  5:  箱子
	 * */
	private boolean item_USE(){
		//turn
		//int randomInt = eBattleEven.RandomInt(1, 100);
		if(eBattleEven.RandomPer(10)){
		if(use_atkScroll(5)){
			return true;
		}
		}
		return false;
	}
//	 "立刻恢复20%体力",
//	 "立刻恢复40%体力",
//	 "立刻恢复60%体力",
//	 "体力立刻恢复到上限。",
	private boolean staminaRecovery_USE(){
		//如果被随机到的物品没有，那么就往下使用。往下也没有就不使用。
		//0-4  //9-13	
		//如果体力低于  30%       使用  3(34%)，12(33%)  13(33%)
		//如果体力低于  40%       使用  2(50%)，11(50%)
		//如果体力低于  60%       使用  1(50%)，10(50%)
		//如果体力低于  80%       使用  0(50%)，9(50%)
		int staminaPer = Constants.player[Constants.playerIndex].getPerStamina();
		int randomInt = eBattleEven.RandomInt(1, 100);
		boolean itemUsed = false;
		//Log.i("AI", "staminaPer:" + staminaPer);
		//Log.i("AI", "randomInt:" + randomInt);
		
		if(eBattleEven.RandomPer(50)){
		if(staminaPer < 20){
			if(randomInt >= 1 && randomInt <= 34){
				return use_staminaPoint(4);
			}else if(randomInt >= 35 && randomInt <= 67){
				return use_staminaScroll(4);
			}else if(randomInt >= 68 && randomInt <= 100){
				return use_staminaScroll(4);
			}
		}else if(staminaPer < 30){
			if(eBattleEven.RandomPer(50)){
				return use_staminaPoint(3);
			}else{
				return use_staminaScroll(3);
			}
		}else if(staminaPer < 50){
			if(eBattleEven.RandomPer(50)){
				return use_staminaPoint(2);
			}else{
				return use_staminaScroll(2);
			}
		}else if(staminaPer < 70){
			if(eBattleEven.RandomPer(50)){
				return use_staminaPoint(1);
			}else{
				return use_staminaScroll(1);
			}
		}
		}
		return itemUsed;
	}

	private boolean use_staminaPoint(int lv){
		//LV 1 2 3 4
		//Log.i("AI", "use_staminaPoint(lv): " + lv);
		int[] ID = {0,1,2,3};
		
		for(int i = lv - 1 ; i > -1 ; i--){
		 if(CanUseItem(ID[i])){
			 use_item_effect(ID[i]);
			 return true;
		 }	
		}
		return false;
	}
	
	private boolean use_staminaScroll(int lv){
		//Log.i("AI", "use_staminaScroll(lv): " + lv);
		int[] ID = {9,10,11,12,13};
		for(int i = lv - 1 ; i > -1 ; i--){
		 if(CanUseItem(ID[i])){
			 use_item_effect(ID[i]);
			 return true;
		 }	
		}
		return false;
	}
	
	private boolean use_atkScroll(int lv){
		//LV 1 2 3 4
		int[] ID = {4,5,6,7,8};
		
		for(int i = lv - 1 ; i > -1 ; i--){
		 if(CanUseItem(ID[i])){
			 use_item_effect(ID[i]);
			 return true;
		 }	
		}
		return false;
	}
	
	//判断物品是否可使用
	private boolean CanUseItem(int ID){	
		if(getItemCounts(ID)>0){
			//if(isCanAIUse(ID)){
			return true;
			//}
		}
		return false;
	}
	
	private int getUseKeyID(){
		if(CanUseItem(21) && CanUseItem(25)){
			return 21;
		}else if(CanUseItem(20) && CanUseItem(24)){
			return 20;
		}else if(CanUseItem(19) && CanUseItem(23)){
			return 19;
		}else if(CanUseItem(18) && CanUseItem(22)){
			return 18;
		}
		return -1;
	}
	//获取物品数量
	private int getItemCounts(int ID){
		//Log.i("AI", "getCounts(): " + Constants.player[Constants.playerIndex].getItemsByID(ID).getCounts());
		return Constants.player[Constants.playerIndex].getItemsByID(ID).getCounts();
	}
	//获取物品是否可以NPC使用
	public boolean isCanAIUse(int ID){
		//Log.i("AI", "getCounts(): " + Constants.player[Constants.playerIndex].getItemsByID(ID).getCounts());
		return Constants.player[Constants.playerIndex].getItemsByID(ID).isCanAIUse();
	}
	//使用物品的效果
	private void use_item_effect(int ID){
		//Log.i("AI", "use_item_effect(ID):" + ID);
		ItemsDataEven.Even(ID,
				Constants.player[Constants.playerIndex].getItemsByID(ID).getName());
		Constants.player[Constants.playerIndex].removeItems(ID);
		tipMessage(ID);
	} 
	//提示信息
	private void tipMessage(int ID) {
		//Log.i("removeItems index:", ""+index);
		customDialog.Show(CustomDialog.DIG_OK,
				title,Constants.player[Constants.playerIndex].getName() + "　" +Constants.ItemMessage
				, Constants.player[Constants.playerIndex].getItemsByID(ID).getDrawableID()
, TYPE_USE_ITEM , STEP_FRIST);
		// TODO Auto-generated method stub
	}

	@Override
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		evenOverRequest.EvenRequest(Constants.AI_TYPE_ITEM_USE);
		return false;
	}
}
