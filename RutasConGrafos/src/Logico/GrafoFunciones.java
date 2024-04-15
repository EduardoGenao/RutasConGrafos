package Logico;

import java.util.ArrayList;
import java.util.Scanner;

public class GrafoFunciones {
	    private Arista grafoMatriz;
	    private static ArrayList<String> ubicaciones;

	    public GrafoFunciones(int numVertices) {
	        grafoMatriz = new Arista(numVertices);
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
		    		nombreUbicacion = ("Ubicacion " + (char)(i+'A'));
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
		    	int indice1 = 0, indice2 = 0, peso = 0,tiempo = 0;
		    	Scanner scanner = new Scanner(System.in);
		    	System.out.print("\nConeccion de Ubicaciones:\nElija la primera ubicacion que quiere conectar: ");
		    	while(!encontrado) {
		    		String ubicacion1 = scanner.nextLine();
		    		for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
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
		    		for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
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
		    	while(tiempo == 0) {
		    		System.out.print("¿Cual es el tiempo entre estas dos ubicaciones (en minutos)?: ");
		    		tiempo = scanner.nextInt();
		    		if(tiempo == 0)
		    			System.out.println("El tiempo no puede ser 0");
		    	}
		    	
		    	if(grafoMatriz.getPeso()[indice1][indice2]==0) {
			    	grafoMatriz.getPeso()[indice1][indice2]=peso;
			    	grafoMatriz.getPeso()[indice2][indice1]=peso;
			    	grafoMatriz.getTiempo()[indice1][indice2]=tiempo;
			    	grafoMatriz.getTiempo()[indice2][indice1]=tiempo;
			    	
		    	}
		    	else {
		    		System.out.println("Estas ubicaciones ya tienen una coneccion directa");
		    		
		    	}
		    	System.out.println("\nDesea seguir agregando conecciones? 1= SI, 2=NO");
		    	respuesta = scanner.nextInt();
		    	int ceros=0;
		    	if(respuesta==2) {
		    		for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
		    			ceros=0;
		    			for (int j = 0; j < grafoMatriz.getPeso().length; j++) {
							if(grafoMatriz.getPeso()[i][j]==0) {
								ceros++;
							}
						}
		    			if(ceros==ubicaciones.size()) {
		    				System.out.println("Hay ubicaciones que no estan conectadas, conéctelas todas");
		    				respuesta=1;
		    			}
					}
		    	}
		    imprimirgrafoMatriz();  	
	    	}while(respuesta==1);
			 	
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
	    	System.out.println("\nLa lista de ubicaciones quedo así (Peso)");
	    	 // Imprimir la cabecera con las letras de los vértices
	        System.out.print("  ");
	        for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
	            System.out.print((char) ('A' + i) + " ");
	        }
	        System.out.println();

	        // Imprimir la grafoMatriz con las letras de los vértices como índice
	        for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
	            // Imprimir la letra del vértice al inicio de cada fila
	            System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < grafoMatriz.getPeso()[i].length; j++) {
	                System.out.print(grafoMatriz.getPeso()[i][j] + " ");
	            }
	            System.out.println();
	        }
	        System.out.println("\nLa lista de ubicaciones quedo así (Tiempo)");
	    	 // Imprimir la cabecera con las letras de los vértices
	        System.out.print("  ");
	        for (int i = 0; i < grafoMatriz.getTiempo().length; i++) {
	            System.out.print((char) ('A' + i) + " ");
	        }
	        System.out.println();

	        // Imprimir la grafoMatriz con las letras de los vértices como índice
	        for (int i = 0; i < grafoMatriz.getTiempo().length; i++) {
	            // Imprimir la letra del vértice al inicio de cada fila
	            System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < grafoMatriz.getTiempo()[i].length; j++) {
	                System.out.print(grafoMatriz.getTiempo()[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
}
