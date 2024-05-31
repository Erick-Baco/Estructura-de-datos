package fes.aragon.modelo;

import java.util.concurrent.ThreadLocalRandom;

import fes.aragon.utilerias.dinamicas.listadoble.ListaDoble;
import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;

public class pruebas {
	public static void main(String[] args) {
		ListaDoble<ListaDoble<Nodos>> grafoo = new ListaDoble<>();

		ListaDoble<Nodos> datos = new ListaDoble<>();
		ListaSimple<Boolean> con = new ListaSimple<Boolean>();

		/*for (int j = 0; j < 5; j++) {
			con.agregarEnCola(false);
			for (int i = 0; i < 5; i++) {
				int v = ThreadLocalRandom.current().nextInt(1, 100);
				datos.agregarEnCola(new Nodos(0, 0, v, i, con));
			}

			grafoo.agregarEnCola(datos);
			datos = new ListaDoble<>();
		}

		for (int i = 0; i < grafoo.getLongitud(); i++) {
			System.out.println("========");
			for (int j = 0; j < grafoo.obtenerNodo(i).getLongitud(); j++) {
				System.out.print(grafoo.obtenerNodo(i).obtenerNodo(j).toString());
				grafoo.obtenerNodo(i).obtenerNodo(j).getConexiones().imprimirElementos();
			}
		}*/

		//==================================================
		ListaDoble<Nodos> grafo = new ListaDoble<>();
		con = new ListaSimple<Boolean>();
		for (int i = 0; i < 5; i++) {
			double v = ThreadLocalRandom.current().nextInt(1, 100);
			con.agregarEnCola(true);
			grafo.agregarEnCola(new Nodos(0, v, v, 0));
		}
		

		System.out.println("Grafo");
		for (int j = 0; j < grafo.getLongitud(); j++) {
			System.out.println("========");
			System.out.print(grafo.obtenerNodo(j).toString());
	
		}

	}
}
