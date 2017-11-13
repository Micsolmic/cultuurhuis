package pack.mikhail.entities;

import java.util.HashMap;

public class Mandje {

	
	private HashMap<Integer, Reservatie> reserveringen = new HashMap<>();
	
	public void reservatieToevoegen(Reservatie res) {
		
		reserveringen.put(res.getId(), res);
		
	}
	
	public void reservatieVerwijderen(int id) {
		
		reserveringen.remove(id);
		
	}
	
	
	public HashMap<Integer,Reservatie> getReservaties() {
		
		 return reserveringen;
		
	}
}
