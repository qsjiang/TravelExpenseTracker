package ca.ualberta.cs.travelexpensetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseActivity extends Activity {
	
	private Claim targetClaim;
	private Expense targetExpense;
	private int targetClaimPosition;
	private int tagetExpensePosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense);
		Intent intent = getIntent();
		targetClaimPosition = Integer.parseInt(intent.getStringExtra("claimPosition"));
		targetClaim = ClaimListController.getClaimList().getClaims().get(targetClaimPosition);	
		if (intent.getStringExtra("expensePosition").trim().equals("")){
			//parent activity did not provide an expensePosition. => Create an new expense.
			tagetExpensePosition = -1; 
		}else{
			tagetExpensePosition = Integer.parseInt(intent.getStringExtra("expensePosition"));
			targetExpense = targetClaim.getExpenseList().get(tagetExpensePosition);
			populateTextEdit();
		}
		if (targetClaim.isLocked()){
			disableUpdates();
		}
	}
	

	private void populateTextEdit() {
		EditText expnseDateET = (EditText)findViewById(R.id.expnseDateET);
		EditText expenseCategoryET = (EditText)findViewById(R.id.expenseCategoryET);
		EditText expenseAmountET = (EditText)findViewById(R.id.expenseAmountET);
		EditText expenseUnitET = (EditText)findViewById(R.id.expenseUnitET);	
		EditText expenseDescriptionET = (EditText)findViewById(R.id.expenseDescriptionET);
		EditText expenseNameET = (EditText)findViewById(R.id.expenseNameET);
		
		expnseDateET.setText(Integer.toString(targetExpense.getDate()));
		expenseCategoryET.setText((targetExpense.getCategory()));
		expenseAmountET.setText(Float.toString(targetExpense.getAmount()));
		expenseUnitET.setText((targetExpense.getUnit()));
		expenseDescriptionET.setText((targetExpense.getDescription()));
		expenseNameET.setText(targetExpense.getName());
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense, menu);
		return true;
	}

	public void expenseSaveButtonAction(View v){
		if (tagetExpensePosition>=0){
			//editing on existing expense
			updateExisitingExpense();
		}else{
			//no exisiting expesne provided, create a new one
			createNewExpense();
		}
	}
	
	public void expenseDeleteButtonAction(View v){
		targetClaim.removeExpense(targetExpense);
		Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show();
		this.finish();
	}
	private void disableUpdates(){
		Button expenseDeleteButton = (Button)findViewById(R.id.expenseDeleteButton);
		Button expenseSaveButton = (Button)findViewById(R.id.expenseSaveButton);

		expenseDeleteButton.setEnabled(false);
		expenseSaveButton.setEnabled(false);
	}	
	private void updateExisitingExpense(){
		int date=0;
		String Category;
		float amount=0;
		String unit;
		String description;
		String name;
		EditText expnseDateET = (EditText)findViewById(R.id.expnseDateET);
		
		if (!expnseDateET.getText().toString().trim().equals("")){
			date = Integer.parseInt(expnseDateET.getText().toString().trim());
		}
		
		EditText expenseCategoryET = (EditText)findViewById(R.id.expenseCategoryET);
		Category = expenseCategoryET.getText().toString();
		
		
		EditText expenseAmountET = (EditText)findViewById(R.id.expenseAmountET);
		
		if (!expenseAmountET.getText().toString().trim().equals("")){
			amount = Float.valueOf(expenseAmountET.getText().toString());
		}
		
		EditText expenseUnitET = (EditText)findViewById(R.id.expenseUnitET);
		unit = expenseUnitET.getText().toString();
		
		EditText expenseDescriptionET = (EditText)findViewById(R.id.expenseDescriptionET);
		description = expenseDescriptionET.getText().toString();
		
		EditText expenseNameET = (EditText)findViewById(R.id.expenseNameET);
		name = expenseNameET.getText().toString();	
		
		if (Integer.toString(date).length()!=8){
			//a correct date needs total 8 characters
			//Right now the program doesn't check situation where MM is greater than 12 or day is greater than 31
			//but please note this is not a bug its a feature, because this would be really useful feature if the user is on 
			//another planet where a year contains more than 12 months..
			Toast.makeText(this, "Wrong date formate. Expecting YYYYMMDD", Toast.LENGTH_SHORT).show();		
		}else{		
			targetExpense.editExpense(date,Category,description,amount,unit,name);	
			Toast.makeText(this, "Expense Record Updated", Toast.LENGTH_SHORT).show();			
		}
	}
	private void createNewExpense(){
		int date=0;
		String Category;
		float amount=0;
		String unit;
		String description;
		String name;
		EditText expnseDateET = (EditText)findViewById(R.id.expnseDateET);
		
		if (!expnseDateET.getText().toString().trim().equals("")){
			date = Integer.parseInt(expnseDateET.getText().toString().trim());
		}
		
		EditText expenseCategoryET = (EditText)findViewById(R.id.expenseCategoryET);
		Category = expenseCategoryET.getText().toString();
		
		
		EditText expenseAmountET = (EditText)findViewById(R.id.expenseAmountET);
		
		if (!expenseAmountET.getText().toString().trim().equals("")){
			amount = Float.valueOf(expenseAmountET.getText().toString());
		}
		
		EditText expenseUnitET = (EditText)findViewById(R.id.expenseUnitET);
		unit = expenseUnitET.getText().toString();
		
		EditText expenseDescriptionET = (EditText)findViewById(R.id.expenseDescriptionET);
		description = expenseDescriptionET.getText().toString();
		
		EditText expenseNameET = (EditText)findViewById(R.id.expenseNameET);
		name = expenseNameET.getText().toString();		
		
		if (Integer.toString(date).length()!=8){
			//a correct date needs total 8 characters
			//Right now the program doesn't check situation where MM is greater than 12 or day is greater than 31
			//but please note this is not a bug its a feature, because this would be really useful feature if the user is on 
			//another planet where a year contains more than 12 months..
			Toast.makeText(this, "Wrong date formate. Expecting YYYYMMDD", Toast.LENGTH_SHORT).show();		
		}else{
			targetClaim.addExpense(new Expense(date,Category,description,amount,unit,name));
			this.finish();	
		}
	}
}
