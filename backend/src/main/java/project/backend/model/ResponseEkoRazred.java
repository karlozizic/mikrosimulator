package project.backend.model;

import java.util.List;

public class ResponseEkoRazred {
	
	EkoRazred ekoRazred;
	List<EkoRazred> listaEkoRazreda;
	boolean success;
	String message;
	
	public ResponseEkoRazred(EkoRazred ekoRazred, List<EkoRazred> listaEkoRazreda, boolean success, String message) {
		super();
		this.ekoRazred = ekoRazred;
		this.listaEkoRazreda = listaEkoRazreda;
		this.success = success;
		this.message = message;
	}
	public EkoRazred getEkoRazred() {
		return ekoRazred;
	}
	public void setEkoRazred(EkoRazred ekoRazred) {
		this.ekoRazred = ekoRazred;
	}
	public List<EkoRazred> getListaEkoRazreda() {
		return listaEkoRazreda;
	}
	public void setListaEkoRazreda(List<EkoRazred> listaEkoRazreda) {
		this.listaEkoRazreda = listaEkoRazreda;
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
