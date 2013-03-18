package cn.ohyeah.stb.game;

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
	private ParamManager paramManager;
	private EngineService engineService;
	
	private int result;
	private String message;
	
	public ServiceWrapper(IEngine engine, String server) {
		this.engine = engine;
		this.engineService = engine.getEngineService();
		this.paramManager = engineService.getParamManager();
		this.server = server;
		offline = paramManager.offline;
	}
	
	
}
