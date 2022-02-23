package cn.iald.platform.utils;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * @description:
 * @author: wangyc
 * @create: 2020-12-17 10:46
 **/
public class UUIDGenerator {
	public UUIDGenerator() {
	}

	private static final int IP;

	public static int iptoInt(byte[] bytes) {
		int result = 0;
		int len = 4;
		for (int i = 0; i < len; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	static {
		int ipadd;
		try {
			ipadd = iptoInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}

	private static short counter = (short) 0;

	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);


	/**
	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	protected int getjvm() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized (UUIDGenerator.class) {
			if (counter < 0) {
				counter = 0;
			}
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	protected int getip() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	private final static String SEP = "";

	public String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	public String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public Serializable generate() {
		return new StringBuffer(36).append(format(getip())).append(SEP).append(
				format(getjvm())).append(SEP).append(format(getHiTime()))
				.append(SEP).append(format(getLoTime())).append(SEP).append(
						format(getCount())).toString();
	}

}

