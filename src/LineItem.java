import java.io.Serializable;

public class LineItem implements Serializable {
	private Product product;
	private int quantity;
	private double totalPrice;

	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = calculateTotalPrice(product, quantity);
	}

	private double calculateTotalPrice(Product product, int quantity) {
		double totalPrice = product.getPrice() * quantity;

		return totalPrice;
	}

//	private boolean adjustProductQuantity(Product product, int quantity) {
//		int newQuantity = product.getQuantity() - quantity;
//
//		return product.setQuantity(newQuantity);
//	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean setProduct(Product product) {
		if (product != null) {
			this.product = product;
			return true;
		}

		return false;
	}

	public boolean setQuantity(int quantity) {
		if (quantity >= 0) {
			this.quantity = quantity;
			return true;
		}

		return false;
	}

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