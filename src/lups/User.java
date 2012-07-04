package lups;

import java.util.HashMap;

public class User {
	
	/*
	 * ringflags für die vers. Sicherheitsstufen
	 * 0 = Admin
	 * 1 = Prüfender
	 * 2 = Lehrender
	 * 3 = Student
	 */
	private transient final int flag;
	
	private transient final String email;
	
	private String name;
	
	private String passwort;
	
	private HashMap<String, String> adresse = new HashMap<String, String>();
		
	private int semester = 0;
	
	private String fach = null;
	
	private String matnr = null;
	
	private HashMap<String, Double> leistung = new HashMap<String, Double>();
		
	private HashMap<String, Boolean> kurse = new HashMap<String, Boolean>();
	
	public User(String email, String name, String pass, int flag) {
		this.email = email;
		this.name = name;
		this.passwort = pass;
		this.flag = flag;
	}
	
	/*
	 * getter für user
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPass(String pass) {
		this.passwort = pass;
	}
	
	public void setAdr(HashMap<String, String> adr) {
		this.adresse = adr;
	}
	
	/*
	 * setter nur für studenten; 
	 * initialisiert mit null
	 */
	public void setMatNr(String matnr) {
		this.matnr = matnr;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public void setFach(String fach) {
		this.fach = fach;
	}
	
	public void setLeistung(HashMap<String, Double> leistung) {
		this.leistung = leistung;
	}
	
	public void setKurse(HashMap<String, Boolean> kurse) {
		this.kurse = kurse;
	}
	
	/*
	 * getter der user
	 */
	public int getFlag() {
		return this.flag;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPass() {
		return this.passwort;
	}
	
	public String getName() {
		return this.name;
	}
	
	public HashMap<String, String> getAdr() {
		return this.adresse;
	}
	
	/*
	 * getter nur für studenten;
	 */
	public String getMatNr() {
		return this.matnr;
	}
	
	public int getSemester() {
		return this.semester;
	}
	
	public String getFach() {
		return this.fach;
	}
	
	public HashMap<String, Double> getLeistung() {
		return this.leistung;
	}
	
	public HashMap<String, Boolean> getKurse() {
		return this.kurse;
	}
}
