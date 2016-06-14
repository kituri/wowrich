package com.data.mapdata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Bitmap.Config; //import android.util.Log;


import com.kituri.wowrich.R;

public class MapLayer {
	// 一个地图的组成要素，
	// 地图由不同的格子组成循环的地图
	// 每个格子拆
	// 要调用这里的私有变量，统一用Set。
	// 需要调用私有对象里的私有变量，用Set封装Set
	private Bitmap bgImage;

	private int mapData[][] = {
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1 },
			{ -1, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1 },
			{ -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, -1 },
			{ -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, 13, -1 },
			{ -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, 14, -1 },
			{ -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15, -1 },
			{ -1, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, -1 },
			{ -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1 },
			{ -1, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1 },
			{ -1, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19, -1 },
			{ -1, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };;

	// 做死的一张表，仅仅初始化权限显示的位置
	private final int psnIndexData[][] = {
			{ -1, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11 },
			{ 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12 },
			{ 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 13 },
			{ 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 14 },
			{ 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15 },
			{ 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16 },
			{ 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17 },
			{ 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18 },
			{ 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, 29, 28, 27, 26, 25, 24, 23, 22, 21, -1, -1 } };;

	// 具体的权限表示(实时改变，paint支持)
	// public int psnPlayerData[][] =
	// {
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	// };;

	private int typeData[][] = {
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, 3, 1, 5, 1, 6, 2, 1, 4, 1, 1, 9, -1 },
			{ -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1 },
			{ -1, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1 },
			{ -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1 },
			{ -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1 },
			{ -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1 },
			{ -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1 },
			{ -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, 5, -1 },
			{ -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1 },
			{ -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1 },
			{ -1, 7, 1, 10, 1, 1, 2, 1, 1, 4, 1, 8, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };;
	/*
	 * type名字: 各种……
	 */
	String typeName[] = { "", "", "", "GO", "机会", "盒子世界", "酒馆", "【副本】熔火之心",
			"【副本】黑暗神殿", "【副本】太阳井高地", "竞技场"

	};

	// 类型改动说明：先更改typeData：然后更改GameEvenData 最后更改所调用的事件。
	// 把Constants里的TYPE_XXXXX 一并更改

	/*
	 * type: 0:NULL 1:普通城市 //单独一张表，可变化 2:副本 //单独一张表，可变化 3:"出生点" 4:"机会" 5:"宝箱"
	 * 6:"酒馆"(恢复体力) 7:"熔火之心" (副本) 8:"黑暗神殿" (副本) 9:"太阳井高地"(副本) 10:"竞技场"
	 */
	int typeDrawableID[] = { 0, 0, 0, R.drawable.icon_even00,
			R.drawable.icon_even01, R.drawable.icon_even02,
			R.drawable.icon_even03, R.drawable.icon_even04,
			R.drawable.icon_even05, R.drawable.icon_even06, 0 };
	/*
	 * 城市: 各种……
	 */
	int cityDrawableID[] = { R.drawable.city01, R.drawable.city02,
			R.drawable.city03, R.drawable.city04, R.drawable.city05,
			R.drawable.city06, R.drawable.city07, R.drawable.city08,
			R.drawable.city09, R.drawable.city10, R.drawable.city11,
			R.drawable.city12, R.drawable.city13, R.drawable.city14,
			R.drawable.city15, R.drawable.city16, R.drawable.city17,
			R.drawable.city18, R.drawable.city19, R.drawable.city20,
			R.drawable.city21, R.drawable.city22 };

