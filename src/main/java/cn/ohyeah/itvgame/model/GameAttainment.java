package cn.ohyeah.itvgame.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cn.ohyeah.stb.buf.ByteBuffer;

/**
 * ��Ϸ�ɾ���
 * @author maqian
 * @version 1.0
 */
public class GameAttainment {
	private int attainmentId;
	private String userId;
	private int playDuration;	/*��Ϸʱ��(��λ����)*/
	private int scores;			/*��Ϸ����*/
	private int ranking;
	private String remark = "";
	private String time;
	private byte[] data;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setPlayDuration(int playDuration) {
		this.playDuration = playDuration;
	}
	public int getPlayDuration() {
		return playDuration;
	}
	public void setAttainmentId(int attainmentId) {
		this.attainmentId = attainmentId;
	}
	public int getAttainmentId() {
		return attainmentId;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public byte[] getData() {
		return data;
	}
	
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getRanking() {
		return ranking;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void writeData(DataOutputStream dos) throws IOException {
		if (data!=null && data.length>0) {
			dos.writeInt(data.length);
			dos.write(data, 0, data.length);
		}
		else {
			dos.writeInt(0);
		}
	}
	public void writeData(ByteBuffer buf) throws IOException {
		if (data!=null && data.length>0) {
			buf.writeInt(data.length);
			buf.writeBytes(data, 0, data.length);
		}
		else {
			buf.writeInt(0);
		}
	}
	
	public void readData(DataInputStream dis) throws IOException {
		int totalLen = dis.readInt();
		if (totalLen > 0) {
			data = new byte[totalLen];
			int readLen = 0;
			int curReadLen;
			while (readLen < totalLen) {
				curReadLen = dis.read(data, readLen, totalLen-readLen);
				if (curReadLen > 0) {
					readLen += curReadLen;
				}
			}
		}
	}
	
	public void readData(ByteBuffer buf) {
		int len = buf.readInt();
		if (len > 0) {
			data = buf.readBytes(len);
		}
	}
	public void writeSaveRequestData(DataOutputStream dos) throws IOException {
		dos.writeInt(attainmentId);
		dos.writeInt(playDuration);
		dos.writeInt(scores);
		dos.writeUTF(remark);
		writeData(dos);
		
	}
	public void writeSaveRequestData(ByteBuffer buf) throws IOException {
		buf.writeInt(attainmentId);
		buf.writeInt(playDuration);
		buf.writeInt(scores);
		buf.writeUTF(remark);
		writeData(buf);
		
	}
	public void writeUpdateRequestData(DataOutputStream dos) throws IOException {
		dos.writeInt(attainmentId);
		dos.writeInt(playDuration);
		dos.writeInt(scores);
		dos.writeUTF(remark);
		writeData(dos);
	}
	public void writeUpdateRequestData(ByteBuffer buf) throws IOException {
		buf.writeInt(attainmentId);
		buf.writeInt(playDuration);
		buf.writeInt(scores);
		buf.writeUTF(remark);
		writeData(buf);
	}
	public void readReadResponseData(DataInputStream dis) throws IOException {
		attainmentId = dis.readInt();
		playDuration = dis.readInt();
		scores = dis.readInt();
		ranking = dis.readInt();
		remark = dis.readUTF();
		time = dis.readUTF();
		readData(dis);
	}
	
	public void readReadResponseData(ByteBuffer buf) throws IOException {
		attainmentId = buf.readInt();
		playDuration = buf.readInt();
		scores = buf.readInt();
		ranking = buf.readInt();
		remark = buf.readUTF();
		time = buf.readUTF();
		readData(buf);
	}
	
}
