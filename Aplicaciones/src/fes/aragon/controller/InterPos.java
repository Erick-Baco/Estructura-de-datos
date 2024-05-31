package fes.aragon.controller;

import fes.aragon.utilerias.dinamicas.pila.Pila;

public class InterPos {

	public static String transformarInPos(String cadenaEn) throws Exception {
		Pila<String> pila = new Pila<>();

		System.out.println("cadena a separar");
		System.out.println(cadenaEn);
		String[] token = cadenaEn.split(" ");
		int contador = 0;
		String simbolo;
		String cadenaSalida = "";
		boolean bandera = false;

		System.out.println("Transformar");
		for (int i = 0; i <= token.length - 1; i++) {
			System.out.print(token[i]);
		}
		System.out.println();

		while (contador < token.length) {
			simbolo = token[contador];
			if (simbolo.equals(")")) {
				bandera = false;
				while (!pila.esVacia()) {
					String arriba = pila.extraer();
					if (!arriba.equals("(") && !arriba.equals(")")) {
						cadenaSalida += arriba + " ";
					}

				}
			}

			if (bandera) {
				if (!simbolo.equals("+") && !simbolo.equals("-") && !simbolo.equals("*") && !simbolo.equals("/")
						&& !simbolo.equals("(") && !simbolo.equals(")")) {
					cadenaSalida += simbolo + " ";
				} else {
					if (!simbolo.equals("(") && !simbolo.equals(")")) {
						pila.insertar(simbolo);
					}

				}
				contador++;

			} else {
				if (!simbolo.equals("+") && !simbolo.equals("-") && !simbolo.equals("*") && !simbolo.equals("/")
						&& !simbolo.equals("(") && !simbolo.equals(")")) {
					cadenaSalida += simbolo + " ";
				} else if (!simbolo.equals("(") && !simbolo.equals(")")) {

					while (!pila.esVacia() && prc(pila.elementoSuperior(), simbolo)) {
						String simboloArriba = pila.extraer();
						if (!simboloArriba.equals("(") && !simboloArriba.equals(")")) {
							cadenaSalida += simboloArriba + " ";
						}

					}

					if (!simbolo.equals("(") && !simbolo.equals(")")) {
						pila.insertar(simbolo);
					}

				}
				contador++;
			}

		}

		while (!pila.esVacia()) {
			String simboloArriba = pila.extraer();
			if (!simboloArriba.equals("(") && !simboloArriba.equals(")")) {
				cadenaSalida += simboloArriba + " ";
			}
		}

		System.out.println("expresion posfija");
		System.out.println(cadenaSalida);
		return cadenaSalida;

	}

	public static boolean prc(String v1, String v2) {

		boolean valor = false;
		if (v1 != null && v2 != null) {
			switch (v1) {
			case "(":
				valor = ")".equals(v2) || "*".equals(v2) || "/".equals(v2) || "+".equals(v2) || "-".equals(v2);
				break;
			case "*":
				valor = "*".equals(v2) || "/".equals(v2) || "+".equals(v2) || "-".equals(v2);
				break;
			case "/":
				valor = "/".equals(v2) || "*".equals(v2) || "+".equals(v2) || "-".equals(v2);
				break;
			case "+":
				valor = "+".equals(v2) || "-".equals(v2);
				break;
			case "-":
				valor = "-".equals(v2) || "+".equals(v2);
				break;
			default:
				valor = false;
			}
		} else {
			valor = false; // o manejar el caso en el que v1 o v2 es nulo
		}

		return valor;

	}

	public static Float evaluador(String cadenaEn) throws Exception {

		System.out.println("cadena recibida");
		System.out.println(cadenaEn);
		Pila<Float> operandos = new Pila<>();

		Float primero;
		Float segundo;
		Float res = null;
		String[] token = cadenaEn.split(" ");
		int contador = 0;
		String simbolo = " ";
		Integer operando = 0;

		System.out.println("cadena dividida");
		for (int i = 0; i <= token.length - 1; i++) {
			System.out.print(token[i]);
		}
		System.out.println();

		while (contador < token.length ) {
			simbolo = token[contador];
		
			
			
			if (!simbolo.equals("+") && !simbolo.equals("-") && !simbolo.equals("*") && !simbolo.equals("/")) {
				
				operandos.insertar(Float.parseFloat(simbolo));
			} else {
				
				
				segundo = operandos.extraer();
				primero = operandos.extraer();
				
				System.out.println("primero y segundo: " + primero  + " " + segundo);

				switch (simbolo) {

				case "*":
					
					res = primero * segundo;
					
					break;

				case "/":
					res = primero / segundo;
					break;

				case "+":
					
					res = primero + segundo;
					
					break;

				case "-":
					res = primero - segundo;
					break;
				}
				
				operandos.insertar(res);
				
			}
			
			contador++;
		}

		
		return operandos.extraer();
	}

	public static void main(String[] args) throws Exception {
		InterPos operacion = new InterPos();
		Float resultado = evaluador(operacion.transformarInPos( "( 2 + 2 * 5 ) / 2  "));
		System.out.println("resultado " + resultado);
	}
}

