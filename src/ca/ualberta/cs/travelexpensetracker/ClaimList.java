package ca.ualberta.cs.travelexpensetracker;

import java.util.ArrayList;

public class ClaimList {
	private ArrayList<Claim> claims;
	
	public ClaimList(){
		this.claims = new ArrayList<Claim>();
	}

	public void addClaim(Claim claim) {
		this.claims.add(claim);
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
