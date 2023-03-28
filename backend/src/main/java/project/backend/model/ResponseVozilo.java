package project.backend.model;

import java.util.List;

public class ResponseVozilo {
	Vozilo vozilo;
	List<Vozilo> listaVozila;
	boolean success;
	String message;
	
	public ResponseVozilo(Vozilo vozilo, List<Vozilo> listaVozila, boolean success, String message) {
		super();
		this.vozilo = vozilo;
		this.listaVozila = listaVozila;
		this.success = success;
		this.message = message;
	}
	public Vozilo getVozilo() {
		return vozilo;
	}
	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}
	public List<Vozilo> getListaVozila() {
		return listaVozila;
	}
	public void setListaVozila(List<Vozilo> listaVozila) {
		this.listaVozila = listaVozila;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
