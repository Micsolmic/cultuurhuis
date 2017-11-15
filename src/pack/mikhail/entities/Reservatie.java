package pack.mikhail.entities;


public class Reservatie {

	
	private Voorstelling voorstelling;
	private int plaatsen;
	
	public Reservatie(Voorstelling voorstelling, int plaatsen) {
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((voorstelling == null) ? 0 : voorstelling.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservatie other = (Reservatie) obj;
		if (voorstelling == null) {
			if (other.voorstelling != null)
				return false;
		} else if (!voorstelling.equals(other.voorstelling))
			return false;
		return true;
	}
	
	
	
	
}
