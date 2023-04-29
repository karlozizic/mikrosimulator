package project.backend.model;

import java.util.List;

public class ResponseUredaj {
	NoviUredaj uredaj;
	List<NoviUredaj> listaUredaja;
	boolean success;
	String message;
	
	public ResponseUredaj(NoviUredaj uredaj, List<NoviUredaj> listaUredaja, boolean success, String message) {
		super();
		this.uredaj = uredaj;
		this.listaUredaja = listaUredaja;
		this.success = success;
		this.message = message;
	}
	public NoviUredaj getUredaj() {
		return uredaj;
	}
	public void setUredaj(NoviUredaj uredaj) {
		this.uredaj = uredaj;
	}
	public List<NoviUredaj> getListaUredaja() {
		return listaUredaja;
	}
	public void setListaUredaja(List<NoviUredaj> listaUredaja) {
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
