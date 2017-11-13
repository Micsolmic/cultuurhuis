package pack.mikhail.entities;

import java.time.LocalDateTime;

public class Reservatie {

	private int id;
	private LocalDateTime datumVoorstelling;
	private String titel;
	private String uitvoerders;
	private double prijs;
	private int plaatsen;
	
	
	public Reservatie(int id, LocalDateTime ldt, String titel, String uitvoerder, double prijs, int plaatsen) {
		
		setId(id);
		setDatumVoorstelling(ldt);
		setTitel(titel);
		setUitvoerders(uitvoerder);
		setPrijs(prijs);
		setPlaatsen(plaatsen);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDatumVoorstelling() {
		return datumVoorstelling;
	}
	public void setDatumVoorstelling(LocalDateTime datumVoorstelling) {
		this.datumVoorstelling = datumVoorstelling;
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
	public double getPrijs() {
		return prijs;
	}
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	public int getPlaatsen() {
		return plaatsen;
	}
	public void setPlaatsen(int plaatsen) {
		this.plaatsen = plaatsen;
	}
	
	
	
	
}
