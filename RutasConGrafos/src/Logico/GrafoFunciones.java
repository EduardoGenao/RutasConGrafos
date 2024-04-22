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

		//boolean dataDumping, le pregunta al usuario si quiere usar el dataDumping y la respuesta la retorna en forma de boolean:
		//true si la respuesta es si, false si la respuesta es no.
		public static boolean dataDumping(){
	    	boolean used = false;
	    	int[] pesos, tiempos;
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.print("¿Desea hacer uso del dataDumping? 1=Si 2=NO\n");
	    	int resp = scanner.nextInt();
	    	if(resp == 1)
	    		used = true;
	    	return used;
	    }
		
		//void dumpingAristas, Inserta las aristas predeterminadas dentro de la matriz en caso de haberse usado el dataDumping.
		public void dumpingAristas() {
			int[] pesos = {69, 0, 0, 4, 69, 0, 43, 0, 78, 25, 0, 0, 2, 0, 64, 0, 0, 92, 0, 6, 59, 15, 0, 19, 71, 82, 59, 34};
			int[] tiempos = {71, 0, 0, 9, 41, 0, 38, 0, 16, 54, 0, 0, 85, 0, 65, 0, 0, 52, 0, 15, 35, 100, 0, 67, 91, 81, 25, 53};
			int index = 0;
			for(int i = 0; i < ubicaciones.size(); i++) {
				for(int j = i+1; j < ubicaciones.size(); j++) {
					grafoMatriz.getPeso()[i][j] = pesos[index];
					grafoMatriz.getPeso()[j][i] = pesos[index];
					grafoMatriz.getTiempo()[i][j] = tiempos[index];
					grafoMatriz.getTiempo()[j][i] = tiempos[index];
					index++;
				}
			}
		}
		
		
		//void agregarUbicaciones, se encarga de agregar las ubicaciones (nodos) dentro del arreglo de nombres, pero no dentro de la
		//matriz (eso lo hace agregarAristas). numUbicaciones es la cantidad de ubicaciones que debe tener el arreglo y ddp es el
		//boolean que indica si al datadumping se uso.
	    public void agregarUbicaciones(int numUbicaciones, boolean ddp) {
		    Scanner scanner = new Scanner(System.in);
		    int indice=0;
		    String nombreUbicacion;
		    for(int i=0; i<numUbicaciones;i++) {	
		    	
		    	//Si el arreglo de ubicaciones no esta vacio, indice es el tamaño del arreglo.
		    	if(!ubicaciones.isEmpty())
		    		 indice = ubicaciones.size();
		    	
		    	if(ddp == true)
		    		nombreUbicacion = ("Ubicacion " + (char)(i+'A'));
		    	
		    	else {
		    		System.out.print("Nombre de la Ubicacion "+ (char)(i+65) + ": ");
			    	nombreUbicacion = scanner.nextLine(); // Leer el nombre de la ubicación desde la consola
			    	if(nombreUbicacion.length() == 0) {
			    		nombreUbicacion = ("Ubicacion " + (char)(i+'A'));
			    		System.out.println("La Ubicacion se va a llamar por defecto " + nombreUbicacion);
			    	}
		    	}
		    	//agregar ubicacion al arrayList
		    	ubicaciones.add(indice, nombreUbicacion);
	    	}
	        
	    }
	  
	    //agregarArista, esta funcion se encarga de agregar las aristas a la matriz del grafo, tanto la de distancia como
	    //la de tiempo. La funcion tiene dos formas de llamarse, desde el main al iniciar el programa, en cuyo caso el valor de indice1
	    //es -1 (no existe) o luego de la funcion editarrutas, donde se enviara el valor del nodo de origen de la arista.
	    public void agregarArista(int indice1) {
	    	//La variable respuesta se usa para ver si el usuario quiere seguir agregando aristas, 1 es si y 2 es no, pero antes de salir
	    	//se revisa que todos los nodos esten conectados al grafo, y si no es así respuesta vuelve a 1. 3 es para indicar que la funcion
	    	//se llamo del editor y por ende solo se esta agregando una arista.
	    	int respuesta = 1;
			do {
		    	int indice2 = 0, peso = 0,tiempo = 0;
		    	boolean replace = true;
		    	Scanner scanner = new Scanner(System.in);
		    	
		    	//Si no se ha enviado un lugar del nodo de origen, se le pregunta aqui al usuario
		    	if(indice1 == -1) {
		    		System.out.println("\nLista de Ubicaciones:");
		    		for (int i = 0; i < ubicaciones.size(); i++) {
		    			System.out.println((i+1) + ". " + ubicaciones.get(i));
				    }
		    		System.out.print("\nruta de Ubicaciones:\nDigite el numero en la lista de la primera ubicacion a conectar: ");
			    	do {
			    		indice1 = scanner.nextInt()-1;
						if(indice1 < 0 || indice1 >= ubicaciones.size())
							System.out.print("Ubicacion no encontrada, digite otra vez: ");
			    	} while(indice1 < 0 || indice1 >= ubicaciones.size());
			    	
		    	}
		    	
		    	//Si el lugar esta definido por el editor, se salta a preguntar directamente por el 2do nodo
		    	else {
		    		respuesta = 3;
		    		System.out.println("\nAñadir otra ruta a la Ubicacion " + ubicaciones.get(indice1));
		    		System.out.println("\nLista de Ubicaciones:");
			        for (int i = 0; i < ubicaciones.size(); i++) {
			            System.out.println((i+1) + ". " + ubicaciones.get(i));
			        }
		    	}
		    	// el usuario digita la ubicacion a conectar
		    	System.out.print("Conectarla con (Digite otro numero de la lista): ");
		    	do {
		    		indice2 = scanner.nextInt()-1;
		    		//si no se encuentra debe digitar de nuevo
					if(indice2 < 0 || indice2 == indice1 || indice2 >= ubicaciones.size())
						System.out.print("Ubicacion no encontrada, digite otra vez: ");
					// si es igual a la primera ubicacion asignada, no se puede conectar a si misma
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
		    	
		    	//Si se va a poner una ruta en un lugar donde ya hay una, se pregunta si se quiere reemplazar la anterior.
		    	if(grafoMatriz.getPeso()[indice1][indice2]!=0) {
		    		System.out.println("Estas ubicaciones ya tienen una ruta directa, ¿desea reemplazar"
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
		    		// si pone un numero que no sea o 2, que siga preguntando que desea hacer
		    	if(respuesta != 3) {
		    		System.out.println("\nDesea seguir agregando rutas? 1= SI, 2=NO");
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
			
			if(respuesta == 3) {
				this.imprimirgrafoMatriz();
				this.menuOpciones();
			}
	    }
	    
	    //void imprimirgrafoMatriz, como dice su nombre se encarga de imprimir las matrices del grafo en su estado actual.
	    public void imprimirgrafoMatriz() {
	    	System.out.println("\nLa lista de ubicaciones quedo así (Peso)");
	    	 // Imprimir la cabecera con las letras de los vértices
	        System.out.print("  ");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	        	System.out.print(String.format("%3c ",(char) ('A' + i)));
	        }
	        System.out.println();

	        // Imprimir la grafoMatriz con las letras de los vértices como índice
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            // Imprimir la letra del vértice al inicio de cada fila
	            System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < ubicaciones.size(); j++) {
	            	System.out.print(String.format("%3d ", grafoMatriz.getPeso()[i][j]));
	            }
	            System.out.println();
	        }
	        System.out.println("\nLa lista de Tiempos quedo así (Tiempo)");
	    	 // Imprimir la cabecera con las letras de los vértices
	        System.out.print("  ");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.print(String.format("%3c ",(char) ('A' + i)));
	        }
	        System.out.println();

	        // Imprimir la grafoMatriz con las letras de los vértices como índice
	        for (int i = 0; i <ubicaciones.size(); i++) {
	            // Imprimir la letra del vértice al inicio de cada fila
	            System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < ubicaciones.size(); j++) {
	            	System.out.print(String.format("%3d ", grafoMatriz.getTiempo()[i][j]));
	            }
	            System.out.println();
	        }
	    }
	    
	    //void menuOpciones, la funcion que se encarga de interactuar con el usuario y preguntarle que va a querer hacer. esta funcion va a
	    //ser llamada recurrentemente dentro del menu, hasta que el responda 9, que significa que quiere terminar.
	    public void menuOpciones() {
	    	int ans = 0;
	    	//menu para que el usuario ingrese el numero del apartado lo que desea hacer
	    	do {
		    	Scanner scanner = new Scanner(System.in); 
		    	
			    System.out.println("\nAhora, ¿Que desea hacer?\n1 = Agregar Otra Ubicacion\n2 = Editar Una Ubicacion o sus rutas"
			    		+ "\n3 = Eliminar una Ubicacion\n4 = Imprimir matrices\n5 = Encontrar ruta mas corta"
			    		+ "\n6 = Crear una ruta interconectada minima \n7 = Encontrar el camino más corto entre todas las ubicaciones\n" + 
			    		"8 = Ruta Personalizada \n9 = Salir del programa");
			    ans = scanner.nextInt();
			    if(ans == 1)
			    	this.agregarUbicacionExtra(ubicaciones.size());
			    if(ans == 2)
			    	this.editarUbicacion();
			    if(ans == 3)
			    	this.eliminarUbicacion();
			    if(ans == 4) {
			    	this.imprimirgrafoMatriz();
			    	this.menuOpciones();
			    }
			    if(ans == 5)
			    	this.dijkstra();
			    if(ans == 6)
			    	this.primPeso(ubicaciones.size());
			    if(ans == 7)
			    	this.floydWarshall();
			    if(ans == 8)
			    	this.rutaPersonalizada();
			    if(ans == 9) {
			    	System.out.println("\n¡Gracias por usar este programa!");
			    }
	    	}while(ans!=9);
	    }
	    
	    
	    
	    
	   //--FUNCIONES PARA EDITAR UBICACIONES Y RUTAS
	    
	    //void editarUbicacion, este es el menu principal para editar las ubicacciones ya existentes. Se le pregunta al usuario cual
	    //ubicacion quiere editar, y luego se le pregunta si quiere editar el nombre de la ubicacion, editar las aristas relaccionadas con
	    //la ubicacion, o salir.
	    public void editarUbicacion() {
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
	    	System.out.print("¿Que desea editar?: 1=Nombre 2=rutas 3=Cancelar   ");
	    	ans = scanner.nextInt();
	    	if(ans == 1)
	    		this.editarNombre(ubicEdit);
	    	if(ans == 2)
	    		this.editarrutas(ubicEdit);
	    	if(ans == 3)
	    		this.menuOpciones();
	    }
	    //void editarNombre, cambia el nombre de la ubicacion enviada.
	    private void editarNombre(int ubicEdit) {
	    	String newNombre = "";
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.print("El nombre actual es " + ubicaciones.get(ubicEdit) + ", Escriba el nombre nuevo para reemplazar: ");
	    	while(newNombre.length() == 0) {
	    		newNombre = scanner.nextLine();
	    		//Si el usuario no escribe nada en el nombre, se le pregunta si quiere cancelar. Si no quiere, se vuelve a preguntar nombre
	    		if(newNombre.length() == 0) {
	    			System.out.print("No hay nombre, ¿desea Cancelar? 1=Si, 2=No");
		    		if(scanner.nextInt()==1)
		    			return;
		    		if(scanner.nextInt()==2)
		    			System.out.print("Entonces digite otra vez el nuevo nombre: ");
	    		}
	    	}
	    	ubicaciones.set(ubicEdit, newNombre);
	    	//Se imprime la lista de ubicaciones nueva y se vuelve al menu
	    	System.out.println("\nLa Nueva lista de ubicaciones es la siguiente:");
	    	for (int i = 0; i < ubicaciones.size(); i++)
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	    	this.menuOpciones();
	    }
	    
	    //void editarConnecciones, se encarga de editar las aristas relaccionadas con la ubicaccion enviada. Se muestran las aristas en forma
	    //de lista para que el usuario escoja una, y luego puede decidir si cambiar el peso y el tiempo, no hacer nada, eliminar la arista o
	    //agregar otra arista extra.
	    private void editarrutas(int ubicEdit) {
	    	int resp = 0, ans = 0, newWeight = 0, newTime = 0;
	    	Scanner scanner = new Scanner(System.in);
	    	//Se imprimen la lista de rutas relaccionadas.
	    	System.out.println("\nLas rutas que contienen a " + ubicaciones.get(ubicEdit) + " son las siguientes:");
	    	for(int i = 0; i < grafoMatriz.getPeso()[ubicEdit].length; i++) {
	    		if(grafoMatriz.getPeso()[ubicEdit][i] != 0) {
	    			System.out.println("Posicion " + (i+1) + ": De " + (ubicaciones.get(ubicEdit)) + " a " + ubicaciones.get(i) + ". Peso: "
	    					+ (grafoMatriz.getPeso()[ubicEdit][i]) + " Tiempo: " + (grafoMatriz.getTiempo()[ubicEdit][i]));
	    		}
	    	}
	    	//Se pregunta cual de todas se quiere editar
	    	System.out.print("Digite el numero de la ruta a editar (0=Agregar): ");
	    	do{
	    		resp = scanner.nextInt()-1;
	    		if(resp > -1 && grafoMatriz.getPeso()[ubicEdit][resp] == 0)
	    			System.out.print("Ese numero no se encuentra en la lista, digite otro: ");
	    	}while(resp > -1 && grafoMatriz.getPeso()[ubicEdit][resp] == 0);
	    	
	    	//el usuario quiere agregar una arista
	    	if(resp == -1)
	    		//Se invoca la funcion agregar arista con la ubicacion del nodo de origen de la arista a agregar
	    		this.agregarArista(ubicEdit);
	    	else {
	    		System.out.print("\n¿Quiere cambiar el peso y el tiempo?  1=Si 2=Cancelar 3=Eliminar ruta");
	    		ans = scanner.nextInt();
	    		
	    		//el usuario quiere cambiar los valores de la arista
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
	    		//El usuario quiere cancelar
	    		if(ans == 2) {
	    			this.menuOpciones();
	    		}
	    		//El usuario desea eliminar la arista
	    		if(ans == 3) {
	    			System.out.print("¿Seguro que quiere eliminar esta ruta? 1=Si 2=No");
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
	    					System.out.println("La ruta no se puede eliminar porque una o ambas ubicaciones quedarian aisladas");
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
	    
	    
	    
	    
	    //--FUNCIONES PARA AGREGAR ELIMINAR UBICACIONES
	    /* eliminarUbicacion() esta funcion elimina una ubicacion deseada, primero
	     *  pregunta al usuario el numero de la ubicacion y las conexiones que 
	     *  tiene esta ubicacion, por lo que si el grafo no queda unido 
	     *  completamente, te pide insertar conexiones hasta que lo este. */
	    public void eliminarUbicacion() {
	        Scanner scanner = new Scanner(System.in);
	        // se imprime la lista de ubicaciones
	        System.out.println("\nLista de Ubicaciones:");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.println((i + 1) + ". " + ubicaciones.get(i));
	        }
	        //se digita el numero de la ubicacion a eliminar
	        int resp = 0;
	        System.out.print("Digite el numero de la ubicacion a eliminar: ");
	        resp = scanner.nextInt();
	        // advertencia que al eliminar la ubicacion se eliminaran sus aristas
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
	            // verificaf que todo el grafo esta unido
	            for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
    				ceros=0;
    				for (int j = 0; j < grafoMatriz.getPeso().length; j++) {
    					if(grafoMatriz.getPeso()[i][j]==0) {
						ceros++;
    					}
    				}
    				//si hay una ubicacion sin aristas, se deben de ingresar aristas para que se pueda continuar
    				if(ceros==ubicaciones.size()) {
    					
    					System.out.println("Al eliminar la ubicacion seleccionada, hay ubicaciones que no estan conectadas, conéctelas todas");
    					System.out.println("\nLista de Ubicaciones:");
    			        for (int k = 0; k < ubicaciones.size(); k++) {
    			            System.out.println((k+1) + ". " + ubicaciones.get(k));
    			        }
    					this.agregarArista(-1);
    					break;
    				}
    			}
	        }
	        
	    }
	    /*
	     * void ajustarMatriz() esta funcion se usa en eliminar ubicacion y sirve para
	     *  ajustar la matriz luego de haber eliminado la ubicacion y sus conexiones
	     */
	    public void ajustarMatriz(int indiceEliminar) {
	        int nuevaDimension = grafoMatriz.getPeso().length - 1;
	        // nueva matriz que se remplazara con la actual
	        Arista nuevaMatriz = new Arista(nuevaDimension);
	        

	        // Copiar los elementos de la matriz original a la nueva matriz, ignorando el indice a eliminar
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
	    /*
	     * void agregarUbicacionExtra(int numUbicaciones) 
	     * esta funcion sirve para agregar ubicacion extra luego
	     * de haber definido las ubicaciones iniciales
	     */
	    public void agregarUbicacionExtra(int numUbicaciones) {
	    	Scanner scanner = new Scanner(System.in);
	    	//ubicaciones extra
	    	System.out.print("Cuantas ubicaciones extra desea?");
	    	int respuesta = scanner.nextInt();
	    	String nombreUbicacion = "";
	    	//poner los datos de la nueva ubicacion
    		for (int i = 0; i < respuesta; i++) {
    			
    			System.out.print("Nombre de la Ubicacion "+ (char)(i+65+numUbicaciones) + ": ");
		    	nombreUbicacion = scanner.next(); // Leer el nombre de la ubicación desde la consola
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
	        //se debe agregar aristas extra para que el grafo este conectado
	        agregarAristaExtra(-1, numUbicaciones+respuesta);
	        
	    	
	    }
	    /*
	     * void agregarAristaExtra(int indice1, int numUbicaciones) 
	     * esta funcion sirve para agregar arista extra luego
	     * de haber definido las ubicaciones y aristas iniciales,
	     *  la funcion se encarga de crear una matriz nueva y 
	     *  ampliada para poder presentarla como el grafo
	     */
	    public void agregarAristaExtra(int indice1, int numUbicaciones) {
	    	//se crea la nueva matriz a remplazar
	    	Arista nueva = new Arista(numUbicaciones);
	    	//se copian los datos de la matriz antigua a la nueva
	    	for (int i = 0; i < grafoMatriz.getPeso().length; i++) {
	            for (int j = 0; j < grafoMatriz.getPeso()[i].length; j++) {
	                nueva.getPeso()[i][j] = grafoMatriz.getPeso()[i][j];
	                nueva.getTiempo()[i][j] = grafoMatriz.getTiempo()[i][j];
	            }
	        }
	    	GrafoFunciones.setGrafoMatriz(nueva);
	    	//agregar nuevas aristas
	    	agregarArista(-1);
			
	    }
	    
	    
	    //--FUNCIONES PARA EL ALGORITMO DE PRIM--
	    
	    
	    //void primPeso, implementa el algoritmo de prim con el peso para encontrar el arbol de expansion minima.
	    private void primPeso(int numVertices) {
	    	//Se crea un array boolean para verificar que cada ubicacion este en el arbol, se rellena con falsos.
	    	boolean[] dentroPrim = new boolean[numVertices];
            Arrays.fill(dentroPrim, false);

            //Se crea un array de llaves para ver el orden de cada nodo, se rellena con el valor maximo
            int[] llave = new int[numVertices];
            Arrays.fill(llave, Integer.MAX_VALUE);
            //La llave del prime nodo se pone en 0
            llave[0] = 0;
            
            //Se crea un arreglo para indicar los padres de cada nodo
            int[] padres = new int[numVertices];
            //El padre del primer nodo es -1 (no existe)
            padres[0] = -1;

            //for que sigue hasta que count llegue a todos los vertices - 1
            for (int count = 0; count < numVertices - 1; count++) {
            	//u = la ubicacion de la llave minima que no este dentro del arbol en el arreglo de llaves
                int u = minLlave(llave, dentroPrim, numVertices);
                //Se marca la ubicacion de la llave dentro del arbol
                dentroPrim[u] = true;

                //for que recorre todas las ubicaciones y verifica si tienen aristas con u
                for (int i = 0; i < numVertices; i++) {
                	//Se adquiere el peso de la arista
                    int peso = grafoMatriz.getPeso()[u][i];
                    //Si el peso es diferente de 0 y menor que la llave del nodo de destino y dicho nodo no esta en el arbol
                    if (!dentroPrim[i] && (peso != 0 && peso < llave[i])) {
                    	//El padre del nodo de destino es u
                        padres[i] = u;
                        //La llave del nodo de destino se convierte en su peso
                        llave[i] = peso;
                    }
                }
            }
            //Se imprime el arbol
            System.out.println("\nLa interruta mas corta en cuanto a distancia es:");
	        for (int i = 1; i < numVertices; i++) {
	            System.out.println(ubicaciones.get(padres[i]) + " - " + ubicaciones.get(i) + "        "
	            + grafoMatriz.getPeso()[padres[i]][i] + " kilómetros");
	        }
	        //Se llama al algoritmo de kruskal para el tiempo
	    	this.kruskalTiempo(numVertices);
	    }
	    
	    //int minLlave, se encarga de encontrar la llave con el valor minimo que no este dentro del arbol en el arreglo llaves y retorna su
	    //lugar en el arreglo
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
	    
	    
	    
	    //--FUNCIONES PARA EL ALGORITMO DE KRUSKAL
	    
	    //void kruskalTiempo, se encarga de crear un arbol de expansion minima con los valores de tiempo
	    private void kruskalTiempo(int numVertices) {
	    	//Se crea una lista de aristas que representa el arbol
	    	int[][] dentroKruskal = new int[numVertices-1][2];
            // Se buscan todos los tiempos existentes en la matriz y se ordenan por su valor
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
                int[] nodos = new int [numVertices];
                nodos =buscartiempo(tiempos[index++], numVertices);
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

            //Se imprime el algoritmo
            System.out.println("\nEn cuanto a tiempo, la interruta mas corta es:");
            for (int i = 0; i < numVertices-1; i++) {
                System.out.println(ubicaciones.get(dentroKruskal[i][0]) + " - " + ubicaciones.get(dentroKruskal[i][1]) 
                + "        " + grafoMatriz.getTiempo()[dentroKruskal[i][0]][dentroKruskal[i][1]] + " segundos");
            }
            
            this.menuOpciones();
        }
	    
	    //int buscartiempo. El objetivo de esta funcion es buscar la posicion del tiempo emviado en la matriz de tiempos, y retorna dichos
	    //valores en un arreglo de dos posiciones
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

	    //int buscarArista, se encarga de buscar el padre del vertice (nodo) enviado dentro del arreglo padre. Es una funcion recursiva que
	    //sigue llamandose hasta que se encuentre un nodo cuyo padre es el mismo.
        private int buscarArista(int[] parent, int vertex) {
        	//Si el padre del nodo de origen/destino de la proxima arista no es ese mismo nodo
            if (parent[vertex] != vertex)
            	//El padre del nodo es el resultado de esta misma funcion con el padre del nodo anterior 
                parent[vertex] = buscarArista(parent, parent[vertex]);
            //Se retorna el padre del nodo de origen/destino
            return parent[vertex];
        }

        //void union, se encarga de unir el padre del nodo inicial con el padre del nodo de destino
        private void union(int[] parent, int x, int y) {
        	//xSet = el padre de x
            int xSet = buscarArista(parent, x);
            //ySet = el padre de y
            int ySet = buscarArista(parent, y);
            //El padre de ySet es xSet
            parent[ySet] = xSet;

        }
	    
	    
	    /*
	     * void dijkstra() este algoritmo de dijkstra se encarga deencontarr la ruta mas corta 
	     * de la matriz de adyacencia del grafo, este algoritmo te pide un destino de inicio y un destino final
	     * y recorrera las ubicaciones con menor peso y las marcara como visitadas para no utilizalas de nuevo
	     */
	    public void dijkstra() {
	    	
	    	int inicio = 0, fin = 0;
	    	Scanner scanner = new Scanner(System.in);
	    	//imprime la lista de ubicaciones
	    	System.out.println("\nLista de Ubicaciones:");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	        }
	        //el usuario coloca los indices de las ubicaciones que sea calcular la ruta
	    	System.out.print("Indique el numero de la ubicacion en la que quiere iniciar: ");
	    	inicio= scanner.nextInt();
	    	inicio-=1;
	    	System.out.print("Indique el numero de la ubicacion en la que quiere terminar: ");
	    	fin= scanner.nextInt();
	    	fin-=1;
	    	// nombrar varaiables y arreglos para almacenar los visitados y las distancias
	        int numVertices = ubicaciones.size();
	        int[] distancias = new int[numVertices];
	        boolean[] visitado = new boolean[numVertices];
	        int[] distanciasPeso = new int[numVertices];
	        boolean[] visitadoPeso = new boolean[numVertices];
	        int[] distanciasTiempo = new int[numVertices];
	        boolean[] visitadoTiempo = new boolean[numVertices];
	        
	        int[][] grafoCombinado = new int[numVertices][numVertices];
	        // creacion del grafo combinado
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
	            	//si la ubicacion no ha sido visitada y la posicion en el grafo es diferente de cero,
	            	//es decir que tiene una arista y ademas  si la distancia desde el nodo de inicio hasta 
	            	//  el nodo v a través del nodo actual u es menor que la distancia actualmente conocida
	            	//desde el inicio hasta v, entones actualiza la distancia conocida desde el inicio hasta el nodo v con la nueva distancia calculada 
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

	        // Print la distancia mas corta en tiempo, peso y combinado
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
	        // imprime grafos de tiempo y peso
	        imprimirgrafoMatriz();
	    }
	    /*
	     * int minDistance(int[] distancias, boolean[] visitado)
	     * funcion utilizada en dijkstra para determinar la ubicacion con menor distancia 
	     * a la que se puede ir , siempre y cuando no este visitado
	     */
	    public int minDistance(int[] distancias, boolean[] visitado) {
	        int min = Integer.MAX_VALUE;
	        int minIndex = -1;
	        int numVertices = distancias.length;
	        //for para tomar la distancia/tiempo minimo
	        for (int v = 0; v < numVertices; v++) {
	            if (!visitado[v] && distancias[v] <= min) {
	                min = distancias[v];
	                minIndex = v;
	            }
	        }

	        return minIndex;
	    }
	    /*
	     * void floydWarshall()
	     * Este método implementa el algoritmo de Floyd-Warshall
	     *  para encontrar los caminos más cortos entre todos los pares 
	     *  de vértices en un grafo ponderado. Si no hay coneccion directa
	     *   al final en la matriz presentara un INF de infinito
	     */
	    public void floydWarshall() {
	    	
	        int numVertices = ubicaciones.size();
	        //bariasbles para peso y tiempo
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
	                	// asignar infinito a las ubicaciones que no estan conectadas directamente
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
	                	//Verifica si hay una conexión directa entre el nodo i y el nodo k y entre k y j
	                	//Verifica si la suma de las distancias desde el nodo i hasta el nodo k y desde el nodo k 
	                	//hasta el nodo j es menor que la distancia directa desde el nodo i hasta el nodo j.
	                	//Si esta condición se cumple, significa que se ha encontrado una ruta más corta desde
	                	//el nodo i hasta el nodo j pasando por el nodo k.
	                    if (distancias[i][k] != Integer.MAX_VALUE && distancias[k][j] != Integer.MAX_VALUE && 
	                        distancias[i][k] + distancias[k][j] < distancias[i][j]) {
	                    	//se actualiza la distancia conocida por la nueva de ij pasando por k
	                        distancias[i][j] = distancias[i][k] + distancias[k][j];
	                        // Actualiza el camino más corto conocido desde el nodo i hasta el nodo j
	                        //con el camino más corto conocido desde el nodo k hasta el nodo j
	                        camino[i][j] = camino[k][j];
	                    }
	                    if (distanciasTiempo[i][k] != Integer.MAX_VALUE && distanciasTiempo[k][j] != Integer.MAX_VALUE && 
		                        distanciasTiempo[i][k] + distanciasTiempo[k][j] < distanciasTiempo[i][j]) {
		                        distanciasTiempo[i][j] = distanciasTiempo[i][k] + distanciasTiempo[k][j];
		                        caminoTiempo[i][j] = caminoTiempo[k][j];
		                    }
	                    // compara la suma de distancias entre diferentes posiciones de la matriz para saber si estan indirectamente conectadas
	                }
	            }
	        }
	        System.out.print("Resultados con el peso\n");
	        System.out.print("  ");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	        	System.out.print(String.format("%3c ",(char) ('A' + i)));
	        }
	        System.out.println();
	        
	        // Imprimir los resultados
	        for (int i = 0; i < numVertices; i++) {
	        	System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < numVertices; j++) {
	                // Si la distancia es Integer.MAX_VALUE, significa que no hay conexión directa
	                if (distancias[i][j] == Integer.MAX_VALUE) {
	                    // En este caso, se imprime "INF" para indicar que no hay conexión directa
	                    
	                    System.out.print(String.format("%3s ","INF"));
	                } else {
	                    // Si hay una conexión directa, se imprime la distancia
	                    System.out.print(String.format("%3d ",distancias[i][j]));
	                }
	            }
	            System.out.println();
	        }
	        System.out.print("Resultados con el tiempo\n");
	        // Imprimir los resultados
	        System.out.print("  ");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	        	System.out.print(String.format("%3c ",(char) ('A' + i)));
	        }
	        System.out.println();
	        for (int i = 0; i < numVertices; i++) {
	        	System.out.print((char) ('A' + i) + " ");
	            for (int j = 0; j < numVertices; j++) {
	                // Si la distancia es Integer.MAX_VALUE, significa que no hay conexión directa
	                if (distanciasTiempo[i][j] == Integer.MAX_VALUE) {
	                    // En este caso, se imprime "INF" para indicar que no hay conexión directa
	                	System.out.print(String.format("%3s ","INF"));
	                } else {
	                    // Si hay una conexión directa, se imprime la distancia
	                    System.out.print(String.format("%3d ",distanciasTiempo[i][j]));
	                }
	            }
	            System.out.println();
	        }
	    }
	    /*
	     * void rutaPersonalizada()
	     * esta funcion te permite  escoger un destino inicial y uno final, para luego
	     * ir eligiendo la ruta paso por paso como lo desee el usuario, haciendo mas iterativo el camino
	     */
	    public void rutaPersonalizada() {
	    	int peso =0, tiempo = 0;
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.println("\nLista de Ubicaciones:");
	        for (int i = 0; i < ubicaciones.size(); i++) {
	            System.out.println((i+1) + ". " + ubicaciones.get(i));
	        }
	        // el usuario digita la ubicacion inicial y final
	    	System.out.print("Desde que ubicacion desea empezar?\n");
	    	int inicio = scanner.nextInt() -1;
	    	System.out.print("Cual es la ubicacion de destino?\n");
	    	int destino = scanner.nextInt() -1;
	    	// inicialmente la actual debe ser el inicio
	    	int actual = inicio;
	    	//mostrar ubicaciones directamente conectadas para avanzar
	    	System.out.print("Las siguientes ubicaciones son las disponibles para avanzar en la ruta\n");
	    	while(actual!=destino){
	    		System.out.print("Ubicacion Actual: " + (actual+1)+"\n");
	    		for (int i = 0; i < ubicaciones.size(); i++) {
					if(grafoMatriz.getPeso()[actual][i]!=0) {
						System.out.print((i+1)+" - "+ubicaciones.get(i)+" con un peso de "+grafoMatriz.getPeso()[actual][i]+
								" y un tiempo de "+grafoMatriz.getTiempo()[actual][i]+"\n");
					}
				}
	    		// el usuario digita la ubicacion deseada
	    		System.out.print("A que ubicacion desea ir? Coloque el indice: ");
	    		int ruta = scanner.nextInt()-1;
	    		boolean conectado = false;
	    		// metodo para verificar que la ubicacion existe
	    		while(!conectado){
	    			// si el indice digitado es mayor que el numero total de ubicaciones, es porque la ubicacion no existe
	    			if(ruta>=ubicaciones.size()) {
	    				System.out.print("Ingrese un destino existente: ");
	    	    		ruta = scanner.nextInt()-1;
	    			}
		    		else {
		    			// si el peso en la el indice digitado por la ubicacion actual y la ruta significa quye estan conectados
		    			if(grafoMatriz.getPeso()[actual][ruta]==0) {
		    				System.out.print("Ingrese un destino conectado a su ubicaacion actual: ");
		    	    		ruta = scanner.nextInt()-1;
		    			}
		    			else
		    				conectado = true;
		    		}
	    		}
	    		// suma de pesos y tiempo para presentarlos al final
	    		peso+= grafoMatriz.getPeso()[actual][ruta];
	    		tiempo+= grafoMatriz.getTiempo()[actual][ruta];
	    		
	    		actual=ruta;
	    	}
	    	// imprime resultados
	    	System.out.print("\nEl peso final fue de "+peso+" y el tiempo final fue de "+tiempo);
	    	
	    }
	
}