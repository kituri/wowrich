package com.data.mapdata;

public class MapBarriers {

	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getSingleLength() {
		return singleLength;
	}
	public void setSingleLength(int singleLength) {
		this.singleLength = singleLength;
	}
	public boolean isEnabled() {
		return Enabled;
	}
	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}
	
	public boolean getEnabled(){
		return Enabled;
	}
	
	private int posX;
	private int posY;
	private int singleLength;
	private boolean Enabled;
	private int index;
	public int getIndex() {
		if(Enabled){
		return index;
		}else{
			return -1;
		}
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public MapBarriers(){
		this.singleLength = 0;
		this.posX = -1;
		this.posY = -1;
		this.index = -1;
		this.Enabled = false;
	}
	
}
