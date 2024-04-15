package Logico;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Cuantas ubicaciones desea agregar?");
		int numUbicaciones = scanner.nextInt();
		GrafoFunciones grafo = new GrafoFunciones(numUbicaciones); 
		grafo.agregarUbicacion(numUbicaciones);
		grafo.agregarArista();
	}
	
}
