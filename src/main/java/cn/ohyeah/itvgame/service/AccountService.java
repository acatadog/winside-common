package cn.ohyeah.itvgame.service;

import java.io.IOException;
import java.util.Date;

import cn.ohyeah.stb.util.ConvertUtil;

/**
 * �û�����ӿ�
 * @author Administrator
 *
 */
public class AccountService extends AbstractHttpService{

	public AccountService(String url){
		super(url);
	}
	
	/**
	 * ������Ϸ
	 * @param userid
	 * @param username
	 * @param product
	 */
	public void cmdLogin(String userid, String username, String product, boolean isLogin) {
		String sendCmd = null;
		if(isLogin){
			serviceLocation += addr_login;
		}else{
			serviceLocation += addr_quit;
		}
		
		sendCmd = "userid=" + HURLEncoder.encode(userid) + 
				  "&username="+ HURLEncoder.encode(username) + 
				  "&product="+ HURLEncoder.encode(product);

		try {
			String str = postViaHttpConnection(serviceLocation, sendCmd);
			String info[] = ConvertUtil.split(str, "#");
			if(info[1].equals("0")){
				result = 0;
			}else{
				result = -1;
				message = info[2];
			}
		} catch (IOException e) {
			result = -1;
			message = e.getMessage();
			e.printStackTrace();
		}
	}

	/**
	 * ����Ϸ��ص�ȫ�����ݣ��ڷ�������ֻ��һ������
	 * @param userid
	 * @param username
	 * @param product
	 * @param data ��Ϸ���ݵ�����(65535�ֽ�)
	 */
	public void saveGobalData(String userid, String username, String product, String data){
		String sendCmd = null;
		serviceLocation += addr_saveGlobalData;
		
		sendCmd = "userid=" + HURLEncoder.encode(userid) + 
				  "&username="+ HURLEncoder.encode(username) + 
				  "&product="+ HURLEncoder.encode(product) +
				  "&datas="+ HURLEncoder.encode(data);

		try {
			String str = postViaHttpConnection(serviceLocation, sendCmd);
			String info[] = ConvertUtil.split(str, "#");
			if(info[1].equals("0")){
				result = 0;
			}else{
				result = -1;
				message = info[2];
			}
		} catch (IOException e) {
			result = -1;
			message = e.getMessage();
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡȫ������
	 * @param userid
	 * @param username
	 * @param product
	 * @param data
	 */
	public String loadGobalData(String userid, String username, String product){
		String sendCmd = null;
		serviceLocation += addr_loadGlobalData;
		
		sendCmd = "userid=" + HURLEncoder.encode(userid) + 
				  "&username="+ HURLEncoder.encode(username) + 
				  "&product="+ HURLEncoder.encode(product);

		try {
			String str = postViaHttpConnection(serviceLocation, sendCmd);
			String info[] = ConvertUtil.split(str, "#");
			if(info[1].equals("0")){
				result = 0;
				return info[2];
			}else{
				result = -1;
				message = info[2];
				return null;
			}
		} catch (IOException e) {
			result = -1;
			message = e.getMessage();
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * �������ʱ���������д������־
	 * @param userid
	 * @param username
	 * @param product
	 * @param contentStr �ַ���ֵ���磺�������Ʒ����
	 * @param contentVal ����ֵ���磺���ѵ�Ԫ����
	 * @param memo ��ע(65535�ֽ�)
	 */
	public void writePurchaseLog(String userid, String username, String product,
			String contentStr, int contentVal, String memo){
		String sendCmd = null;
		serviceLocation += addr_log;
		
		sendCmd = "userid=" + HURLEncoder.encode(userid) + 
				  "&username="+ HURLEncoder.encode(username) + 
				  "&product="+ HURLEncoder.encode(product) +
				  "&contentStr="+ HURLEncoder.encode(contentStr) +
				  "&contentVal="+ HURLEncoder.encode(String.valueOf(contentVal)) +
				  "&memo="+ HURLEncoder.encode(memo);

		try {
			String str = postViaHttpConnection(serviceLocation, sendCmd);
			String info[] = ConvertUtil.split(str, "#");
			if(info[1].equals("0")){
				result = 0;
			}else{
				result = -1;
				message = info[2];
			}
		} catch (IOException e) {
			result = -1;
			message = e.getMessage();
			e.printStackTrace();
		}
	}
	
	/**
	 * ��������,���Ϊ10����
	 * @param userid
	 * @param username
	 * @param product
	 */
	public void sendHeartBeat(String userid, String username, String product){
		String sendCmd = null;
		serviceLocation += addr_heartBeat;
		
		sendCmd = "userid=" + HURLEncoder.encode(userid) + 
				  "&username="+ HURLEncoder.encode(username) + 
				  "&product="+ HURLEncoder.encode(product);
		
		try {
			String str = postViaHttpConnection(serviceLocation, sendCmd);
			String info[] = ConvertUtil.split(str, "#");
			if(info[1].equals("0")){
				result = 0;
				message = info[2];
			}else{
				result = -1;
				message = info[2];
			}
		} catch (IOException e) {
			result = -1;
			message = e.getMessage();
			e.printStackTrace();
		}
	}
	
	/**
	 *  ��ѯϵͳʱ��
	 * @param userid
	 * @param username
	 * @param product
	 * @param format ʱ��ĸ�ʽ����ǰֻ֧��0��
	 */
	public Date querySystemTime(String userid, String username, String product, int format){
		String sendCmd = null;
		serviceLocation += addr_queryTime;
		
		sendCmd = "userid=" + HURLEncoder.encode(userid) + 
				  "&username="+ HURLEncoder.encode(username) + 
				  "&product="+ HURLEncoder.encode(product) +
				  "&format="+ HURLEncoder.encode(String.valueOf(format));
		
		try {
			String str = postViaHttpConnection(serviceLocation, sendCmd);
			String info[] = ConvertUtil.split(str, "#");
			if(info[1].equals("0")){
				result = 0;
				return new Date(Long.parseLong(info[2]));
			}else{
				result = -1;
				message = info[2];
				return null;
			}
		} catch (IOException e) {
			result = -1;
			message = e.getMessage();
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��ѯ����(success#���صĹ�������#��ʼʱ��1, ���ʱ��1,�������1,��������1\n��ʼʱ��2, ���ʱ��2, �������2,��������2
	 * @param product
	 */
	public String queryNews(String product){
		String sendCmd = null;
		serviceLocation += addr_news;
		
		sendCmd = "&product="+ HURLEncoder.encode(product);
		
		try {
			String str = postViaHttpConnection(serviceLocation, sendCmd);
			String info[] = ConvertUtil.split(str, "#");
			if(info[1].equals("0")){
				result = 0;
				return info[2];
			}else{
				result = -1;
				message = info[2];
				return null;
			}
		} catch (IOException e) {
			result = -1;
			message = e.getMessage();
			e.printStackTrace();
			return null;
		}
	}
}
