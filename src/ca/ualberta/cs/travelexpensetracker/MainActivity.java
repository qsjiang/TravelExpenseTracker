package ca.ualberta.cs.travelexpensetracker;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView claimListView = (ListView) findViewById(R.id.ClaimListView);
		
		claimListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				//Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this,ClaimActivity.class);
				intent.putExtra("position",Integer.toString(position));
		    	startActivity(intent);  				
			}
		});
	
	}
	@Override
	protected void onResume() {
		super.onResume();
		ListView claimListView = (ListView) findViewById(R.id.ClaimListView);
		final ArrayList<Claim> claimList = ClaimListController.getClaimList().getClaims();
		final ArrayAdapter <Claim> claimAdapter = new ArrayAdapter<Claim>(this,android.R.layout.simple_list_item_1,claimList);
		claimListView.setAdapter(claimAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void addClaimButtonAction(View v){
		Intent intent = new Intent(MainActivity.this,ClaimCreationActivity.class);
    	startActivity(intent);    		
	}
}
