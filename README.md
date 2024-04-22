(este programa fue creado como el proyecto final de la materia Algoritmos Clasicos y estructura de datos, de parte de Eduardo Genao (10149035) y Jean Carlos Perez)

SISTEMA DE GESTIÓN DE RUTAS

EL programa aquí presente tiene como propósito el manejo de grafos, representados como ubicaciones y rutas, de forma iterativa implementando los diferentes algoritmos enseñados a lo largo de la clase para conseguir las rutas más efectivas de un lugar a otro. El programa maneja los grafos desde matrices de adyacencia, usando dos, una para la distancia entre las ubicaciones, y otra para el tiempo de dichas conecciones. Ambas matrices son indepedientes una de la otra, pero poseen numeros en los mismos lugares para mantener consistente el numero de rutas, tambien se guarda el nombre de las ubicaciones en un ArrayList separado.

Luego de inicializar las variables globales, el programa inicia preguntandole al usuario si quiere usar el dataDumping (una coleccion de rutas predeterminada) para iniciarlizar el programa, de no ser así, el programa procedera a la insercion manual de ubicaciones y rutas. Tras esto, se le presentara al usuario un menu de opciones, en el que el puede decidir que quiere hacer siguiente. Las opciones son las siguientes:

1. Agregar Ubicacion: Se agrega una ubicacion (nodo) reemplazando las matrices originales con una copia ampliada de ellas mismas, tambien le pregunta el nombre de la ubicacion y las aristas.

2. Editar ubicacion: Se le permite al usuario cambiar el nombre de una ubicacion o editar las rutas relacionadas a ellas, pudiendo cambiar valores de distancia/tiempo o agregar o eliminar una ruta.

3. Eliminar Ubicacion: Lo inverso a agregar una ubicacion, se reduce la matriz y se quita la ubicacion especificada.

4. Imprimir Matrices: Se vuelve a imprimir las matrices, se vuelve a preguntar por otra accion inmediatamente.

5. Encontrar ruta mas corta: Se invoca el algoritmo de Dijkstra para encontrar el camino mas corto entre dos ubicaciones.

6. Ver rutas interconectadas minimas: Se invocan los algoritmos de Prim (distancia) y Kruskal (tiempo) para crear arboles de expansion minima

7. Camino mas corto entre todas las ubicaciones: Se invoca al algoritmo de Floyd Warshall para encontrar la ruta mas corta para cada una de las ubicaciones

8. Ruta personalizada.

9. Salir: El usuario pide finalizar el programa y este termina de correr
.....
