package lups;

import java.util.HashMap;

public class Veranstaltung {

	private String titel;
	
	private String beschreibung;
	
	private String pruefender = null;
	
	private String lehrender;
	
	private String zeit;
	
	private HashMap<String, Boolean> teilnehmer = null;
	
	/*
	 * grundkonstruktor um eine veranstaltung zu erstellen
	 */
	public Veranstaltung(String titel, String lehrender, String beschreibung, String zeit) {
		this.titel = titel;
		this.lehrender = lehrender;
		this.beschreibung = beschreibung;
		this.zeit = zeit;
	}
	
	/*
	 * allgemeine setter für veranstaltungen
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public void setLehrender(String lehrender) {
		this.lehrender = lehrender;
	}
	
	public void setPruefender(String pruefender) {
		this.pruefender = pruefender;
	}
	
	public void setZeit(String zeit) {
		this.zeit = zeit;
	}
	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public void setTeilnehmer(HashMap<String, Boolean> teilnehmer) {
		this.teilnehmer = teilnehmer;
	}
	
	/*
	 * allgemeine getter für veranstaltungen
	 */
	public String getPruefender() {
		return this.pruefender;
	}
	
	public String getZeit() {
		return this.zeit;
	}
	
	public String getBeschreibung() {
		return this.beschreibung;
	}
	
	public String getTitel() {
		return this.titel;
	}
	
	public String getLehrender() {
		return this.lehrender;
	}
	
	public HashMap<String, Boolean> getTeilnehmer() {
		return this.teilnehmer;
	}
}
