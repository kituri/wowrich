package com.kituri.rich.ItemsData;




import com.data.Constants.Constants;
import com.data.GameEvenData.eBattleEven;


public class EvenKeyChest {

	static public final int TYPE_S_POINT = 1310;
	static public final int TYPE_M_POINT = 514;
	static public final int TYPE_L_POINT = 124142;
	static public final int TYPE_MAX_POINT = 1411113;

	static public int UseOpenChestLV1() {
		// 青铜宝箱掉落
		// 小血瓶 35
		// 中血瓶 35
		// 诺兹多姆的沙漏 10
		// 烈焰风暴 10
		// 法力屏障 10

		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);
		if (roll >= 1 && roll <= 35) {
			ID = 0;
		} else if (roll >= 36 && roll <= 70) {
			ID = 1;
		} else if (roll >= 71 && roll <= 80) {
			ID = 4;
		} else if (roll >= 81 && roll <= 90) {
			ID = 15;
		} else if (roll >= 91 && roll <= 100) {
			ID = 14;
		}
		// int keyID = 18;
		// int chestID = 22;
		Constants.player[Constants.playerIndex].addItems(ID);
		Constants.ItemMessage = Constants.player[Constants.playerIndex]
				.getName()
				+ "开启了"
				+ Constants.player[Constants.playerIndex].getItemsByID(22)
						.getName()
				+ "，从宝箱中获得了【 "
				+ Constants.player[Constants.playerIndex].getItemsByID(ID)
						.getName() + " 】。";
		return ID;
	}

	static public int UseOpenChestLV2() {
		// 白银宝箱掉落
		// 小血瓶 20
		// 中血瓶 20
		// 暗月钥匙 20
		// 大血瓶 5
		// 烈焰风暴 5
		// 法力屏障 5
		// 地精工程师 5
		// 愈合祷言 5
		// 圣疗 5
		// 大地图腾 5
		// 寒冬号角 5
		

		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);
		if (roll >= 1 && roll <= 20) {
			ID = 0;
		} else if (roll >= 21 && roll <= 40) {
			ID = 1;
		}else if (roll >= 41 && roll <= 60) {
			ID = 20;
		}else if (roll >= 61 && roll <= 65) {
			ID = 2;
		} else if (roll >= 65 && roll <= 70) {
			ID = 15;
		} else if (roll >= 71 && roll <= 75) {
			ID = 14;
		} else if (roll >= 76 && roll <= 80) {
			ID = 16;
		} else if (roll >= 81 && roll <= 85) {
			ID = 9;
		} else if (roll >= 86 && roll <= 90) {
			ID = 10;
		} else if (roll >= 91 && roll <= 95) {
			ID = 4;
		} else if (roll >= 96 && roll <= 100) {
			ID = 5;
		}
		Constants.player[Constants.playerIndex].addItems(ID);
		Constants.ItemMessage = Constants.player[Constants.playerIndex]
				.getName()
				+ "开启了"
				+ Constants.player[Constants.playerIndex].getItemsByID(23)
						.getName()
				+ "，从宝箱中获得了【 "
				+ Constants.player[Constants.playerIndex].getItemsByID(ID)
						.getName() + "】 。";
		return ID;
	}

	static public int UseOpenChestLV3() {

		// 黄金宝箱掉落
		
		
		// 回春术 10
		// 暗月钥匙 10
		// 神圣的赞美诗 10
		// 宁静之雨 10
		// 超级血瓶 10
		// 天崩地裂 10
		// 地狱咆哮的挽歌 10
		// 艾露恩的恩赐 10
		// 屠龙者的咆哮 10
		// 黄金钥匙 10

		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);
		if (roll >= 1 && roll <= 10) {
			ID = 11;
		}else if (roll >= 11 && roll <= 20) {
			ID = 20;
		} else if (roll >= 21 && roll <= 30) {
			ID = 12;
		} else if (roll >= 31 && roll <= 40) {
			ID = 13;
		} else if (roll >= 41 && roll <= 50) {
			ID = 3;
		} else if (roll >= 51 && roll <= 60) {
			ID = 17;
		} else if (roll >= 61 && roll <= 70) {
			ID = 8;
		} else if (roll >= 81 && roll <= 80) {
			ID = 7;
		} else if (roll >= 81 && roll <= 90) {
			ID = 6;
		} else if (roll >= 91 && roll <= 100) {
			ID = 21;
		}

		Constants.player[Constants.playerIndex].addItems(ID);
		Constants.ItemMessage = Constants.player[Constants.playerIndex]
				.getName()
				+ "开启了"
				+ Constants.player[Constants.playerIndex].getItemsByID(24)
						.getName()
				+ "，从宝箱中获得了【 "
				+ Constants.player[Constants.playerIndex].getItemsByID(ID)
						.getName() + "】 。";
		return ID;
	}

	static public int UseOpenChestLV4() {

		// 白金宝箱掉落

		// 回春术 11
		// 神圣的赞美诗 11
		// 宁静之雨 10
		// 超级血瓶 10
		// 天崩地裂 11
		// 地狱咆哮的挽歌 11
		// 艾露恩的恩赐 11
		// 埃提耶什＊守护者的传说之杖 5
		// 雷霆之怒＊逐风者的祝福 5
		// 萨弗拉斯＊炎魔拉格纳罗斯之手 5
		// 埃辛诺斯双刃 5
		// 索利达尔＊群星之怒 5

		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);
		if (roll >= 1 && roll <= 11) {
			ID = 11;
		} else if (roll >= 12 && roll <= 22) {
			ID = 12;
		} else if (roll >= 23 && roll <= 32) {
			ID = 13;
		} else if (roll >= 33 && roll <= 42) {
			ID = 3;
		} else if (roll >= 43 && roll <= 53) {
			ID = 17;
		} else if (roll >= 54 && roll <= 64) {
			ID = 8;
		} else if (roll >= 65 && roll <= 75) {
			ID = 7;
		} else if (roll >= 76 && roll <= 80) {
			ID = -1;
		} else if (roll >= 81 && roll <= 85) {
			ID = -2;
		} else if (roll >= 86 && roll <= 90) {
			ID = -3;
		} else if (roll >= 91 && roll <= 95) {
			ID = -4;
		} else if (roll >= 96 && roll <= 100) {
			ID = -5;
		}
		if (ID > -1) {
			Constants.player[Constants.playerIndex].addItems(ID);
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "开启了"
					+ Constants.player[Constants.playerIndex].getItemsByID(25)
							.getName()
					+ "，从宝箱中获得了【 "
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
		} else {
			Constants.player[Constants.playerIndex].addEquips(ID + 5);
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "开启了"
					+ Constants.player[Constants.playerIndex].getItemsByID(25)
							.getName()
					+ "，从宝箱中获得了【 "
					+ Constants.player[Constants.playerIndex].getEquipsByID(
							ID + 5).getName() + " 】。";
		}
		return ID;
	}

	// Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";

	static public void UseOpenRaidChestLV4() {

		// Raid白金宝箱掉落 (无橙武)

		// 黄金钥匙 30
		// 回春术 10
		// 神圣的赞美诗 10
		// 宁静之雨 10
		// 天崩地裂 10
		// 地狱咆哮的挽歌 10
		// 艾露恩的恩赐 10
		// 超级血瓶 10

		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);
		if (roll >= 1 && roll <= 30) {
			ID = 21;
		}else if (roll >= 31 && roll <= 40) {
			ID = 11;
		} else if (roll >= 41 && roll <= 50) {
			ID = 12;
		} else if (roll >= 51 && roll <= 60) {
			ID = 13;
		} else if (roll >= 61 && roll <= 70) {
			ID = 7;
		} else if (roll >= 71 && roll <= 80) {
			ID = 17;
		} else if (roll >= 81 && roll <= 90) {
			ID = 8;
		} else if (roll >= 91 && roll <= 100) {
			ID = 3;
		}

		if (ID > -1) {
			Constants.player[Constants.playerIndex].addItems(ID);
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "从宝箱中获得了【 "
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
		} else {
			Constants.player[Constants.playerIndex].addEquips(ID + 5);
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "从宝箱中获得了【 "
					+ Constants.player[Constants.playerIndex].getEquipsByID(
							ID + 5).getName() + " 】。";
		}
	}

	// Constants.ItemMessage = "使用了 " + name + "。道路上出现了一道屏障！";

	static public void UseOpenArenaChestLV1() {
		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);

		if (roll >= 1 && roll <= 50) {
			ID = UseOpenChestLV1();
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【"
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
			return;
		} else if (roll >= 51 && roll <= 100) {
			ID = 5;
		
		Constants.player[Constants.playerIndex].addEquips(ID);
		Constants.ItemMessage = Constants.player[Constants.playerIndex]
				.getName()
				+ "得到竞技场获胜者奖励。从宝箱中获得了【 "
				+ Constants.player[Constants.playerIndex].getEquipsByID(ID)
						.getName() + " 】。";
		}
	}

	static public void UseOpenArenaChestLV2() {
		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);
		// Log.i("JAVA_TEST", "roll:" + roll);
		if (roll >= 1 && roll <= 30) {
			ID = UseOpenChestLV1();
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【"
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
			return;
		} else if (roll >= 31 && roll <= 60) {
			ID = UseOpenChestLV2();
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【"
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
			return;
		} else if (roll >= 61 && roll <= 100) {
			ID = 6;
		
		Constants.player[Constants.playerIndex].addEquips(ID);
		Constants.ItemMessage = Constants.player[Constants.playerIndex]
				.getName()
				+ "得到竞技场获胜者奖励。从宝箱中获得了【 "
				+ Constants.player[Constants.playerIndex].getEquipsByID(ID)
						.getName() + " 】。";
		}
	}

	static public void UseOpenArenaChestLV3() {
		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);

		
