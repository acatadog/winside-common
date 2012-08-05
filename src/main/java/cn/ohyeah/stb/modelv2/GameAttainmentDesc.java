package cn.ohyeah.stb.modelv2;

import cn.ohyeah.stb.buf.ByteBuffer;

/**
 * ��Ϸ�ɾ�������
 * @author maqian
 * @version 2.0
 */
public class GameAttainmentDesc {
	private int attainmentId;
	private int playDuration;	/*��Ϸʱ��(��λ����)*/
	private int scores;			/*��Ϸ����*/
	private int ranking;
	private String remark;
	private String time;
	
	public int getAttainmentId() {
		return attainmentId;
	}
	public void setAttainmentId(int attainmentId) {
		this.attainmentId = attainmentId;
	}
	public int getPlayDuration() {
		return playDuration;
	}
	public void setPlayDuration(int playDuration) {
		this.playDuration = playDuration;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getRanking() {
		return ranking;
	}
	
	public void readQueryResponseData(ByteBuffer buf) {
		attainmentId = buf.readInt();
		playDuration = buf.readInt();
		scores = buf.readInt();
		ranking = buf.readInt();
		remark = buf.readUTF();
		time = buf.readUTF();
	}
}