	/*
	 * 城市名字: 各种……
	 */
	String cityName[] = { "【死亡矿井】",// 1 死亡矿井
			"【哀嚎洞窟】",// 3 哀嚎洞窟
			// "【阿拉希战场】", // 5 阿拉希战场
			"【血色修道院】", // 6 血色修道院
			"【祖尔法拉克】", // 8 祖尔法拉克
			"【斯坦索姆】", // 9 斯坦索姆
			"【黑铁熔炉】", // 11 黑铁熔炉
			// "【地精城市-加基森】", // 12 地精城市-加基森
			"【安琪拉神庙】", // 13 安琪拉神庙
			"【纳克萨马斯】", // 14 纳克萨马斯
			// "【战歌峡谷战场】", // 15 战歌峡谷战场
			"【卡拉赞】", // 16 卡拉赞
			"【祖阿曼】", // 18 祖阿曼
			"【格鲁尔】", // 19 格鲁尔

			"【达纳苏斯】", // 21 达纳苏斯
			"【铁炉堡】", // 23 铁炉堡
			"【暴风城】", // 24 暴风城
			// "【奥克兰克山谷战场】", // 25 奥克兰克山谷战场
			"【奥格瑞玛】", // 26 奥格瑞玛
			"【幽暗城】", // 27 幽暗城
			// "【地精城市-藏宝海湾】", // 28 地精城市-藏宝海湾
			"【雷霆崖】", // 29 雷霆崖
			"【风暴要塞】", // 31 风暴要塞
			"【毒蛇神殿】", // 32 毒蛇神殿
			"【海加尔峰】", // 34 海加尔峰
			// "【风暴之眼战场】", // 35 风暴之眼战场
			"【马瑟利顿】", // 37 马瑟利顿
			"【黑暗之门】" // 39 黑暗之门
	};
	/*
	 * 城市的价值(COST): 各种……
	 */
	int cityCost[] = { 150, 150, 180, 180, 200, 200, 240, 240, 270, 270, 270,
			300, 300, 300, 300, 300, 300, 300, 340, 340, 340, 340, };
	/*
	 * 城市防御力
	 */
	int cityDef[] = { 15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 19, 20, 20, 20,
			20, 20, 20, 20, 21, 21, 21, 21 };
	/*
	 * 特殊城市图片: 可能会是副本（未定）……
	 */
	// int spCityDrawableID[] = { R.drawable.city_sp01, R.drawable.city_sp02,
	// R.drawable.city_sp01, R.drawable.city_sp01, R.drawable.city_sp02,
	// R.drawable.city_sp01
	// // R.drawable.city
	// };
	/*
	 * 特殊城市名字: 可能会是副本（未定）……
	 */
	String spCityName[] = { "【阿拉希战场】", "【战歌峡谷战场】", "【奥克兰克山谷战场】", "【风暴之眼战场】" };
	/*
	 * 特殊城市价值: 可能会是副本（未定）……
	 */
	int spCityCost[] = { 200, 150, 200, 200, 150, 200 };

	String ArenaName[] = { "【竞技场】", "【竞技场】" };

	int ArenaDrawableID[] = { R.drawable.arena_00, R.drawable.arena_01 };
	/*
	 * MapAura
	 */
	private MapAuraData[] mapAura;

	/*
	 * 一共有多少个MapAura //无SET
	 */
	private int MapAuraCounts;
	/*
	 * 地图宽度，返回像素值//无SET
	 */
	private int Width;
	/*
	 * 地图高度，返回像素值//无SET
	 */
	private int Height;
	/*
	 * 地图有多少行(MapData)//无SET
	 */
	private int Row;
	/*
	 * 地图有多少列(MapData)//无SET
	 */
	private int Column;
	/*
	 * 地图每个格子的长和宽（必须正方形）
	 */
	private int singleLength;

	private MapBarriers myBarriers;

	public MapLayer(Context context, int screenWidth, int screenHeight) {
		this.MapAuraCounts = 40;
		this.singleLength = 96;
		this.Row = 13;
		this.Column = 13;
		// this.mapData = new int[Row][Column];
		this.Width = Column * singleLength;
		this.Height = Row * singleLength;
		this.mapAura = new MapAuraData[Row * Column];
		for (int i = 0; i < mapAura.length; i++) {
			mapAura[i] = new MapAuraData();
		}
		myBarriers = new MapBarriers();
		myBarriers.setSingleLength(singleLength);
		InitMapData();
		InitbgImage(context, screenWidth, screenHeight);
		// mapAura[mapData[y][x]].getDrawableID();
		// Log.i("MapLayer","mapAura[0].getDrawableID():" +
		// mapAura[0].getDrawableID());
	}

	public void setBarriers(boolean enabled, int posX, int posY, int index) {
		myBarriers.setEnabled(enabled);
		myBarriers.setPosX(posX);
		myBarriers.setPosY(posY);
		myBarriers.setIndex(index);
	}

	public void setBarriersEnabled(boolean enabled) {
		myBarriers.setEnabled(enabled);
	}

	public boolean getBarriersEnabled() {
		return myBarriers.getEnabled();
	}

	public int getBarriersIndex() {
		return myBarriers.getIndex();
	}

	public boolean isBarriersEnabled() {
		return myBarriers.isEnabled();
	}

	public int getBarriersX() {
		return myBarriers.getPosX();
	}

	public int getBarriersY() {
		return myBarriers.getPosY();
	}

