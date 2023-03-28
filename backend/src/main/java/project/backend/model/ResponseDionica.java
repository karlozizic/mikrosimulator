package project.backend.model;

import java.util.List;

public class ResponseDionica {
	
	Dionica dionica;
	List<Dionica> listaDionica;
	boolean success;
	String message;
	
	public ResponseDionica(Dionica dionica, List<Dionica> listaDionica, boolean success, String message) {
		super();
		this.dionica = dionica;
		this.listaDionica = listaDionica;
		this.success = success;
		this.message = message;
	}

	public Dionica getDionica() {
		return dionica;
	}

	public void setDionica(Dionica dionica) {
		this.dionica = dionica;
	}

	public List<Dionica> getListaDionica() {
		return listaDionica;
	}

	public void setListaDionica(List<Dionica> listaDionica) {
		this.listaDionica = listaDionica;
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
