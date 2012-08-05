package cn.ohyeah.stb.modelv2;

import cn.ohyeah.stb.buf.ByteBuffer;

/**
 * ���ӵ�е�����Ϣ��
 * @author maqian
 * @version 2.0
 */
public class OwnProp {
	private int propId;		/*����ID*/
	private int count;		/*����*/
	
	public int getPropId() {
		return propId;
	}
	public void setPropId(int propId) {
		this.propId = propId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void readQueryResponseData(ByteBuffer dis) {
		propId = dis.readInt();
		count = dis.readInt();
	}
	
}
