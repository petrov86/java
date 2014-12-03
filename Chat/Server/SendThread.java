import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SendThread extends Thread {

	private boolean startFlag = true;
	private InetAddress addressToSend = null;
	private byte[] sendData = new byte[1024];
	private DatagramSocket socket;
	private String in = null;
	DatagramPacket sendPacket = null;

	public SendThread(InetAddress ip) {

		addressToSend = ip;
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		BufferedReader input = null;
		
		while (startFlag) {
			input = new BufferedReader(new InputStreamReader(System.in));

			try {
				in = input.readLine();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			sendData = in.getBytes();
			sendPacket = new DatagramPacket(sendData, sendData.length,
					addressToSend, Parameters.PORT_TO_SEND);
			try {
				socket.send(sendPacket);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void stopThread() {
		startFlag = false;
		socket.close();

	}

}
