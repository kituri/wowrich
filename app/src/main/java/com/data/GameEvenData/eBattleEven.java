package com.data.GameEvenData;

import java.util.Random;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;


import com.data.Constants.Constants;
import com.kituri.mediapPlayer.mediaPlayer;

public class eBattleEven {
	public static Random random = new Random();

	// public static boolean Battle(int atk,int def){

	static public int RandomInt(int mix, int max) {
		if (mix == max) {
			return mix;
		}
		int randomInt = mix + random.nextInt(max - mix);
		return randomInt;
	}
	
	static public boolean RandomPer(int per) {
		int mix = 0;
		int max = 99;
		//randomInt 范围 0 - 99
		//per       范围 1 - 100
		int randomInt = mix + random.nextInt(max - mix);
		if(per  >  randomInt){
		return true;	
		}else{
			return false;
		}
	}

	static public int  FormatPer(int value,int per) {
		double dValue = (double)value;
		dValue = dValue / 100.00 * per;
		return (int)dValue;
	}
	
//	static public int  getPer(int value,int per) {
//		double dValue = (double)value;
//		dValue = dValue / 100.00 * per;
//		return (int)dValue;
//	}
	
	static public int BattleRandom() {
		int randomInt = RandomInt(1, 20);
		return randomInt;
	}

	static public boolean Battle(int atk, int oldDef, int randomInt) {
		playAtkSound();
		int type = Constants.player[Constants.playerIndex].getEquipItemsWP();
		
		int def = oldDef +
		Constants.player[Constants.playerIndex].equipItemsBUFF.getExtraAC(type, oldDef);
		
		Constants._gamecanvas.startAnimation(Constants.animation);
		StaminaChange( 0 -(def + Constants.player[Constants.playerIndex].
				equipItemsBUFF.getExtraStamina(type, def)));

		
		if (randomInt == 1) {
			ExpChange(2);
			return false;
		} else if (randomInt == 20) {
			ExpChange(3);
			return true;
		} else {
			if (atk + randomInt >= def) {
				ExpChange(3);
				return true;
			} else {
				ExpChange(2);
				return false;
			}
		}
		
	}
	
	static public void playAtkSound(){
		int randomInt = RandomInt(mediaPlayer.SOUND_ATTACK01, mediaPlayer.SOUND_ATTACK08);
		Constants.mp.playSound(randomInt);
		//Constants.mp.playSound(SOUND_ATTACK05);
	}

	static public boolean Battle(int atk, int oldDef, int randomInt,boolean IsUpExp,boolean IsStaminaChange) {
		playAtkSound();
		int type = Constants.player[Constants.playerIndex].getEquipItemsWP();
		
		int def = oldDef +
		Constants.player[Constants.playerIndex].equipItemsBUFF.getExtraAC(type, oldDef);
		
		Constants._gamecanvas.startAnimation(Constants.animation);
		if(IsStaminaChange){
		StaminaChange( 0 - (def + Constants.player[Constants.playerIndex].
				equipItemsBUFF.getExtraStamina(type, def)));
		}

		if (randomInt == 1) {
			if(IsUpExp){
			ExpChange(2);
			}
			return false;
		} else if (randomInt == 20) {
			if(IsUpExp){
			ExpChange(3);
			}
			return true;
		} else {
			if (atk + randomInt >= def) {
				if(IsUpExp){
				ExpChange(3);
				}
				return true;
			} else {
				if(IsUpExp){
				ExpChange(2);
				}
				return false;
			}
		}
	}
	
	static public void StaminaChange(int def) {

		Constants.player[Constants.playerIndex].setStaminaChange(def);
	}

	static public void ExpChange(int exp) {
		Constants.player[Constants.playerIndex].setExpChange(exp);
	}

