package com.kituri.rich.ItemsData;

import java.io.Serializable;

import com.kituri.wowrich.R;

public class ItemsData implements Serializable{
	private static final long serialVersionUID = 1L;
//这个类只取数据
	
	
//	//==========道具============
//	//唯一标识符 ID
//	private int ID;
//	//名字
//	private String name;
//	//叙述（作用）
//	private String about;
//	//对应的图标图片
//	private int drawableID; 
//	//物品等级
//	private int LV;
//	//是否是 收费道具
//	private boolean chargesProps;
	
 private GameItemsListData[] gameData;

 private final int size = 26;

 
 private final String[] name = {
		 "体力恢复药(小)",//00
		 "体力恢复药(中)",//01
		 "体力恢复药(大)",//02
		 "超级体力恢复药",//03
		 //4
		 "力量卷轴",      //04
		 "大地图腾",      //05
		 "力量祝福",      //06
		 "屠龙者的咆哮",  //07
		 "地狱咆哮的战歌",//08
		//9 
		 "愈合祷言",      //09
		 "圣疗"	,         //10
		 "回春术",        //11
		 "圣光术"	,     //12
		 "宁静",          //13
		 //14
		 "冰冻陷阱",      //14
		 "烈焰风暴",      //15
		 "地精工程师",    //16
		 "天崩地裂",      //17
		 //18
		 "鲜血钥匙",      //18
		 "翡翠钥匙",      //19
		 "暗月钥匙",      //20
		 "黄金钥匙",      //21
		 //22
		 "鲜血宝箱",      //22
		 "翡翠宝箱",      //23
		 "暗月宝箱",      //24
		 "黄金宝箱"       //25
 };
 
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
 
private final int[] itemType= {
		 0,0,0,0,
		 1,1,1,1,1,
		 2,2,2,2,2,
		 3,3,3,3,
		 4,4,4,4,
		 5,5,5,5
};
 
 private final String[] about = {
		 "立刻恢复20%体力",
		 "立刻恢复40%体力",
		 "立刻恢复60%体力",
		 "体力立刻恢复到上限。",
		 
		 "攻击力上升10%（最少上升1点），持续8个战斗回合",
		 "攻击力上升20%（最少上升2点），持续4个战斗回合",
		 "攻击力上升20%（最少上升2点），持续10个战斗回合",
		 "攻击力上升25%（最少上升3点），持续8个战斗回合",
		 "攻击力上升30%（最少上升4点），持续4个战斗回合",
		 
		 "体力恢复12，持续8个回合",
		 "体力恢复10，持续6个回合",
		 "体力恢复35，持续6个回合",
		 "体力恢复50，持续6个回合",
		 "体力恢复80，持续4个回合",
		 
		 "设置一个冰冻陷阱，阻止玩家前进",
		 "其他玩家的一幢建筑物降低一级。(降到最低不可再降)",
		 "为自己的一幢建筑物立刻升一级",
		 "直接摧毁其他玩家的一幢建筑物，并取消对方对该地图的持有权。",
		 
		 "一把开启鲜血宝箱的钥匙",
		 "一把开启翡翠宝箱的钥匙",
		 "一把开启暗月宝箱的钥匙",
		 "一把开启黄金宝箱的钥匙",
		 
		 "需要鲜血钥匙才能开启该宝箱",
		 "需要翡翠钥匙才能开启该宝箱",
		 "需要暗月钥匙才能开启该宝箱",
		 "需要黄金钥匙才能开启该宝箱"
 };
 private final int[] drawableID = {
		 R.drawable.item_00,
		 R.drawable.item_01,
		 R.drawable.item_02,
		 R.drawable.item_03,
		 
		 R.drawable.item_04,
		 R.drawable.item_05,
		 R.drawable.item_06,
		 R.drawable.item_07,
		 R.drawable.item_08,
		 
		 R.drawable.item_09,
		 R.drawable.item_10,
		 R.drawable.item_11,
		 R.drawable.item_12,
		 R.drawable.item_13,
		 
		 R.drawable.item_14,
		 R.drawable.item_15,
		 R.drawable.item_16,
		 R.drawable.item_17,
		 
		 R.drawable.item_18,
		 R.drawable.item_19,
		 R.drawable.item_20,
		 R.drawable.item_21,
		 
		 R.drawable.item_22,
		 R.drawable.item_23,
		 R.drawable.item_24,
		 R.drawable.item_25
		 
 };
 private final int[] LV = {
		 1,2,3,4,
		 1,2,3,4,4,
		 1,2,3,4,4,
		 3,3,4,3,
		 1,2,3,4,
		 1,2,3,4
 };
 private final boolean[] chargesProps = {
		 false,false,false,true,
		 true,true,true,false,false,
		 true,true,true,false,false,
		 true,true,true,true,
		 true,true,false,false,
		 true,true,true,true
 };
 
