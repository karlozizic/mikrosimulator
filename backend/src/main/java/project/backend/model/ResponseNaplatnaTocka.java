package project.backend.model;

import java.util.List;

public class ResponseNaplatnaTocka {

	NaplatnaTocka naplatnaTocka;
	List<NaplatnaTocka> listaNaplatnihTocki;
	boolean success;
	String message;
	
	public ResponseNaplatnaTocka(NaplatnaTocka naplatnaTocka, List<NaplatnaTocka> listaNaplatnihTocki, boolean success,
			String message) {
		super();
		this.naplatnaTocka = naplatnaTocka;
		this.listaNaplatnihTocki = listaNaplatnihTocki;
		this.success = success;
		this.message = message;
	}
	public NaplatnaTocka getNaplatnaTocka() {
		return naplatnaTocka;
	}
	public void setNaplatnaTocka(NaplatnaTocka naplatnaTocka) {
		this.naplatnaTocka = naplatnaTocka;
	}
	public List<NaplatnaTocka> getListaNaplatnihTocki() {
		return listaNaplatnihTocki;
	}
	public void setListaNaplatnihTocki(List<NaplatnaTocka> listaNaplatnihTocki) {
		this.listaNaplatnihTocki = listaNaplatnihTocki;
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
