import java.io.*;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class GroceryStore implements Serializable{
	private static final long serialVersionUID = 1L;
	public static GroceryStore groceryStore;
	private MemberList memberList;
	private Inventory inventory;
	private TransactionList transactionList;
	public static final int NOT_ENOUGH_IN_STOCK=1;




	/**
	 * Private for the singleton pattern creates the MemberList,Inventory
	 * and TransactionList collection objects
	 *
	 */
	private GroceryStore() {
		memberList = MemberList.getInstance();
		inventory = Inventory.getInstance();
		transactionList = TransactionList.getInstance();

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
	 * Organizes the operations for removing a member
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
	 * @param memberName name of the member

	 * @return a reference of the Iterator object that contains members with
	 * the same name.
	 */
	public Iterator retrieveMember(String memberName) {
		return memberList.findMember(memberName);

	}

	/**
	 * Organizes the operations for retrieving a member from a member list using .
     *
     * @param memberId id of the member
	 * @return a reference of the Member object found.
	 */
	public Member retrieveMemberByID(String memberId) {
		   return memberList.findMemberByID(memberId);

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
		if(retrieveProduct(name)==null){
			Product product = new Product(name, quantity,minimumLevel,price);
			return inventory.addProduct(product);
		}else{
			return false;
		}


	}
	/**
	 * Organizes the operations for checking a member's cart out.
	 *
	 * @param memberId id of the member

	 * @return a reference of the Transaction object created.
	 */
	public Transaction checkout(String memberId) {
		Member member = memberList.findMemberByID(memberId);
		if(member==null){
			return null;
		}else {
			Transaction transaction = new Transaction(member);
			transactionList.addTransaction(transaction);
			return transaction;
		}
	}

	public int checkout(Product product, int quantity, Transaction transaction){
		if(product.getQuantity()<quantity){
			return NOT_ENOUGH_IN_STOCK;
		}else{
			product.setQuantity(product.getQuantity()-quantity);
			LineItem lineItem = new LineItem(product,quantity);
			transaction.addLineItem(lineItem);
		}
			return 0;


	}
	/**
	 * Organizes the operations for retrieving a product's information.
	 *
	 * @param productName name of the product

	 * @return a reference of the Product object returned by the inventory.
	 */
	public Product retrieveProduct(String productName) {
		return inventory.findProduct(productName);
	}

	/**
	 * Organizes the operations for retrieving a product's information.
	 *
	 * @param productId id of the product

	 * @return a reference of the Product object returned by the inventory.
	 */
	public Product findProductById(String productId) {
		return inventory.findProductById(productId);
	}

	/**
	 * Organizes the operations for retrieving a processing a shipment.
	 *
	 * @param productId id of the product
	 * @param quantity quantity of the item shipped.
	 * @return a boolan to indicate if processing the shipment was successful.
	 */
	public Product processShipment(String productId, int quantity) {
		Product product = inventory.findProductById(productId);
		if(product != null){
			product.setQuantity(product.getQuantity()+quantity);
		}

		return product;

	}
	
	public Product changePrice(String productId, double price) {
		Product product = inventory.findProductById(productId);
		if(product!=null){
			if(product.setPrice(price)){
				return product;
			}
		}
		return null;
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
		return transactionList.getTransactions(memberId, startDate, endDate);
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
	 *
	 */
	public String checkForReorder(){
		String reorderItems="Reordering items...\n";
		Iterator iterator = inventory.getProducts();
		while(iterator.hasNext()){
			Product product = (Product) iterator.next();
			if(product.getQuantity()<=product.getMinimumLevel()){
				reorderItems+=product.getName()+"\n";
			}
		}
		return reorderItems;
	}

	/**
	 *
	 */
	public GroceryStore test(){
		TesterFile testerFile = new TesterFile();
		testerFile.startTesting();
		return getInstance();
	}
	/**
	 * Retrieves a deserialized version of the grocerystore from disk
	 *
	 * @return a groceryStore object
	 */
	public static GroceryStore retrieve() {
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
