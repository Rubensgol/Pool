package servidorCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import runnables.Notifier;
import runnables.NotifierAll;
import runnables.NovoQuadrado;
import runnables.Waiter;
import teste3.Quadrado;

public class ServidorSocket {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket servidor = null;
		Socket conexao = null;
		BufferedReader entrada = null;
		Quadrado q = null;
		Random r = new Random();
		ExecutorService se = Executors.newFixedThreadPool(4);
		try {
			servidor = new ServerSocket(7000);
			conexao = servidor.accept();
			int op;
			entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
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

			System.out.println("Servidor finalizado");

		} catch (IOException e) {
			System.out.println("Algo errado aconteceu");
		} finally {
			conexao.close();
			servidor.close();
		}

	}
}

class ThreadFor extends Thread {
	@Override
	public void run() {
		for (int x = 0; x < 100; x++) {
			System.out.println("Rodando o for:" + x);
			Thread.currentThread();
			Thread.yield();
		}
	}
}

class ThreadPrint extends Thread {
	String str;

	public ThreadPrint(String str) {
		this.str = str;
	}

	@Override
	public void run() {
		Thread.currentThread();
		System.out.println("ThreadCriada com o texto:" + str);
	}
}