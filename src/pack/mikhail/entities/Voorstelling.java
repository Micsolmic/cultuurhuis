package pack.mikhail.entities;


import java.util.Date;


public class Voorstelling {

	private int id;
	private String titel;
	private String uitvoerders;
	private Date datum;
	private int genreid;
	private double prijs;
	private int vrijeplaatsen;
	
	
	public Voorstelling(int id, String titel, String uitvoerders, Date datum, int genreid, double prijs,
			int vrijeplaatsen) {
		setId(id);
		setTitel(titel);
		setUitvoerders(uitvoerders);
		setDatum(datum);
		setGenreid(genreid);
		setPrijs(prijs);
		setVrijeplaatsen(vrijeplaatsen);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitel() {
		return titel;
	}


	public void setTitel(String titel) {
		this.titel = titel;
	}


	public String getUitvoerders() {
		return uitvoerders;
	}


	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
	}


	public Date getDatum() {
		return datum;
	}


	public void setDatum(Date datum) {
		this.datum = datum;
	}


	public int getGenreid() {
		return genreid;
	}


	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}


	public double getPrijs() {
		return prijs;
	}


	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}


	public int getVrijeplaatsen() {
		return vrijeplaatsen;
	}


	public void setVrijeplaatsen(int vrijeplaatsen) {
		this.vrijeplaatsen = vrijeplaatsen;
	}
	
	
	
	
	
	
	
	
	
	
}


