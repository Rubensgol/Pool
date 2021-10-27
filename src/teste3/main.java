package teste3;

public class main {

	public static void main(String[] args) {
		PoolServer server;
		server = new PoolServer(9000);
		new Thread(server).start();
		server = new PoolServer(7000);
		new Thread(server).start();
		server = new PoolServer(7500);
		new Thread(server).start();
		server = new PoolServer(8080);
		new Thread(server).start();
		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
