import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Transaction {
	private GregorianCalendar saleDate;
	private double total;
	private Member member;
	private LinkedList<LineItem> LineItemList = new LinkedList<LineItem>();

	public Transaction(Member member) {
		this.member = member;
		this.saleDate = new GregorianCalendar();
	}

	public boolean addLineItem(LineItem lineItem) {
		return LineItemList.add(lineItem);
	}

	public double calculateTotal() {
		double runningTotal = 0.0;

		for (LineItem lineItem: LineItemList) {
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

}