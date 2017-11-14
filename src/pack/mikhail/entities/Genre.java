package pack.mikhail.entities;

public class Genre {

	public int id;
	public String naam;
	
	public Genre(int id, String naam) {
		
		setId(id);
		setNaam(naam);
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	
	
}
