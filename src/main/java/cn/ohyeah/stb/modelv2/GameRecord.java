package cn.ohyeah.stb.modelv2;

import cn.ohyeah.stb.buf.ByteBuffer;

/**
 * ��Ϸ��¼��
 * @author maqian
 * @version 2.0
 */
public class GameRecord {
	private int recordId;
	private int playDuration;	/*��Ϸʱ��(��λ����)*/
	private int scores;			/*��Ϸ����*/
	private String remark = "";
	private String time;
	private byte []data;

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public int getRecordId() {
		return recordId;
	}
	
	public void setData(byte [] data) {
		this.data = data;
	}
	
	public byte [] getData() {
		return data;
	} 
	
	public void setPlayDuration(int playDuration) {
		this.playDuration = playDuration;
	}
	
	public int getPlayDuration() {
		return playDuration;
	}
	
	public void setScores(int scores) {
		this.scores = scores;
	}
	
	public int getScores() {
		return scores;
	}
	
	public void writeData(ByteBuffer buf) {
		if (data!=null && data.length>0) {
			buf.writeInt(data.length);
			buf.writeBytes(data, 0, data.length);
		}
		else {
			buf.writeInt(0);
		}
	}
	
	public void readData(ByteBuffer buf) {
		int len = buf.readInt();
		if (len > 0) {
			data = buf.readBytes(len);
		}
	}
	
	public void writeSaveRequestData(ByteBuffer buf) {
		buf.writeInt(recordId);
		buf.writeInt(playDuration);
		buf.writeInt(scores);
		buf.writeUTF(remark);
		writeData(buf);
	}
	
	public void writeUpdateRequestData(ByteBuffer buf) {
		buf.writeInt(recordId);
		buf.writeInt(playDuration);
		buf.writeInt(scores);
		buf.writeUTF(remark);
		writeData(buf);
	}
	
	public void readReadResponseData(ByteBuffer dis) {
		recordId = dis.readInt();
		playDuration = dis.readInt();
		scores = dis.readInt();
		remark = dis.readUTF();
		time = dis.readUTF();
		readData(dis);
	}
}