package cn.ohyeah.itvgame.service;

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import cn.ohyeah.stb.util.ConvertUtil;

public class AccountService extends AbstractHttpService{

	public AccountService(String url){
		super(url);
	}
	
	public void cmdLogin(String userid, String username, String product) {
		String sendCmd = null;
		serviceLocation += "login.do";
		
		// ��Ҫ���͵�����Ⱥ��ұߵĲ�����ҪHRLEncode����һ��
		sendCmd = "userid=" + HURLEncoder.encode(userid) + "&username="
				+ HURLEncoder.encode(username) + "&product="
				+ HURLEncoder.encode(product);

		try {
			postViaHttpConnection(serviceLocation, sendCmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��������
	 */
	private void postViaHttpConnection(String url, String cmd) throws IOException {
		int rc;
		try {
			url += "?" + cmd;
			httpConnection = (HttpConnection) Connector.open(url);
			System.out.println("url:"+url);
			httpConnection.setRequestMethod(HttpConnection.GET);
			rc = httpConnection.getResponseCode();
			if (rc != HttpConnection.HTTP_OK) {
				result = -1;
				throw new IOException("HTTP response code: " + rc);
			}
			System.out.println("request state:"+rc);
			// ��ȡ���ؽ��
			inputStream = httpConnection.openInputStream();
			// ���������������ݵĴ���
			
			byte[] b = new byte[1024];
			while(inputStream.read(b) != -1){
				inputStream.read(b);
			}
			
			String str3 = new String(b,"utf-8");
			System.out.println("str3:"+str3);
			String info[] = ConvertUtil.split(str3, "#");
			if(info[1].equals("0")){
				result = 0;
			}else{
				result = -1;
				message = info[2];
			}
			
		} catch (ClassCastException e) {
			result = -1;
			throw new IllegalArgumentException("Not an HTTP URL");
		} finally {
			close();
		}
	}
}
