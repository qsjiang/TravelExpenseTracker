package ca.ualberta.cs.travelexpensetracker;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClaimActivity extends Activity {
	private Claim targetClaim;
	private int targetClaimPosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim);
		
		/*Read position passed by parent activity and retrieve the correct claim*/
		Intent intent = getIntent();
		targetClaimPosition = Integer.parseInt(intent.getStringExtra("position"));
		targetClaim = ClaimListController.getClaimList().getClaims().get(targetClaimPosition);

		/*display the claim info on the UI*/
		TextView claimStatusTV = (TextView)findViewById(R.id.claimStatusTV);
		claimStatusTV.setText(targetClaim.getStatus());
		
		EditText updateStartDateET = (EditText)findViewById(R.id.updateStartDateET);
		updateStartDateET.setText(Integer.toString(targetClaim.getStartDate()));
	
		EditText updateEndDateET = (EditText)findViewById(R.id.updateEndDateET);
		updateEndDateET.setText(Integer.toString(targetClaim.getEndDate()));

		EditText updateDescriptionET = (EditText)findViewById(R.id.updateDescriptionET);
		updateDescriptionET.setText(targetClaim.getDescription());
		
		//link click event on list view items
		ListView expenseLV = (ListView) findViewById(R.id.expenseLV);
		expenseLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent(ClaimActivity.this,ExpenseActivity.class);
				intent.putExtra("claimPosition",Integer.toString(targetClaimPosition));
				intent.putExtra("expensePosition",Integer.toString(position));
				startActivity(intent);  				
			}
		});
		
		//Disable update if claim is locked
		if (targetClaim.isLocked()){
			disableUpdates();
		}
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		ListView expenseLV= (ListView) findViewById(R.id.expenseLV);
		final ArrayList<Expense> expnseList = targetClaim.getExpenseList();
		final ArrayAdapter <Expense> expenseAdapter = new ArrayAdapter<Expense>(this,android.R.layout.simple_list_item_1,expnseList);
		expenseLV.setAdapter(expenseAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim, menu);
		return true;
	}
	
	public void submitClaimButtonAction(View v){
		targetClaim.submit();
		Toast.makeText(this, "Successfully Submitted. You can no longer edit this claim", Toast.LENGTH_LONG).show();
		disableUpdates();
	}

	private void disableUpdates(){
		Button submitClaimButton = (Button)findViewById(R.id.submitClaimButton);
		Button updateClaimButton = (Button)findViewById(R.id.updateClaimButton);
		Button addExpenseButton = (Button)findViewById(R.id.addExpenseButton);
		Button deleteClaimButton = (Button)findViewById(R.id.deleteClaimButton);
		
		EditText updateStartDateET = (EditText)findViewById(R.id.updateStartDateET);
		EditText updateEndDateET = (EditText)findViewById(R.id.updateEndDateET);
		EditText updateDescriptionET = (EditText)findViewById(R.id.updateDescriptionET);
		
		submitClaimButton.setVisibility(View.INVISIBLE);
		updateClaimButton.setEnabled(false);
		deleteClaimButton.setEnabled(false);
		addExpenseButton.setEnabled(false);
		updateStartDateET.setFocusable(false);
		updateEndDateET.setFocusable(false);
		updateDescriptionET.setFocusable(false);
	}
	
	public void updateClaimButtonAction(View V){
		int startDate = 0;
		int endDate = 0;
		String description;
		EditText startDateET = (EditText)findViewById(R.id.updateStartDateET);
		
		if (!startDateET.getText().toString().trim().equals("")){
			startDate = Integer.parseInt(startDateET.getText().toString().trim());
		}
		EditText endDateET = (EditText)findViewById(R.id.updateEndDateET);
		if (!endDateET.getText().toString().trim().equals("")){
			endDate = Integer.parseInt(endDateET.getText().toString().trim());
		}
		EditText descriptionET = (EditText)findViewById(R.id.updateDescriptionET);
		description = descriptionET.getText().toString();
		
		
		if ((Integer.toString(startDate).length()!=8) ||
				(Integer.toString(endDate).length()!=8)){
			Toast.makeText(this, "Wrong date formate. Expecting YYYYMMDD", Toast.LENGTH_SHORT).show();		
		}else{
			targetClaim.editClaim(startDate, endDate, description);
			Toast.makeText(this, "Successfully Updated", Toast.LENGTH_LONG).show();	
		}
	}
	
	public void addExpenseButtonAction(View v){
		Intent intent = new Intent(ClaimActivity.this,ExpenseActivity.class);
		intent.putExtra("claimPosition",Integer.toString(targetClaimPosition));
		intent.putExtra("expensePosition", ""); //empty string indicate create an expense from scratch.
    	startActivity(intent);   	
	}	
	public void deleteClaimButtonAction(View v){
		ClaimListController.removeClaim(targetClaim);
		Toast.makeText(this, "Record delelted", Toast.LENGTH_SHORT).show();
		this.finish();
	}
}
