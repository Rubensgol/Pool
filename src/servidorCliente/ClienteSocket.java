package servidorCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocket {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		int tamanho = 0;
		Socket cliente = null;
		PrintStream saida = null;
		try {
			cliente = new Socket("127.0.0.1", 7000);
			saida = new PrintStream(cliente.getOutputStream());
			do {
				System.out.println("Digite 1-Iniciar uma thread nova 2-Pausar 3-Voltar 4-Voltar tudo 5-espera time");
				tamanho = entrada.nextInt();
				saida.println(tamanho);
			} while (tamanho < 10);

		} catch (IOException e) {
			System.out.println("Algo deu errado");
		} finally {
			cliente.close();
		}

	}

}
