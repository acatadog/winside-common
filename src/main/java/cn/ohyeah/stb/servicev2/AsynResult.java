package cn.ohyeah.stb.servicev2;

import cn.ohyeah.stb.asyn.AsynResponse;
import cn.ohyeah.stb.buf.ByteBuffer;
import cn.ohyeah.stb.protocolv2.HeadWrapper;

/**
 * <pre>
 * �첽��������, ���̰߳�ȫ
 * V2���service�����첽���, �첽�Ľ������������Ч.
 * (1)ʹ�÷�������ʽ��ȡ���,��Ҫ���жϽ���Ƿ���Ч,������������:
 * AsynResult asynResult = SystemService.getSystemTime();
 * if (asynResult.isComplete()) {
 *     if (asynResult.isSuccessful()) {
 *         //�����Ч,���Ҵ���ɹ�
 *         //�ɹ���Ĵ���...
 *         java.util.Date now = (java.util.Date)asynResult.getResult();
 *     }
 *     else {
 *         //�����Ч,���Ǵ���ʧ��
 *         //ʧ�ܺ�Ĵ���...
 *     }
 * }
 * else {
 *     //�����Ȼ��Ч
 *     //����ȥ����������...
 * }
 * 
 * (2)ʹ��������ʽ��ȡ���
 * AsynResult asynResult = SystemService.getSystemTime();
 * //����ķ�����������,ֱ�����ؽ��,���߷�������
 * java.util.Date now = (java.util.Date)asynResult.get();
 * 
 * </pre>
 * @author maqian
 * @version 1.0
 */
public class AsynResult {
	private static final String RESULT_NOT_REALIZE = "result not realized";
	
	private HeadWrapper head;
	private int errorCode;
	private String errorMessage;
	private AsynResponse asynResponse;
	private FrameDecoder decoder;
	private Object result;
	
	public AsynResult(AsynResponse rsp, FrameDecoder decoder, HeadWrapper reqHead) {
		this.asynResponse = rsp;
		this.decoder = decoder;
		this.head = reqHead;
		this.errorCode = -1;
	}
	
	private void checkComplete() {
		if (!isComplete()) {
			throw new RuntimeException(RESULT_NOT_REALIZE);
		}
	}
	
	/**
	 * �����Ƿ�ɹ�,isComplete��������true��,�˽����������
	 * @return
	 */
	public boolean isSuccessful() {
		checkComplete();
		return errorCode==0;
	}
	
	/**
	 * ���������Ϣ,isComplete��������true��,�˽����������
	 * @return
	 */
	public String getErrorMessage() {
		checkComplete();
		return errorMessage;
	}

	/**
	 * �жϽ���Ƿ��Ѿ���Ч
	 * @return
	 */
	public boolean isComplete() {
		if (asynResponse != null) {
			return asynResponse.isComplete();
		}
		return true;
	}
	
	/**
	 * ������ɰٷֱ�
	 * ���������,ָ��ȡ�������ݳ���ռ�����ܳ��ȵİٷֱ�
	 * @return
	 */
	public int getPercent() {
		if (asynResponse != null) {
			return asynResponse.getPercent();
		}
		return 100;
	}
	
	/**
	 * ������Ӧ��Ϣͷ���Ƿ������һ��
	 * @param rsp
	 */
	private void checkHead(ByteBuffer rsp) {
	    HeadWrapper rspHead = new HeadWrapper(rsp.readInt());
	    if (rspHead.getVersion() != head.getVersion()) {
	    	throw new ServiceException("Э��汾��һ��");
	    }
	    if (rspHead.getTag() != head.getTag()) {
	    	throw new ServiceException("Э���ʶ��һ��");
	    }
	    if (rspHead.getCommand() != head.getCommand()) {
	    	throw new ServiceException("Э�����һ��");
	    }
	    head = rspHead;
	}
	
	/**
	 * ��ȡ������
	 * @param rsp
	 * @return
	 */
	private boolean readErrorCode(ByteBuffer rsp) {
		//����len�ֶ�
		rsp.skipReader(4);
		errorCode = rsp.readInt();
		if (errorCode != 0) {
			errorMessage = rsp.readUTF();
		}
		return errorCode == 0;
	}
	
	/**
	 * ������Ӧ����
	 * @param data
	 * @return
	 */
	private Object decodeResult(ByteBuffer data) {
		if (data != null) {
			checkHead(data);
			if (readErrorCode(data)) {
				return decoder.decode(data, head);
			}
		}
		return null;
	}
	
	/**
	 * ֱ�ӻ�ȡ�첽������,isComplete��������true��,�˽����������
	 * @return
	 */
	public Object getResult() {
		checkComplete();
		if (asynResponse != null) {
			result = decodeResult((ByteBuffer)asynResponse.getData());
			asynResponse = null;
		}
		return result;
	}
	
	/**
	 * ������ʽ��ȡ�첽������
	 * @return
	 * @throws InterruptedException 
	 */
	public Object get() throws InterruptedException {
		if (asynResponse != null) {
			result = decodeResult((ByteBuffer)asynResponse.get());
			asynResponse = null;
		}
		return result;
	}
}
