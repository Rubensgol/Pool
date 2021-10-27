package servidorCliente;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import runnables.Notifier;
import runnables.NotifierAll;
import runnables.NovoQuadrado;
import runnables.Waiter;
import teste3.Quadrado;

public class ServidorComando implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;

	public ServidorComando(Socket clientSocket, String serverText) {
		this.clientSocket = clientSocket;
		this.serverText = serverText;
	}

	public void run() {
		Quadrado q = null;
		Random r = new Random();
		ExecutorService se = Executors.newFixedThreadPool(4);
		BufferedReader entrada = null;
		try {
			int op;
			entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			do {
				op = Integer.parseInt(entrada.readLine());
				if (op == 1) {
					q = new Quadrado(r.nextInt(5) + 1);
					se.execute(new NovoQuadrado(q));

				} else if (op == 2) {
					se.execute(new Waiter(q));

				} else if (op == 3) {
					se.execute(new NotifierAll(q));

				} else if (op == 4) {
					se.execute(new NovoQuadrado(q));
 
				} else if (op == 5) {
					se.execute(new Notifier(q));
				} else
					break;
			} while (op < 10);
		} catch (IOException e) {
			System.out.println("Algo errado aconteceu");
		}
		System.out.println("Servidor finalizado");
	}
}