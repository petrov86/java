import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.IOException;

public class ListenThread extends Thread {
	boolean startFlag = true;
	DatagramSocket socket;
	DatagramPacket receivePacket;
	private InetAddress IPAddress = null;
	byte[] receiveData = new byte[1024];

	@Override
	public void run() {
		try {
			socket = new DatagramSocket(Parameters.PORT_TO_LISTEN);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (startFlag) {

			receivePacket = new DatagramPacket(receiveData, receiveData.length);

			try {
				socket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String sentence = new String(receivePacket.getData(), 0,
					receivePacket.getLength());

			IPAddress = receivePacket.getAddress();

			System.err.println(IPAddress.getHostAddress() + ": " + sentence);
			receivePacket = null;

		}
	}
	
	public void stopThread() {
		startFlag = false;
		socket.close();
	}

	public InetAddress getIP() {
		return IPAddress;

	}

}
