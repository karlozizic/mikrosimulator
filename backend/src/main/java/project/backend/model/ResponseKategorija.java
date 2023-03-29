package project.backend.model;

import java.util.List;

public class ResponseKategorija {
	Kategorija kategorija;
	List<Kategorija> listaKategorija;
	boolean success;
	String message;
	
	public ResponseKategorija(Kategorija kategorija, List<Kategorija> listaKategorija, boolean success,
			String message) {
		super();
		this.kategorija = kategorija;
		this.listaKategorija = listaKategorija;
		this.success = success;
		this.message = message;
	}
	public Kategorija getKategorija() {
		return kategorija;
	}
	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	public List<Kategorija> getListaKategorija() {
		return listaKategorija;
	}
	public void setListaKategorija(List<Kategorija> listaKategorija) {
		this.listaKategorija = listaKategorija;
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
