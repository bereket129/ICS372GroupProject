import java.io.*;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

public class GroceryStore implements Serializable{
	private static final long serialVersionUID = 1L;
	public static GroceryStore groceryStore;
	private MemberList memberList;
	private Inventory inventory;
	private TransactionList transactionList;
	private UserInterface userInterface;

	/**
	 * Private for the singleton pattern creates the MemberList,Inventory
	 * and TransactionList collection objects and stores a reference to
	 * the userInterface singleton Object.
	 *
	 */
	private GroceryStore() {
		memberList = MemberList.getInstance();
		inventory = Inventory.getInstance();
		transactionList = TransactionList.getInstance();
		userInterface = UserInterface.instance();
	}
	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static GroceryStore getInstance() {
		if(groceryStore==null){
			MemberIdServer.instance();
			groceryStore = new GroceryStore(); // instantiates all singletons.
		}
		return groceryStore;
	}
	/**
	 * Organizes the operations for enrolling a member
	 *
	 * @param name name of the member
	 *
	 * @param address address of the member
	 * @param phoneNumber phone number of the member
	 * @return boolean to indicate if the member was enrolled successfully.
	 */
	public boolean enrollMember(String name, String address, int phoneNumber, double feePaid) {
		Member member = new Member(name, address, phoneNumber, feePaid);
		return memberList.addMember(member);
	}
	/**
	 * Organizes the operations for enrolling a member
	 *
	 * @param memberId id of the member

	 * @return boolean to indicate if the member was removed successfully.
	 */
	public boolean removeMember(String memberId) {
		return memberList.removeMember(memberId);
	}
	/**
	 * Organizes the operations for retrieving a member from a member list.
	 *
	 * @param memberId id of the member

	 * @return a reference of the Member object found.
	 */
	public Member retrieveMember(String memberId) {
		   return memberList.findMember(memberId);

	}
	/**
	 * Organizes the operations for adding a product.
	 *
	 * @param name name of the product
	 * @param quantity stock quantity of the product
	 * @param price price of the product
	 * @param minimumLevel the minimum re-order level
	 * @return a boolean to indicate if the the product was added successfully.
	 */
	public boolean addProduct(String name, int quantity, double price, int minimumLevel) {
		Product product = new Product(name, quantity, price,minimumLevel);
		return inventory.addProduct(product);
	}
	/**
	 * Organizes the operations for checking a member's cart out.
	 *
	 * @param memberId id of the member

	 * @return a reference of the Transaction object created.
	 */
	public Transaction checkout(String memberId) {
		//TODO
	}
	/**
	 * Organizes the operations for retrieving a product's information.
	 *
	 * @param productId id of the product

	 * @return a reference of the Product object returned by the inventory.
	 */
	public Product retrieveProduct(String productId) {
		return inventory.findProduct(productId);
	}

	/**
	 * Organizes the operations for retrieving a processing a shipment.
	 *
	 * @param productId id of the product
	 * @param quantity quantity of the item shipped.
	 * @return a boolan to indicate if processing the shipment was successful.
	 */
	public boolean processShipment(String productId, int quantity) {
		Product product = inventory.findProduct(productId);
		return product.setQuantity(quantity);
	}
	
	public boolean changePrice(String productId, double price) {
		Product product = inventory.findProduct(productId);
		return product.setPrice(price);
		//TODO
	}
	/**
	 * Organizes the operations for printing a transaction on the given
	 * date range
	 *
	 * @param memberId id of the member
	 * @param startDate start date  of the transaction
	 * @param endDate end date of the transaction
	 * @return a list of transactions that were made between the date range.
	 */
	
	public Iterator printTransactions(String memberId, GregorianCalendar startDate, GregorianCalendar endDate) {
		Iterator result=  transactionList.getTransactions(startDate,endDate);
		LinkedList<Transaction> memberTransactions = new LinkedList<Transaction>();
		while(result.hasNext()){
			Transaction transaction = (Transaction) result.next();
			if(transaction.getMember().getMemberId().equals(memberId)){
				memberTransactions.add(transaction);
			}
		}
		return memberTransactions.iterator();
	}
	/**
	 * Organizes the operations for listing all members
	 *
	 * @return a list of members.
	 */
	public Iterator listMembers() {
		return memberList.getMembers();
	}
	/**
	 * Organizes the operations for listing all products
	 *
	 * @return a list of products.
	 */

	public Iterator listProducts() {
		return inventory.getProducts();
		//TODO
	}

	/**
	 * Serializes the Library object
	 *
	 * @return true iff the data could be saved
	 */
	public boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(groceryStore);
			output.writeObject(MemberIdServer.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	/**
	 * Retrieves a deserialized version of the library from disk
	 *
	 * @return a Library object
	 */
	public GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			groceryStore = (GroceryStore) input.readObject();
			MemberIdServer.retrieve(input);
			return groceryStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	
}
