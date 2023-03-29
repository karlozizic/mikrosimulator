package project.backend.model;

import java.util.List;

public class ResponseDrzavaRegistracije {
	DrzavaRegistracije drzavaRegistracije;
	List<DrzavaRegistracije> listaDrzavaRegistracije;
	boolean success;
	String message;
	
	public ResponseDrzavaRegistracije(DrzavaRegistracije drzavaRegistracije,
			List<DrzavaRegistracije> listaDrzavaRegistracije, boolean success, String message) {
		super();
		this.drzavaRegistracije = drzavaRegistracije;
		this.listaDrzavaRegistracije = listaDrzavaRegistracije;
		this.success = success;
		this.message = message;
	}

	public DrzavaRegistracije getDrzavaRegistracije() {
		return drzavaRegistracije;
	}

	public void setDrzavaRegistracije(DrzavaRegistracije drzavaRegistracije) {
		this.drzavaRegistracije = drzavaRegistracije;
	}

	public List<DrzavaRegistracije> getListaDrzavaRegistracije() {
		return listaDrzavaRegistracije;
	}

	public void setListaDrzavaRegistracije(List<DrzavaRegistracije> listaDrzavaRegistracije) {
		this.listaDrzavaRegistracije = listaDrzavaRegistracije;
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