	static public SpannableString getWinString(int atk, int AtkALL, int oldDef,
			int BattleRandom) {
		int type = Constants.player[Constants.playerIndex].getEquipItemsWP();
		int costStamina = oldDef + Constants.player[Constants.playerIndex].
		equipItemsBUFF.getExtraStamina(type, oldDef);
		
		int def = oldDef +
		Constants.player[Constants.playerIndex].equipItemsBUFF.getExtraAC(type, oldDef);

		String str = "战斗难度：" + def + "\n" + "战斗结果：" + AtkALL + " = " + atk
				+ " + " + BattleRandom + "\n获得经验3点！\n体力下降" + costStamina + "点!"
				+ "\n挑战成功！";

		SpannableString sp = new SpannableString(str);
		int nowYellow = str.lastIndexOf("经验3点") + 2;// 经验5点
		int nowRed = str.lastIndexOf("体力下降") + 4;// 经验5点
		sp.setSpan(new ForegroundColorSpan(Color.YELLOW), nowYellow, nowYellow + 1,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		sp.setSpan(new ForegroundColorSpan(Color.RED), nowRed, nowRed
				+ String.valueOf(costStamina).length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return sp;
	}

	static public String getRaidLostString(int atk, int AtkALL, int oldDef,
			int BattleRandom) {
		int type = Constants.player[Constants.playerIndex].getEquipItemsWP();
//		int costStamina = oldDef + Constants.player[Constants.playerIndex].
//		equipItemsBUFF.getExtraStamina(type, oldDef);
		
		int def = oldDef +
		Constants.player[Constants.playerIndex].equipItemsBUFF.getExtraAC(type, oldDef);
		
		String str = "战斗难度：" + def + "\n" + "战斗结果：" + AtkALL + " = " + atk
				+ " + " + BattleRandom //+ "\n获得经验2点！\n体力下降" + costStamina + "点!"
				+ "\n挑战失败………";
//		SpannableString sp = new SpannableString(str);
//		int nowYellow = str.lastIndexOf("经验2点") + 2;// 经验5点
//		int nowRed = str.lastIndexOf("体力下降") + 4;// 经验5点
//		sp.setSpan(new ForegroundColorSpan(Color.YELLOW), nowYellow, nowYellow + 1,
//				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
//
//		sp.setSpan(new ForegroundColorSpan(Color.RED), nowRed, nowRed
//				+ String.valueOf(costStamina).length(),
//				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return str;
	}
	
	static public String getRaidWinString(int atk, int AtkALL, int oldDef,
			int BattleRandom) {
		int type = Constants.player[Constants.playerIndex].getEquipItemsWP();
//		int costStamina = oldDef + Constants.player[Constants.playerIndex].
//		equipItemsBUFF.getExtraStamina(type, oldDef);
		
		int def = oldDef +
		Constants.player[Constants.playerIndex].equipItemsBUFF.getExtraAC(type, oldDef);

		String str = "战斗难度：" + def + "\n" + "战斗结果：" + AtkALL + " = " + atk
				+ " + " + BattleRandom //+ "\n获得经验3点！\n体力下降" + costStamina + "点!"
				+ "\n挑战成功！";

//		SpannableString sp = new SpannableString(str);
//		int nowYellow = str.lastIndexOf("经验3点") + 2;// 经验5点
//		int nowRed = str.lastIndexOf("体力下降") + 4;// 经验5点
//		sp.setSpan(new ForegroundColorSpan(Color.YELLOW), nowYellow, nowYellow + 1,
//				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//		sp.setSpan(new ForegroundColorSpan(Color.RED), nowRed, nowRed
//				+ String.valueOf(costStamina).length(),
//				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		return sp;
		return str;
	}

	static public SpannableString getLostString(int atk, int AtkALL, int oldDef,
			int BattleRandom) {
		int type = Constants.player[Constants.playerIndex].getEquipItemsWP();
		int costStamina = oldDef + Constants.player[Constants.playerIndex].
		equipItemsBUFF.getExtraStamina(type, oldDef);
		
		int def = oldDef +
		Constants.player[Constants.playerIndex].equipItemsBUFF.getExtraAC(type, oldDef);
		
		String str = "战斗难度：" + def + "\n" + "战斗结果：" + AtkALL + " = " + atk
				+ " + " + BattleRandom + "\n获得经验2点！\n体力下降" + costStamina + "点!"
				+ "\n挑战失败………";
		SpannableString sp = new SpannableString(str);
		int nowYellow = str.lastIndexOf("经验2点") + 2;// 经验5点
		int nowRed = str.lastIndexOf("体力下降") + 4;// 经验5点
		sp.setSpan(new ForegroundColorSpan(Color.YELLOW), nowYellow, nowYellow + 1,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 

		sp.setSpan(new ForegroundColorSpan(Color.RED), nowRed, nowRed
				+ String.valueOf(costStamina).length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return sp;
	}
	
	
	static public void LVUP(){
		//级别上升
		Constants.player[Constants.playerIndex].LVUP();
	}
	
	static public String getLVUP_Message(){
		//升好级别后再调用这个
		//int nameLength = Constants.player[Constants.playerIndex].getName().length();
		int oldLV = Constants.player[Constants.playerIndex].getLV() - 1;
		int newLV = oldLV + 1;
		int oldStamina = Constants.player[Constants.playerIndex].getStaminaMaxP();
		int newStamina = Constants.player[Constants.playerIndex].getStaminaMax();
		int oldAtk = Constants.player[Constants.playerIndex].getAtkMax() - 1;
		int newAtk = Constants.player[Constants.playerIndex].getAtkMax();
		
		//int oldStaminaLength = String.valueOf(oldStamina).length();
		
		String oldLVString = String.valueOf(oldLV);
		String oldAtkString = String.valueOf(oldAtk);

			if(oldLVString.length() < 2){
		oldLVString =	"    " + String.valueOf(oldLV);
			}else if(oldLVString.length() < 3){
		oldLVString =	"  " + String.valueOf(oldLV);
			}

			if(oldAtkString.length() < 2){
				oldAtkString =	"    " + String.valueOf(oldAtk);
					}else if(oldLVString.length() < 3){
						oldAtkString =	"  " + String.valueOf(oldAtk);
					}
		
			
			String newLVString = String.valueOf(newLV);
			String newAtkString = String.valueOf(newAtk);
			
			if(newLVString.length() < 2){
				newLVString =	"    " + String.valueOf(newLV);
					}else if(newLVString.length() < 3){
						newLVString =	"  " + String.valueOf(newLV);
					}

					if(newAtkString.length() < 2){
						newAtkString =	"    " + String.valueOf(newAtk);
							}else if(newAtkString.length() < 3){
								newAtkString =	"  " + String.valueOf(newAtk);
							}
		
		String str = Constants.player[Constants.playerIndex].getName()+
			"的等级提升了！\n\n" + 
			"ＬＶ :　　" + oldLVString   + " → " + newLVString + "\n" + 
			"攻击 :　　" + oldAtkString  + " → " + newAtkString + "\n" +
			"体力 :　　" + oldStamina    + " → " + newStamina;
		;
		//SpannableString sp = new SpannableString(str);
		
		return str;
	}
	
	static public void MoneyChange(int money){
		Constants.player[Constants.playerIndex].setMoneyChange(money);
	}
	
	static public void PayMoney(int money){
		Constants.player[Constants.playerIndex].setMoneyChange(-money);
		int Possession = Constants.gameLayer.getPossession(
				Constants.player[Constants.playerIndex].getIndex());
		Constants.player[Possession].setMoneyChange(money);
	}
	
	static public void BulidLVChange(int LVChange){
		Constants.gameLayer.setMapAuraLVChange(
				Constants.player[Constants.playerIndex].getIndex(), 
				LVChange);
	}
	
	static public int getBulidLV(){
		int lv =
		Constants.gameLayer.getMapAura(
				Constants.player[Constants.playerIndex].getIndex()).getLV();
		return lv;
	}
	
	static 	public boolean AI_FightOrPay(int atk,int def){
		
		int WinPer =  atk + 20 - def;
		
		//Log.i("IsLost()", "WinPer:" + WinPer);
		
		if(WinPer >= 20){
			return true;
		}

		//if(RandomPer(LostPer + RandomInt(- 10 , 10)) == false){

		if(RandomPer(WinPer + 80)){
			return true;
		}
		return false;
	}
	
	static public boolean AI_BuyOrNoBuy(){
		return true;
	}

	static public boolean AI_LvupOrNoLvup(){
		return true;
	}

	static public boolean IsNoMoney(int money){
		if(money < Constants.player[Constants.playerIndex].getMoney()){
			return false;
		}else{
			return true;
		}
	}
	
//该地是否属于自己
	static public boolean IsMyArea(){
		return Constants.gameLayer.IsPossession(
				Constants.player[Constants.playerIndex].getIndex(),
				Constants.playerIndex);
	} 
//该地是否可以买卖（无人占领）
	static public boolean CanBuy(){
		return Constants.gameLayer.IsPoNossession(
				Constants.player[Constants.playerIndex].getIndex(),
				Constants.playerIndex);
	} 
	
	//该地是否有体力买下
	static public boolean CanStamina(int stamina){
		if(Constants.player[Constants.playerIndex].getStamina() - stamina >= 0){
			return true;
		}
		return false;
	}
	
	static public boolean IsLost(){
		if(Constants.player[Constants.playerIndex].getMoney() < 0){
			return true;
		}else{
			return false;
		}
	}
}
