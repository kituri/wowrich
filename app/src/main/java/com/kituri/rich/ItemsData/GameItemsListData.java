package com.kituri.rich.ItemsData;

import java.io.Serializable;



public class GameItemsListData implements Serializable{
	private static final long serialVersionUID = 1L;
//Context context

	private int ID;
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getDrawableID() {
		return drawableID;
	}

	public void setDrawableID(int drawableID) {
		this.drawableID = drawableID;
	}

	public int getLV() {
		return LV;
	}

	public void setLV(int lv) {
		LV = lv;
	}

	public boolean isChargesProps() {
		return chargesProps;
	}

	public void setChargesProps(boolean chargesProps) {
		this.chargesProps = chargesProps;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
		if(counts < 0){
			this.counts = 0;
		}
		
		if(counts > countsMax){
			this.counts = countsMax;
		}
	}
	
//	public void setCountsChange(int countsChange) {
//		this.counts += counts;
//		if(counts > countsMax){
//			this.counts = countsMax;
//		}
//	}

	private int counts;

	private int countsMax;
//	public int getCountsMax() {
//		return countsMax;
//	}
//
//	public void setCountsMax(int countsMax) {
//		this.countsMax = countsMax;
//	}


	private String name;

	private String about;

	private int drawableID; 

	private int LV;

	private boolean chargesProps;

	private int itemType;
	
	private boolean canAIUse;
	
	public boolean isCanAIUse() {
		return canAIUse;
	}

	public void setCanAIUse(boolean canAIUse) {
		this.canAIUse = canAIUse;
	}

	public int getCountsMax() {
		return countsMax;
	}

	public void setCountsMax(int countsMax) {
		this.countsMax = countsMax;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public GameItemsListData(){
		this.ID = 0;
		this.name = "";
		this.about = "";
		this.drawableID = 0;
		this.chargesProps = false;
		this.countsMax = 9;
		this.counts = 0;
		this.itemType = 0;
	}
}
