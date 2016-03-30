package com.example.davidsanta.mirestaurante;

public class Producto {
 
    private int idProducto;
    private String nombreProducto;
    private String descProducto;
    private int valor;
 
    public Producto( String nombreProducto, String descProducto, int valor) {
		super();
		this.nombreProducto = nombreProducto;
		this.descProducto = descProducto;
		this.valor = valor;
	}

	public Producto() {

	}
     
    public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto){ this.idProducto=idProducto;}

	@Override
	public String toString() {
		return "Producto= " + nombreProducto + "\nDescripcion= " + descProducto
				+ "\nValor=" + valor;
	}
}