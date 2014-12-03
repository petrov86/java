import java.net.*;

class Client {
	public static void main(String args[]) throws Exception {
		ListenThread listen = new ListenThread();
		InetAddress ip = InetAddress.getByName("192.168.42.30");
		SendThread send = new SendThread(ip);
		send.start();
		listen.start();

		

	}
}
