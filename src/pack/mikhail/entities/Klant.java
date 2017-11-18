package pack.mikhail.entities;

public class Klant {

	
	private int id;
	private String voornaam;
	private String familienaam;
	private String straat;
	private int huisnr;
	private int postcode;
	private String gemeente;
	private String gebruikersnaam;
	private String passwoord;
	
	public String getPasswoord() {
		return passwoord;
	}
	public void setPasswoord(String passwoord) {
		this.passwoord = passwoord;
	}
	public Klant(int id, String voornaam, String familienaam, String straat, int huisnr, int postcode, String gemeente,
			String gebruikersnaam, String paswoord) {
		
		setId(id);
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setStraat(straat);
		setHuisnr(huisnr);
		setPostcode(postcode);
		setGemeente(gemeente);
		setGebruikersnaam(gebruikersnaam);
		setPasswoord(paswoord);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public String getStraat() {
		return straat;
	}
	public void setStraat(String straat) {
		this.straat = straat;
	}
	public int getHuisnr() {
		return huisnr;
	}
	public void setHuisnr(int huisnr) {
		this.huisnr = huisnr;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getGemeente() {
		return gemeente;
	}
	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	
	public String toInfo() {
		
		return  voornaam+" "+familienaam+" "+straat+" "+huisnr+" "+postcode+" "+gemeente;
		
	}
	
	
}
