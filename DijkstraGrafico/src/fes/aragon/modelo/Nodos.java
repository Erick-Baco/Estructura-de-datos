package fes.aragon.modelo;

public class Nodos {
	
	private Integer numNodo;
	
	private Double xx;
	
	private Double yy;
	
	private Integer valor;

	public Double getXx() {
		return xx;
	}

	public void setXx(Double xx) {
		this.xx = xx;
	}

	public Double getYy() {
		return yy;
	}

	public void setYy(Double yy) {
		this.yy = yy;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getNumNodo() {
		return numNodo;
	}

	public void setNumNodo(Integer numNodo) {
		this.numNodo = numNodo;
	}

	public Nodos() {
		super();
	}

	public Nodos(Integer numNodo, Double xx, Double yy, Integer valor) {
		super();
		this.numNodo = numNodo;
		this.xx = xx;
		this.yy = yy;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "[ Nodo = " + numNodo + ", valor = " + valor + " ]";
	}

	
	
	
	
}
