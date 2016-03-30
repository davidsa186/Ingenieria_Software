package com.example.davidsanta.mirestaurante;

public class Reserva {
	private int idReserva;
	private int docCliente;
	private int cantidad;
	private String fecha;

	public Reserva(int idCliente, int cantidad, String fecha) {
        this.docCliente = idCliente;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
    public Reserva() {

    }
	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getDocCliente() {
		return docCliente;
	}

	public void setDocCliente(int idCliente) {
		this.docCliente = idCliente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

    @Override
    public String toString() {
        return "Reserva{" +
                "docCliente=" + docCliente +
                ", cantidad=" + cantidad +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
