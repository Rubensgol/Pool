package servidorCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientePrimos {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		int tamanho = 0;
		Socket cliente = null;
		PrintStream saida = null;
		try {
			cliente = new Socket("localhost", 8080);
			saida = new PrintStream(cliente.getOutputStream());
			do {
				System.out.println("Digite a quantitade de numero primo que deseja encontrar");
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
