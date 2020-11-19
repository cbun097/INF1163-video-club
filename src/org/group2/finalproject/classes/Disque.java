package org.group2.finalproject.classes;

public class Disque {
	
	String codeFilm;
	String codeDisque;
	String typeDeDisque;
	
	public Disque(String codeFilm, String codeDisque, String typeDeDisque) {
		super();
		this.codeFilm = codeFilm;
		this.codeDisque = codeDisque;
		this.typeDeDisque = typeDeDisque;
	}
	
	public String getCodeFilm() {
		return codeFilm;
	}
	
	public void setCodeFilm(String codeFilm) {
		this.codeFilm = codeFilm;
	}
	
	public String getCodeDisque() {
		return codeDisque;
	}
	
	public void setCodeDisque(String codeDisque) {
		this.codeDisque = codeDisque;
	}
	
	public String getTypeDeDisque() {
		return typeDeDisque;
	}
	
	public void setTypeDeDisque(String typeDeDisque) {
		this.typeDeDisque = typeDeDisque;
	}
	
	@Override
	public String toString() {
		return "Disque [codeFilm=" + codeFilm + ", codeDisque=" + codeDisque + ", typeDeDisque=" + typeDeDisque + "]";
	}
}
