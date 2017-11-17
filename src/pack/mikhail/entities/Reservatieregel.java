package pack.mikhail.entities;


public class Reservatieregel {

	
	private Voorstelling voorstelling;
	private int plaatsen;
	
	public Reservatieregel(Voorstelling voorstelling, int plaatsen) {
		
		setVoorstelling(voorstelling);
		setPlaatsen(plaatsen);
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
