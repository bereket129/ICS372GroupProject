import java.io.Serializable;

/**
 * This class will store line items in a transaction
 */
public class LineItem implements Serializable {
	private Product product;
	private int quantity;
	private double totalPrice;

	/**
	 * constructs for lineitem
	 *
	 * @param product
	 *                 the corresponding product
	 * @param quantity
	 *                 quantity of the product
	 */
	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = calculateTotalPrice(product, quantity);
	}

	/**
	 * calculates the total price of the line item
	 * @param product
	 *                the product object
	 * @param quantity
	 *                 the quantity of the product
	 * @return
	 *         the total price of the line item.
	 */
	private double calculateTotalPrice(Product product, int quantity) {
		return product.getPrice() * quantity;
	}

	/**
	 * adjusts the quantity of a product
	 * @param product
	 *               the product object
	 * @param quantity
	 *                the new quantity to be adjusted in to
	 * @return
	 *         true if setting the quantity was successful, false otherwise.
	 */
	private boolean adjustProductQuantity(Product product, int quantity) {
		int newQuantity = product.getQuantity() - quantity;
		return product.setQuantity(newQuantity);
	}

	/**
	 * getter for the product object
	 *
	 * @return
	 *       product object
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * getter for the quantity
	 * @return
	 *       the line item quantity
	 */

	public int getQuantity() {
		return quantity;
	}

	/**
	 * setter for a product
	 *
	 * @param product
	 *             the product object to set to
	 * @return
	 *       true if setting the product was successful,
	 *       false other wise.
	 */
	public boolean setProduct(Product product) {
		if (product != null) {
			this.product = product;
			return true;
		}
		return false;
	}

	/**
	 * setter for quantity
	 * @param quantity
	 *               new quantity to set.
	 * @return
	 *        true if the operation was successful, false otherwise.
	 */
	public boolean setQuantity(int quantity) {
		if (quantity >= 0) {
			this.quantity = quantity;
			return true;
		}
		return false;
	}

	/**
	 * gets the total price of the line item
	 * @return
	 *         total price for the line item.
	 */

	public double getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		String name = product.getName();
		int quantity = product.getQuantity();
		double price = product.getPrice();
		return String.format("%s %d $%1.2f $%1.2f", name, quantity, price, totalPrice);
	}
}