package runnables;

import teste3.Quadrado;

public class NotifierAll implements Runnable {
	private Quadrado quadrado;

	public NotifierAll(Quadrado quadrado) {
		this.quadrado = quadrado;
	}

	public void run() {

		try {
			Thread.sleep(500);
			synchronized (quadrado) {
				String name = Thread.currentThread().getName();
				System.out.println(name + " started");
				// quadrado.notify();
				quadrado.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
