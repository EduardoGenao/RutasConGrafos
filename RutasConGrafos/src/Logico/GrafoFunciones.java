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
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
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
	    public void agregarArista(int indice1) {
	    	
	    	int respuesta = 1;
			do {
		    	int indice2 = 0, peso = 0,tiempo = 0;
		    	boolean replace = true;
		    	Scanner scanner = new Scanner(System.in);
		    	if(indice1 == -1) {
		    		System.out.print("\nConeccion de Ubicaciones:\nDigite el numero en la lista de la primera ubicacion a conectar: ");
			    	do {
			    		indice1 = scanner.nextInt()-1;
						if(indice1 < 0 || indice1 >= ubicaciones.size())
							System.out.print("Ubicacion no encontrada, digite otra vez: ");
			    	} while(indice1 < 0 || indice1 >= ubicaciones.size());
			    	
		    	}
		    	else {
		    		respuesta = 3;
		    		System.out.println("\nAñadir otra coneccion a la Ubicacion " + ubicaciones.get(indice1));
		    		System.out.println("\nLista de Ubicaciones:");
			        for (int i = 0; i < ubicaciones.size(); i++) {
			            System.out.println((i+1) + ". " + ubicaciones.get(i));
			        }
		    	}
		    	System.out.print("Conectarla con (Digite otro numero de la lista): ");
		    	do {
		    		indice2 = scanner.nextInt()-1;
					if(indice2 < 0 || indice2 == indice1 || indice2 >= ubicaciones.size())
						System.out.print("Ubicacion no encontrada, digite otra vez: ");
					if(indice2 == indice1)
						System.out.print("No puedes conectar una ubicacion con si misma, digite otra vez:");
		    	} while(indice2 < 0 || indice2 == indice1 || indice2 >= ubicaciones.size());
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
		    	
		    	if(grafoMatriz.getPeso()[indice1][indice2]!=0) {
		    		System.out.println("Estas ubicaciones ya tienen una coneccion directa, ¿desea reemplazar"
		    				+ " los viejos valores por los nuevos? 1=Si 2=No");
		    		if(scanner.nextInt() != 1)
		    			replace = false ;
		    	}
		    		if(replace == true) {
		    			grafoMatriz.getPeso()[indice1][indice2]=peso;
				    	grafoMatriz.getPeso()[indice2][indice1]=peso;
				    	grafoMatriz.getTiempo()[indice1][indice2]=tiempo;
				    	grafoMatriz.getTiempo()[indice2][indice1]=tiempo;
		    		}
		    		replace = true;
		    		
		    	if(respuesta != 3) {
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
		    	}
		    	indice1 = -1;
	    	}while(respuesta==1);
			if(respuesta == 3)
				this.imprimirgrafoMatriz();
	    }
	    
	    public void menuOpciones() {
	    	Scanner scanner = new Scanner(System.in);
	    	int ans = 0;
		    System.out.println("\nAhora, ¿Que desea hacer?\n1 = Agregar Otra Ubicacion\n2 = Editar Una Ubicacion o sus conecciones"
		    		+ "\n3 = Eliminar una Ubicacion\n4 = Imprimir matrices\n10 = Salir del programa");
		    ans = scanner.nextInt();
		    if(ans == 2)
		    	this.editarUbicacion();
		    if(ans == 4)
		    	this.imprimirgrafoMatriz();
		    if(ans == 10) {
		    	System.out.println("\n¡Gracias por usar este programa!");
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
	        this.menuOpciones();
	    }
	    
	    private void editarUbicacion() {
	    	Scanner scanner = new Scanner(System.in);
	    	int ans = 0;
	    	int ubicEdit = -1;
	    	System.out.println("\nLa lista de ubicaciones es la siguiente:");
	    	for (int i = 0; i < ubicaciones.size(); i++)
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	    	System.out.print("Digite el numero en la lista de la ubicacion a editar: ");
	    	do{
	    		ubicEdit = scanner.nextInt()-1;
	    		if(ubicEdit <= -1 || ubicEdit >= ubicaciones.size())
	    			System.out.print("Ese numero no se encuentra en la lista, digite otro: ");
	    	}while(ubicEdit <= -1 || ubicEdit > ubicaciones.size());
	    	System.out.print("¿Que desea editar?: 1=Nombre 2=Conecciones 3=Cancelar   ");
	    	ans = scanner.nextInt();
	    	if(ans == 1)
	    		this.editarNombre(ubicEdit);
	    	if(ans == 2)
	    		this.editarConecciones(ubicEdit);
	    	if(ans == 3)
	    		this.menuOpciones();
	    }
	    
	    private void editarNombre(int ubicEdit) {
	    	String newNombre = "";
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.print("El nombre actual es " + ubicaciones.get(ubicEdit) + ", Escriba el nombre nuevo para reemplazar: ");
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
	    	ubicaciones.set(ubicEdit, newNombre);
	    	System.out.println("\nLa Nueva lista de ubicaciones es la siguiente:");
	    	for (int i = 0; i < ubicaciones.size(); i++)
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	    	this.menuOpciones();
	    }
	    
	    private void editarConecciones(int ubicEdit) {
	    	int resp = 0, ans = 0, newWeight = 0, newTime = 0;
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.println("\nLas conecciones que contienen a " + ubicaciones.get(ubicEdit) + " son las siguientes:");
	    	for(int i = 0; i < grafoMatriz.getPeso()[ubicEdit].length; i++) {
	    		if(grafoMatriz.getPeso()[ubicEdit][i] != 0) {
	    			System.out.println("Posicion " + (i+1) + ": De " + (ubicaciones.get(ubicEdit)) + " a " + ubicaciones.get(i) + ". Peso: "
	    					+ (grafoMatriz.getPeso()[ubicEdit][i]) + " Tiempo: " + (grafoMatriz.getTiempo()[ubicEdit][i]));
	    		}
	    	}
	    	System.out.print("Digite el numero de la coneccion a editar (0=Agregar): ");
	    	do{
	    		resp = scanner.nextInt()-1;
	    		if(resp > -1 && grafoMatriz.getPeso()[ubicEdit][resp] == 0)
	    			System.out.print("Ese numero no se encuentra en la lista, digite otro: ");
	    	}while(resp > -1 && grafoMatriz.getPeso()[ubicEdit][resp] == 0);
	    	
	    	if(resp == -1)
	    		this.agregarArista(ubicEdit);
	    	else {
	    		System.out.print("\n¿Quiere cambiar el peso y el tiempo?  1=Si 2=Cancelar 3=Eliminar Coneccion");
	    		ans = scanner.nextInt();
	    		if(ans == 1) {
	    			System.out.print("Diga el nuevo peso: ");
	    			newWeight = scanner.nextInt();
	    			if(newWeight <= 0 || newWeight == grafoMatriz.getPeso()[ubicEdit][resp]) {
	    				System.out.println("Como no puso un nuevo valor, el peso se queda igual");
	    			}
	    			else {
	    				grafoMatriz.getPeso()[ubicEdit][resp]=newWeight;
				    	grafoMatriz.getPeso()[resp][ubicEdit]=newWeight;
	    			}
	    			System.out.print("Diga el nuevo tiempo: ");
	    			newTime = scanner.nextInt();
	    			if(newTime <= 0 || newTime == grafoMatriz.getPeso()[ubicEdit][resp]) {
	    				System.out.println("Como no puso un nuevo valor, el tiempo se queda igual");
	    			}
	    			else {
	    				grafoMatriz.getTiempo()[ubicEdit][resp]=newTime;
				    	grafoMatriz.getTiempo()[resp][ubicEdit]=newTime;
	    			}
	    			this.menuOpciones();
	    		}
	    		if(ans == 2) {
	    			this.menuOpciones();
	    		}
	    		if(ans == 3) {
	    			System.out.print("¿Seguro que quiere eliminar esta coneccion? 1=Si 2=No");
	    			ans = scanner.nextInt();
	    			if(ans == 1) {
	    				boolean getcut = false;
	    				int ceros=0;
	    				for (int j = 0; j < grafoMatriz.getPeso().length; j++) {
	    					if(grafoMatriz.getPeso()[ubicEdit][j]==0) {
							ceros++;
	    					}
	    				}
	    				if(ceros>=ubicaciones.size()-1)
	    					getcut = true;
	    				ceros=0;
	    				for (int j = 0; j < grafoMatriz.getPeso().length; j++) {
	    					if(grafoMatriz.getPeso()[resp][j]==0) {
							ceros++;
	    					}
	    				}
	    				if(ceros>=ubicaciones.size()-1)
	    					getcut = true;
	    				if(getcut == true)
	    					System.out.println("La coneccion no se puede eliminar porque una o ambas ubicaciones quedarian aisladas");
	    				else {
	    					grafoMatriz.getPeso()[ubicEdit][resp]=0;
					    	grafoMatriz.getPeso()[resp][ubicEdit]=0;
					    	grafoMatriz.getTiempo()[ubicEdit][resp]=newTime;
					    	grafoMatriz.getTiempo()[resp][ubicEdit]=newTime;
	    				}
	    				this.menuOpciones();
	    			}
	    			if(ans == 2) {
	    				this.menuOpciones();
	    			}
	    		}
	    	}
	    		
	    }
	    
	    
}
