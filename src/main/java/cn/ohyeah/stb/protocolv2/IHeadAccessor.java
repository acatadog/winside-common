package cn.ohyeah.stb.protocolv2;

/**
 * Э��ͷ������
 * @author maqian
 * @version 1.0
 */
public interface IHeadAccessor {

	/**
	 * <pre>
	 * Э��ͷ(���ݳ���32λ)
	 * </pre>
	 * @return
	 */
	public abstract int getHead();
	public abstract void setHead(int head);
	
	/**
	 * <pre>
	 * Э��汾��
	 * </pre>
	 * @return
	 */
	public abstract int getVersion();
	public abstract void setVersion(int version);
	
	/**
	 * <pre>
	 * ��ԭ���ݻ��߼���ǰ�����ݽ�����䣨AES������16�ֽڶ��룩��
	 * 0:�����
	 * 1:16�ֽ����
	 * </pre>
	 * @return
	 */
	public abstract int getPadding();
	public abstract void setPadding(int padding);
	
	/**
	 * <pre>
	 * ���������ݱ������ص������Ƿ񾭹��ֿ鷢�͡�
	 * 0:�����ݱ���δ���ֿ�
	 * 1:�ֿ飬��һ�����ݱ�
	 * 2:�ֿ飬�������ݱ�
	 * 3:δ����
	 * </pre>
	 * @return
	 */
	public abstract int getSplit();
	public abstract void setSplit(int split);
	
	/**
	 * <pre>
	 * ��ʶ�����ݱ��Ƿ񾭹����ܡ����ܷ�����ϵͳ����ָ�������ݱ���ʹ�õļ��ܷ�����Э�鱾��ȷ����
	 * 0:�Ǽ���
	 * 1:����
	 * </pre>
	 * @return
	 */
	public abstract int getCrypt();
	public abstract void setCrypt(int crypt);
	
	/**
	 * <pre>
	 * ��ʾ�����ݱ��Ƿ񾭹�ѹ��
	 * 0:��ʾδѹ��
	 * 1:��ʾѹ��
	 * </pre>
	 * @return
	 */
	public abstract int getCompress();
	public abstract void setCompress(int compress);
	
	/**
	 * <pre>
	 * 1��ʾ���յ�������ʱӦ�÷���Ӧ��(UDP��Ч)
	 * </pre>
	 * @return
	 */
	public abstract int getAck();
	public abstract void setAck(int ack);
	
	/**
	 * <pre>
	 * 1��ʾЭ��Ҫ�󷵻ز�����ΪӦ���
	 * </pre>
	 * @return
	 */
	public abstract int getAckparam();
	public abstract void setAckparam(int ackParam);
	
	/**
	 * <pre>
	 * ������Ự����ʱ��ʾ�Ự����
	 * ������������Ϊý������ʱ��ʾ����֡����
	 * </pre>
	 * @return
	 */
	public abstract int getType();
	public abstract void setType(int type);
	
	/**
	 * <pre>
	 * ����Э���ʶ
	 * </pre>
	 * @return
	 */
	public abstract int getTag();
	public abstract void setTag(int tag);
	
	/**
	 * <pre>
	 * ����Э������
	 * </pre>
	 * @return
	 */
	public abstract int getCommand();
	public abstract void setCommand(int command);
	
	
	/**
	 * �û����ݣ�8λ��
	 * @return
	 */
	public abstract int getUserdata();
	public abstract void setUserdata(int userData);

	

	

	

	

	

	

	

	
	
	

	

}