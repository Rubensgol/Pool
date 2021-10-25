package teste3;

public class Quadrado {
	private int[][] mapa;
	private int lado;
	private int[][] posicao;

	public Quadrado(int lado) {
		this.lado = lado;
		this.mapa = new int[5 * lado][5 * lado];
		int pos = 0;
		this.posicao = new int[lado * lado][2];
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa.length; j++) {
				if (i < lado && j < lado) {
					posicao[pos][0] = i;
					posicao[pos][1] = j;
					pos++;
					mapa[i][j] = 1;
				} else
					mapa[i][j] = 0;
			}
		}

	}

	public int[][] getMapa() {
		return mapa;
	}

	public void setMapa(int[][] mapa) {
		this.mapa = mapa;
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	synchronized public String andar(int onde) {
		switch (onde) {
		// cima
		case 1:
			if (posicao[0][0] > 0) {
				for (int i = 0; i < posicao.length; i++) {
					posicao[i][0] -= 1;
				}

			}
			break;

		// baixo
		case 2:
			if (posicao[(lado * lado) - 1][0] < mapa.length) {
				for (int i = 0; i < posicao.length; i++) {
					posicao[i][0] += 1;
				}

			}
			break;
		// direita
		case 3:
			if (posicao[(lado * lado) - 1][1] < mapa.length) {
				for (int i = 0; i < posicao.length; i++) {
					posicao[i][1] += 1;
				}

			}
			break;
		// esquerda
		case 4:
			if (posicao[0][1] > 0) {
				for (int i = 0; i < posicao.length; i++) {
					posicao[i][1] -= 1;
				}

			}
			break;
		default:
			return "";

		}
		return "Andou para" + posicao();
	}

	public String posicao() {
		String posicaoAtual = "";
		int[][] pos = getPosicao();
		for (int j = 0; j < (lado * lado); j++) {
			posicaoAtual += " X:" + pos[j][0] + " J:" + pos[j][1];
			posicaoAtual += "\n";
		}
		return posicaoAtual;
	}

	public int[][] getPosicao() {
		return posicao;
	}

	public void setPosicao(int[][] posicao) {
		this.posicao = posicao;
	}

}
