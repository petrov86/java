import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.IOException;

public class ListenThread extends Thread {
	boolean startFlag = true;
	DatagramSocket serverSocket;
	DatagramPacket receivePacket;
	private InetAddress IPAddress = null;
	private SendThread send = null;
	byte[] receiveData = new byte[1024];

	@Override
	public void run() {

		try {
			serverSocket = new DatagramSocket(Parameters.PORT_TO_LISTEN);
			System.out.println("Port to listen " + Parameters.PORT_TO_LISTEN);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (startFlag) {
			receivePacket = new DatagramPacket(receiveData, receiveData.length);

			try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String sentence = new String(receivePacket.getData(), 0,
					receivePacket.getLength());

			IPAddress = receivePacket.getAddress();

			System.out.println();
			System.err.println(IPAddress.getHostAddress() + " says: "
					+ sentence);
			receivePacket = null;
			
			if (send == null) {
				send = new SendThread(IPAddress);
				send.start();
			}

		}
	}

	public void stopThread() {
		startFlag = false;
		serverSocket.close();
	}

	public InetAddress getIP() {
		return IPAddress;

	}
}
