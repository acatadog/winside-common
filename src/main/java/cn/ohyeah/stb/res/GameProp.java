package cn.ohyeah.stb.res;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * ����������
 * @author maqian
 * @version 1.0
 */
public class GameProp {
	private String name;	/*��������*/
	private short id;		/*����ID*/
	private short propId;	/*���ݿ��е���ID*/
	private short price;	/*���߼۸�*/
	private short reserved;	/*�����ֶ�*/
	private String intro;	/*����˵��*/
	private String icon;	/*����ͼ��*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public short getPropId() {
		return propId;
	}
	public void setPropId(short propId) {
		this.propId = propId;
	}
	public short getPrice() {
		return price;
	}
	public void setPrice(short price) {
		this.price = price;
	}
	public short getReserved() {
		return reserved;
	}
	public void setReserved(short reserved) {
		this.reserved = reserved;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public void serialize(DataOutputStream out) throws IOException {
		out.writeShort(id);
		out.writeUTF(name);
		out.writeUTF(intro);
		out.writeShort(propId);
		out.writeShort(price);
		out.writeShort(reserved);
		out.writeUTF(icon);
	}
	
	public void deserialize(DataInputStream in) throws IOException {
		id = in.readShort();
		name = in.readUTF();
		intro = in.readUTF();
		propId = in.readShort();
		price = in.readShort();
		reserved = in.readShort();
		icon = in.readUTF();
	}
	
}
