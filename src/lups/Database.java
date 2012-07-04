package lups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Database {

	private HashMap<String, Veranstaltung> veranstaltungen = new HashMap<String, Veranstaltung>();
	
	private HashMap<String, User> userlist = new HashMap<String, User>();

	
	/*
	 * aufruf grundkonstruktor und zuweisung der id veranstaltung
	 */
	public void newVeranstaltung(String IDfuckPMD, String titel, String lehrender, String beschreibung, String zeit) {
		veranstaltungen.put(IDfuckPMD, new Veranstaltung(titel, lehrender, beschreibung, zeit));
		userlist.get(lehrender).getKurse().put(IDfuckPMD, false);

	}
	
	public boolean existsVer(String titel) {
		if (veranstaltungen.isEmpty())
			return false;
		return veranstaltungen.containsValue(titel);
	}
	
	public void deleteVer(String IDfuckPMD) {
		veranstaltungen.remove(IDfuckPMD);
	}
	
	/*
	 * getter für spez. veranstaltungen
	 */
	public void setTitel(String IDfuckPMD, String titel) {
		veranstaltungen.get(IDfuckPMD).setTitel(titel);
	}
	
	public void setBeschreibung(String IDfuckPMD, String beschreibung) {
		veranstaltungen.get(IDfuckPMD).setBeschreibung(beschreibung);
		
	}
	
	public void setPruefender(String IDfuckPMD, String pruefender) {
		veranstaltungen.get(IDfuckPMD).setPruefender(pruefender);
		userlist.get(pruefender).getKurse().put(IDfuckPMD, false);
	}
	
	public void setLehrender(String IDfuckPMD, String lehrender) {
		String delete = veranstaltungen.get(IDfuckPMD).getLehrender();
		HashMap<String, Boolean> old = userlist.get(delete).getKurse();
		old.remove(delete);
		userlist.get(delete).setKurse(old);
		
		veranstaltungen.get(IDfuckPMD).setLehrender(lehrender);
		old.clear();
		old = userlist.get(lehrender).getKurse();
		old.put(IDfuckPMD, false);
		userlist.get(lehrender).setKurse(old);
	}
	
	public void setZeit(String IDfuckPMD, String zeit) {
		veranstaltungen.get(IDfuckPMD).setZeit(zeit);
	}
	
	public void setTeilnehmer(String IDfuckPMD, HashMap<String, Boolean> teilnehmer) {
		veranstaltungen.get(IDfuckPMD).getTeilnehmer().putAll(teilnehmer);
	}
	
	/*
	 * getter für spez. veranstaltungen
	 */
	public String getTitel(String IDfuckPMD) {
		return veranstaltungen.get(IDfuckPMD).getTitel();

	}
	
	public String getBeschreibung(String IDfuckPMD) {
		return veranstaltungen.get(IDfuckPMD).getBeschreibung();
	}
	
	public String getPruefender(String IDfuckPMD) {
		return veranstaltungen.get(IDfuckPMD).getPruefender();
	}
	
	public String getLehrender(String IDfuckPMD) {
		return veranstaltungen.get(IDfuckPMD).getLehrender();
	}
	
	public String getZeit(String IDfuckPMD) {
		return veranstaltungen.get(IDfuckPMD).getZeit();

	}
	
	/*
	 * liste der angemeldeten oder zugelassenen teilnehmer einer veranstaltung
	 * false = angemeldet
	 * true = zugelassen
	 * für gui
	 */
	public Vector<Vector<String>> getTeilnehmerliste(String IDfuckPMD, Boolean flag) {
		Iterator<String> itr = veranstaltungen.get(IDfuckPMD).getTeilnehmer().keySet().iterator();
		Vector<Vector<String>> teilnehmerliste = new Vector<Vector<String>>();
		Vector spalten = new Vector<String>();
		
		while (itr.hasNext()) {
			if (veranstaltungen.get(IDfuckPMD).getTeilnehmer().get(itr.next()) == flag) {
				spalten.add(userlist.get(itr.next()).getName());
				spalten.add(userlist.get(flag));
				teilnehmerliste.add(spalten);
			}
		}
		return teilnehmerliste;
	}
	
	/*
	 * liste der angemeldeten oder zugelassenen teilnehmer einer veranstaltung
	 * false = angemeldet
	 * true = zugelassen
	 * für den druck als liste
	 */
	public List<String> getTeilnehmerListe(String IDfuckPMD) {
		Iterator<String> itr = veranstaltungen.get(IDfuckPMD).getTeilnehmer().keySet().iterator();
		List<String> teilnehmerliste = new ArrayList<String>();
		
		while (itr.hasNext()) {
				teilnehmerliste.add(userlist.get(itr.next()).getName());
		}
		return teilnehmerliste;
	}
	
	public Vector<Vector<String>> getPruefenderliste(String Pruefender) {
		Iterator<String> itr = veranstaltungen.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();
		Vector<String> spalten = new Vector<String>();
		
		while (itr.hasNext()) {
			if (veranstaltungen.get(itr.next()).getPruefender().equals(Pruefender)) {
				spalten.add(itr.next());
				spalten.add(veranstaltungen.get(itr.next()).getTitel());
				spalten.add(Pruefender);
				liste.add(spalten);
			} else if (veranstaltungen.get(itr.next()).getPruefender().isEmpty()) {
				spalten.add(itr.next());
				spalten.add(veranstaltungen.get(itr.next()).getTitel());
				spalten.add("Noch kein Prüfender vorhanden");
				liste.add(spalten);
			}
			
		}
		return liste;
	}
	
	public Vector<Vector<String>> getVorlesungsverzeichnis() {
		Iterator<String> itr = veranstaltungen.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();
		Vector<String> spalten = new Vector<String>();
		
		while (itr.hasNext()) {
				spalten.add(itr.next());
				spalten.add(veranstaltungen.get(itr.next()).getTitel());
				spalten.add(veranstaltungen.get(itr.next()).getLehrender());
				liste.add(spalten);
		}
		return liste;
	}
	
	public HashMap<String, String> getVorlesung(String IDfuckPMD) {
		HashMap<String, String> vorlesung = new HashMap<String, String>();
		vorlesung.put("Titel", veranstaltungen.get(IDfuckPMD).getTitel());
		vorlesung.put("Beschreibung", veranstaltungen.get(IDfuckPMD).getBeschreibung());
		vorlesung.put("Lehrender", veranstaltungen.get(IDfuckPMD).getLehrender());
		vorlesung.put("Zeit", veranstaltungen.get(IDfuckPMD).getZeit());
		if (veranstaltungen.get(IDfuckPMD).getPruefender() != null) {
			vorlesung.put("Pruefender", veranstaltungen.get(IDfuckPMD).getPruefender());
		}
		return vorlesung;
	}
	/*
	 * konstruktuor für user, flag muss gleich gesetzt werden
	 */
	public void newUser(String email, String name, String pass, int flag) {
		userlist.put(email, new User(email, name, pass, flag));
	}
	
	public boolean existsUsr(String email) {
			if (userlist.isEmpty())
				return false;
		return userlist.containsKey(email);
	}
	
	public void deleteUsr(String email) {
		userlist.remove(email);
	}
	
	public Vector<String> getUserList() {
		Iterator<String> itr = userlist.keySet().iterator();
		Vector<String> liste = new Vector<String>();
	
		while (itr.hasNext()) {
				liste.add(itr.next());
		}
		return liste;
	}
	
	/**
	 * setter für spezifischen user
	 * 
	 */
	public void setName(String email, String name) {
		userlist.get(email).setName(name);
	}

	public void setPasswort(String email, String pass) {
		userlist.get(email).setPass(pass);
	}
	
	public void setMatNr(String email, String matnr) {
		userlist.get(email).setMatNr(matnr);
	}
	
	public void setSemester(String email, int semester) {
		User usr = userlist.get(email);
		usr.setSemester(semester);
	}
	
	public void setFach(String email, String fach) {
		userlist.get(email).setFach(fach);
	}
	
	public void setAdresse(String email, HashMap<String, String> adr) {
		userlist.get(email).setAdr(adr);
	}
	
	public void setNote(String email, String IDfuckPMD, Double note) {
		userlist.get(email).getLeistung().put(IDfuckPMD, note);
	}
	
	/**
	 * setzen der kurse
	 * @param email referenz auf richtigen benutzer
	 * @param id um den richtigen kurs zu setzen, zuzulassen oder zu loeschen
	 */
	public void anmeldenKurse(String email, String IDfuckPMD) {
		userlist.get(email).getKurse().put(IDfuckPMD, false);
		veranstaltungen.get(IDfuckPMD).getTeilnehmer().put(email, false);
	}
	
	public void zulassenKurse(String email, String IDfuckPMD) {
		userlist.get(email).getKurse().put(IDfuckPMD, true);
		veranstaltungen.get(IDfuckPMD).getTeilnehmer().put(email, false);
	}
	
	public void abmeldenKurse(String email, String IDfuckPMD) {
		userlist.get(email).getKurse().remove(IDfuckPMD);
		veranstaltungen.get(IDfuckPMD).getTeilnehmer().remove(email);
	}
	
	public void prueferAbmelden(String email, String IDfuckPMD) {
		userlist.get(email).getKurse().remove(IDfuckPMD);
		Iterator<String> itr = veranstaltungen.keySet().iterator();
		
		while (itr.hasNext()) {
			if (veranstaltungen.get(itr.next()).getPruefender() == email) {
				veranstaltungen.get(itr.next()).setPruefender(null);
			}
		}
	}
	
	/**
	 * getter für spezifischen user
	 * @param email referenz auf den richtigen nutzer
	 * @return das angeforderte element
	 */
	public String getName(String email) {
		return userlist.get(email).getName();
	}

	public String getPasswort(String email) {
		return userlist.get(email).getPass();
	}
	
	public String getMatNr(String email) {
		return userlist.get(email).getMatNr();
	}
	
	public int getSemester(String email) {
		return userlist.get(email).getSemester();
	}
	
	public String getFach(String email) {
		return userlist.get(email).getFach();
	}
	
	public int getFlag(String email) {
		return userlist.get(email).getFlag();
	}
	
	public HashMap<String, String> getAdresse(String email) {
		return userlist.get(email).getAdr();
	}
	
	/**
	 * leistungsuebersicht gui
	 * @param email referenz auf den richtigen nutzer
	 * @return vector fuer gui table-model; bestend aus id und note
	 */
	public Vector<Vector<String>> getLeistung(String email) {
		HashMap<String, Double> noten = userlist.get(email).getLeistung();
		Iterator<String> itr = noten.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();
		Vector<String> spalten = new Vector<String>();
		
		while (itr.hasNext()) {
				spalten.add(itr.next());
				spalten.add(noten.get(itr.next()).toString());
				liste.add(spalten);
		}
		return liste;
	}	
	
	/**
	 * leistungsuebersicht fuer pdf
	 * @param email referenz auf den richtigen nutzer
	 * @return stringliste fuer den pdfdruck; bestehend aus fach, wertung und note
	 */
	public List<String> getLeistungsuebersicht(String email) {
		HashMap<String, Double> noten = userlist.get(email).getLeistung();
		Iterator<String> itr = noten.keySet().iterator();
		List<String> liste = new ArrayList<String>();
	
		while (itr.hasNext()) {
				liste.add(itr.next());
				if ( noten.get(itr.next()) <= 4.0) {
					liste.add("Bestanden");
				} else {
					liste.add("nicht Bestanden");
				}
				liste.add(noten.get(itr.next()).toString());
								
		}
		return liste;
	}	
	
	/**
	 * kursliste fuer pdf
	 * @param email referenz auf den richtigen nutzer
	 * @return stringliste fuer den pdfdruck; bestehnd aus faechernamen
	 */
	public List<String> getKursListe(String email) {
		Iterator<String> itr = userlist.get(email).getKurse().keySet().iterator();
		List<String> liste = new ArrayList<String>();
		
		while (itr.hasNext()) {
			if (!userlist.get(email).getLeistung().containsKey(itr.next())) {
				liste.add(veranstaltungen.get(itr.next()).getTitel());
			}
		}
		return liste;
	}
	
	/**
	 * kursliste fuer gui erstellen
	 * @param email referenz auf den richtigen nutzer
	 * @return vector fuer gui table-model; bestehend aus id, titel, lehrender und status
	 */
	public Vector<Vector<String>> getKursliste(String email) {
		HashMap<String, Boolean> kurse = userlist.get(email).getKurse();
		Iterator<String> itr = kurse.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();
		Vector<String> spalten = new Vector<String>();
		
		while (itr.hasNext()) {
			if (!userlist.get(email).getLeistung().containsKey(itr.next())) {
				spalten.add(itr.next());
				spalten.add(veranstaltungen.get(itr.next()).getTitel());
				spalten.add(veranstaltungen.get(itr.next()).getLehrender());
				if(kurse.get(itr.next())) {
					spalten.add("zugelassen");
				} else {
					spalten.add("angemeldet");
				}
			liste.add(spalten);
			}
		}
		return liste;
	}
}
