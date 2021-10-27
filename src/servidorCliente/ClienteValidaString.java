package servidorCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteValidaString {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		String palavra;
		Socket cliente = null;
		PrintStream saida = null;
		try {
			cliente = new Socket("localhost", 7500);
			saida = new PrintStream(cliente.getOutputStream());
			do {
				System.out.println("Digite a string que desja validar");
				palavra = entrada.nextLine();
				saida.println(palavra);
			} while (!palavra.equals("sair"));

		} catch (IOException e) {
			System.out.println("Algo deu errado");
		} finally {
			cliente.close();
		}

	}
}
