package pack.mikhail.entities;

import java.util.HashMap;

public class Mandje {
	
	
	private HashMap<Integer, Integer> reserveringen = new HashMap<>();
	private boolean leeg = true;
	
	public void addReservatieEntry(int idVoorstelling, int zitjes) {
		
		reserveringen.put(idVoorstelling, zitjes);
		this.leeg = false;
	}
	
	public void verwijderReservatieEntry(int idVoorstelling) {
		
		
		reserveringen.remove(idVoorstelling);
		
	}
	
	public int getZitjes(int idVoorstelling){
		
		if(reserveringen.get(idVoorstelling)!=null) {
			
			return (int) reserveringen.get(idVoorstelling);
			
		};
		
		return -1;
		
	}
	
	public HashMap<Integer,Integer> getReserveringen(){
		
		return reserveringen;
		
	}

	public boolean isLeeg() {
		return leeg;
	}

	public void setLeeg(boolean leeg) {
		this.leeg = leeg;
	}
	

	

}
