package teste3;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import servidor.ServidorComando;
import servidor.ServidorDNA;
import servidor.ServidorFibonacci;
import servidor.ServidorNomes;
import servidor.ServidorPrimos;
import servidor.ServidorValidaString;

public class PoolServer implements Runnable {
	protected int serverPort = 8080;
	protected ServerSocket serverSocket = null;
	protected boolean isStopped = false;
	protected Thread runningThread = null;
	protected ExecutorService threadPool = Executors.newFixedThreadPool(10);

	public PoolServer(int port) {
		this.serverPort = port;
	}

	public void run() {
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		while (!isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if (isStopped()) {
					System.out.println("Server Stopped.");
					break;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
			if (serverPort == 9000)
				this.threadPool.execute(new ServidorComando(clientSocket, " Server Comandos"));
			else if (serverPort == 7000)
				this.threadPool.execute(new ServidorFibonacci(clientSocket, " Server Fiboonaci"));
			else if (serverPort == 8080)
				this.threadPool.execute(new ServidorPrimos(clientSocket, "Server Primos"));
			else if (serverPort == 7500)
				this.threadPool.execute(new ServidorValidaString(clientSocket, "Server valida string"));
			else if (serverPort == 7700)
				this.threadPool.execute(new ServidorDNA(clientSocket, "Server gera fita"));
			else if (serverPort == 8030)
				this.threadPool.execute(new ServidorNomes(clientSocket, "Server Nomes"));
		}
		this.threadPool.shutdown();
		System.out.println("Server Stopped.");
	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error closing server", e);
		}
	}

	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port " + this.serverPort, e);
		}
	}
}
