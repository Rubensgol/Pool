package servidorCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServidorValidaString implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;

	public ServidorValidaString(Socket clientSocket, String serverText) {
		this.clientSocket = clientSocket;
		this.serverText = serverText;
	}

	@Override
	public void run() {
		BufferedReader entrada = null;
		try {
			entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String valida = "";
			do {
				valida = entrada.readLine();
				if (valida.length() > 0)
					System.out.println("String com mais de 1 carcter");
				if (valida.contains("@"))
					System.out.println("String é um emial");
			} while (!valida.equals("sair"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}