package Logico;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int ext = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Cuantas ubicaciones desea agregar?: ");
		int numUbicaciones = scanner.nextInt();
		System.out.println();
		GrafoFunciones grafo = new GrafoFunciones(numUbicaciones); 
		grafo.agregarUbicacion(numUbicaciones);
		grafo.agregarArista(-1);
		grafo.imprimirgrafoMatriz();
		//grafo.menuOpciones(); Ahora se llama directamente de imprimirgrafoMatriz
		return;
	}
	
}
