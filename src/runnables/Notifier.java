package runnables;

import teste3.Quadrado;

public class Notifier implements Runnable {
	private Quadrado quadrado;

	public Notifier(Quadrado quadrado) {
		this.quadrado = quadrado;
	}
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " started");
		try {
			Thread.sleep(500);
			synchronized (quadrado) {
				quadrado.notify();
				// quadrado.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}