//UseOpenArenaChestLV3
		//Log.i("JAVA_TEST", "UseOpenArenaChestLV3");
		//Log.i("JAVA_TEST", "roll:" + roll);

		if (roll >= 1 && roll <= 40) {
			ID = UseOpenChestLV2();
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【"
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
			return;
		} else if (roll >= 41 && roll <= 70) {
			ID = UseOpenChestLV3();
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【"
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
			return;
		} else if (roll >= 71 && roll <= 100) {
			ID = 7;
			//Log.i("JAVA_TEST", "EQUIP ID:" + ID);
			Constants.player[Constants.playerIndex].addEquips(ID);
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【 "
					+ Constants.player[Constants.playerIndex].getEquipsByID(ID)
							.getName() + " 】。";
		}
	}

	static public void UseOpenArenaChestLV4() {
		int ID = 0;
		int roll = eBattleEven.RandomInt(1, 100);
		
		//roll = 40;
		
		if (roll >= 1 && roll <= 40) {
			ID = UseOpenChestLV3();
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【"
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
			return;
		} else if (roll >= 41 && roll <= 80) {
			ID = UseOpenChestLV4();
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【"
					+ Constants.player[Constants.playerIndex].getItemsByID(ID)
							.getName() + " 】。";
			return;
		} else if (roll >= 81 && roll <= 100) {
			ID = 8;

			Constants.player[Constants.playerIndex].addEquips(ID);
			Constants.ItemMessage = Constants.player[Constants.playerIndex]
					.getName()
					+ "得到竞技场获胜者奖励。从宝箱中获得了【 "
					+ Constants.player[Constants.playerIndex].getEquipsByID(ID)
							.getName() + " 】。";
		}
	}
}
