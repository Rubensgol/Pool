package servidorCliente;

public class BufferImple implements Buffer {
	private final int[] buffer = { -1, -1, -1, -1, -1 }; // buffer compartilhado
	private String nome;
	private int occupiedCells = 0; // conta n�mero de buffers utilizados
	private int writeIndex = 0; // �ndice do pr�ximo elemento em que gravar
	private int readIndex = 0; // �ndice do pr�ximo elemento a ler

	// coloca o valor no buffer
	public synchronized void blockingPut(int value) throws InterruptedException {
		while (occupiedCells == buffer.length) {
			System.out.printf("Buffer is full. Producer waits.%n");
			wait();
		}

		buffer[writeIndex] = value; // configura novo valor de buffer

		writeIndex = (writeIndex + 1) % buffer.length;

		++occupiedCells; // mais uma c�lula do buffer est� cheia
		displayState("Producer writes " + value);
		notifyAll(); // notifica threads que est�o esperando para ler a partir do buffer
	}

	// retorna valor do buffer
	public synchronized int blockingGet() throws InterruptedException {
		while (occupiedCells == 0) {
			System.out.printf("Buffer is empty. Consumer waits.%n");
			wait(); // espera at� que uma c�lula do buffer seja preenchida
		} // fim do while

		int readValue = buffer[readIndex]; // l� valor do buffer

		// atualiza �ndice de leitura circular
		readIndex = (readIndex + 1) % buffer.length;

		--occupiedCells; // um n�mero menor de c�lulas do buffer � ocupado
		displayState("Consumer reads " + readValue);
		notifyAll(); // notifica threads que est�o esperando para gravar no buffer

		return readValue;
	}

	// exibe a opera��o atual e o estado de buffer
	public synchronized void displayState(String operation) {
		// gera sa�da de opera��o e n�mero de c�lulas de buffers ocupadas
		System.out.printf("%s%s%d)%n%s", operation, " (buffer cells occupied: ", occupiedCells, "buffer cells: ");

		for (int value : buffer)
			System.out.printf(" %d ", value); // gera a sa�da dos valores no buffer

		System.out.printf("%n ");

		for (int i = 0; i < buffer.length; i++)
			System.out.print("---- ");

		System.out.printf("%n ");

		for (int i = 0; i < buffer.length; i++) {
			if (i == writeIndex && i == readIndex)
				System.out.print(" WR"); // �ndice de grava��o e leitura
			else if (i == writeIndex)
				System.out.print(" W "); // s� grava �ndice
			else if (i == readIndex)
				System.out.print(" R "); // s� l� �ndice
			else
				System.out.print(" "); // nenhum dos �ndices
		}

		System.out.printf("%n%n");
	}
} // fim da classe CircularBuffe