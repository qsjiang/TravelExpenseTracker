package ca.ualberta.cs.travelexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClaimCreationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_creation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_creation, menu);
		return true;
	}

	public void saveClaimButtonAction(View v){
		int startDate = 0;
		int endDate =0;
		String description;
		EditText startDateET = (EditText)findViewById(R.id.startDateET);
		if (!startDateET.getText().toString().trim().equals("")){
			startDate = Integer.parseInt(startDateET.getText().toString().trim());
		}
		EditText endDateET = (EditText)findViewById(R.id.endDateET);
		if (!endDateET.getText().toString().trim().equals("")){
			endDate = Integer.parseInt(endDateET.getText().toString().trim());
		}
		EditText descriptionET = (EditText)findViewById(R.id.descriptionET);
		description = descriptionET.getText().toString();
		
		if ((Integer.toString(startDate).length()!=8) ||
				(Integer.toString(endDate).length()!=8)){
			Toast.makeText(this, "Wrong date formate. Expecting YYYYMMDD", Toast.LENGTH_SHORT).show();		
		}else{
			Claim newClaim = new Claim(startDate, endDate,description);
			ClaimListController.addClaim(newClaim);
			
			this.finish();
		}
	}
	
}
