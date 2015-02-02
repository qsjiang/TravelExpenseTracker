package ca.ualberta.cs.travelexpensetracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClaimList {
	private ArrayList<Claim> claims;
	
	public ClaimList(){
		this.claims = new ArrayList<Claim>();
	}

	public void addClaim(Claim claim) {
		this.claims.add(claim);
		Collections.sort(claims, new Comparator<Claim>() {
	        @Override
	        public int compare(Claim  claim1, Claim  claim2){
	        	if (claim1.getStartDate()<claim2.getStartDate()){
	        		return -1;
	        	}
	        	if (claim1.getStartDate()>claim2.getStartDate()){
	        		return 1;
	        	}
	        	return 0;
	        }
	    });
	}
	public void removeClaim(Claim claim){
		this.claims.remove(claim);
	}
	public ArrayList<Claim> getClaims(){
		return this.claims;
	}
	
	public int size(){
		return claims.size();
	}
}
