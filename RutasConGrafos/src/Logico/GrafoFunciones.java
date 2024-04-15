package Logico;

import java.util.Scanner;
import java.util.Arrays;

public class GrafoFunciones {

	private int[][] matrizAdyacencia;

		public GrafoFunciones(int numVertices) {
	        matrizAdyacencia = new int[numVertices][numVertices];
	    }

	    public void agregarArista(int origen, int destino) {
	        matrizAdyacencia[origen][destino] = 1;
	        matrizAdyacencia[destino][origen] = 1; 
	    }

	    public void imprimirMatriz() {
	    	System.out.println("MATRIZ DE ADYACENCIA:");
	    	char letter;
	    	System.out.print("   ");
	    	for (int a = 0; a < matrizAdyacencia.length; a++) {
	    		letter = (char)(a+65);
	    		System.out.println(letter + "  ");
	    	}
	    	System.out.print("\n");
	        for (int i = 0; i < matrizAdyacencia.length; i++) {
	        	letter = (char)(i+65);
	           System.out.println((letter) + (" ") + (Arrays.toString(matrizAdyacencia[i])));
	        }
	    }
	
}
