package cn.ohyeah.stb.ui;

import javax.microedition.lcdui.Graphics;

/**
 * �˵��ӿ�
 * @author maqian
 * @version 1.0
 */
public interface IMenu {
	public int getHilightIndex();
	public void setHilightIndex(int index);
	public void show(Graphics g);
	public void nextItem();
	public void prevItem();
}