 private final boolean[] canAIUse = {
		 true,true,true,true,
		 true,true,true,true,true,
		 true,true,true,true,true,
		 false,false,false,false,
		 false,false,false,false,
		 false,false,false,false
 };
 
 private int[] ID = {
		 0,1,2,3,
		 4,5,6,7,8,
		 9,10,11,12,13,
		 14,15,16,17,
		 18,19,20,21,
		 22,23,24,25
 };
 
	public  ItemsData(){
		gameData = new GameItemsListData[size];
//		ID = new int[counts];
//		name = new String[counts];
//		about = new String[counts];
//		drawableID = new int[counts];
//		LV = new int[counts];
//		chargesProps = new boolean[counts];
		for(int i = 0;i < gameData.length;i++){
			GameItemsListData giData = new GameItemsListData();
			giData.setID(ID[i]);
			giData.setName(name[i]);
			giData.setAbout(about[i]);
			giData.setDrawableID(drawableID[i]);
			giData.setLV(LV[i]);
			giData.setChargesProps(chargesProps[i]);
			giData.setCanAIUse(canAIUse[i]);
			giData.setItemType(itemType[i]);
			gameData[i] = giData;
		}
	}
	
	public void setItemCounts(int ID,int counts){
		for(int i = 0;i<gameData.length;i++){
			if(gameData[i].getID() == ID){
				//Log.i("setItemCounts:", "gameData[i].getID() == ID i:" + i);
				gameData[i].setCounts(counts);
				return;
			}
		}
	}
	
	public void setItemCountsChange(int ID,int counts){
		for(int i = 0;i<gameData.length;i++){
			if(gameData[i].getID() == ID){
				//Log.i("setItemCounts:", "gameData[i].getID() == ID i:" + i);
				gameData[i].setCounts(gameData[i].getCounts() + counts);
				return;
			}
		}
	}
	
	private GameItemsListData getGameData(int itemIndex){
		return gameData[itemIndex];
	}
	
	public int[] getItemID_bySaveGame(){
		int[] itemsID = new int[gameData.length];
		for(int i = 0;i < itemsID.length;i++){
			itemsID[i] = gameData[i].getID();
		}
		return itemsID;
	}

	public int[] getItemCounts_bySaveGame(){
		int[] itemsCounts = new int[gameData.length];
		for(int i = 0;i < itemsCounts.length;i++){
			itemsCounts[i] = gameData[i].getCounts();
		}
		return itemsCounts;
	}

	public void setItems_byLoadGame(int id[],int Counts[]){
		for(int i = 0;i < gameData.length;i++){
		setItemCountsChange(id[i],Counts[i]);
		}
	}
	
	public GameItemsListData[] getGameData(){
		int itemSize = 0;
		GameItemsListData[] newData;
		for(int i = 0;i < gameData.length;i++){
			if(gameData[i].getCounts() > 0){
				itemSize++;
			}
		}
		if(itemSize > 0){
		newData = new GameItemsListData[itemSize];
		int newDataIndex = 0;
		for(int i = 0;i < gameData.length;i++){
			if(gameData[i].getCounts() > 0){
				newData[newDataIndex] = getGameData(i);
				newDataIndex++;
			}
		}
		}else{
			return null;
		}
		return newData;
	}
	
	public int getSize(){
		int itemSize = 0;
		for(int i = 0;i < gameData.length;i++){
			if(gameData[i].getCounts() > 0){
				itemSize++;
			}
		}
		return itemSize;
	}
	
	public GameItemsListData getGameDataByID(int ID){
		for(int i = 0;i<gameData.length;i++){
			if(gameData[i].getID() == ID){
				//Log.i("setItemCounts:", "gameData[i].getID() == ID i:" + i);
				return gameData[i];
			}
		}
		return null;
	}
}
