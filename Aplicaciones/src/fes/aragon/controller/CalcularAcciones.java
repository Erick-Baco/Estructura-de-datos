package fes.aragon.controller;

import java.io.BufferedReader;
import java.io.FileReader;

import fes.aragon.utilerias.dinamicas.cola.Cola;

public class CalcularAcciones {

	int contador = 0;
	public int contadorDividido = 0;
	int accionesTotales = 0;
	int accionesVendidas = 0;
	int ganancia = 0;
	int sumaPrecios = 0;
	int cuentaPrecios = 0;
	int promedio = 0;
	Cola<Integer> accionesTotalesC = new Cola<Integer>();
	Cola<Integer> accionesTotalesC2 = new Cola<Integer>();
	Cola<String> datosDivididos = new Cola<String>();
	Cola<Integer> precios = new Cola<Integer>();
	Cola<Integer> precios2 = new Cola<Integer>();
	Cola<Integer> dias = new Cola<Integer>();
	Cola<Boolean> compraOVenta = new Cola<Boolean>();
	String tmpTxt;

	public void calcularGanancia(String direccion, Cola<String> cola) {
		try {

			BufferedReader bf = new BufferedReader(new FileReader(direccion));
			String tmp;
			String bfRead;

			while ((bfRead = bf.readLine()) != null) {
				cola.insertar(bfRead);
				contador++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < contador; i++) {

			int inicioCadena = 0;

			try {
				tmpTxt = cola.extraer();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (int j = 0; j < tmpTxt.length(); j++) {
				if (tmpTxt.charAt(j) == ';') {
					datosDivididos.insertar(tmpTxt.substring(inicioCadena, j));
					inicioCadena = j + 1;
					contadorDividido++;
				}
			}

			datosDivididos.insertar(tmpTxt.substring(inicioCadena));
			contadorDividido++;
		}

		int decision = 1;
		int sumaOResta = 0;
		for (int i = 0; i < contadorDividido; i++) {

			if (decision == 5) {
				decision = 1;
			}

			switch (decision) {

			case 4:
				try {
					dias.insertar(Integer.parseInt(datosDivididos.extraer()));
				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (Exception e) {

					e.printStackTrace();
				}

				decision++;

				break;

			case 3:
				try {
					int tmp = Integer.parseInt(datosDivididos.extraer());
					precios.insertar(tmp);
					precios2.insertar(tmp);
				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (Exception e) {

					e.printStackTrace();
				}

				decision++;
				break;

			case 2:
				if (sumaOResta == 1) {

					try {
						int tmp = Integer.parseInt(datosDivididos.extraer());
						accionesTotalesC.insertar(tmp);
						accionesTotalesC2.insertar(tmp);
						accionesVendidas += tmp;
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {

					int tmp = 0;
					try {
						tmp = Integer.parseInt(datosDivididos.extraer());
						accionesTotales += tmp;
						accionesTotalesC.insertar(tmp);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				decision++;
				break;

			case 1:
				try {

					String tmp = datosDivididos.extraer();
					if (tmp.equals("V")) {
						sumaOResta = 1;
						compraOVenta.insertar(true);
					} else {
						compraOVenta.insertar(false);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				decision++;
				break;

			default:
				break;
			}

		}

		int pivote = 0;

		Boolean vOc = null;
		for (int i = 1; i <= contador; i++) {

			try {
				vOc = compraOVenta.extraer();
				// System.out.println(vOc);
			} catch (Exception e) {

				e.printStackTrace();
			}

			try {
				if (vOc) {
					int pivote2 = accionesTotalesC2.extraer();
					int precioActual = precios.extraer();
					int precioDeCompra = precios2.extraer();
					int accionesTotalesV = accionesTotalesC.extraer();
					for (int k = 1; k <= accionesTotalesV; k++) {
						ganancia = ganancia - (precioDeCompra - precioActual);
						pivote--;

						if (pivote2 == 0) {
							precioActual = precios2.extraer();
							pivote2 = accionesTotalesC2.extraer();
						}
					}
				} else {
					accionesTotalesC.extraer();
					precios.extraer();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("El resultado de las ganancias de captial fueron de : " + ganancia);
	}

}
