package ca.ualberta.cs.travelexpensetracker;


public class ClaimListController {
	private static ClaimList claimList = null;
	
	static public ClaimList getClaimList(){
		if (claimList == null){
			claimList = new ClaimList();
		}
		return claimList;
	}

	static public void addClaim(Claim claim){
		getClaimList().addClaim(claim);
	}
	static public void removeClaim(Claim claim){
		getClaimList().removeClaim(claim);
	}
}
