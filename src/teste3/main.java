package teste3;

public class main {

	public static void main(String[] args) {
		PoolServer server;
		while (true) {
			server = new PoolServer(9000);
			new Thread(server).start();

			try {
				Thread.sleep(20 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("Stopping Server");
		server.stop();

	}

}
