package cn.ohyeah.stb.modelv2;

import cn.ohyeah.stb.buf.ByteBuffer;

/**
 * ��Ϸ��¼������
 * @author maqian
 * @version 2.0
 */
public class GameRecordDesc {
	private int recordId;
	private int playDuration;	/*��Ϸʱ��(��λ����)*/
	private int scores;			/*��Ϸ����*/
	private String time;
	private String remark;
	
	public int getRecordId() {
		return recordId;
	}
	
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public void setScores(int scores) {
		this.scores = scores;
	}
	public int getScores() {
		return scores;
	}
	
	public void readQueryResponseData(ByteBuffer buf) {
		recordId = buf.readInt();
		playDuration = buf.readInt();
		scores = buf.readInt();
		remark = buf.readUTF();
		time = buf.readUTF();
	}
}