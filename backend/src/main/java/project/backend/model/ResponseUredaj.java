package project.backend.model;

import java.util.List;

public class ResponseUredaj {
	Uredaj uredaj;
	List<Uredaj> listaUredaja;
	boolean success;
	String message;
	
	public ResponseUredaj(Uredaj uredaj, List<Uredaj> listaUredaja, boolean success, String message) {
		super();
		this.uredaj = uredaj;
		this.listaUredaja = listaUredaja;
		this.success = success;
		this.message = message;
	}
	public Uredaj getUredaj() {
		return uredaj;
	}
	public void setUredaj(Uredaj uredaj) {
		this.uredaj = uredaj;
	}
	public List<Uredaj> getListaUredaja() {
		return listaUredaja;
	}
	public void setListaUredaja(List<Uredaj> listaUredaja) {
		this.listaUredaja = listaUredaja;
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
