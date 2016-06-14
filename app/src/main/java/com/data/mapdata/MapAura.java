package com.data.mapdata;

public class MapAura {



	public void setIndex(int previousIndex,int index,int nextIndex){
		this.index = index;
		NextIndex = nextIndex;
		PreviousIndex = previousIndex;
	}
	
	public void setPos(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getNextIndex() {
		return NextIndex;
	}

	public int getPreviousIndex() {
		return PreviousIndex;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDrawableID() {
		return drawableID;
	}
	public void setDrawableID(int drawableID) {
		this.drawableID = drawableID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public MapAura(){
		this.index = -1;
		this.NextIndex = -1;
		this.PreviousIndex = -1;
		this.name = "";
		this.x = 0;
		this.y = 0;
		this.drawableID = 0;
		this.type = 0;
	}
	/*
	  * ��ǰ��������
	 * */
	protected int index;
	/*
	 * ��һ�����������
	 * */
	protected int NextIndex;
	/*
	 * ��һ�����������
	 * */
	protected int PreviousIndex;
	/*
	 * ���������
	 * */
	protected String name;

	/*
	 * �����ڵ�ͼ��λ��X�����λ�ò�������,���ǻ�ľ�飩
	 * */
	protected int x;
	/*
	 * �����ڵ�ͼ��λ��Y�����λ�ò�������,���ǻ�ľ�飩
	 * */
	protected int y;
	/*
	 * �������ͼƬ
	 * */
	protected int drawableID;
	/*
	 * type:
	 *     0:NULL
	 *     1:��ͨ����
	 *     2:����
	 *     3:"������"
	 *     4:"����" 
	 *     5:"����"
	 *     6:"����"
	 *     7:"��ָ"
	 *     8:"������"
	 *     9:"���ƽ�"
	 *     
	 * */
	
	protected int type;

}
