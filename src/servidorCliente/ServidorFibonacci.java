package servidorCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServidorFibonacci implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;

	public ServidorFibonacci(Socket clientSocket, String serverText) {
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
			ExecutorService executorService = Executors.newCachedThreadPool();
			Buffer buffcompartilhado = new BufferImple();
			executorService.execute(new Produtor(buffcompartilhado, tamanho));
			executorService.execute(new Consumidor(buffcompartilhado, tamanho));
			executorService.shutdown();
			executorService.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println(entrada);
		} catch (IOException e) {
			System.out.println("Algo errado aconteceu");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

class Produtor implements Runnable {

	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation;
	private int tamanho;

	public Produtor(Buffer shaBuffer, int tamanho) {
		this.sharedLocation = shaBuffer;
		this.tamanho = tamanho;
	}

	public void run() {
		int f1 = 1;
		int f2 = 1;
		int fn;
		for (int i = 0; i < tamanho; i++) {
			fn = f2 + f1;
			f1 = f2;
			f2 = fn;
			try {

				Thread.sleep(generator.nextInt(3000));
				System.out.println("nao botou");
				sharedLocation.blockingPut(fn);
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			} finally {

			}
		}

	}
}

class Consumidor implements Runnable {
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer shaBuffer;
	private int tamanho;

	public Consumidor(Buffer shaBuffer, int tamanho) {
		this.shaBuffer = shaBuffer;
		this.tamanho = tamanho;
	}

	public void run() {
		int v = 0;
		for (int i = 0; i < tamanho; i++) {
			try {
				Thread.sleep(generator.nextInt(3000));
				v = shaBuffer.blockingGet();
				System.out.println(v);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
