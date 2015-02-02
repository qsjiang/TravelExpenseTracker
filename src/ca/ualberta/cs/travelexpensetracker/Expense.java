package ca.ualberta.cs.travelexpensetracker;

public class Expense {
	private int date;
	private String category;
	private String description;
	private float amount;
	private String unit;
	
	public Expense(int date, String category,String description, float amount, String unit){
		this.date = date;
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.unit = unit;
	}
	
	public void editExpense(int date, String category,String description, float amount, String unit){
		this.date = date;
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.unit = unit;
	}
	
	public String toString(){
		return  Integer.toString(this.date).substring(0, 4)+"/"+
				Integer.toString(this.date).substring(4, 6)+"/"+
				Integer.toString(this.date).substring(6, 8)+" "+
				this.category+" "+
				Float.toString(this.amount)+" "+
				this.unit;
				
		
	}


	public int getDate() {
		return date;
	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}

	public float getAmount() {
		return amount;
	}

	public String getUnit() {
		// TODO Auto-generated method stub
		return unit;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}
}
