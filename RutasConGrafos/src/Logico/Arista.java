package Logico;

public class Arista {
	private int tiempo[][];
	private int peso[][];
	
	public Arista(int numVertices) {
		setTiempo(new int[numVertices][numVertices]);
		setPeso(new int[numVertices][numVertices]);
	}

	public int[][] getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo[][]) {
		this.tiempo = tiempo;
	}

	public int[][] getPeso() {
		return peso;
	}

	public void setPeso(int peso[][]) {
		this.peso = peso;
	}



}
