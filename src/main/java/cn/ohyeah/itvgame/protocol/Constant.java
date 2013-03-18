package cn.ohyeah.itvgame.protocol;

/**
 * ����
 * @author jackey chen
 * @version 1.0
 *
 */
public interface Constant {
	
	/*Э������*/
	public static final byte SYS_SERV_SYN_TIME = 1;				/*ʱ��ͬ��*/
	public static final byte SYS_SERV_ADD_FAVORITEGD = 2;		/*�㶫����ӵ��ղؼ�*/
	public static final byte SYS_SERV_GOTO_RECHARGE_PAGE = 3;	/*���������������������ֵҳ��*/
	public static final byte SYS_SERV_ONLINE = 4;				/*���������������������������*/
	
	public static final byte RECORD_SAVE = 1;					/*�����¼*/
	public static final byte RECORD_READ = 2;					/*��ȡ��¼*/
	
	public static final byte ATTAINMENT_SAVE = 1;				/*����ɾ�*/
	public static final byte ATTAINMENT_READ = 2;				/*��ȡ�ɾ�*/
	
	public static final byte ATTAINMENT_QUERY_RANKING_LIST = 3;	/*��ѯ�����б�*/
	
	public static final byte SUBSCRIBE_RECHARGE = 1;				/*��ֵ*/
	public static final byte SUBSCRIBE_QUERY_BALANCE = 2;			/*��ѯ���*/
	public static final byte SUBSCRIBE_RECHARGE_WINSIDEGD = 3;		/*winsidegd��ֵ*/
	
	public static final byte PURCHASE_PURCHASE_PROP = 1;			/*�������*/
	public static final byte PURCHASE_EXPEND = 2;					/*���ѽ��*/
	
	public static final byte ACCOUNT_USER_LOGIN = 3;				/*�û���¼*/
	
	
	/*������(Error Code)*/
	public static final byte EC_RECORD_NOT_EXIST = -1;		/*��Ϸ��¼������*/
	public static final byte EC_ATTAINMENT_NOT_EXIST = -2;	/*��Ϸ�ɾͲ�����*/
	
}
