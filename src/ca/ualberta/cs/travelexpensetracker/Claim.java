package ca.ualberta.cs.travelexpensetracker;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Claim {
	private int startDate;
	private int endDate;
	String status;
	String description;
	String name;
	ArrayList<Expense> expenseList; 
	
	static final String IN_PROGRESS = "In Progress";
	static final String SUBMITTED = "Submitted";
	static final String RETURNED = "Returned";
	static final String APPROVED = "Approved";

	public Claim(int startDate, int endDate,String description,String name){
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.expenseList = new ArrayList<Expense>();
		this.status = IN_PROGRESS;
		this.name = name;
	}
	
	public void editClaim(int startDate, int endDate,String description,String name){
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.name= name;
	}
	
	public ArrayList<Expense> getExpenseList(){
		return this.expenseList;
	}
	
	public void addExpense(Expense expense){
		this.expenseList.add(expense);
	}
	public void removeExpense(Expense expense){
		this.expenseList.remove(expense);
	}
	public String toString(){
		return  "["+this.status+"] "+name+" From: "+
				Integer.toString(this.startDate).substring(0, 4)+"/"+
				Integer.toString(this.startDate).substring(4, 6)+"/"+
				Integer.toString(this.startDate).substring(6, 8)+
				" - To: "+
				Integer.toString(this.endDate).substring(0, 4)+"/"+
				Integer.toString(this.endDate).substring(4, 6)+"/"+
				Integer.toString(this.endDate).substring(6, 8);
	}

	public String getStatus() {
		
		return this.status;
	}

	public int getStartDate() {
		return this.startDate;
	}
	public int getEndDate() {
		return this.endDate;
	}	
	public String getDescription(){
		return this.description;
	}
	
	public boolean isLocked(){
		if (status.equals(SUBMITTED)){
			return true;
		}
		if (status.equals(APPROVED)){
			return true;
		}		
		return false;
	}

	public void submit() {
		this.status = SUBMITTED;
	}
	public void approve(){
		this.status = APPROVED;
	}
	public void returnClaim(){
		this.status = RETURNED;
	}

	public String getName() {
		return name;
	}
		
}

