package pack.mikhail.entities;

import java.util.HashMap;

public class Mandje {
	
	
	HashMap<Integer, Integer> reserveringen = new HashMap<>();
	boolean leeg = true;
	
	public void addReservatieRegel(int idVoorstelling, int zitjes) {
		
		reserveringen.put(idVoorstelling, zitjes);
		this.leeg = false;
	}
	
	public int getZitjes(int idVoorstelling){
		
		if(reserveringen.get(idVoorstelling)!=null) {
			
			return (int) reserveringen.get(idVoorstelling);
			
		};
		
		return -1;
		
	}

	public boolean isLeeg() {
		return leeg;
	}

	public void setLeeg(boolean leeg) {
		this.leeg = leeg;
	}
	

	

}
