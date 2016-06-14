package com.kituri.rich.ItemsData;


import com.data.Constants.Constants;


public class EvenOther {

	// "大地图腾",
	// "寒冬号角",
	// "屠龙者的咆哮",
	// "艾露恩的恩赐",
	// "地狱咆哮的挽歌"

	static public void useBarriers(String name,int index) {
		Constants.gameLayer.setBarriers
		(true, Constants.gameLayer.getMapAura(index).getX(), 
				Constants.gameLayer.getMapAura(index).getY(), index);
		Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";
	}
}
