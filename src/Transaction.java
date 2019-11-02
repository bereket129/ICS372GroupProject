import java.io.Serializable;
import java.util.*;

public class Transaction implements Serializable {
	private GregorianCalendar saleDate;
	private double total;
	private Member member;
	private LinkedList<LineItem> lineItemList = new LinkedList<LineItem>();

	public Transaction(Member member) {
		this.member = member;
		this.saleDate = new GregorianCalendar(TimeZone.getTimeZone("Canada/Central"));
	}

	public boolean addLineItem(LineItem lineItem) {
		return lineItemList.add(lineItem);
	}

	public Iterator getLineItems(){
		return lineItemList.iterator();
	}

	public double calculateTotal() {
		double runningTotal = 0.0;

		for (LineItem lineItem: lineItemList) {
			runningTotal += lineItem.getTotalPrice();
		}

		return runningTotal;
	}

	public Member getMember() {
		return member;
	}

	public GregorianCalendar getSaleDate() {
		return saleDate;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(String.format("%-30s%-30s%-30s%s", "Product Name", "Quantity", "Unit Price","total\n"));
		for(LineItem lineItem : lineItemList){
			stringBuilder.append(String.format("%-30s%-30s%-30s%s",
					lineItem.getProduct().getName(),
					lineItem.getQuantity(),
					lineItem.getProduct().getPrice(),
					lineItem.getTotalPrice()+"\n"));
		}

		stringBuilder.append(String.format("%-30s%s","Total",calculateTotal()+"\n"));
		stringBuilder.append(String.format("%-30s%s","Date",saleDate.getTime()+"\n"));
		return stringBuilder.toString();
	}
}