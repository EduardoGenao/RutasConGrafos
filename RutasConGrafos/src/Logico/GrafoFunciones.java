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
	    
	    public int menuOpciones() {
	    	int ext = 0;
	    	Scanner scanner = new Scanner(System.in);
		    System.out.println("\nAhora, ¿Que desea hacer?\n1 = Agregar Otra Ubicacion\n2 = Editar Una Ubicacion o sus conecciones"
		    		+ "\n3 = Eliminar una Ubicacion\n10 = Salir del programa");
		    if(scanner.nextInt() == 2)
		    	this.editarUbicacion();
		    if(scanner.nextInt() == 10) {
		    	System.out.println("Gracias por usar este programa");
		    	ext = 1;
		    }
		    return ext;
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
	        System.out.println("\nLa lista de Tiempos quedo así (Tiempo)");
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
	    
	    private void editarUbicacion() {
	    	Scanner scanner = new Scanner(System.in);
	    	int ubicEdit = 0;
	    	System.out.println("\nLa lista de ubicaciones es la siguiente:");
	    	for (int i = 0; i < ubicaciones.size(); i++)
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	    	System.out.print("Digite el numero en la lista de la ubicacion a editar: ");
	    	while(ubicEdit == 0 || ubicEdit > ubicaciones.size()) {
	    		ubicEdit = scanner.nextInt();
	    		if(ubicEdit > ubicaciones.size())
	    			System.out.print("Ese numero no se encuentra en la lista");
	    	}
	    	System.out.print("¿Que desea editar?: 1=Nombre 2=Conecciones 3=Cancelar   ");
	    	if(scanner.nextInt() == 1) {
	    		this.editarNombre(ubicEdit);
	    		return;
	    	}
	    	if(scanner.nextInt() == 3)
	    		return;
	    }
	    
	    private void editarNombre(int ubicEdit) {
	    	String newNombre = "";
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.print("El nombre actual es " + ubicaciones.get(ubicEdit-1) + ", Escriba el nombre nuevo para reemplazar: ");
	    	while(newNombre.length() == 0) {
	    		newNombre = scanner.nextLine();
	    		if(newNombre.length() == 0) {
	    			System.out.print("No hay nombre, ¿desea Cancelar? 1=Si, 2=No");
		    		if(scanner.nextInt()==1)
		    			return;
		    		if(scanner.nextInt()==2)
		    			System.out.print("Entonces digite otra vez el nuevo nombre: ");
	    		}
	    	}
	    	ubicaciones.set(ubicEdit-1, newNombre);
	    	System.out.println("\nLa Nueva lista de ubicaciones es la siguiente:");
	    	for (int i = 0; i < ubicaciones.size(); i++)
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	    	return;
	    }
}
