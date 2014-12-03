class UDPServer {
	public static void main(String args[]) throws Exception {
		ListenThread listen = new ListenThread();
		System.out.println("SERVER STARTED");
		listen.start();
	}
}
