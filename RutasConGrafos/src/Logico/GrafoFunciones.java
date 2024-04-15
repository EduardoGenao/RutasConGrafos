package Logico;

import java.util.ArrayList;
import java.util.Scanner;

public class GrafoFunciones {
	    private int[][] grafoMatriz = {};
	    private static ArrayList<String> ubicaciones;

	    public GrafoFunciones(int numVertices) {
	        grafoMatriz = new int[numVertices][numVertices];
	        ubicaciones = new ArrayList<String>();
	    }

	    // Sobrecarga del método agregarArista para aceptar caracteres
	    public void agregarUbicacion(int numUbicaciones) {
		    Scanner scanner = new Scanner(System.in);
		    int indice=0;
		    for(int i=0; i<numUbicaciones;i++) {	
		    	
		    	if(!ubicaciones.isEmpty())
		    		 indice = ubicaciones.size();
		    	
		    	
		    	System.out.print("Nombre de la Ubicacion "+ (i+1)+"\n");
		    	String nombreUbicacion = scanner.nextLine(); // Leer el nombre de la ubicación desde la consola
		    	ubicaciones.add(indice, nombreUbicacion);
	    	}
	    	System.out.println("Contenido del ArrayList:");
	        for (String ubicacion : ubicaciones) {
	            System.out.println(ubicacion);
	        }
	        // Convertir caracteres a índices numéricos (A->0, B->1, ...)
	       

	        // Agregar arista en ambos sentidos para un grafo no dirigido
	       /* 
	        *  int indiceOrigen = origen - 'A';
	        * int indiceDestino = destino - 'A';
	        * grafoMatriz[indiceOrigen][indiceDestino] = 1;
	        * grafoMatriz[indiceDestino][indiceOrigen] = 1;
	        */
	        
	    }
	   // public static void 
	    public void agregarArista() {
	    	
	    	int respuesta;
			do {
		    	int indice1 = 0, indice2 = 0;
		    	Scanner scanner = new Scanner(System.in);
		    	System.out.println("Elija las ubicaciones que quiere conectar:");
		    	String ubicacion1 = scanner.nextLine();
		    	System.out.println("Conectarla con:");
		    	String ubicacion2 = scanner.nextLine();
		    	System.out.println("Que peso tendra esta arista:");
		    	int peso = scanner.nextInt();
		    	for (int i = 0; i < grafoMatriz.length; i++) {
					if(ubicaciones.get(i).equalsIgnoreCase(ubicacion1)) {
						indice1=i;
					}
					if(ubicaciones.get(i).equalsIgnoreCase(ubicacion2)) {
						indice2=i;
					}
				}
		    	if(grafoMatriz[indice1][indice2]==0) {
			    	grafoMatriz[indice1][indice2]=peso;
			    	grafoMatriz[indice2][indice1]=peso;
		    	}
		    	else {
		    		System.out.println("Esta ubicacion ya tiene arista\n");
		    		
		    	}
		    	System.out.println("Desea seguir agregando aristas? 1= SI, 2=NO\n");
		    	respuesta = scanner.nextInt();
		    	int ceros=0;
		    	if(respuesta==2) {
		    		for (int i = 0; i < grafoMatriz.length; i++) {
		    			ceros=0;
		    			for (int j = 0; j < grafoMatriz.length; j++) {
							if(grafoMatriz[i][j]==0) {
								ceros++;
							}
						}
		    			if(ceros==ubicaciones.size()) {
		    				System.out.println("Debe unir todas las ubiucaciones para que sea un grafo.");
		    				respuesta=1;
		    				break;
		    			}
					}
		    	}
		    	imprimirgrafoMatriz();

	    	}while(respuesta==1);
		    		
					
				
		    	
	    }
	    public void imprimirgrafoMatriz() {
	    	 // Imprimir la cabecera con las letras de los vértices
	        System.out.print("  ");
	        for (int i = 0; i < grafoMatriz.length; i++) {
	            System.out.print((char) ('A' + i) + " ");
	        }
	        System.out.println();

	        // Imprimir la grafoMatriz con las letras de los vértices como índice
	        for (int i = 0; i < grafoMatriz.length; i++) {
	            // Imprimir la letra del vértice al inicio de cada fila
	            System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < grafoMatriz[i].length; j++) {
	                System.out.print(grafoMatriz[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
}
