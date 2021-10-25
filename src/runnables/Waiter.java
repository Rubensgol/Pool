package runnables;

import teste3.Quadrado;

public class Waiter implements Runnable {

	private Quadrado quadrado;

	public Waiter(Quadrado quadrado) {
		this.quadrado = quadrado;
	}

	public void run() {
		String name = Thread.currentThread().getName();
		synchronized (quadrado) {
			try {
				System.out.println("Parou");
				quadrado.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + " waiter thread got notified at time:" + System.currentTimeMillis());
			// process the message no
		}
	}
}
