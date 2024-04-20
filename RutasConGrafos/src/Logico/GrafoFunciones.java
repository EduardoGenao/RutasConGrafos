package Logico;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GrafoFunciones {
	    private static Arista grafoMatriz;
	    private static ArrayList<String> ubicaciones;

	    public GrafoFunciones(int numVertices) {
	        grafoMatriz = new Arista(numVertices);
	        ubicaciones = new ArrayList<String>();
	    }

	    public static Arista getGrafoMatriz() {
			return grafoMatriz;
		}

		public static void setGrafoMatriz(Arista grafoMatriz) {
			GrafoFunciones.grafoMatriz = grafoMatriz;
		}

		public static ArrayList<String> getUbicaciones() {
			return ubicaciones;
		}

		public static void setUbicaciones(ArrayList<String> ubicaciones) {
			GrafoFunciones.ubicaciones = ubicaciones;
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
	    	int ans = 0;
	    	do {
		    	Scanner scanner = new Scanner(System.in); 
		    	
			    System.out.println("\nAhora, ¿Que desea hacer?\n1 = Agregar Otra Ubicacion\n2 = Editar Una Ubicacion o sus conecciones"
			    		+ "\n3 = Eliminar una Ubicacion\n4 = Imprimir matrices\n5 = Encontrar ruta mas corta"
			    		+ "\n6 = Encontrar la ruta de distancia/tiempo mas corta entre todos los nodos \n7 = Encontrar el camino más corto entre todas las ubicaciones\n" + 
			    		"\n10 = Salir del programa");
			    ans = scanner.nextInt();
			    if(ans == 1)
			    	this.agregarUbicacionExtra(ubicaciones.size());
			    if(ans == 2)
			    	this.editarUbicacion();
			    if(ans == 3)
			    	this.eliminarUbicacion();
			    if(ans == 4)
			    	this.imprimirgrafoMatriz();
			    if(ans == 5)
			    	this.dijkstra();
			    if(ans == 6)
			    	this.primPeso(ubicaciones.size());
			    if(ans == 7)
			    	this.floydWarshall();
			    if(ans == 10) {
			    	System.out.println("\n¡Gracias por usar este programa!");
			    }
	    	}while(ans!=10);
	    }
	    
	    public void imprimirgrafoMatriz() {
	    	System.out.println("\nLa lista de ubicaciones quedo así (Peso)");
	    	 // Imprimir la cabecera con las letras de los vértices
	        System.out.print("  ");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.print((char) ('A' + i) + " ");
	        }
	        System.out.println();

	        // Imprimir la grafoMatriz con las letras de los vértices como índice
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            // Imprimir la letra del vértice al inicio de cada fila
	            System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < ubicaciones.size(); j++) {
	                System.out.print(grafoMatriz.getPeso()[i][j] + " ");
	            }
	            System.out.println();
	        }
	        System.out.println("\nLa lista de Tiempos quedo así (Tiempo)");
	    	 // Imprimir la cabecera con las letras de los vértices
	        System.out.print("  ");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.print((char) ('A' + i) + " ");
	        }
	        System.out.println();

	        // Imprimir la grafoMatriz con las letras de los vértices como índice
	        for (int i = 0; i <ubicaciones.size(); i++) {
	            // Imprimir la letra del vértice al inicio de cada fila
	            System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < ubicaciones.size(); j++) {
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
	    
	    public void eliminarUbicacion() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("\nLista de Ubicaciones:");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.println((i + 1) + ". " + ubicaciones.get(i));
	        }
	        int resp = 0;
	        System.out.print("Digite el numero de la ubicacion a eliminar: ");
	        resp = scanner.nextInt();

	        System.out.print("Al eliminar " + ubicaciones.get(resp - 1) +
	                ", se eliminaran las conexiones que conectan con:\n");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            if (grafoMatriz.getPeso()[resp - 1][i] != 0) {
	                System.out.print(ubicaciones.get(i) + "\n");
	                // Eliminar la arista
	                grafoMatriz.getPeso()[resp - 1][i] = 0;
	                grafoMatriz.getPeso()[i][resp - 1] = 0;
	                grafoMatriz.getTiempo()[resp - 1][i] = 0;
	                grafoMatriz.getTiempo()[i][resp - 1] = 0;
	            }
	        }

	        System.out.print("Esta seguro? 1=SI 2=NO: ");
	        int resp2 = 0;
	        resp2 = scanner.nextInt();
	        if (resp2 == 1) {
	            ubicaciones.remove(resp - 1);
	            // Ajustar el tamaño de la matriz
	            ajustarMatriz(resp - 1);
	            int ceros;
	            for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
    				ceros=0;
    				for (int j = 0; j < grafoMatriz.getPeso().length; j++) {
    					if(grafoMatriz.getPeso()[i][j]==0) {
						ceros++;
    					}
    				}
    				if(ceros==ubicaciones.size()) {
    					System.out.println("Hay ubicaciones que no estan conectadas, conéctelas todas");
    					System.out.println("\nLista de Ubicaciones:");
    			        for (int k = 0; k < ubicaciones.size(); k++) {
    			            System.out.println((k+1) + ". " + ubicaciones.get(k));
    			        }
    					this.agregarArista(-1);
    				}
    			}
	        }
	        
	    }

	    public void ajustarMatriz(int indiceEliminar) {
	        int nuevaDimension = grafoMatriz.getPeso().length - 1;

	        Arista nuevaMatriz = new Arista(nuevaDimension);
	        

	        // Copiar los elementos de la matriz original a la nueva matriz
	        for (int i = 0, x = 0; i < grafoMatriz.getPeso().length; i++) {
	            if (i != indiceEliminar) {
	                for (int j = 0, y = 0; j < grafoMatriz.getPeso()[i].length; j++) {
	                    if (j != indiceEliminar) {
	                        nuevaMatriz.getPeso()[x][y] = grafoMatriz.getPeso()[i][j];
	                        nuevaMatriz.getTiempo()[x][y] = grafoMatriz.getTiempo()[i][j];
	                        y++;
	                    }
	                }
	                x++;
	            }
	        }

	        // Actualizar la referencia de la matriz en el objeto GrafoFunciones
	        grafoMatriz.setPeso(nuevaMatriz.getPeso());
	        grafoMatriz.setTiempo(nuevaMatriz.getTiempo());
	    }

	    public void agregarUbicacionExtra(int numUbicaciones) {
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.print("Cuantas ubicaciones extra desea?");
	    	int respuesta = scanner.nextInt();
	    	
    		for (int i = 0; i < respuesta; i++) {
    			
    			System.out.print("Nombre de la Ubicacion "+ (char)(i+65+numUbicaciones) + ": ");
		    	String nombreUbicacion = scanner.nextLine(); // Leer el nombre de la ubicación desde la consola
		    	if(nombreUbicacion.length() == 0) {
		    		nombreUbicacion = ("Ubicacion " + (char)(i+'A'+numUbicaciones));
		    		System.out.println("La Ubicacion se va a llamar por defecto " + nombreUbicacion);
		    	}
		    	ubicaciones.add(numUbicaciones, nombreUbicacion);
	    	}
	    	System.out.println("\nLista de Ubicaciones:");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	        }
	        agregarAristaExtra(-1, numUbicaciones+respuesta);
	        
	    	
	    }
	    
	    public void agregarAristaExtra(int indice1, int numUbicaciones) {
	    	Arista nueva = new Arista(numUbicaciones);
	    	for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
	            for (int j = 0; j < grafoMatriz.getPeso()[i].length; j++) {
	                nueva.getPeso()[i][j] = grafoMatriz.getPeso()[i][j];
	                nueva.getTiempo()[i][j] = grafoMatriz.getTiempo()[i][j];
	            }
	        }
	    	GrafoFunciones.setGrafoMatriz(nueva);
	    	
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
			
	    }
	    
	    private void primPeso(int numVertices) {
	    	//Se crea un array boolean para verificar que cada ubicacion este en el arbol, se rellena con falsos.
	    	boolean[] dentroPrim = new boolean[numVertices];
            Arrays.fill(dentroPrim, false);
            boolean pesoTiempo = false;

            //Se crea una llave para 
            int[] llave = new int[numVertices];
            Arrays.fill(llave, Integer.MAX_VALUE);

            llave[0] = 0;
            int[] padres = new int[numVertices];
            padres[0] = -1;

            for (int count = 0; count < numVertices - 1; count++) {
                int u = minLlave(llave, dentroPrim, numVertices);
                dentroPrim[u] = true;

                for (int i = 0; i < ubicaciones.size(); i++) {	
                    int peso = grafoMatriz.getPeso()[u][i];
                    if (!dentroPrim[i] && (peso != 0 && peso < llave[i])) {
                        padres[i] = u;
                        llave[i] = peso;
                    }
                }
            }
	    	this.printPrim(padres, numVertices, pesoTiempo);
	    	this.kruskalTiempo(numVertices);
	    }
	    
	    private void kruskalTiempo(int numVertices) {
	    	//Se crea una lista de aristas que representa el arbol
	    	int[][] dentroKruskal = new int[numVertices-1][2];
            // Sort edges by weight
	    	int cantTiempos = 0;
            int[] tiempos = new int [numVertices*numVertices];
            for(int i = 0; i < numVertices; i++) {
            	for(int j = i; j < numVertices; j++)
            		if(grafoMatriz.getTiempo()[i][j] != 0) {
            			tiempos[cantTiempos] = grafoMatriz.getTiempo()[i][j];
            			cantTiempos++;
            		}
            }
            Arrays.sort(tiempos);
            //Se cre un arreglo para los nodos padres
            int[] padres = new int[numVertices];
            //for que recorre todos los vertices
            for (int i = 0; i < numVertices; i++)
            	//El nodo padre de cada nodo es ese mismo nodo
                padres[i] = i;

            //Variable que cuenta las aristas
            int cuentAristas = 0;
            //Variable que indica la posicion
            int index = 0;
            //Mientras la cuenta de aristas sea menor a la cantidad de vertices - 1
            while (cuentAristas < numVertices - 1) {
            	//La proxima arista es la arista señalada por el indice, el valor del indice luego aumenta en 1
                int[] nodos = buscartiempo(tiempos[index++], numVertices);
                //La fuente del padre es el resultado de la funcion find con el arreglo de padres y el nodo de origen de la proxima arista
                int sourceParent = buscarArista(padres, nodos[0]);
                //Lo mismo pero ahora con el nodo de destino de la proxima arista
                int destinationParent = buscarArista(padres, nodos[1]);

                //Si el padre del origen es diferente al padre del destino
                if (sourceParent != destinationParent) {
                	//Se añade la arista a la lista del arbol
                    dentroKruskal[cuentAristas] = nodos;
                    //Se llama la funcion union con el arreglo padres y los dos padres
                    union(padres, sourceParent, destinationParent);
                    //Se añade una arista a la cuenta
                    cuentAristas++;
                }
            }

            System.out.println("\nEn cuanto a tiempo, la interconeccion mas corta es:");
            for (int i = 0; i < numVertices-1; i++) {
                System.out.println(ubicaciones.get(dentroKruskal[i][0]) + " - " + ubicaciones.get(dentroKruskal[i][1]) 
                + "        " + grafoMatriz.getTiempo()[dentroKruskal[i][0]][dentroKruskal[i][1]] + " segundos");
            }
            
            this.menuOpciones();
        }
	    
	    private int[] buscartiempo(int tiempo, int numVertices) {
	    	int[] nodos = new int[2];
	    	for(int i = 0; i < numVertices; i++) {
            	for(int j = i; j < numVertices; j++)
            		if(grafoMatriz.getTiempo()[i][j] == tiempo) {
            			nodos[0] = i;
            			nodos[1] = j;
            			return nodos;
            		}
            }
	    	return nodos;
	    }

        private int buscarArista(int[] parent, int vertex) {
        	//Si el padre del nodo de origen/destino de la proxima arista no es ese mismo nodo
            if (parent[vertex] != vertex)
            	//El padre del nodo es el resultado de esta misma funcion con el padre del nodo anterior 
                parent[vertex] = buscarArista(parent, parent[vertex]);
            //Se retorna el padre del nodo de origen/destino
            return parent[vertex];
        }

        
        private void union(int[] parent, int x, int y) {
        	//xSet = el padre de x
            int xSet = buscarArista(parent, x);
            //ySet = el padre de y
            int ySet = buscarArista(parent, y);
            //El padre de ySet es xSet
            parent[ySet] = xSet;

        }
	    
	    private int minLlave(int[] llave, boolean[] dentroPrim, int numVertices) {
	    	//min inicia como el numero maximo
            int min = Integer.MAX_VALUE;
          //El lugar del minimo, inicialmente no existe
            int minIndex = -1;

          //for que chequea todos los vertices
            for (int v = 0; v < numVertices; v++) {
            	//Si (el vertice seleccionado) esta fuera del arbol de prim y la llave del vertice es menor que el minimo
                if (!dentroPrim[v] && llave[v] < min) {
                	//El nuevo minimo es la llave de vertice seleccionado
                    min = llave[v];
                  //El lugar del minimo es el lugar del vertice.
                    minIndex = v;
                }
            }
            //Se devuelve el lugar del minimo
            return minIndex;
	    }
	    
	    private void printPrim (int[] padres, int numVertices, boolean pesoTiempo) {
	    	if(pesoTiempo == false) {
	    		System.out.println("\nLa interconeccion mas corta en cuanto a distancia es:");
	            for (int i = 1; i < numVertices; i++) {
	                System.out.println(ubicaciones.get(padres[i]) + " - " + ubicaciones.get(i) + "        "
	                	+ grafoMatriz.getPeso()[padres[i]][i] + " kilómetros");
	            }
	    	}
	    	else {
	    		System.out.println("\nLa interconeccion mas corta en cuanto a distancia es:");
	            for (int i = 1; i < numVertices; i++) {
	                System.out.println(ubicaciones.get(padres[i]) + " - " + ubicaciones.get(i) + "        "
	                	+ grafoMatriz.getTiempo()[padres[i]][i] + " minutos");
	            }
	    	}
	    }
	    
	    public void dijkstra() {
	    	
	    	int inicio = 0, fin = 0;
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.println("\nLista de Ubicaciones:");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	        }
	    	System.out.print("Indique el numero de la ubicacion en la que quiere iniciar: ");
	    	inicio= scanner.nextInt();
	    	inicio-=1;
	    	System.out.print("Indique el numero de la ubicacion en la que quiere terminar: ");
	    	fin= scanner.nextInt();
	    	fin-=1;
	        int numVertices = ubicaciones.size();
	        int[] distancias = new int[numVertices];
	        boolean[] visitado = new boolean[numVertices];
	        int[] distanciasPeso = new int[numVertices];
	        boolean[] visitadoPeso = new boolean[numVertices];
	        int[] distanciasTiempo = new int[numVertices];
	        boolean[] visitadoTiempo = new boolean[numVertices];
	        
	        int[][] grafoCombinado = new int[numVertices][numVertices];
	        
	        for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < numVertices; j++) {
					grafoCombinado[i][j]=grafoMatriz.getPeso()[i][j]+grafoMatriz.getTiempo()[i][j];
				}
			}

	        // inicializa distancias a infinito y las marca como no visitada
	        for (int i = 0; i < numVertices; i++) {
	            distancias[i] = Integer.MAX_VALUE;
	            visitado[i] = false;
	            distanciasPeso[i] = Integer.MAX_VALUE;
	            visitadoPeso[i] = false;
	            distanciasTiempo[i] = Integer.MAX_VALUE;
	            visitadoTiempo[i] = false;
	        }

	        // distancia inicio siempre es cero
	        distancias[inicio] = 0;
	        distanciasPeso[inicio] = 0;
	        distanciasTiempo[inicio] = 0;

	        // encontrar la menor distancia entre vertices
	        for (int count = 0; count < numVertices - 1; count++) {
	            int u = minDistance(distancias, visitado);
	            visitado[u] = true;
	            int peso = minDistance(distanciasPeso, visitadoPeso);
	            visitadoPeso[peso] = true;
	            int tiempo = minDistance(distanciasTiempo, visitadoTiempo);
	            visitadoTiempo[tiempo] = true;
	            

	            //actualiar distancia de vertices adyacentes
	            for (int v = 0; v < numVertices; v++) {
	                if (!visitado[v] && grafoCombinado[u][v] != 0 &&
	                        distancias[u] != Integer.MAX_VALUE &&
	                        distancias[u] + grafoCombinado[u][v] < distancias[v]) {
	                    distancias[v] = distancias[u] + grafoCombinado[u][v];
	                }
	                if (!visitadoPeso[v] && grafoMatriz.getPeso()[u][v] != 0 &&
	                        distanciasPeso[u] != Integer.MAX_VALUE &&
	                        distanciasPeso[u] + grafoMatriz.getPeso()[u][v] < distanciasPeso[v]) {
	                    distanciasPeso[v] = distanciasPeso[u] + grafoMatriz.getPeso()[u][v];
	                }
	                if (!visitadoTiempo[v] && grafoMatriz.getTiempo()[u][v] != 0 &&
	                        distanciasTiempo[u] != Integer.MAX_VALUE &&
	                        distanciasTiempo[u] + grafoMatriz.getTiempo()[u][v] < distanciasTiempo[v]) {
	                    distanciasTiempo[v] = distanciasTiempo[u] + grafoMatriz.getTiempo()[u][v];
	                }
	            }
	        }

	        // Print la distancia mas corta
	        System.out.println("La distancia mas corta combinando el tiempo y la distancia entre " + ubicaciones.get(inicio) +
	                " y " + ubicaciones.get(fin) + " es: " + distancias[fin]);
	        System.out.println("La distancia mas corta teniendo en cuenta el tiempoc entre " + ubicaciones.get(inicio) +
	                " y " + ubicaciones.get(fin) + " es: " + distanciasTiempo[fin]);
	        System.out.println("La distancia mas corta teniendo en cuenta la distancia entre " + ubicaciones.get(inicio) +
	                " y " + ubicaciones.get(fin) + " es: " + distanciasPeso[fin]);
	        System.out.println("Grafo combinado de peso y tiempo\n");
	        System.out.print("  ");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.print((char) ('A' + i) + " ");
	        }
	        System.out.println();
	        
	        // Imprimir la grafocombinado con las letras de los vértices como índice
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            // Imprimir la letra del vértice al inicio de cada fila
	            System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < ubicaciones.size(); j++) {
	                System.out.print(grafoCombinado[i][j] + " ");
	            }
	            System.out.println();
	        }
	        imprimirgrafoMatriz();
	    }
	    public int minDistance(int[] distancias, boolean[] visitado) {
	        int min = Integer.MAX_VALUE;
	        int minIndex = -1;
	        int numVertices = distancias.length;

	        for (int v = 0; v < numVertices; v++) {
	            if (!visitado[v] && distancias[v] <= min) {
	                min = distancias[v];
	                minIndex = v;
	            }
	        }

	        return minIndex;
	    }
	    public void floydWarshall() {
	    	
	        int numVertices = ubicaciones.size();
	        //int[][] grafo = new int[numVertices][numVertices];
	        int[][] distancias = new int[numVertices][numVertices];
	        int[][] camino = new int[numVertices][numVertices];
	        int[][] distanciasTiempo = new int[numVertices][numVertices];
	        int[][] caminoTiempo = new int[numVertices][numVertices];
	        
	        // Inicializar la matriz de distancias con los valores del grafo
	        for (int i = 0; i < numVertices; i++) {
	            for (int j = 0; j < numVertices; j++) {
	                distancias[i][j] = grafoMatriz.getPeso()[i][j];
	                distanciasTiempo[i][j] = grafoMatriz.getTiempo()[i][j];
	                if(grafoMatriz.getPeso()[i][j]==0 && i!=j) {
	                	distancias[i][j]=Integer.MAX_VALUE;
	                	distanciasTiempo[i][j]=Integer.MAX_VALUE;
	                }
	                	
	                if (grafoMatriz.getPeso()[i][j] != Integer.MAX_VALUE && i != j) {
	                    camino[i][j] = i;
	                    caminoTiempo[i][j] = i;
	                } else {
	                    camino[i][j] = -1;
	                    caminoTiempo[i][j] = -1;
	                }
	            }
	        }

	        // Algoritmo de Floyd-Warshall
	        for (int k = 0; k < numVertices; k++) {
	            for (int i = 0; i < numVertices; i++) {
	                for (int j = 0; j < numVertices; j++) {
	                    if (distancias[i][k] != Integer.MAX_VALUE && distancias[k][j] != Integer.MAX_VALUE && 
	                        distancias[i][k] + distancias[k][j] < distancias[i][j]) {
	                        distancias[i][j] = distancias[i][k] + distancias[k][j];
	                        camino[i][j] = camino[k][j];
	                    }
	                    if (distanciasTiempo[i][k] != Integer.MAX_VALUE && distanciasTiempo[k][j] != Integer.MAX_VALUE && 
		                        distanciasTiempo[i][k] + distanciasTiempo[k][j] < distanciasTiempo[i][j]) {
		                        distanciasTiempo[i][j] = distanciasTiempo[i][k] + distanciasTiempo[k][j];
		                        caminoTiempo[i][j] = caminoTiempo[k][j];
		                    }
	                }
	            }
	        }
	        System.out.print("Resultados con el peso\n");
	        // Imprimir los resultados
	        for (int i = 0; i < numVertices; i++) {
	            for (int j = 0; j < numVertices; j++) {
	                // Si la distancia es Integer.MAX_VALUE, significa que no hay conexión directa
	                if (distancias[i][j] == Integer.MAX_VALUE) {
	                    // En este caso, se imprime "INF" para indicar que no hay conexión directa
	                    System.out.print("INF\t");
	                } else {
	                    // Si hay una conexión directa, se imprime la distancia
	                    System.out.print(distancias[i][j] + "\t");
	                }
	            }
	            System.out.println();
	        }
	        System.out.print("Resultados con el tiempo\n");
	        // Imprimir los resultados
	        for (int i = 0; i < numVertices; i++) {
	            for (int j = 0; j < numVertices; j++) {
	                // Si la distancia es Integer.MAX_VALUE, significa que no hay conexión directa
	                if (distanciasTiempo[i][j] == Integer.MAX_VALUE) {
	                    // En este caso, se imprime "INF" para indicar que no hay conexión directa
	                    System.out.print("INF\t");
	                } else {
	                    // Si hay una conexión directa, se imprime la distancia
	                    System.out.print(distanciasTiempo[i][j] + "\t");
	                }
	            }
	            System.out.println();
	        }
	    }
	
}
