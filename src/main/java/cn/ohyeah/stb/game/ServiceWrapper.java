package cn.ohyeah.stb.game;

import cn.ohyeah.itvgame.service.AccountService;

/**
 * �����װ�࣬�Է��������˼򵥵İ�װ��
 * Ϊ�˱�֤�̰߳�ȫ�����ȴ����µĶ����ٵ���
 * @author maqian
 * @version 1.0
 */
public final class ServiceWrapper {
	private static final String OFFLINE_MSG = "����ģʽ��֧�ִ˲���";
	private static boolean offline;
	
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
		offline = pm.offline;
	}
	
	
	public boolean userLogin(){
		AccountService as = new AccountService(server);
		as.cmdLogin(pm.userId, pm.accountName, pm.appName);
		message = as.getMessage();
		return as.isSuccess();
	}
	
}
