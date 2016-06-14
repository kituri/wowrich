package com.data.playerdata;

import android.graphics.Bitmap;

import com.data.Constants.Constants;
import com.data.mapdata.MapAuraData;
import com.kituri.rich.EquipData.EquipItemsBUFF;
import com.kituri.rich.EquipData.EquipItemsData;
import com.kituri.rich.EquipData.EquipItemsListData;
import com.kituri.rich.ItemsData.GameItemsBUFF;
import com.kituri.rich.ItemsData.GameItemsListData;
import com.kituri.rich.ItemsData.ItemsData;
import com.mapdigit.game.Sprite;

import java.io.Serializable;

//玩家类
public class PlayerData implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
//	private long serialVersionUID;


	public PlayerData(int singleLength, int MapAuraCounts, MapAuraData[] mapAura) {
		//this.serialVersionUID = serialVersionUID;
		this.atk = 5;
		this.atkMax = 5;
		this.CityNum = 0;
		// this.drawableFrame = 0;
		// this.drawableID = 0;
		this.index = 0;
		this.money = Constants.INITIAL_MONEY;
		this.MoveStepMix = 1;
		this.MoveStepMax = 6;
		this.SpCityNum = 0;
		this.Status = 3;
		this.moveStep = 0;
		// this.moveSpeed = 0;
		// this.spriteLayer = new Sprite();
		this.singleLength = singleLength;
		this.MapAuraCounts = MapAuraCounts;
		
		this.mapAura = new MapAuraData[mapAura.length];
		for(int i = 0;i < mapAura.length;i++){
			this.mapAura[i] = mapAura[i];
		}
		this.mapAura = mapAura;
		
		this.name = "";
		this.playerType = 0;
		this.Go = false;
		// 测试才改成 LV = 10，不测试改回LV = 1；
		this.LV = 1;
		this.LVMAX = 20;
		this.Exp = 0;
		this.Stamina = 300;
		this.StaminaMax = 300;
		this.StaminaMaxP = StaminaMax;
		this.atkSPCityBuff = 0;
		this.gameItemsBUFF = new GameItemsBUFF();
		this.equipItemsBUFF = new EquipItemsBUFF();
		this.items = new ItemsData();
		this.equips = new EquipItemsData();
		this.equipItemsWP = -1;
		this.turnCounts = 0;
		
		// addItems(0);
		
//		for(int i=0;i<26;i++){
//			addItems(i);
//		}
//
//		for(int i=0;i<9;i++){
//			addEquips(i);
//		}
		
		addItems(0);
		addItems(18);
		addItems(22);
		
		addItems(19);
		addItems(23);

	}

	public void setMapAura(MapAuraData[] mapAura) {
		this.mapAura = mapAura;
	}

	public int getLVMAX() {
		return LVMAX;
	}

	public void setLVMAX(int lvmax) {
		LVMAX = lvmax;
	}

	public boolean CanLVUP() {
		// Exp
		if (Exp >= NextLVExp()) {
			if (this.LV < LVMAX) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int NextLVExp() {
		int NextLVExp = 0;
		if (this.LV < 6) {
			NextLVExp = this.LV * 6;
		} else {
			NextLVExp = this.LV * 6 + (this.LV - 5) * (6 + (this.LV - 5));
		}
		return NextLVExp;
	}

	public int getAtkBuff() {
		return atkSPCityBuff + gameItemsBUFF.getAtkBuff(atkMax)
				+ equipItemsBUFF.getEquipAtk(equipItemsWP);
	}

	public int getAtkALL() {
		int AtkALL = atk + getAtkBuff();
		return AtkALL;
	}

	public void setAtkSPCityBuff(int atkSPCityBuff) {
		this.atkSPCityBuff = atkSPCityBuff;
	}

	// 所在位置
	private int index;
	// 金钱数
	private int money;
	// 攻击力
	private int atk;
	// 最大攻击力
	private int atkMax;
	// 战场攻击力 Buff
	private int atkSPCityBuff;
	// 一些Scroll物品的Buff增益
	// "大地图腾",
	// "寒冬号角",
	// "屠龙者的咆哮",
	// "艾露恩的恩赐",
	// "地狱咆哮的挽歌"

	// 城市数量
	private int CityNum;
	// Sp城市数量
	private int SpCityNum;
	// 玩家状态 一些BUFF也加在这里
	// -1: even发生完毕等待
	// 0: 准备移动
	// 1: 正在移动
	// 2: 移动完毕
	// 3: 发生even
	// nextTurn
	public int Status;

	//public Sprite spriteLayer;
	// 是否是自己的回合
	private boolean Turn;
	// //可以掷骰子
	// private boolean CanDice;
	/*
	 * 地图每个格子的长和宽（必须正方形）
	 */
	private int singleLength;
	/*
	 * 地图可行走区域的总和
	 */
	private int MapAuraCounts;

	private MapAuraData[] mapAura;
	// 人物的名字
	private String name;
	// 人物的类型，0 为NPC 1 为 玩家。
	private int playerType;
	// 是否路过开始起点（每回合需要经过这个地点来获得金钱）
	private boolean Go;
	// 人物级别 //级别增加时 恢复 体力和ATK。提升ATK 上限和 体力上限。
	private int LV;
	// 人物最大级别，限制人物级别的提升
	private int LVMAX;
	// 回合红利，每次经过起点时增加的钱。（公式：总拥有建筑的LV数 相加）
	private int turnBonus;
	// 已装备的武器ID。 - 1为未装备。
	private int equipItemsWP;
	//已经经过几回合
	private int turnCounts;
	
	// 体力(耐力) 当该值变动时，影响atk。
	private int Stamina;
	// 体力(耐力)最大值
	private int StaminaMax;
	// 保存上一次升级时的体力最大值。
	private int StaminaMaxP;

	// 当改变 耐力最大值，攻击力最大值，耐力当前值时，都会引发 ATK值改变的事件。
	// 用户事件无法直接改变ATK值。

	private ItemsData items;
	private EquipItemsData equips;
	
	// 最小移动点数
	private int MoveStepMix;
	// 最大移动点数
	private int MoveStepMax;
	// 所用动画(行走图)
	// private int drawableID;
	// 需要移动的步数
	public int moveStep;
	// 移动速度
	private int moveSpeed;

	public GameItemsBUFF gameItemsBUFF;
	public EquipItemsBUFF equipItemsBUFF;
	
	public void setLV(int lv) {
		LV = lv;
	}
	
	public int getTurnCounts() {
		return turnCounts;
	}

	public void addTurnCounts() {
		this.turnCounts++;
	}

	public int getEquipItemsWP() {
		return equipItemsWP;
	}

	//


	public int getTurnBonus() {
		return turnBonus;
	}

	public void setTurnBonus(int turnBonus) {
		this.turnBonus = turnBonus;
	}

	public int getLV() {
		return LV;
	}

	// private void setLV(int lv) {
	// this.LV = lv;
	//		
	// }

	public void LVUP() {
		LV++;
		atkMax++;
		StaminaMaxP = getStaminaMax();
		setStaminaMaxChange(StaminaMax / 20);
		//setStaminaChange(getStaminaMax());
		setStaminaChange(StaminaMax / 20);
		//atk = ((int)((double)getPerStamina() * atkMax / 100));
		//Constants.mp.playSound(mediaPlayer.SOUND_LVUP);
	}

	// 人物经验
	private int Exp;

	public int getExp() {
		// Log.i("EXP", "Exp:" + Exp);
		return this.Exp;
	}

	public void setExpChange(int expChange) {
		// Log.i("EXP", "Exp(no):" + Exp);
		// Log.i("EXP", "expChange:" + expChange);
		this.Exp += expChange;
		// Log.i("EXP", "Exp(yes):" + Exp);
	}



	public int getStamina() {
		return Stamina;
	}

	public int getPerStamina() {
		int PerStamina = (int) ((double) Stamina / (double) StaminaMax * 100.00);
		return PerStamina;
	}

	public void setStaminaChange(int staminaChange) {
		this.Stamina += staminaChange;
		if (Stamina < 0) {
			this.Stamina = 0;
		}
		if (Stamina > StaminaMax) {
			this.Stamina = this.StaminaMax;
		}
		// 改变体力值，引发改变ATK值
		double per = (double) Stamina / (double) StaminaMax;
		this.atk = (int) (atkMax * per / 2) + atkMax / 2;
		if(this.atk > atkMax){
			this.atk = atkMax;
		}
	}

	public int getStaminaMax() {
		return StaminaMax;
	}

	public int getStaminaMaxP() {
		return StaminaMaxP;
	}

	public void setStaminaMaxChange(int setStaminaMaxChange) {
		StaminaMax += setStaminaMaxChange;
		// 改变体力最大值，引发改变ATK值
		double per = (double) Stamina / (double) StaminaMax;
		this.atk = (int) (atkMax * per);
	}

	public boolean isGo() {
		return Go;
	}

	public int getPlayerType() {
		return playerType;
	}

	public void setPlayerType(int playerType) {
		this.playerType = playerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTurn() {
		return Turn;
	}

	public void setTurn(boolean turn) {
		Turn = turn;
		// if (turn) {
		// this.Status = 0;
		// }
	}


	public int getMoveSpeed() {
		return moveSpeed;
	}

	// public void setMoveSpeed(int moveSpeed) {
	// this.moveSpeed = moveSpeed;
	// }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getMoney() {
		return money;
	}

	// public void setMoney(int money) {
	// this.money = money;
	// }

	public void setMoneyChange(int money) {
		// 武器特殊效果↓
		if (money > 0) {
			this.money += equipItemsBUFF.getExtraMoney(equipItemsWP, money);
		}
		this.money += money;
	}

	public int getAtk() {
		return atk;
	}

	public int getAtkMax() {
		return atkMax;
	}

	public void setAtkMaxChange(int atkMaxChange) {
		this.atkMax += atkMaxChange;
		// 改变攻击力最大值，引发改变ATK值
		double per = (double) Stamina / (double) StaminaMax;
		this.atk = (int) (atkMax * per);
	}

	public int getCityNum() {
		return CityNum;
	}

	public void setCityNum(int cityNum) {
		CityNum = cityNum;
	}

	public int getSpCityNum() {
		return SpCityNum;
	}

	public void setSpCityNum(int spCityNum) {
		SpCityNum = spCityNum;
	}

	public int getMoveStepMix() {
		return MoveStepMix;
	}

	public void setMoveStepMix(int moveStepMix) {
		MoveStepMix = moveStepMix;
	}

	public int getMoveStepMax() {
		return MoveStepMax;
	}

	public void setMoveStepMax(int moveStepMax) {
		MoveStepMax = moveStepMax;
	}

	transient public Sprite spriteLayer;
	public void InitSprite(Bitmap spriteImage) {
		
//		Log.i("Save", "spriteImage.getWidth():" + spriteImage.getWidth());
//		Log.i("Save", "spriteImage.getHeight():" + spriteImage.getHeight());
//		Log.i("Save", "mapAura[" + index +  "].getX():" + mapAura[index].getX());
//		Log.i("Save", "mapAura[" + index +  "].getY():" + mapAura[index].getY());
//		Log.i("Save", "singleLength:" + singleLength);
		
		spriteLayer = new Sprite(spriteImage, spriteImage.getWidth() / 2,
				spriteImage.getHeight() / 4);

		spriteLayer.setPosition(mapAura[index].getX() * singleLength, 
				mapAura[index].getY() * singleLength);
		// spriteLayer.setFrame(7);
	}

	public void MoveNextSetp(int BarriersIndex) {

		if (mapAura[index].getNextIndex() == BarriersIndex && moveStep == 1) {
			Constants.gameLayer.setBarriersEnabled(false);
		}
		if (index == BarriersIndex) {
			moveStep = -1;
			Constants.gameLayer.setBarriersEnabled(false);
			return;
		}

		int MoveX = mapAura[mapAura[index].getNextIndex()].getX()
				- mapAura[index].getX();

		int MoveY = mapAura[mapAura[index].getNextIndex()].getY()
				- mapAura[index].getY();

		//		
		spriteLayer.move(MoveX * singleLength
				+ (singleLength - spriteLayer.getWidth()) / 2, MoveY
				* singleLength + (singleLength - spriteLayer.getHeight()) / 2);

		// spriteLayer.move(32, 0);

		moveStep--;

		// if(Constants.gameLayer.getb)

		NextIndex();
		if (index == 0) {
			this.Go = true;
		}

		// MapBarriers

		// for(int i = 0;i < mapData.length;i++){
		// Log.i("Move", "X:" + mapData[i].getX() + ",Y:" + mapData[i].getY());
		// }

	}

	public void setGo(boolean go) {
		Go = go;
	}

	public void NextIndex() {
		index++;
		if (index > MapAuraCounts - 1) {
			index = 0;
		}
	}

	public int getX() {
		return mapAura[index].getX() * singleLength
				+ (singleLength - spriteLayer.getWidth()) / 2;
	}

	public int getY() {
		return mapAura[index].getY() * singleLength
				+ (singleLength - spriteLayer.getHeight()) / 2;
	}

	// item 操作
	// 给玩家增加一个道具
	public void addItems(int ID) {
		items.setItemCountsChange(ID, 1);
	}

	public void removeItems(int ID) {
		items.setItemCountsChange(ID, -1);
	}

	// 获取玩家所获得道具的总数
	public int getItemsSize() {
		return items.getSize();
	}

	// 获取index号的单项道具
	public GameItemsListData[] getItems() {

		return items.getGameData();
	}

	public GameItemsListData getItems(int index) {
		return items.getGameData()[index];
	}

	public GameItemsListData getItemsByID(int ID) {
		return items.getGameDataByID(ID);
	}

	public void SetAtkBuff(int type) {
		gameItemsBUFF.setAtkBuff(type);
	}

	public void SetRecoveryBuff(int type) {
		gameItemsBUFF.setRecoveryBuff(type);
	}

	public void NextTurn() {
		// if (getPlayerType() == 0) {
		// this.Status = 0;
		// }
		setStaminaChange(gameItemsBUFF.getRecoveryBuff());
		gameItemsBUFF.nextTurn();
	}

	// item 操作
	// 给玩家增加一个道具
	public void addEquips(int ID) {
		equips.setItemCounts(ID, 1);

	}

	public void removeEquips(int ID) {
		equips.setItemCounts(ID, 0);
	}

	// 获取玩家所获得道具的总数
	public int getEquipsSize() {
		return equips.getSize();
	}

	// 获取index号的单项道具
	public EquipItemsListData[] getEquips() {

		return equips.getGameData();
	}

	public EquipItemsListData getEquips(int index) {
		return equips.getGameData()[index];
	}

	public EquipItemsListData getEquipsByID(int ID) {
		return equips.getGameDataByID(ID);
	}
	
	public int getEquipItemsTopLV_ID(){
		return equips.getEquipItemsTopLV_ID();
	}

	public void setEquipItemsWP(int equipItemsWP) {
		this.equipItemsWP = equipItemsWP;
		//Constants.mp.playSound(mediaPlayer.SOUND_ATTACK05);
		// this.SetAtkBuff(type)
	}

	//玩家是否在这个index上
	public boolean IsPlayer(int index) {
		if(this.index == index){
			return true;
		}
		return false;
	}
//	public void setItemCountsChange(){
//		
//	}
	// setEquipAtk

}
