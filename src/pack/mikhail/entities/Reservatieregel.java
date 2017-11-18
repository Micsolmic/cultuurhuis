package pack.mikhail.entities;


public class Reservatieregel {

	
	private Voorstelling voorstelling;
	private int plaatsen;
	private int vrijePlaatsen;
	private boolean gelukt;
	
	public boolean isGelukt() {
		return gelukt;
	}

	public void setGelukt(boolean gelukt) {
		this.gelukt = gelukt;
	}

	public int getVrijePlaatsen() {
		return vrijePlaatsen;
	}

	public void setVrijePlaatsen(int vrijePlaatsen) {
		this.vrijePlaatsen = vrijePlaatsen;
	}

	public Reservatieregel(Voorstelling voorstelling, int plaatsen, int vrijePlaatsen, boolean gelukt) {
		
		setVoorstelling(voorstelling);
		setPlaatsen(plaatsen);
		setVrijePlaatsen(vrijePlaatsen);
		setGelukt(gelukt);
	}

	public Voorstelling getVoorstelling() {
		return voorstelling;
	}

	public void setVoorstelling(Voorstelling voorstelling) {
		this.voorstelling = voorstelling;
	}

	public int getPlaatsen() {
		return plaatsen;
	}

	public void setPlaatsen(int plaatsen) {
		this.plaatsen = plaatsen;
	}

	
	
	
	
	
}
