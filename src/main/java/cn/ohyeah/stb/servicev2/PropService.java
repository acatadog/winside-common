package cn.ohyeah.stb.servicev2;

import cn.ohyeah.stb.buf.ByteBuffer;
import cn.ohyeah.stb.protocolv2.Constant;
import cn.ohyeah.stb.protocolv2.HeadWrapper;

/**
 * ���߷�����
 * 
 * @author maqian
 * @version 2.0
 */
public class PropService extends AbstractService {

	public PropService(String server, int connType) {
		super(server, connType);
	}

	/**
	 * <pre>
	 * ��ѯ��Ϸ�����б�
	 * asynResult.getResult() ==> Prop[]
	 * </pre>
	 * 
	 * @param productId
	 * @return
	 * @throws InterruptedException
	 */
	public final AsynResult queryPropList(int productId)
			throws InterruptedException {
		try {
			HeadWrapper head = buildHead(Constant.PROTOCOL_VERSION,
					Constant.PROTOCOL_TAG_PROP,
					Constant.PROP_CMD_QUERY_PROP_LIST);
			ByteBuffer req = new ByteBuffer(16);
			writeHead(req, head);
			req.writeInt(productId);

			return asynRequest(req, head);
		} catch (RuntimeException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * <pre>
	 * ��ѯ���ӵ�еĵ����б�
	 * asynResult.getResult() ==> OwnProp[]
	 * </pre>
	 * 
	 * @param accountId
	 * @param productId
	 * @return
	 * @throws InterruptedException
	 */
	public final AsynResult queryOwnPropList(
			int accountId, int productId) throws InterruptedException {
		try {
			HeadWrapper head = buildHead(Constant.PROTOCOL_VERSION,
					Constant.PROTOCOL_TAG_PROP,
					Constant.PROP_CMD_QUERY_OWN_PROP_LIST);
			ByteBuffer req = new ByteBuffer(16);
			writeHead(req, head);
			req.writeInt(accountId);
			req.writeInt(productId);

			return asynRequest(req, head);
		} catch (RuntimeException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * <pre>
	 * ʹ�õ���
	 * asynResult.getResult() ==> null
	 * </pre>
	 * 
	 * @param accountId
	 * @param productId
	 * @param propIds
	 * @param nums
	 * @return
	 * @throws InterruptedException
	 */
	public final AsynResult useProps(int accountId,
			int productId, int[] propIds, int[] nums)
			throws InterruptedException {
		if (propIds.length == 0 || nums.length == 0
				|| propIds.length != nums.length) {
			throw new ServiceException("propIds����nums���ȴ���");
		}
		try {
			HeadWrapper head = buildHead(Constant.PROTOCOL_VERSION,
					Constant.PROTOCOL_TAG_PROP, Constant.PROP_CMD_USE_PROPS);
			int bufLen = 32 + (propIds.length << 3);
			ByteBuffer req = new ByteBuffer(bufLen);
			writeHead(req, head);
			req.writeInt(accountId);
			req.writeInt(productId);
			req.writeShort((short) propIds.length);
			for (int i = 0; i < propIds.length; ++i) {
				req.writeInt(propIds[i]);
				req.writeInt(nums[i]);
			}

			return asynRequest(req, head);
		} catch (RuntimeException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * <pre>
	 * ͬ������
	 * asynResult.getResult() ==> null
	 * </pre>
	 * 
	 * @param accountId
	 * @param productId
	 * @param propIds
	 * @param counts
	 * @return
	 * @throws InterruptedException
	 */
	public final AsynResult synProps(int accountId,
			int productId, int[] propIds, int[] counts)
			throws InterruptedException {
		if (propIds.length == 0 || counts.length == 0
				|| propIds.length != counts.length) {
			throw new ServiceException("propIds����counts���ȴ���");
		}
		try {
			HeadWrapper head = buildHead(Constant.PROTOCOL_VERSION,
					Constant.PROTOCOL_TAG_PROP, Constant.PROP_CMD_SYN_PROPS);
			int bufLen = 32 + (propIds.length << 3);
			ByteBuffer req = new ByteBuffer(bufLen);
			writeHead(req, head);
			req.writeInt(accountId);
			req.writeInt(productId);
			req.writeShort((short) propIds.length);
			for (int i = 0; i < propIds.length; ++i) {
				req.writeInt(propIds[i]);
				req.writeInt(counts[i]);
			}

			return asynRequest(req, head);
		} catch (RuntimeException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
