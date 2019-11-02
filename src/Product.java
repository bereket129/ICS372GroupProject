import java.io.Serializable;

public class Product implements Serializable {
	private int productId;
	private String name;
	private int quantity;
	private int minimumLevel;
	private double price;
	
	public Product(String name, int quantity, int minimumLevel, double price) {
		this.name = name;
		this.quantity=quantity;
		this.minimumLevel=minimumLevel;
		this.price = price;
		this.productId = MemberIdServer.instance().getId();
		
	}

	public int getProductId() {
		return productId;
	}

	public boolean setProductId(int productId) {
		this.productId = productId;
		return true;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean setQuantity(int quantity) {
		if(quantity>=0) {
			this.quantity = quantity;
			return true;
		}else{
			return false;
		}
	}

	public int getMinimumLevel() {
		return minimumLevel;
	}

	public boolean setMinimumLevel(int minimumLevel) {
		if(minimumLevel>=0){
			this.minimumLevel = minimumLevel;
			return true;
		}else{
			return false;
		}

	}

	public double getPrice() {
		return price;
	}

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
		return "id: "+productId+ " Product Name: "+name+" quantity in stock : "
				+quantity+" price: "
				+ price;
	}
}