	private void InitbgImage(Context context, int screenWidth, int screenHeight) {
		// TODO Auto-generated method stub

		// bgImage = Bitmap.createBitmap(singleLength * Row,
		// singleLength * Column, Config.ARGB_8888);

		// Display display = getWindowManager().getDefaultDisplay();
		bgImage = Bitmap.createBitmap(singleLength * Row,
				singleLength * Column, Config.ARGB_8888);

		Canvas cv = new Canvas(bgImage);

		Bitmap bgPic = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bg);
		cv.drawBitmap(bgPic, singleLength * 2, singleLength * 2, null);
		// 背景在这里画

		for (int i = 0; i < mapData.length; i++) {
			for (int j = 0; j < mapData[i].length; j++) {
				// //bgLayer.setCell(j, i, mapData[i][j]);
				if (mapData[j][i] != -1) {
					Bitmap src = BitmapFactory.decodeResource(context
							.getResources(), getDrawableID(i, j));
					//
					// if (getDrawableID(i, j) == R.drawable.icon_even00) {
					//
					// // Log.i("MapLayer","R.drawable.icon_even00");
					// }
					// Log.i("MapLayer","j:" + j + "i:" + i);
					if (src != null) {
						cv.drawBitmap(src, i * singleLength, j * singleLength,
								null);
						// 在 0，0坐标开始画入src
					}
				}
			}
		}
		// Log.i("MapLayer","mapAura[0]");
		Bitmap src = BitmapFactory.decodeResource(context.getResources(),
				mapAura[0].getDrawableID());
		cv.drawBitmap(src, 1 * singleLength, 1 * singleLength, null);

