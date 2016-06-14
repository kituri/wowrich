package com.kituri.rich.ItemsData;

import java.io.Serializable;



import com.data.Constants.Constants;


public class EvenAtkBuff implements Serializable{
	private static final long serialVersionUID = 1L;
	// "大地图腾",
	// "寒冬号角",
	// "屠龙者的咆哮",
	// "艾露恩的恩赐",
	// "地狱咆哮的挽歌"

	static public void UseAtkBuff(int TypeID, String name) {
		Constants.player[Constants.playerIndex]
				.SetAtkBuff(TypeID);
		Constants.ItemMessage = "使用了 " + name + " 后，身体里不断涌出着力量！";
	}
}
