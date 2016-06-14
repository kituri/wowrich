package com.data.Constants;

import android.app.Activity;
import android.view.animation.Animation;

import com.data.mapdata.MapLayer;
import com.data.playerdata.PlayerData;


import com.kituri.mediapPlayer.mediaPlayer;
import com.kituri.rich.GameCanvas;
import com.kituri.rich.ItemsData.ItemsData;



public class Constants {
	
	
	/*
	 * type: 
	 * 0:NULL 
	 * 1:普通城市 //单独一张表，可变化
	 *  2:副本 //单独一张表，可变化 
	 *  3:"出生点" 
	 *  4:"机会" 
	 *  5:"宝箱"
	 *  6:"酒馆"(恢复体力) 
	 *  7:"熔火之心" (副本)
	 *  8:"黑暗神殿" (副本)
	 *  9:"太阳井高地"(副本)
	 *  10:"竞技场"
	 */
	
	// 事件列表 宏定义
	static public final int TYPE_NULL = 0;
	static public final int TYPE_CITY = 1;
	static public final int TYPE_SP_CITY = 2;
	static public final int TYPE_START = 3;
	static public final int TYPE_CHANGE = 4;
	static public final int TYPE_CHEST = 5;
	static public final int TYPE_TAVERN = 6;
	static public final int TYPE_US_MC = 7;
	static public final int TYPE_US_BT = 8;
	static public final int TYPE_US_SW = 9;
    static public final int TYPE_ARENA = 10;
	static public final int TYPE_UI_GO = 11;
	static public final int TYPE_UI_ITEM = 12;
	
	static public final int AI_TYPE_ITEM_USE = 101;
    
	static public final int C_FRAME_INTERVAL = 30;
	
	static public int[][] mapData;

	static public MapLayer gameLayer;
	
	static public PlayerData[] player;
	
	static public int playerIndex;

	public static mediaPlayer mp;

	static public GameCanvas _gamecanvas;
	
	static public Animation animation;
	
	public static boolean updata;
	public static boolean IsPaint;

	public static ItemsData itemsData = new ItemsData();
	
//	public static boolean GoIconCanClick = true;
//	public static boolean ItemIconCanClick = true;
//	public static boolean EquipIconCanClick = true;
	
	public static String ItemMessage;
	
	public static  boolean SoundEnabled = false;
	public static Activity ActivityMIDlet = null;
	public static boolean LoadSaveMode = true;
	public static String FileName = "save.db";
	/////////////====DEBUG====//////////////////////////////////////////

	
	public static boolean DebugMode_BattleWin = false;
	
	public static int LV = 1;
	
	public final static int LVMAX = 30;

	public final static int INITIAL_MONEY = 5000;
	
	public static final boolean DebugMode = false;
	public static final int DebugModeStep = 0;
	
	
	public static boolean GameMapViewActivityRun = false;
	public static boolean EquipActivityIsRun = false;
	public static boolean ItemActivityIsRun = false;
	

	 
}
