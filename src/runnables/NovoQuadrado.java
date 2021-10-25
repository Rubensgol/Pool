package runnables;

import java.util.Random;

import teste3.Quadrado;

public class NovoQuadrado implements Runnable {
	private Quadrado quadrado;

	public NovoQuadrado(Quadrado quadrado) {
		this.quadrado = quadrado;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " nova");
		try {
			Random r = new Random();

			synchronized (quadrado) {
				for (int i = 0; i < r.nextInt(10); i++) {
					Thread.sleep(1000);
					System.out.println(name + " Posicao atual quadrado: " + quadrado.andar(r.nextInt(4)+1) + " Quadrado: " + name);
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
