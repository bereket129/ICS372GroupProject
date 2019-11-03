import java.io.Serializable;
import java.util.*;
/**
 *
 * This implements the transaction of our grocery store system
 *
 */
public class Transaction implements Serializable {
	private GregorianCalendar saleDate;
	private double total;
	private Member member;
	private LinkedList<LineItem> lineItemList = new LinkedList<LineItem>();
	/**
	 * Constructor for transaction
	 * @param member
	 *             The member that is buying products
	 */
	public Transaction(Member member) {
		this.member = member;
		this.saleDate = new GregorianCalendar(TimeZone.getTimeZone("Canada/Central"));
	}
	/**
	 * This adds a line item to the list
	 * @param lineItem
	 *               The lineItem being added
	 * @return
	 *       Returns true if added.
	 */
	public boolean addLineItem(LineItem lineItem) {
		return lineItemList.add(lineItem);
	}
	/**
	 * This is the iterator for the line items
	 * @return
	 *        Returns an iterator object
	 */
	public Iterator getLineItems(){
		return lineItemList.iterator();
	}
	/**
	 * This calculates the total price for all the line items
	 * @return
	 *       Returns the total price
	 */
	public double calculateTotal() {
		double runningTotal = 0.0;

		for (LineItem lineItem: lineItemList) {
			runningTotal += lineItem.getTotalPrice();
		}

		return runningTotal;
	}
	/**
	 * This gets the member of the transaction
	 * @return
	 *         Returns the member object
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * Getter for SaleDate
	 * @return
	 *        Returns SaleDate
	 */
	public GregorianCalendar getSaleDate() {
		return saleDate;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(String.format("%-30s%-30s%-30s%s",
				"Product Name", "Quantity",
				"Unit Price","total\n"));
		for(LineItem lineItem : lineItemList){
			stringBuilder.append(String.format("%-30s%-30s%-30s%s",
					lineItem.getProduct().getName(),
					lineItem.getQuantity(),
					lineItem.getProduct().getPrice(),
					lineItem.getTotalPrice()+"\n"));
		}

		stringBuilder.append(String.format("%-30s%s","Total",
				String.format("%.2f",calculateTotal()) + "\n"));
		stringBuilder.append(String.format("%-30s%s","Date",
				saleDate.getTime() + "\n"));
		return stringBuilder.toString();
	}
}