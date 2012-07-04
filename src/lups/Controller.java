package lups;

/**
 * Klasse Controller des Projekts
 * @author SE2_TEAM08, Matti Hartfiel
 * @version 1.2
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;
import java.io.*;

//import pdf.PDF;
//import project.*;

public class Controller {
	static String user;
	static String kuser;
	static String mail;
	static long ido;

	private static Database datenbank = new Database();
	
	private static final Logger LOG = Logger.getLogger("lups.Controller");

	/**
	 * Konstruktor
	 */
	public Controller() {
		try {
			FileHandler handler = new FileHandler("Logging.log", true);
			Formatter klartext = new SimpleFormatter();
			handler.setFormatter(klartext);
			LOG.addHandler(handler);
		} catch (IOException ex) {
			LOG.warning("Error: "+ex);
		}
		
		LOG.setLevel(Level.ALL);
		LOG.info("erzeuge Objekt der Klasse Controller.");
		//MainFrame frame = new MainFrame(this);
		//frame.setVisible(true);
		HashMap<String,String> lalilu = new HashMap<String,String>();
		HashMap<String,String> lu = new HashMap<String,String>();
		HashMap<String,String> lo = new HashMap<String,String>();
		lalilu.put("email","lalilu@p.to");
		lalilu.put("name","Matti");
		lalilu.put("flag","Admin");
		lalilu.put("matnr","");
		lu.put("email", "max@mustermann.de");
		lu.put("name", "Max Maustermann");
		lu.put("flag", "Student");
		lu.put("matnr", "1234");
		lo.put("ort", "Berlin");
		lo.put("strasse", "marieallee");
		lo.put("nr", "10");
		createUser(lalilu,1);
		createUser(lu,2);
		datenbank.setSemester("max@mustermann.de",3);
		datenbank.setFach("max@mustermann.de", "Informatik");
		datenbank.setAdresse("max@mustermann.de",lo);
	}
	
	/**
 	* Main des Projekts, erzeugt Frame und Initialisiert den Nutzer (user)
 	* wurde ausgelagert!
 	*/

	/**
 	* logIn(String,String) prüft ob Email und zugehöriges Passwort existieren 
 	* und vergleicht eingegebenes Passwort und das gespeicherte
 	* @param email - email des Nutzers
 	* @param password - ist das Password(vom Nutzer eingegeben)
 	* @return 0 - 4  für art des nutzers(1-3) oder nicht vorhanden(4)
 	*/
	public int logIn(String email, String password) {
    	LOG.entering(this.getClass().getName(), "logIn",new Object[]{email, password});
		try {
			String sha1pwd = sha1(password);
			if (datenbank.existsUsr(email)) {
				if(	sha1pwd.equals(datenbank.getPasswort(email))) { 
			
					user = datenbank.getName(email);
					int lala = datenbank.getFlag(email);
					switch(lala) {
						case 0: kuser = "Admin";
						case 1:	kuser = "Prüfer";
						case 2:	kuser = "Lehrender";
						case 3:	kuser = "Student";
					}
					mail = email;
					LOG.info("Nutzer: "+mail+" Gruppe: "+kuser+" hat sich angemeldet.");
					LOG.exiting(this.getClass().getName(), "logIn", lala);
					return lala;
				}
			}
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		LOG.warning("Falsche Eingabe der Benutzergruppe.");
		LOG.exiting(this.getClass().getName(), "logIn", 4);
		return 4;
	}

	/**
 	* logOut() Meldet den angemeldeten Nutzer vom Systhem ab.
 	*/
	public void logOut() {
		LOG.entering(this.getClass().getName(), "logOut");
		user = "user";
		kuser = "user";
		mail = "mail";
		LOG.info("Nutzer: "+user+" Gruppe: "+kuser+" hat sich abgemeldet.");
		LOG.exiting(this.getClass().getName(), "logOut");
	}

	/**
 	* setBewertung(String,Int,String) Speichert eine Note für die jeweilige Lehrveranstaltung
 	* @param id - ID der Lehrveranstaltung
 	* @param note - Die Bewertung der Lehrveranstaltung
 	*/
	public void setBewertung(String email, String id, String note) {
		LOG.entering(this.getClass().getName(), "setBewertung", new Object[] {email, id, note});
		datenbank.setNote(email,id,Double.parseDouble(note));
		LOG.info("Für "+email+" wurde für die Veranstaltung "+id+" die Bewertung "+note+" eingetragen.");
		LOG.exiting(this.getClass().getName(), "setBewertung");
	}
	
	/**
 	* changeUserData(HashMap<String,String>) Zum Speichern neuer Nutzerdaten
 	* @param data - HashMap von zu ändernden Daten
 	*/
	public void changeUserData(HashMap<String, String> userDaten) {
		LOG.entering(this.getClass().getName(), "setPerson", userDaten);
		LOG.info("Neuer Nutzer ("+mail+","+kuser+") wurde im System gespeichert.");
		datenbank.setMatNr(mail, userDaten.get("matnr"));
		datenbank.setName(mail, userDaten.get("name"));
		userDaten.remove("name");
		userDaten.remove("matnr");
		datenbank.setAdresse(mail, userDaten);
		LOG.exiting(this.getClass().getName(), "setPerson");
	}

	/**
 	* getPerson() lädt daten des angemelderen nutzers aus der datenbank und gibt sie weiter
 	* @return HashMap<Art der Daten, Daten>
 	*/
	public HashMap<String,String> getPerson() {
		LOG.entering(this.getClass().getName(), "getPerson");
		HashMap<String,String> lola = new HashMap<String,String>();
		lola.putAll(datenbank.getAdresse(mail));
		lola.put("name",datenbank.getName(mail));
		lola.put("matnr",datenbank.getMatNr(mail));
		lola.put("email",mail);
		lola.put("flag",kuser);
		LOG.info("Lade Nutzerdaten ("+mail+","+kuser+").");
		LOG.exiting(this.getClass().getName(), "getPerson", lola);
		return lola;
	}

	/**
 	* anmeldungLV(String) zur Lehrveranstaltung anmelden
 	* @param id
 	* 	id, ist die Identifikationsnummer der Lehrveranstaltung
 	*/
	public void anmeldungLV(String id) {
		LOG.entering(this.getClass().getName(), "anmeldenLV");
		datenbank.anmeldenKurse(mail,id);
		LOG.info(mail+" hat sich zu der Lehrveranstaltung "+id+" angemeldet.");
		LOG.exiting(this.getClass().getName(), "anmeldungLV");
	}

	/**
 	* abmeldungLV(String) löscht eine Person aus der Teilnehmerliste der Lehrveranstaltung.
 	* @param id
 	* 	id, ist ID der LV
 	*/
	public void abmeldungLV(String id) {
		LOG.entering(this.getClass().getName(), "abmeldenLV");
		datenbank.abmeldenKurse(mail, id);
		LOG.info(mail+" hat sich von der Lehrveranstaltung "+id+" abgemeldet.");
		LOG.exiting(this.getClass().getName(), "abmeldungLV");
	}

	/**
 	* setPasswd(String,String) setzt ein neues Passwort
 	* @param alt - altes Passwort als String
 	* @param neu - neues Passwort als String
 	* @return true wenn altes mit gespeicherten übereinstimmt, sonst false
 	*/
	public boolean setPasswd(String alt,String neu) {
		LOG.entering(this.getClass().getName(), "setPasswd", new Object[] {alt,neu});
		String alt1;
		try {
			alt1 = sha1(alt);
			if(alt1.equals(datenbank.getPasswort(mail))) {
				String neu1 = sha1(neu);
				datenbank.setPasswort(mail,neu1);
				LOG.info("Neues Passwort für "+mail+" gespeichert");
				LOG.exiting(this.getClass().getName(), "setPasswd", true);
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		LOG.warning("Passwort konnte für "+mail+" nicht geändert werden.");
		LOG.exiting(this.getClass().getName(), "setPasswd", false);
		return false;	
	}

	/**
 	* resetPasswd(String,String) setzt das Passwort eines Nutzers zurück
 	* @param email - email des Nutzers
 	* @param pass - Passwort das gespeichet werden soll
 	*/
	public void resetPasswd(String email,String pass) {
		LOG.entering(this.getClass().getName(), "resetPasswd");
		try	{
			String pwd = sha1(pass);
			datenbank.setPasswort(email,pwd);
			LOG.info("Passwort von Nutzer: "+email+" wurde zurückgesetzt.");
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		LOG.exiting(this.getClass().getName(), "resetPasswd");
	}

	/**
 	* setZulassungLV(String,Vector) Speichert angemeldete als zugelassene Studenten
 	* @param lola - vector<vector>>
 	* @param id - id (String) der Lehrveranstaltung
 	*/
	public void zulassungLehrveranstaltung(String id,Vector<Vector<String>> lola) {
		LOG.entering(this.getClass().getName(), "setZulassung");
		HashMap<String,Boolean> map = new HashMap();
		for(Vector x:lola) {
			map.put((String)x.get(0), (Boolean)x.get(1));
		}
		datenbank.setTeilnehmer(id, map);
		LOG.info(mail+" hat seine Zulassung zur Lehrveranstaltung: "+id+" erhalten.");
		LOG.exiting(this.getClass().getName(), "setZulassungLV");
	}

	/**
 	* createUser(HashMap<String,String>) Speichert Daten eines neu eingestellten Nutzers(vom Admin)
 	* @param data Hashmap<Beschreibung,Daten>
 	*/
	public static boolean createUser(HashMap<String,String> data,int flag) {
		LOG.entering("lups.Controller", "createUser", new Object[] {data,flag});
		String pass = "random001";
		if (!datenbank.existsUsr(data.get("email"))) {
			HashMap<String,String> op = new HashMap();
			op.put("straße", "");
			op.put("ort", "");
			op.put("plz", "");
			try {
				String pwd = sha1(pass);
				datenbank.newUser(data.get("email"), data.get("name"), pwd, data.get("matnr"), flag);
			}
			catch(NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			LOG.info("Nutzer: "+data.get("email")+"wurde von "+mail+" erstellt.");
			LOG.exiting("lups.Controller", "createUser", true);
			return true;
		} else {
			LOG.warning("Nutzer ist schon vorhanden.");
			LOG.exiting("lups.Controller", "createUser", false);
			return false;
		}
	}

	/**
 	* deleteUser(String) löscht einen Nutzer
 	* @param email - email des Nutzers
 	*/
	public void deleteUser(String email) {
		LOG.entering(this.getClass().getName(), "deleteUser");
		datenbank.deleteUsr(email);	
		LOG.info(mail+" "+kuser+" wurde von "+email+" gelöscht.");
		LOG.exiting(this.getClass().getName(), "deleteUser");
	}

	/**
 	* createLV(HashMap<String,String>)erstellt Lehrveranstalltung
 	* @param data - HashMap<Beschreibung, Daten>
 	* @return true wenn noch nicht existiert sonst false
 	*/
	public boolean createLV(HashMap<String,String> data) {
		LOG.entering(this.getClass().getName(), "createLV", data);
		String titel = data.get("titel");
		if(datenbank.existsVer(titel)) {
			LOG.warning("Lehrveranstaltung konnte nicht erstellt werden. Bereits vorhanden?");
			LOG.exiting(this.getClass().getName(), "createLV", false);
			return false;
		}
		else {
			String beschreibung = data.get("beschreibung");
			String zeit = data.get("zeit");
			ido++;
			datenbank.newVeranstaltung(String.valueOf(ido),titel,mail,beschreibung,zeit);
			LOG.info("Lehrveranstaltung: "+data+" wurde erfolgreich erstellt.");
			LOG.exiting(this.getClass().getName(), "createLV", true);
			return true;
		} 		
	}

	/**
 	* deleteLV() löscht Lehrveranstaltung
 	* @param id - id der Lehrveranstaltung
 	*/
	public void deleteLV(String id) {
		LOG.entering(this.getClass().getName(), "deleteLV");
		datenbank.deleteVer(id);
		LOG.info("Lehrveranstaltung: "+id+" wurde von "+mail+" gelöscht.");
		LOG.exiting(this.getClass().getName(), "deleteLV");
	}

	/**
 	* prüferAbmelden(String) Prüfer meldet sich bei Lehrveranstlltung ab
 	* @param id - id der Lehrveranstalltung als int
 	*/
	public void prüferAbmelden(String id) {
		LOG.entering(this.getClass().getName(), "prüferAbmelden");
		datenbank.prueferAbmelden(mail, id);
		LOG.info("Prüfer: "+mail+" hat sich von Lehrveranstaltung: "+id+" abgemeldet.");
		LOG.exiting(this.getClass().getName(), "prüferAbmelden");
	}

	/**
 	* pruefenderListe() Listet alle Lehrveranstaltungen eines Prüfers 
 	* @return Vector<Vector<String>> Liste der Lehrveranstaltungen
 	*/
	public Vector<Vector<String>> pruefenderListe() {
		LOG.entering(this.getClass().getName(), "pruefenderListe");
		LOG.info("Lehrveranstaltung eines Prüfer: "+mail+" ausgegeben.");
		LOG.exiting(this.getClass().getName(), "pruefenderListe", datenbank.getPruefenderliste(user));
		return datenbank.getPruefenderliste(user);
	}

	/**
 	* zugelassen(String) Alle zugelassenen Studenten einer Lehrveranstaltung
 	* @param id - id der Lehrveranstaltung
 	* @return Liste aller zugelassenen Studenten
 	*/
	public Vector<Vector<String>> zugelassen(String id) {
		LOG.entering(this.getClass().getName(), "zugelassen", id);
		LOG.info("Alle Zugelassenen Studenten ausgeben.");
		LOG.exiting(this.getClass().getName(), "zugelassen", datenbank.getTeilnehmerliste(id,true));
		return datenbank.getTeilnehmerliste(id,true);
	}

	/**
 	* leistungsUeberbersicht() des angemeldeten Studenten 
 	* @return Vector<Vector<String>> Leistunsübersicht
 	*/
	public Vector<Vector<String>> leistungsUebersicht() {
		LOG.entering(this.getClass().getName(), "leistungsübersicht");
		LOG.info("Leistungsübersicht von "+mail+" ausgegeben.");
		LOG.exiting(this.getClass().getName(), "leistungsübersicht", datenbank.getLeistung(mail));
		return datenbank.getLeistung(mail);
	}

	/**
 	* Gibt die Leistungsübersicht des angemeldetan Nutzers in String-Listenform
 	* zurück
 	* @return Vector vom Vector von Strings (Lehrveranstaltungen und Note)
 	*/
	public Vector<Vector<String>> leistungsuebersicht() {
		LOG.entering(this.getClass().getName(), "leistungsübersicht");
		LOG.info("Leistungsübersicht von "+mail+" ausgegeben.");
		LOG.exiting(this.getClass().getName(), "zugelassen", datenbank.getLeistung(mail));
		return datenbank.getLeistung(mail);
	}

	/**
 	* angemeldeteKurse() Alle Kurse bei denen ein Studenten angemeldet ist
 	* @return Vector<Vector<String>> 
 	*/
	public Vector<Vector<String>> angemeldeteKurse() {
		LOG.entering(this.getClass().getName(),"angemeldeteKurse");
		LOG.info("Alle Kurse von "+mail+" ausgegeben.");
		LOG.exiting(this.getClass().getName(), "angemeldeteKurse", datenbank.getKursliste(user));
		return datenbank.getKursliste(user);
	}

	/**
 	* logEinsehen() Stellt auf der GUI die Loggs dar.
 	* @ return list: List<String>
 	*/
	public List<String> logEinsehen() {
		LOG.entering(this.getClass().getName(),"logEinsehen");
		List<String> list = new ArrayList(20);
		try {
			BufferedReader in = new BufferedReader(new FileReader("lups.log"));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				list.add(zeile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOG.info("Logs wurden von "+mail+" eingesehen.");
		LOG.exiting(this.getClass().getName(), "logEinsehen", list);
		return list;
	}

	/**
 	* getNutzerList() Gibt Liste aller Nutzer zurück
 	* @return Vector<String> Nutzerliste
 	*/
	public Vector<String> getNutzerList() {
		LOG.entering(this.getClass().getName(), "getNutzerList");
		LOG.info("Alle Nutzer wurden ausgegeben.");
		LOG.exiting(this.getClass().getName(), "getNutzerList", datenbank.getUserList());
		return datenbank.getUserList();
	}

	/**
 	* isStudent() Ist Nutzer student?
 	* @return true wenn ja sonst nein
 	*/
	public boolean isStudent() {
		LOG.entering(this.getClass().getName(), "isStudent");
		LOG.info("Prüfe ob Nutzer Student ist.");
		if(kuser.equals("Student")) {
			LOG.info("Nutzer "+mail+" ist Student.");
			LOG.exiting(this.getClass().getName(), "isStudent", true);
			return true;
		} else {
			LOG.info("Nutzer "+mail+" ist kein Student.");
			LOG.exiting(this.getClass().getName(), "isStudent", false);
			return false;
		}
	}

	/**
	 * isAngemeldet(String) Ist Nutzer angemeldet?
	 * @param id
	 * @return ...
	 */
	public boolean isAngemeldet(String id) {
		LOG.entering(this.getClass().getName(), "isAngemeldet", new Object[] {id});
		List<String> lola = new ArrayList<String>();
		lola = datenbank.getTeilnehmerListe(id);
		LOG.info("Prüfe ob Nutzer angemeldet ist.");
		LOG.exiting(this.getClass().getName(), "isAngemeldet", lola.contains(user));
		return lola.contains(user);
	}

	/**
	 * getAllLV() hohlt kommplettes Vorleseungsverzeichniss
	 * @return Vector<Vector<String>>
	 */
	public Vector<Vector<String>> getAllLV() {
		LOG.entering(this.getClass().getName(), "getAllLV");
		LOG.info("Alle Lehrveranstaltungen ausgeben.");
		LOG.exiting(this.getClass().getName(), "getAllLV", datenbank.getVorlesungsverzeichnis());
		return datenbank.getVorlesungsverzeichnis();
	}

	/**
	 * getErstellteLV() Gibt die Liste von Lehrveranstaltungen eines Prüfenden/Lehrenden zurück
	 * @return Liste von Lehrveranstaltungen (Vector<Vector<String>>>)
	 */
	public Vector<Vector<String>> getErstellteLV() {
		LOG.entering(this.getClass().getName(), "getErstellteLV");
		LOG.info("Alle Lehrveranstaltungen von "+mail+" ausgegeben.");
		LOG.exiting(this.getClass().getName(), "getErstellteLV", datenbank.getKursliste(mail));
		return datenbank.getKursliste(mail);
	}

	/**
	 * getTList(String,boolean) Gibt Liste von Namen(Studenten) einer Lehrveranstaltung zurück
	 * @param id - String id der Lehrveranstaltung
	 * @return Vector<Vector<String>> Teilnehmerliste
	 */
	public Vector<Vector<String>> getTList(String id,boolean a) {
		LOG.entering(this.getClass().getName(), "getTList", new Object[] {id,a});
		LOG.info("Liste alle Teilnehmer von der Lehrveranstaltung: "+id+" auf.");
		LOG.exiting(this.getClass().getName(), "getTList", datenbank.getTeilnehmerliste(id,a));
		return datenbank.getTeilnehmerliste(id,a);
	}

	/**
	 * getBewertungen() Gibt Veranstaltungen und Noten von user zurück.
	 * @return Vector vom Vektor
	 */
	public Vector<Vector<String>> getBewertungen() {
		LOG.entering(this.getClass().getName(), "getBewertungen");
		LOG.info("Liste alle Veranstaltungen + Note von "+mail+".");
		LOG.exiting(this.getClass().getName(), "getBewertungen", datenbank.getLeistung(mail));
		return datenbank.getLeistung(mail);
	}

	/**	
 	* studenplan() Gibt den Stundenplan des angemeldetan Nutzers in String-Listenform zurück
 	* @return Liste von Strings
 	*/
	public List<String> stundenplan() {
		LOG.entering(this.getClass().getName(), "stundenplan");
		LOG.info("Stundenplan von "+mail+" ausgegeben.");
		LOG.exiting(this.getClass().getName(), "stundenplan", datenbank.getKursListe(mail));
		return datenbank.getKursListe(mail);
	}

	/**
 	* print(Int) Druckt je nach id das jewilige Dokument
 	* @param id ist eine Zahl zwischen 1 und 4, id repräsentiert die gewollte Dokumentart
 	* (Leistungsübersicht, Studienbescheinigung, Stundenplan, Studienverlaufsbescheinigung)
 	*/
	public void print(int id) {
		LOG.entering(this.getClass().getName(), "print", id);
		switch(id){
			case 1:	{ 	
				//PDF.leistungsuebersicht(datenbank, mail);
				LOG.info("drucke Leistungsübersicht von "+mail);
				LOG.exiting(this.getClass().getName(), "print");
			}
			case 2:	{
				//PDF.studienbescheinigung(datenbank, mail);
				LOG.info("drucke Studienbescheinigung von "+mail);
				LOG.exiting(this.getClass().getName(), "print");
			}
			case 3:	{
				//PDF.stundenplan(datenbank, mail);
				LOG.info("drucke Stundenplan von "+mail);
				LOG.exiting(this.getClass().getName(), "print");
			}
			case 4:	{
				//PDF.studienverlaufsbescheinigung(datenbank, mail);
				LOG.info("drucke Studienberlaufsbescheinigung von "+mail);
				LOG.exiting(this.getClass().getName(), "print");
			}
		}
	}

	/**
 	* printTeilnehmerliste(String) Druckt Teilnehmerliste einer Lehrveranstaltung
 	* @param id - id der Lehrveranstaltung
 	*/
	public void printTeilnehmerliste(String id) {
		LOG.entering(this.getClass().getName(), "printTeilnehmerliste");
		LOG.info("drucke Teilnehmerliste von "+mail);
		//PDF.teilnehmerliste(datenbank, id);
		LOG.exiting(this.getClass().getName(), "printTeilnehmerliste");
	}

	/**
 	* sha1(String) Gibt den verschlüsselten (sha1) Eingabestring als String zurück
 	* @param input - zu verschlüsselnder String (Passwort)
 	* @return sha1 verschlüsselter String
 	*/
	static String sha1(String input) throws NoSuchAlgorithmException {
		LOG.entering("lups.Controller", "sha1");
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		LOG.info("Gibt den verschlüsselten Eingabestring als String zurück");
		LOG.exiting("lups.Controller", "sha1", sb.toString()); 
		return sb.toString();
	}

}