package servidorCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket servidor = null;
		Socket conexao = null;
		BufferedReader entrada = null;
		try {
			servidor = new ServerSocket(7000);
			conexao = servidor.accept();
			
			entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

			String texto = entrada.readLine();
			ThreadFor tdf= new ThreadFor();
			tdf.start();
			
			texto = entrada.readLine();
			ThreadPrint printa=new ThreadPrint(texto);
			
			printa.start();
			try {
				tdf.join();
				printa.join();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
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
			System.out.println("Rodando o for:"+x);
			Thread.currentThread();
			Thread.yield();
		}
	}
}
class ThreadPrint extends Thread {
	String str;
	public ThreadPrint(String str) {
		this.str=str;
	}
	@Override
	public void run() {
		Thread.currentThread();
		System.out.println("ThreadCriada com o texto:"+str);
	}
}