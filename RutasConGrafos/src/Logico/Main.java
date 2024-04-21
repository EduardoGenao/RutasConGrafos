package Logico;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int ext = 0;
		Scanner scanner = new Scanner(System.in);
		boolean ddp = GrafoFunciones.dataDumping();
		if(ddp == true) {
			GrafoFunciones grafo = new GrafoFunciones(8);
			grafo.agregarUbicaciones(8, ddp);
			grafo.dumpingAristas();
			grafo.imprimirgrafoMatriz();
		}
		else {
			System.out.print("\nCuantas ubicaciones desea agregar?: ");
			int numUbicaciones = scanner.nextInt();
			System.out.println();
			GrafoFunciones grafo = new GrafoFunciones(numUbicaciones); 
			grafo.agregarUbicaciones(numUbicaciones, ddp);
			grafo.agregarArista(-1);
			grafo.imprimirgrafoMatriz();
		}
		
		//grafo.menuOpciones(); Ahora se llama directamente de imprimirgrafoMatriz
		return;
	}
	
}
