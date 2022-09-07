package servidorCliente;

public class BufferImple implements Buffer {
	private final int[] buffer = { -1, -1, -1, -1, -1 }; 
	private String nome;
	private int occupiedCells = 0;
	private int writeIndex = 0; 
	private int readIndex = 0;

	public synchronized void blockingPut(int value) throws InterruptedException {
		while (occupiedCells == buffer.length) {
			System.out.printf("Buffer is full. Producer waits.%n");
			wait();
		}

		buffer[writeIndex] = value;
		writeIndex = (writeIndex + 1) % buffer.length;

		++occupiedCells;
		displayState("Producer writes " + value);
		notifyAll();
	}

	// retorna valor do buffer
	public synchronized int blockingGet() throws InterruptedException {
		while (occupiedCells == 0) {
			System.out.printf("Buffer is empty. Consumer waits.%n");
			wait();
		} 

		int readValue = buffer[readIndex];
		readIndex = (readIndex + 1) % buffer.length;

		--occupiedCells;
		displayState("Consumer reads " + readValue);
		notifyAll(); 

		return readValue;
	}

	public synchronized void displayState(String operation) {
		System.out.printf("%s%s%d)%n%s", operation, " (buffer cells occupied: ", occupiedCells, "buffer cells: ");

		for (int value : buffer)
			System.out.printf(" %d ", value); 

		System.out.printf("%n ");

		for (int i = 0; i < buffer.length; i++)
			System.out.print("---- ");

		System.out.printf("%n ");

		for (int i = 0; i < buffer.length; i++) {
			if (i == writeIndex && i == readIndex)
				System.out.print(" WR"); 
			else if (i == writeIndex)
				System.out.print(" W "); 
			else if (i == readIndex)
				System.out.print(" R "); 
			else
				System.out.print(" ");
		}

		System.out.printf("%n%n");
	}
}