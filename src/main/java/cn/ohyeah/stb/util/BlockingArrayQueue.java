package cn.ohyeah.stb.util;

/**
 * �������У����ڹ���������-������ģ��
 * @author maqian
 * @version 1.0
 */
public class BlockingArrayQueue {
	private Object[] queue;
	private short writerIndex;
	private short readerIndex;
	private short size;
	volatile private short len;
	
	public BlockingArrayQueue() {
		this(32);
	}
	
	public BlockingArrayQueue(int size) {
		this.queue = new Object[size];
		this.size = (short)size;
	}
	
	/**
	 * �������ǰ���ж�Ԫ���Ƿ�Ϊnull
	 * @param obj
	 */
	private void checkNull(Object obj) {
		if (obj == null) {
			throw new NullPointerException("����Ԫ�ز���Ϊnull");
		}
	}
	
	/**
	 * ��Ԫ����ӵ�����β��
	 * @param obj
	 */
	private void enqueue(Object obj) {
		queue[writerIndex] = obj;
		if (writerIndex < size-1) {
			++writerIndex;
		}
		else {
			writerIndex = 0;
		}
		++len;
	}
	
	/**
	 * ȡ������ͷ��Ԫ��
	 * @return
	 */
	private Object dequeue() {
		Object obj = queue[readerIndex];
		queue[readerIndex] = null;
		if (readerIndex < size-1) {
			++readerIndex;
		}
		else {
			readerIndex = 0;
		}
		--len;
		return obj;
	}
	
	/**
	 * <pre>
	 * ��ӷǿ�Ԫ�ص�������
	 * �������δ������Ԫ����ӵ�����β��������������
	 * ������������������ȴ���ֱ������δ��
	 * </pre>
	 * @param obj
	 * @throws InterruptedException
	 */
	public void put(Object obj) throws InterruptedException {
		checkNull(obj);
		synchronized (this) {
			while (len >= size) {
				wait();
			}
			enqueue(obj);
			notifyAll();
		}
	}
	
	/**
	 * <pre>
	 * ��ӷǿ�Ԫ�ص�������
	 * �������δ������Ԫ����ӵ�����β��������������true
	 * ���������������������false
	 * </pre>
	 * @param obj
	 * @return
	 */
	public boolean offer(Object obj) {
		checkNull(obj);
		synchronized (this) {
			if (len < size) {
				enqueue(obj);
				notifyAll();
				return true;
			}
			return false;
		}
	}
	
	/**
	 * <pre>
	 * ��ӷǿ�Ԫ�ص�������
	 * �������δ������Ԫ����ӵ�����β��������������true
	 * ������������������ȴ�timout���룬�������δ������Ԫ����ӵ�����β����������true����֮������false
	 * </pre>
	 * @param obj
	 * @param timeout
	 * @return
	 * @throws InterruptedException
	 */
	public boolean offer(Object obj, int timeout) throws InterruptedException {
		checkNull(obj);
		synchronized (this) {
			if (len >= size) {
				wait(timeout);
			}
			return offer(obj);
		}
	}
	
	/**
	 * <pre>
	 * �Ӷ�����ȡ��Ԫ��
	 * ������зǿգ��������ض���ͷ��Ԫ��
	 * �������Ϊ�գ������ȴ���ֱ�����зǿ�
	 * </pre>
	 * @return
	 * @throws InterruptedException
	 */
	public Object take() throws InterruptedException {
		synchronized (this) {
			while (len <= 0) {
				wait();
			}
			Object obj = dequeue();
			notifyAll();
			return obj;
		}
	}
	
	/**
	 * <pre>
	 * �Ӷ�����ȡ��Ԫ��
	 * ������зǿգ��������ض���ͷ��Ԫ��
	 * �������Ϊ�գ���������null
	 * </pre>
	 * @return
	 */
	public Object poll() {
		synchronized (this) {
			if (len > 0) {
				Object obj = dequeue();
				notifyAll();
				return obj;
			}
			return null;
		}
	}
	
	/**
	 * <pre>
	 * �Ӷ�����ȡ��Ԫ��
	 * ������зǿգ��������ض���ͷ��Ԫ��
	 * �������Ϊ�գ������ȴ�timout���룬������зǿգ��򷵻ض���ͷ��Ԫ�أ���֮������null
	 * </pre>
	 * @param timeout
	 * @return
	 * @throws InterruptedException
	 */
	public Object poll(int timeout) throws InterruptedException {
		synchronized (this) {
			if (len <= 0) {
				wait(timeout);
			}
			return poll();
		}
	}
	
	/**
	 * ��ն���
	 */
	public void clear() {
		synchronized (this) {
			for (int i = 0; i < queue.length; ++i) {
				queue[i] = null;
			}
			writerIndex = 0;
			readerIndex = 0;
			len = 0;
		}
	}
	
	/**
	 * ������Ԫ�ظ���
	 * @return
	 */
	public int length() {
		return len;
	}
	
	/**
	 * �����п�λ����
	 * @return
	 */
	public int available() {
		return size-len;
	}
	
}
