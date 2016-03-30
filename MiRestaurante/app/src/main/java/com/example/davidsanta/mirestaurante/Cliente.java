package com.example.davidsanta.mirestaurante;

public class Cliente {
	private int idCliente;
	private String nombreCliente;
    private int documentoCliente;
    private String password;

	public Cliente( String nombreCliente, int documentoCliente, String password) {

		this.nombreCliente = nombreCliente;
		this.documentoCliente = documentoCliente;
		this.password = password;
	}
	public Cliente() {

	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getDocumentoCliente() {
		return documentoCliente;
	}

	public void setDocumentoCliente(int documentoCliente) {
		this.documentoCliente = documentoCliente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				" nombreCliente='" + nombreCliente + '\'' +
				", documentoCliente='" + documentoCliente + '\'' +
				'}';
	}
}
