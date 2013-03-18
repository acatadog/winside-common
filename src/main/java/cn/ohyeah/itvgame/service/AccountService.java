package cn.ohyeah.itvgame.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public class AccountService {

	private String url;
	
	public AccountService(String url){
		this.url = url;
	}
	
	public void cmdLogin(String userid, String username, String iptvname, String product, String gameName) {
		url = "http://113.106.54.36:8210/itvsrv/fortest/";
		String sendCmd = null;
		// urlΪ��������ַ��login.doΪ�ӿ�
		if (!url.endsWith("/")) {
			url += "/" + "login.do";
		} else {
			url += "login.do";
		}
		// ��Ҫ���͵�����Ⱥ��ұߵĲ�����ҪHRLEncode����һ��
		sendCmd = "userid=" + HURLEncoder.encode(userid) + "&username="
				+ HURLEncoder.encode(iptvname) + "&product="
				+ HURLEncoder.encode(gameName);

		try {
			postViaHttpConnection(url, sendCmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ��������
	 */
	void postViaHttpConnection(String url, String cmd) throws IOException {
		HttpConnection c = null;
		InputStream is = null;
		OutputStream os = null;
		int rc;
		try {
			url += "?" + cmd;
			c = (HttpConnection) Connector.open(url);
			System.out.println("url:"+url);
			c.setRequestMethod(HttpConnection.GET);
			rc = c.getResponseCode();
			if (rc != HttpConnection.HTTP_OK) {
				throw new IOException("HTTP response code: " + rc);
			}
			System.out.println("request state:"+rc);
			// ��ȡ���ؽ��
			is = c.openInputStream();
			// ���������������ݵĴ���
			
			byte[] b = new byte[1024];
			while(is.read(b) != -1){
				is.read(b);
			}
			
			String str3 = new String(b,"utf-8");
			System.out.println("str3:"+str3);
			
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Not an HTTP URL");
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
			if (c != null)
				c.close();
		}
	}
}
