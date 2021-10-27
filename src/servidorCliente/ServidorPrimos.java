package servidorCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServidorPrimos implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;

	public ServidorPrimos(Socket clientSocket, String serverText) {
		this.clientSocket = clientSocket;
		this.serverText = serverText;
	}

	@Override
	public void run() {
		Socket conexao = clientSocket;
		BufferedReader entrada = null;
		try {

			entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			int tamanho = Integer.parseInt(entrada.readLine());
			int nprimo = 0;
			int primo = 2;
			
			while (nprimo < tamanho) {

				int aux = 0;
				for (int i = 2; i < primo; i++) {
					if (primo % i == 0) {
						aux++;
					}
					if (aux >= 2)
						break;
				}
				if (!(aux >= 2)) {
					nprimo++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(primo);
				}
			
				primo++;
			}
		} catch (IOException e) {
			System.out.println("Algo errado aconteceu");
		} finally {
			try {
				conexao.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
