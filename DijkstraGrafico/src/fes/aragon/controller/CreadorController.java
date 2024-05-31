package fes.aragon.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import fes.aragon.inicio.CDatos;
import fes.aragon.inicio.Union;
import fes.aragon.modelo.Nodos;
import fes.aragon.utilerias.dinamicas.listadoble.ListaDoble;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class CreadorController {

	@FXML
	private Button btnNodo;

	@FXML
	private Button btnUnir;

	@FXML
	private Button btnImprimir;

	@FXML
	private Button btnLimpiar;

	@FXML
	private Label lblTitulo;

	@FXML
	private Canvas lienzo;

	@FXML
	private Pane pane;

	private GraphicsContext graficos;

	private Image nodo = new Image("/fes/aragon/recursos/R.png");

	private boolean bandera = false;

	private static boolean banderaUnir = false;

	private double x, y;

	private ListaDoble<Nodos> grafo = new ListaDoble<>();

	private ListaDoble<ListaDoble<Boolean>> adyacencia = new ListaDoble<>();

	private int indice = 0;

	private static Integer valor;

	private static ObservableList<Integer> indicesObservable;

	private static Integer unirUno;

	private static Integer unirDos;

	private String nombreArchivo = "/Users/erickbaco/eclipse-workspace/ColchonDos/src/fes/aragon/recursos/ImpresiondeGrafo.txt";


	// se activa cuando se presiona el boton "nodo nuevo"
	// habilita el metodo "pintarNodo" con la bandera en true
	
	@FXML
	void capturar(ActionEvent event) {

		bandera = true;
	}

	
	// regresa la banderaUnir a falso
	// carga los indices del grafo en una lista observable
	// lanza la ventana para elegir que nodos se van a unir
	// CONTINUA EN UnionController
	// con los valores establecidos en unirUno y unirDos se hace la unión 
	// si los valores de unirUno y unirDos no son nulos se va al metodo conectar linea 167
	
	@FXML
	void metodoUnir(ActionEvent event) {

		banderaUnir = false;

		Union ventanaUnion = new Union();

		indicesObservable = FXCollections.observableArrayList(getIndices(grafo));

		ventanaUnion.showAndWait();

		graficos = lienzo.getGraphicsContext2D();

		graficos.setStroke(Color.BLACK);
		if (banderaUnir) {
			if (unirUno == unirDos) {
				graficos.strokeArc(grafo.obtenerNodo(unirUno).getXx() - 60, grafo.obtenerNodo(unirUno).getYy() - 30, 60,
						65, 330, 360, ArcType.CHORD);
			} else {
				graficos.strokeLine(grafo.obtenerNodo(unirUno).getXx(), grafo.obtenerNodo(unirUno).getYy() + 20,
						grafo.obtenerNodo(unirDos).getXx(), grafo.obtenerNodo(unirDos).getYy() + 20);
			}
		}

		if (unirUno != null & unirDos != null) {
			conectar(unirDos, unirUno);
		}

	}

	// se activa cuando se presiona el cualquier parte del lienzo
	// lo primero que se revisa es si está habilitada la creación, permiso desde linea 80
	// después recupera las posiciones y lanza la ventana nueva que viene de "CDatos"
	// retoma el control de CDatos controller
	// pinta el nodo y el numero de nodo
	// entra metodo guardar linea 178
	// regresa la bandera a false
	
	@FXML
	void pintarNodo(MouseEvent event) {

		CDatos nueva = new CDatos();
		if (bandera) {
			x = event.getX();
			y = event.getY();
			graficos = lienzo.getGraphicsContext2D();
			nueva.showAndWait();
			if (banderaUnir) {
				graficos.drawImage(nodo, x, y);
				graficos.setStroke(Color.WHITE);
				graficos.strokeText(indice + "", x + 8.5, y + 17);
				guardar();
				indice++;
			}

			bandera = false;

		}
	}

	// renicia el grafo
	// reinicia la matriz de adyacencia
	// limpia el canvas
	
	@FXML
	void metodoLimpiar(ActionEvent event) {
		String fileName = nombreArchivo;
        int[][] matriz = leerMatrizDeArchivo(fileName);

        if (matriz != null) {
            // Ejecutar el algoritmo de Dijkstra desde el nodo 0
            int[] distancias = dijkstra(matriz, 0);
            System.out.println("Distancias desde el nodo 0:");
            System.out.println(Arrays.toString(distancias));
        }
	}
	

    private int[][] leerMatrizDeArchivo(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea = br.readLine();  // Leer la primera línea (puede ser un título o encabezado)
            linea = br.readLine();  // Leer la segunda línea (encabezado de columnas)

            String[] columnas = linea.trim().split("\\s+");
            int n = columnas.length;
            int[][] matriz = new int[n][n];

            for (int i = 0; i < n; i++) {
                linea = br.readLine().trim();
                String[] valores = linea.split("\\s+");
                for (int j = 1; j < valores.length; j++) {  // Empezar desde 1 para omitir el índice de la fila
                    matriz[i][j - 1] = Integer.parseInt(valores[j]);
                }
            }

            return matriz;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int[] dijkstra(int[][] graph, int src) {
        int n = graph.length;
        int[] dist = new int[n]; // Distancia mínima desde src a cada nodo
        boolean[] sptSet = new boolean[n]; // Conjunto de nodos incluidos en el camino más corto

        // Inicializar todas las distancias como INFINITO y sptSet[] como falso
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0; // La distancia del nodo fuente a sí mismo es siempre 0

        for (int count = 0; count < n - 1; count++) {
            // Seleccionar el vértice de distancia mínima que aún no está procesado
            int u = minDistance(dist, sptSet);

            // Marcar el vértice u como procesado
            sptSet[u] = true;

            // Actualizar dist[] de los vértices adyacentes al vértice seleccionado
            for (int v = 0; v < n; v++) {
                if (!sptSet[v] && graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        return dist;
    }

    private int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }
	// cambia a true las uniones en la matriz de adyacencia
	// regresa a unirNodo linea 118
	public void conectar(Integer ix, Integer iy) {

		adyacencia.obtenerNodo(ix).asignar(iy, true);
		adyacencia.obtenerNodo(iy).asignar(ix, true);

	}

	
	// toma los valores de indice y valor y los guarda en el grafo
	// regresa el control a linea 144
	
	public void guardar() {
		grafo.agregarEnCola(new Nodos(indice, x + 10, y - 10, valor));
		adyacencia.agregarEnCola(new ListaDoble<>());
		for (int i = 0; i < adyacencia.getLongitud(); i++) {
			adyacencia.obtenerNodo(i).agregarEnCola(false);
		}
		for (int i = 1; i < adyacencia.getLongitud(); i++) {
			adyacencia.obtenerCola().agregarEnCola(false);
			/*
			 * for (int j = 0; j < adyacencia.obtenerNodo(0).getLongitud()-
			 * adyacencia.obtenerNodo(i).getLongitud(); j++) {
			 * adyacencia.obtenerNodo(i).agregarEnCola(false); }
			 */
		}
	}

	// escribe sin borrar lo anterior la nueva forma del grafo usando la matriz de adyacencia para representar las conexiones
	// se activa cuando se presiona el boton "imprimir"
	@FXML
	void metodoEscribir(ActionEvent event) {

		FileWriter archivoEscritura;
		try {
			archivoEscritura = new FileWriter(nombreArchivo);
			BufferedWriter writer = new BufferedWriter(archivoEscritura);
			
			writer.write("Matriz de Adyacencia");
			writer.newLine();
			writer.write("  ");
			for (int i = 0; i < adyacencia.getLongitud(); i++) {
				writer.write("  " + i + "   ");
			}
			writer.newLine();
			for (int i = 0; i < adyacencia.getLongitud(); i++) {
	            writer.write(i + " ");
	            for (int j = 0; j < adyacencia.obtenerNodo(i).getLongitud(); j++) {
	                // Escribir 1 para true y 0 para false
	                writer.write((adyacencia.obtenerNodo(i).obtenerNodo(j) ? 1 : 0) + " ");
	            }
	            writer.newLine();
	            writer.newLine();
	        }

			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static Integer getValor() {
		return valor;
	}

	public static void setValor(int valor) {
		CreadorController.valor = valor;
	}

	public ArrayList<Integer> getIndices(ListaDoble<Nodos> grafo) {
		ArrayList<Integer> indices = new ArrayList<>();
		for (int i = 0; i < grafo.getLongitud(); i++) {
			indices.add(grafo.obtenerNodo(i).getNumNodo());
		}
		return indices;
	}

	public static ObservableList<Integer> getIndicesObservable() {
		return indicesObservable;
	}

	public static void setIndicesObservable(ObservableList<Integer> indicesObservable) {
		CreadorController.indicesObservable = indicesObservable;
	}

	public static Integer getUnirUno() {
		return unirUno;
	}

	public static void setUnirUno(Integer unirUno) {
		CreadorController.unirUno = unirUno;
	}

	public static Integer getUnirDos() {
		return unirDos;
	}

	public static void setUnirDos(Integer unirDos) {
		CreadorController.unirDos = unirDos;
	}

	public static boolean isBandera() {
		return banderaUnir;
	}

	public static void setBanderaUnir(boolean bandera) {
		CreadorController.banderaUnir = bandera;
	}

}
