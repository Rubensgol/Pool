package servidorCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteFibonaci {

	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(System.in);
		int tamanho = 0;
		Socket cliente = null;
		PrintStream saida = null;
		try {
			while (true) {
				cliente = new Socket("localhost", 7000);
				saida = new PrintStream(cliente.getOutputStream());

				System.out.println("A quantidade de numeros da serie");
				tamanho = entrada.nextInt();
				saida.println(tamanho);
			}
		} catch (IOException e) {
			System.out.println("Algo deu errado");
		} finally {
			cliente.close();
		}

	}

}
