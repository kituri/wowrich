package com.kituri.rich.ItemsData;
import com.data.Constants.Constants;

public class EvenRecoveryBuff {

	// "大地图腾",
	// "寒冬号角",
	// "屠龙者的咆哮",
	// "艾露恩的恩赐",
	// "地狱咆哮的挽歌"

	static public void UseAtkBuff(int TypeID, String name) {
		Constants.player[Constants.playerIndex]
				.SetRecoveryBuff(TypeID);
		Constants.ItemMessage = "使用了 " + name + " 后，体力开始逐渐恢复！";
	}
}