		cv.save(Canvas.ALL_SAVE_FLAG);// 保存
		// //store
		cv.restore();// 存储
	}

	// 即使设置了一样的，index还是需要自己设的。
	private void SetMapAuraDataType(int mapAuraIndex, int type) {
		// MapAuraData mac = new MapAuraData();
		// if(mapAuraIndex == 0){
		// // Log.i("MapLayer","mapAuraIndex == 0");
		// mac.setDrawableID(R.drawable.icon_even00);
		// mac.setName("GO");
		// mac.setType(3);
		// this.mapAura[0] = mac;
		// // Log.i("MapLayer","mapAura[0]:" + mapAura[0].getDrawableID());
		// return;
		// }
		// Log.i("MapLayer","mapAuraIndex:" + mapAuraIndex);
		mapAura[mapAuraIndex].setDrawableID(typeDrawableID[type]);
		mapAura[mapAuraIndex].setName(typeName[type]);
		mapAura[mapAuraIndex].setType(type);

		// this.mapAura[mapAuraIndex] = mac;
	}

	private void SetCity(int mapAuraIndex, int cityType) {
		// Log.i("MapLayer","SetCity Star");
		// MapAuraCity mac = (MapAuraCity)mapAura[mapAuraIndex];
		// MapAuraData mac = new MapAuraData();

		// Log.i("MapLayer",
		// "MapAuraCity mac = (MapAuraCity)mapAura[mapAuraIndex];");
		mapAura[mapAuraIndex].setDrawableID(cityDrawableID[cityType]);
		mapAura[mapAuraIndex].setName(cityName[cityType]);
		mapAura[mapAuraIndex].setType(1);
		mapAura[mapAuraIndex].setCost(cityCost[cityType]);
		mapAura[mapAuraIndex].setDef(cityDef[cityType]);
		// Log.i("MapLayer","SetCity SetOver");
		// this.mapAura[mapAuraIndex] = mac;
		// Log.i("MapLayer","this.mapAura[mapAuraIndex] = mac;");
		// Log.i("MapLayer","SetCity Over");
	}

	private void SetSPCity(int mapAuraIndex, int cityType) {
		// MapAuraSPCity mac = (MapAuraSPCity)mapAura[mapAuraIndex];
		// MapAuraData mac = new MapAuraData();
		mapAura[mapAuraIndex].setDrawableID(R.drawable.city_sp01);
		mapAura[mapAuraIndex].setName(spCityName[cityType]);
		mapAura[mapAuraIndex].setType(2);
		// mapAura[mapAuraIndex].setCost(spCityCost[cityType]);
		// mac.setCostPay(costPay);
		// this.mapAura[mapAuraIndex] = mac;
	}

	private void SetArena(int mapAuraIndex, int arenaType) {
		mapAura[mapAuraIndex].setDrawableID(ArenaDrawableID[arenaType]);
		mapAura[mapAuraIndex].setName(ArenaName[arenaType]);
		mapAura[mapAuraIndex].setType(10);
		// mapAura[mapAuraIndex].setCost(spCityCost[arenaType]);
	}

	// private void SetIndex(int mapAuraIndex,int index,
	// int previousIndex,int nextIndex ,int x,int y){
	//
	// //MapAura mac = (MapAura)mapAura[mapAuraIndex];
	// this.mapAura[mapAuraIndex].setIndex(index, previousIndex, nextIndex);
	// this.mapAura[mapAuraIndex].setPos(x, y);
	// //cityCost
	// }
	/*
	 * type: 0:NULL 1:普通城市 //单独一张表，可变化 2:副本 //单独一张表，可变化 3:"出生点" 4:"机会" 5:"宝箱"
	 * 6:"修理" 7:"戒指" 8:"传送门" 9:"美酒节"
	 */
	private void InitMapData() {
		// Log.i("MapLayer","Star");
		int j = 0;
		int k = 0;
		for (int i = 0; i < mapAura.length; i++) {
			// 0:NULL
			// Log.i("MapLayer","Star:" + i);

			SetMapAuraDataType(i, 0);
			mapAura[i].setPos(j, k);
			// mapAura[i].setIndex(previousIndex, index, nextIndex);
			j++;
			if (j > Row) {
				j = 0;
				k++;
			}
		}
		// Log.i("MapLayer","SetMapAuraDataType OK");
		InitType();
		// Log.i("MapLayer","InitType OK");
		InitIndex();
		// Log.i("MapLayer","InitIndex OK");
		InitPos();
		// Log.i("MapLayer","InitPos OK");
		InitCity();
		// Log.i("MapLayer","InitCity OK");
		InitSPCity();
		// Log.i("MapLayer","InitSPCity OK");
		InitArena();
		InitPossession();
		// for(int i = 0;i < mapAura.length;i++){
		// Log.i("Move", "X:" + mapAura[i].getX() + ",Y:" + mapAura[i].getY());
		// }
		// Log.i("Move", "length:" + mapAura.length);
	}

	private void InitSPCity() {
		// TODO Auto-generated method stub
		SetSPCity(5, 0);
		// SetSPCity(12, 1);
		SetSPCity(15, 1);
		SetSPCity(25, 2);
		// SetSPCity(28, 4);
		SetSPCity(35, 3);
	}

	private void InitArena() {
		SetArena(12, 0);
		SetArena(28, 1);
	}

	private void InitPossession() {
		// typeData
		for (int i = 0; i < psnIndexData.length; i++) {
			for (int j = 0; j < psnIndexData[i].length; j++) {
				// //bgLayer.setCell(j, i, mapData[i][j]);
				if (psnIndexData[j][i] != -1) {
					mapAura[psnIndexData[j][i]].setPossessionPos(i, j);
				}
			}
		}
	}

	public void setMapAuraPossession(int index, int possession) {
		mapAura[index].setPossession(possession);
	}

	public void setMapAuraPossession(int[] possessionInt) {
		
		//Log.i("Save", "setMapAuraPossession() star");
		
		for(int i = 0;i< MapAuraCounts;i++){
			//Log.i("Save", "setMapAuraPossession() i:" + i);
			setMapAuraPossession(i,possessionInt[i]);
		}
		
		//Log.i("Save", "setMapAuraPossession() over");
	}
	
	public int[] getMapAuraPossession() {
		int[] possessionInt = new int[MapAuraCounts];
		
		
		
		for (int i = 0; i < MapAuraCounts; i++) {
			possessionInt[i] = mapAura[i].getPossession();
		}
		
		
		
		return possessionInt;
	}

	public void setMapAuraLVChange(int index, int ChangeLV) {
		mapAura[index].setLVChange(ChangeLV);
	}

	public int getPossessionPosX(int index) {
		int i = mapAura[index].getPossessionX() * singleLength;
		return i;
	}

	public int getPossessionPosY(int index) {
		int i = mapAura[index].getPossessionY() * singleLength;
		return i;
	}

	private void InitCity() {
		// TODO Auto-generated method stub
		int j = 0;
		for (int i = 0; i < mapAura.length; i++) {
			if (mapAura[i].getType() == 1) {
				// Log.i("MapLayer", "InitCity:" + i);
				SetCity(i, j);
				j++;
			}
		}
	}

	private void InitType() {
		// 只有起始点是需要特殊设置的……
		SetMapAuraDataType(0, 3);
		for (int i = 0; i < typeData.length; i++) {
			for (int j = 0; j < typeData[i].length; j++) {
				// //bgLayer.setCell(j, i, mapData[i][j]);
				if (typeData[j][i] != -1) {
					// typeData
					mapAura[getIndex(j, i)].setType(typeData[j][i]);
				}
			}
		}

		for (int i = 1; i < mapAura.length; i++) {
			// Log.i("mapAura[i].getType()", "mapAura[i].getType():" +
			// mapAura[i].getType());
			SetMapAuraDataType(i, mapAura[i].getType());
		}
	}

	private void InitIndex() {
		// mapAura[0].setIndex(0, 0, 1, 1, 1);
		mapAura[0].setIndex(39, 0, 1);
		for (int i = 1; i < MapAuraCounts - 1; i++) {
			// mapAura[1].setIndex(0, 1, 2);
			mapAura[i].setIndex(i - 1, i, i + 1);
			// mapAura[38].setIndex(37, 38, 39);
		}
		mapAura[39].setIndex(38, 39, 0);
	}

	private void InitPos() {

		for (int i = 0; i < mapData.length; i++) {
			for (int j = 0; j < mapData[i].length; j++) {
				// //bgLayer.setCell(j, i, mapData[i][j]);
				if (mapData[j][i] != -1) {
					mapAura[mapData[j][i]].setPos(i, j);
				}
			}
		}
	}

	public int getMapAuraCounts() {
		return MapAuraCounts;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}

	public int getRow() {
		return Row;
	}

	public int getColumn() {
		return Column;
	}

	public int getSingleLength() {
		return singleLength;
	}

	public int getAllAuraCounts() {
		return Row * Column;
	}

	public MapAuraData getMapAura(int index) {
		if (index < mapAura.length) {
			return mapAura[index];
		} else {
			return null;
		}
	}

	// 做红利查询用
	public int getPlayerCityLVNum(int playerIndex) {
		int num = 0;
		for (int i = 0; i < mapAura.length; i++) {
			if (mapAura[i].getPossession() == playerIndex
					&& mapAura[i].getType() == 1) {
				num += mapAura[i].getLV();
			}
		}
		return num;
	}

	// 做战场归属者查询用
	public int getPlayerSPCityNum(int playerIndex) {
		int num = 0;
		for (int i = 0; i < mapAura.length; i++) {
			if (mapAura[i].getPossession() == playerIndex
					&& mapAura[i].getType() == 2) {
				num += 1;
			}
		}
		return num;
	}

	public boolean IsPossession(int index, int playerIndex) {
		if (mapAura[index].getPossession() == playerIndex) {
			return true;
		}
		return false;
	}

	public int getPossession(int index) {
		return mapAura[index].getPossession();
	}

	// IsPoNossession
	public boolean IsPoNossession(int index, int playerIndex) {
		if (mapAura[index].getPossession() == -1) {
			return true;
		}
		return false;
	}

	public MapAuraData[] getMapAuraArray() {
		// for(int i = 0;i < mapData.length;i++){
		// Log.i("Move", "X:" + mapAura[i].getX() + ",Y:" + mapAura[i].getY());
		// }
		return mapAura;
	}

	public int getIndex(int x, int y) {
		return mapData[y][x];
	}

	public int getPosX(int index){
		for (int i = 0; i < mapData.length; i++) {
			for (int j = 0; j < mapData[i].length; j++) {
				if(mapData[j][i] == index){
					return i;
				}
			}
			}
		return -1;
	}
	
	public int getPosY(int index){
		for (int i = 0; i < mapData.length; i++) {
			for (int j = 0; j < mapData[i].length; j++) {
				if(mapData[j][i] == index){
					return j;
				}
			}
			}
		return -1;
	}
	
	public int getDrawableID(int x, int y) {
		// Log.i("MapLayer", "mapAura[mapData[y][x]].getDrawableID():" +
		// mapAura[mapData[y][x]].getDrawableID());
		// if()
		return mapAura[mapData[y][x]].getDrawableID();
	}

	public String getName(int x, int y) {
		// Log.i("MapLayer", "mapAura[mapData[y][x]].getDrawableID():" +
		// mapAura[mapData[y][x]].getDrawableID());
		// if()
		return mapAura[mapData[y][x]].getName();
	}

	public String getName(int index) {
		// Log.i("MapLayer", "mapAura[mapData[y][x]].getDrawableID():" +
		// mapAura[mapData[y][x]].getDrawableID());
		// if()
		return mapAura[index].getName();
	}

	public boolean IsMapAura(int x, int y) {
		// Log.i("MapLayer", "mapAura[mapData[y][x]].getDrawableID():" +
		// mapAura[mapData[y][x]].getDrawableID());
		// if()

		// Log.i("IsMapAura:", "x:" + x + ",y:" + y);
		if (y < 0 || x < 0) {
			return false;
		}
		if (y > mapData.length - 1 || x > mapData[0].length - 1) {
			return false;
		}

		if (mapData[y][x] == -1) {
			return false;
		}

		return true;
	}

	public Bitmap getBitmap() {
		return bgImage;
	}

}
