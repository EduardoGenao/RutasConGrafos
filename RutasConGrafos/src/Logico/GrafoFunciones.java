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
		    	
		    	
		    	System.out.print("Nombre de la Ubicacion "+ (char)(i+65) + ": ");
		    	String nombreUbicacion = scanner.nextLine(); // Leer el nombre de la ubicación desde la consola
		    	if(nombreUbicacion.length() == 0) {
		    		nombreUbicacion = ("Ubicacion " + Character.toString(i+65));
		    		System.out.println("La Ubicacion se va a llamar por defecto " + nombreUbicacion);
		    	}
		    	ubicaciones.add(indice, nombreUbicacion);
	    	}
	    	System.out.println("\nLista de Ubicaciones:");
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
				boolean encontrado = false;
		    	int indice1 = 0, indice2 = 0, peso = 0;
		    	Scanner scanner = new Scanner(System.in);
		    	System.out.print("\nConeccion de Ubicaciones:\nElija la primera ubicacion que quiere conectar: ");
		    	while(!encontrado) {
		    		String ubicacion1 = scanner.nextLine();
		    		for (int i = 0; i < grafoMatriz.length; i++) {
						if(ubicaciones.get(i).equalsIgnoreCase(ubicacion1)) {
							indice1=i;
							encontrado = true;
						}
					}
					if(!encontrado)
						System.out.print("Ubicacion no encontrada, digite otra vez: ");
		    	}
		    	encontrado = false;
		    	System.out.print("Conectarla con: ");
		    	while(!encontrado) {
		    		String ubicacion2 = scanner.nextLine();
		    		for (int i = 0; i < grafoMatriz.length; i++) {
						if(ubicaciones.get(i).equalsIgnoreCase(ubicacion2)) {
							indice1=i;
							encontrado = true;
						}
					}
					if(!encontrado)
						System.out.print("Ubicacion no encontrada, digite otra vez: ");
		    	}
		    	peso = 0;
		    	while(peso == 0) {
		    		System.out.print("¿Cual es la distancia entre estas dos ubicaciones (en km)?: ");
		    		peso = scanner.nextInt();
		    		if(peso == 0)
		    			System.out.println("La distancia no puede ser 0");
		    	}
		    	
		    	if(grafoMatriz[indice1][indice2]==0) {
			    	grafoMatriz[indice1][indice2]=peso;
			    	grafoMatriz[indice2][indice1]=peso;
		    	}
		    	else {
		    		System.out.println("Estas ubicaciones ya tienen una coneccion directa");
		    		
		    	}
		    	System.out.println("\nDesea seguir agregando conecciones? 1= SI, 2=NO");
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
		    				System.out.println("Hay ubicaciones que no estan conectadas, conéctelas todas");
		    				respuesta=1;
		    			}
					}
		    	}
		    	
	    	}while(respuesta==1);
			imprimirgrafoMatriz();   	
	    }
	    
	    public void menuOpciones() {
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.println("\nAhora, ¿Que desea hacer?\n1 = Agregar Otra Ubicacion\n2 = Añadir otra coneccion\n3 = Editar Una Ubicacion o sus conecciones"
	    			+ "\n4 = Eliminar una Ubicacion\n5 = Salir del programa");
	    	if(scanner.nextInt() == 5) {
	    		System.out.println("Gracias por usar este programa");
	    		return;
	    	}
	    }
	    
	    public void imprimirgrafoMatriz() {
	    	System.out.println("\nLa lista de ubicaciones quedo así");
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
