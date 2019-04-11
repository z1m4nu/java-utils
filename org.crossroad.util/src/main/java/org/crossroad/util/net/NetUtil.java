package org.crossroad.util.net;

import java.net.InetSocketAddress;
import java.net.Socket;

public final class NetUtil {

	private NetUtil() {
	}

	/**
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public static boolean portIsOpen(String ip, int port, int timeout) {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(ip, port), timeout);
			socket.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
