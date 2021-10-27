package runnables;

import java.util.Random;


import teste3.Quadrado;

public class Dormir implements Runnable {
	private Quadrado quadrado;

	public Dormir(Quadrado quadrado) {
		this.quadrado = quadrado;
	}

	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " started");
		try {
			Random r = new Random();
			synchronized (quadrado) {
				Thread.sleep(r.nextLong());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
