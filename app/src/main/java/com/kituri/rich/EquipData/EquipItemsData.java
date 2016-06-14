package com.kituri.rich.EquipData;

import java.io.Serializable;

import com.kituri.wowrich.R;

public class EquipItemsData implements Serializable{
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
//
//S1角斗士

//	S2残酷角斗士
//
//	S3复仇角斗士
//
//	S4野蛮角斗士
////
	
 private EquipItemsListData[] gameData;

 private final int size = 9;

 
 private final String[] name = {
		 "埃提耶什·守护者的传说之杖",
		 "雷霆之怒·逐风者的祝福",
		 "萨弗拉斯·炎魔拉格纳罗斯之手",
		 "埃辛诺斯双刃",
		 "索利达尔·群星之怒",
		 
		 "角斗士的武器",
		 "残酷角斗士的武器",
		 "复仇角斗士的武器",
		 "野蛮角斗士的武器"
 };
 

 
 private final String[] about = {
		 "攻击力上升7点。",
		 "攻击力上升5，获取金币时上升20%获取量。",
		 "攻击力上升8，战斗时额外消耗25%体力。",
		 "攻击力上升6，战斗时减少对方20%防御力。",
		 "攻击力上升4，战斗时减少对方,30%防御力。",
		 
		 "攻击力上升1点。",
		 "攻击力上升2点。",
		 "攻击力上升3点。",
		 "攻击力上升4点。"		 
 };
 private final int[] drawableID = {
		 R.drawable.wp_00,
		 R.drawable.wp_01,
		 R.drawable.wp_02,
		 R.drawable.wp_03,
		 R.drawable.wp_04,
		 
		 R.drawable.wp_05,
		 R.drawable.wp_06,
		 R.drawable.wp_07,
		 R.drawable.wp_08
 };
 //NPC优先级的LV
 private final int[] LV = {
		 5,5,5,5,5,
		 4,4,4,4
 };
 
 private final int[] NPC_LV = {
		 50,60,70,80,90,
		 10,20,30,40
 };
 
 
 private final boolean[] chargesProps = {
		 true,true,true,true,true,
		 true,true,true,true
 };
 
 private final int[] ID = {
		 0,1,2,3,4,
		 5,6,7,8
 };
 
	public  EquipItemsData(){
		gameData = new EquipItemsListData[size];
//		ID = new int[counts];
//		name = new String[counts];
//		about = new String[counts];
//		drawableID = new int[counts];
//		LV = new int[counts];
//		chargesProps = new boolean[counts];
		for(int i = 0;i < gameData.length;i++){
			EquipItemsListData giData = new EquipItemsListData();
			giData.setID(ID[i]);
			giData.setName(name[i]);
			giData.setAbout(about[i]);
			giData.setDrawableID(drawableID[i]);
			giData.setLV(LV[i]);
			giData.setChargesProps(chargesProps[i]);
			giData.setNPC_LV(NPC_LV[i]);
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
	
	public int[] getEquipID_bySaveGame(){
		int[] itemsID = new int[gameData.length];
		for(int i = 0;i < itemsID.length;i++){
			itemsID[i] = gameData[i].getID();
		}
		return itemsID;
	}

	public int[] getEquipCounts_bySaveGame(){
		int[] itemsCounts = new int[gameData.length];
		for(int i = 0;i < itemsCounts.length;i++){
			itemsCounts[i] = gameData[i].getCounts();
		}
		return itemsCounts;
	}

	public void setEquip_byLoadGame(int id[],int Counts[]){
		for(int i = 0;i < gameData.length;i++){
			setItemCounts(id[i],Counts[i]);
		}
	}
	
	private EquipItemsListData getGameData(int itemIndex){
		return gameData[itemIndex];
	}
	
	public EquipItemsListData[] getGameData(){
		int itemSize = 0;
		EquipItemsListData[] newData;
		for(int i = 0;i < gameData.length;i++){
			if(gameData[i].getCounts() > 0){
				itemSize++;
			}
		}
		if(itemSize > 0){
		newData = new EquipItemsListData[itemSize];
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
	
	public EquipItemsListData getGameDataByID(int ID){
		for(int i = 0;i<gameData.length;i++){
			if(gameData[i].getID() == ID){
				//Log.i("setItemCounts:", "gameData[i].getID() == ID i:" + i);
				return gameData[i];
			}
		}
		return null;
	}
	
	public int getEquipItemsTopLV_ID(){
		int lv = -1;
		int ID = -1;
		for(int i = 0;i < gameData.length;i++){
			if(gameData[i].getCounts() > 0){
				if(lv < gameData[i].getNPC_LV()){
					lv = gameData[i].getNPC_LV();
					ID = gameData[i].getID();
				}
			}
		}
		return ID;
	}
}
