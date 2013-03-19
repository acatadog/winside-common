package cn.ohyeah.stb.game;

import cn.ohyeah.itvgame.service.AccountService;
import cn.ohyeah.itvgame.service.PropService;
import cn.ohyeah.itvgame.service.RecordService;

/**
 * �����װ�࣬�Է��������˼򵥵İ�װ��
 * Ϊ�˱�֤�̰߳�ȫ�����ȴ����µĶ����ٵ���
 * @author maqian
 * @version 1.0
 */
public final class ServiceWrapper {
	private String server;
	private IEngine engine;
	private ParamManager pm;
	private EngineService engineService;
	
	private int result;
	private String message;
	
	public ServiceWrapper(IEngine engine, String server) {
		this.engine = engine;
		this.engineService = engine.getEngineService();
		this.pm = engineService.getParamManager();
		this.server = server;
	}
	
	/*�û�����*/
	public void userLogin(){
		AccountService as = new AccountService(server);
		as.cmdLogin(pm.userId, pm.accountName, pm.product, true);
		message = as.getMessage();
		result = as.getResult();
	}
	
	/*�û��˳�*/
	public void userQuit(){
		AccountService as = new AccountService(server);
		as.cmdLogin(pm.userId, pm.accountName, pm.product, false);
		message = as.getMessage();
		result = as.getResult();
	}
	
	/*��ѯϵͳʱ��*/
	public void querySystemTime(){
		AccountService as = new AccountService(server);
		as.querySystemTime(pm.userId, pm.accountName, pm.product, 0);
		message = as.getMessage();
		result = as.getResult();
	}
	
	/*������Ϸȫ������*/
	public void saveGobalData(String data){
		AccountService as = new AccountService(server);
		as.saveGobalData(pm.userId, pm.accountName, pm.product, data);
		message = as.getMessage();
		result = as.getResult();
	}
	
	/*������Ϸȫ������*/
	public String loadGobalData(){
		AccountService as = new AccountService(server);
		String data = as.loadGobalData(pm.userId, pm.accountName, pm.product);
		message = as.getMessage();
		result = as.getResult();
		return data;
	}
	
	/*������Ϸ��¼*/
	public void savrRecord(int index, String datas){
		RecordService rs = new RecordService(server);
		rs.saveRecord(pm.userId, pm.accountName, pm.product, index, datas);
		message = rs.getMessage();
		result = rs.getResult();
	}
	
	/*��ȡ��Ϸ��¼*/
	public String loadRecord(int index){
		RecordService rs = new RecordService(server);
		String datas = rs.loadRecord(pm.userId, pm.accountName, pm.product, index);
		message = rs.getMessage();
		result = rs.getResult();
		return datas;
	}
	
	/*������ֵ��������*/
	public void savePropItem(String datas){
		PropService ps = new PropService(server);
		ps.savePropItem(pm.userId, pm.accountName, pm.product, datas);
		message = ps.getMessage();
		result = ps.getResult();
	}
	
	/*������ֵ��������*/
	public String loadPropItem(){
		PropService ps = new PropService(server);
		String datas = ps.loadPropItem(pm.userId, pm.accountName, pm.product);
		message = ps.getMessage();
		result = ps.getResult();
		return datas;
	}
	
	/*�������д������ߵ���־*/
	public void writePurchaseLog(String contentStr, int contentVal, String memo){
		AccountService as = new AccountService(server);
		as.writePurchaseLog(pm.userId, pm.accountName, pm.product, contentStr, contentVal, memo);
		message = as.getMessage();
		result = as.getResult();
	}
	
	/*�������������������*/
	public void sendHeartBeat(){
		AccountService as = new AccountService(server);
		as.sendHeartBeat(pm.userId, pm.accountName, pm.product);
		message = as.getMessage();
		result = as.getResult();
	}
	
	/*��ѯ����*/
	public void queryNews(){
		AccountService as = new AccountService(server);
		as.queryNews(pm.product);
		message = as.getMessage();
		result = as.getResult();
	}
	
	public String getMessage(){
		return message;
	}
	
	public boolean isServiceSuccessful(){
		return result == 0;
	}
}
