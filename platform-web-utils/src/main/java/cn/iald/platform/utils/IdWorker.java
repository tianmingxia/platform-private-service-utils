package cn.iald.platform.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: id工具类
 * @author: admin
 **/
@Slf4j
public class IdWorker {

	private static IdWorker idWorker;

	static {
		idWorker = new IdWorker(getWorkId(), getDataCenterId(), 0);
	}


	/**
	 * 工作机器ID(0~31)
	 */
	private long workerId;

	/**
	 * 数据中心ID(0~31)
	 */
	private long datacenterId;

	/**
	 * 毫秒内序列(0~4095)
	 */
	private long sequence;

	/**
	 * 开始时间截 2020-11-05 09:00:00
	 * 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
	 */
	private long twepoch = 1604538000000L;

	/**
	 * 机器id所占的位数
	 */
	private long workerIdBits = 5L;

	/**
	 * 数据标识id所占的位数
	 */
	private long datacenterIdBits = 5L;

	/**
	 * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
	 */
	private long maxWorkerId = -1L ^ (-1L << workerIdBits);

	/**
	 * 支持的最大数据标识id，结果是31
	 */
	private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

	/**
	 * 序列在id中占的位数
	 */
	private long sequenceBits = 12L;

	/**
	 * 机器ID向左移12位
	 */
	private long workerIdShift = sequenceBits;

	/**
	 * 数据标识id向左移17位(12+5)
	 */
	private long datacenterIdShift = sequenceBits + workerIdBits;

	/**
	 * 时间截向左移22位(5+5+12)
	 */
	private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

	/**
	 * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
	 */
	private long sequenceMask = -1L ^ (-1L << sequenceBits);

	/**
	 * 上次生成ID的时间截
	 */
	private long lastTimestamp = -1L;

	/**
	 * @param workerId     工作ID (0~31)
	 * @param datacenterId 数据中心ID (0~31)
	 * @param sequence     毫秒内序列(0~4095)
	 */
	public IdWorker(long workerId, long datacenterId, long sequence) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		log.info(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
				timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
		log.info("workerId:" + workerId + ",datacenterId:" + datacenterId + ",sequence:" + sequence);
		this.workerId = workerId;
		this.datacenterId = datacenterId;
		this.sequence = sequence;
	}

	/**
	 * 获得下一个ID (该方法是线程安全的)
	 *
	 * @return
	 */
	public synchronized long nextId() {
		long timestamp = timeGen();

		if (timestamp < lastTimestamp) {
			log.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
			throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
					lastTimestamp - timestamp));
		}

		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}

		lastTimestamp = timestamp;
		return ((timestamp - twepoch) << timestampLeftShift) |
				(datacenterId << datacenterIdShift) |
				(workerId << workerIdShift) |
				sequence;
	}

	/**
	 * 阻塞到下一个毫秒，直到获得新的时间戳
	 *
	 * @param lastTimestamp lastTimestamp 上次生成ID的时间截
	 * @return 当前时间戳
	 */
	private long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 * 返回以毫秒为单位的当前时间
	 *
	 * @return
	 */
	private long timeGen() {
		return System.currentTimeMillis();
	}

	/**
	 * 基于服务器IP获取工作机器ID
	 *
	 * @return
	 */
	public static Long getWorkId() {
		String hostAddress = NetUtil.getLocalhostStr();
		if (StrUtil.isNotBlank(hostAddress)) {
			int[] ints = StringUtils.toCodePoints(hostAddress);
			int sums = 0;
			for (int b : ints) {
				sums += b;
			}
			return (long) (sums % 32);
		}
		// 如果获取失败，则使用随机数备用
		return RandomUtil.randomLong(0, 31);
	}

	/**
	 * 基于服务器名称获取数据标识id部分
	 *
	 * @return
	 */
	public static Long getDataCenterId() {
		int[] ints = StringUtils.toCodePoints(NetUtil.getLocalHostName());
		int sums = 0;
		for (int i : ints) {
			sums += i;
		}
		return (long) (sums % 32);
	}

	/**
	 * 获取id
	 *
	 * @return
	 */
	public static Long getLongId() {
		long id = idWorker.nextId();
		return id;
	}
}
