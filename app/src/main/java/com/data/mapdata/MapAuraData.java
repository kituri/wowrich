package com.data.mapdata;

import java.io.Serializable;

public class MapAuraData extends MapAura implements Serializable{
	private static final long serialVersionUID = 1L;

	private int possession;
	

	private int possessionX;
	public int getPossessionX() {
		return possessionX;
	}

	public void setPossessionPos(int possessionX,int possessionY) {
		this.possessionX = possessionX;
		this.possessionY = possessionY;
	}

	public int getPossessionY() {
		return possessionY;
	}
	
	private int possessionY;

	private int Cost;

	private int LV;

	private int MixLV;
	private int MaxLV;
	private int def;
	
	public int getDef() {
		return def +  (int)((double)LV *  (double)(def / 3));
	}

	public void setDef(int def) {
		this.def = def;
	}

	public MapAuraData(){
		this.possession = -1;
		//this.CostLvUp = 0;
		this.Cost = 0;
		//this.CostPay = 0;
		this.LV = 1;
		this.MixLV = 1;
		this.MaxLV = 4;
	}
	
	public int getPossession() {
		return possession;
	}
	public void setPossession(int possession) {
		this.possession = possession;
	}
	public int getCostLvUp() {
		return Cost / 5 ;

	}

	public int getCostBuy() {
		return Cost;
	}
	public void setCost(int cost) {
		
		Cost = cost;
//		CostLvUp = cost / 10;
//		CostPay = cost / 5;
	}
	public int getCostPay() {
		return (Cost * LV ) / 5;
		//return (Cost * LV ) /2;

	}

	public int getLV() {
		return LV;
	}
//	public void setLV(int lv) {
//		LV = lv;
//		if(LV < MixLV){
//			LV = MixLV;
//		}
//		if(LV > MaxLV){
//			LV = MaxLV;
//		}	
//	}
	
	public void setLVChange(int LVChange) {
		LV += LVChange;
		if(LV < MixLV){
			LV = MixLV;
		}
		if(LV > MaxLV){
			LV = MaxLV;
		}
	}
	
	public int getMaxLV() {
		return MaxLV;
	}
	public void setMaxLV(int maxLV) {
		MaxLV = maxLV;
	}
}
