import java.io.Serializable;
/**
 *
 * The following code is based on code from Brahma Dathan
 * lecture notes
 *
 *This code is for a single product
 */
public class Product implements Serializable {
	private String productId;
	private String name;
	private int quantity;
	private int minimumLevel;
	private double price;
	/**
	 * Represents a single product
	 *
	 * @param name
	 *            name of the product
	 * @param quantity
	 *            the quantity of the certain product in store inventory
	 * @param price
	 *            current price of the product
	 */
	public Product(String name, int quantity, int minimumLevel, double price) {
		this.name = name;
		this.quantity=quantity;
		this.minimumLevel=minimumLevel;
		this.price = price;
		this.productId = "p"+MemberIdServer.instance().getId();
		
	}
	/**
	 * Getter for productId
	 *
	 * @return product productId
	 */

	public String getProductId() {
		return productId;
	}
	/**
	 * Setter for productId
	 *
	 * @param productId
	 *            product's new productId
	 */

	public boolean setProductId(String productId) {
		this.productId = productId;
		return true;
	}
	/**
	 * Getter for name
	 *
	 * @return product name
	 */

	public String getName() {
		return name;
	}
	/**
	 * Setter for name
	 *
	 * @param name
	 *            product's new name
	 */
	public boolean setName(String name) {
		this.name = name;
		return true;
	}
	/**
	 * Getter for quantity
	 *
	 * @return product quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Setter for quantity
	 *
	 * @param quantity
	 *            product's new quantity
	 */
	public boolean setQuantity(int quantity) {
		if(quantity>=0) {
			this.quantity = quantity;
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Getter for minimumLevel
	 *
	 * @return product minimumLevel
	 */
	public int getMinimumLevel() {
		return minimumLevel;
	}
	/**
	 * Setter for minimumLevel
	 *
	 * @param minimumLevel
	 *            product's new minimumLevel
	 */
	public boolean setMinimumLevel(int minimumLevel) {
		if(minimumLevel>=0){
			this.minimumLevel = minimumLevel;
			return true;
		}else{
			return false;
		}

	}
	/**
	 * Getter for price
	 *
	 * @return product price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Setter for price
	 *
	 * @param price
	 *            product's new price
	 */
	public boolean setPrice(double price) {
		if(price>=0){
			this.price = price;
			return true;
		}else {
			return false;
		}

	}

	@Override
	public String toString() {
		return "id: "+productId
				+" name: "+name
				+ " quantity in stock : " +quantity
				+" minimum stock level: "+minimumLevel
				+" price: " + price;
	}
}
