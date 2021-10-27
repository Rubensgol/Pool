package servidorCliente;

public class BufferImple implements Buffer {
	private final int[] buffer = { -1, -1, -1, -1, -1 }; // buffer compartilhado
	private String nome;
	private int occupiedCells = 0; // conta número de buffers utilizados
	private int writeIndex = 0; // índice do próximo elemento em que gravar
	private int readIndex = 0; // índice do próximo elemento a ler

	// coloca o valor no buffer
	public synchronized void blockingPut(int value) throws InterruptedException {
		while (occupiedCells == buffer.length) {
			System.out.printf("Buffer is full. Producer waits.%n");
			wait();
		}

		buffer[writeIndex] = value; // configura novo valor de buffer

		writeIndex = (writeIndex + 1) % buffer.length;

		++occupiedCells; // mais uma célula do buffer está cheia
		displayState("Producer writes " + value);
		notifyAll(); // notifica threads que estão esperando para ler a partir do buffer
	}

	// retorna valor do buffer
	public synchronized int blockingGet() throws InterruptedException {
		while (occupiedCells == 0) {
			System.out.printf("Buffer is empty. Consumer waits.%n");
			wait(); // espera até que uma célula do buffer seja preenchida
		} // fim do while

		int readValue = buffer[readIndex]; // lê valor do buffer

		// atualiza índice de leitura circular
		readIndex = (readIndex + 1) % buffer.length;

		--occupiedCells; // um número menor de células do buffer é ocupado
		displayState("Consumer reads " + readValue);
		notifyAll(); // notifica threads que estão esperando para gravar no buffer

		return readValue;
	}

	// exibe a operação atual e o estado de buffer
	public synchronized void displayState(String operation) {
		// gera saída de operação e número de células de buffers ocupadas
		System.out.printf("%s%s%d)%n%s", operation, " (buffer cells occupied: ", occupiedCells, "buffer cells: ");

		for (int value : buffer)
			System.out.printf(" %d ", value); // gera a saída dos valores no buffer

		System.out.printf("%n ");

		for (int i = 0; i < buffer.length; i++)
			System.out.print("---- ");

		System.out.printf("%n ");

		for (int i = 0; i < buffer.length; i++) {
			if (i == writeIndex && i == readIndex)
				System.out.print(" WR"); // índice de gravação e leitura
			else if (i == writeIndex)
				System.out.print(" W "); // só grava índice
			else if (i == readIndex)
				System.out.print(" R "); // só lê índice
			else
				System.out.print(" "); // nenhum dos índices
		}

		System.out.printf("%n%n");
	}
} // fim da classe CircularBuffe