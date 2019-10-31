
/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 
 * This class implements the user interface for the grocery store project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 *
 */
public class UserInterface {
    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int EXIT = 0;
    private static final int ENROLL_A_MEMBER = 1;
    private static final int REMOVE_A_MEMBER = 2;
    private static final int RETRIEVE_MEMBER_INFO = 3;
    private static final int ADD_PRODUCT = 4;
    private static final int CHECK_OUT_A_MEMBER_CART = 5;
    private static final int RETRIEVE_PRODUCT_INFO = 6;
    private static final int PROCESS_SHIPMENT = 7;
    private static final int CHANGE_PRICE = 8;
    private static final int PRINT_TRANSACTION = 9;
    private static final int LIST_ALL_MEMBERS = 10;
    private static final int LIST_ALL_PRODUCTS = 11;
    private static final int SAVE = 12;
    private static final int HELP = 13;
    private static GroceryStore groceryStore;

    /**
     * Made private for singleton pattern. Conditionally asks for
     * a test bed to be created.Otherwise, it gets a singleton grocery store object.
     */
    private UserInterface() {
        if (yesOrNo("Do you want to generate a test bed and invoke"
                +" the functionality using asserts?")) {
            retrieve();
        } else {
        }
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }

    /**
     * Gets a token after prompting
     * 
     * @param prompt
     *            - whatever the user wants as prompt
     * @return - the token from the keyboard
     * 
     */
    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Queries for a yes or no and returns true for yes and false for no
     * 
     * @param prompt
     *            The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     * 
     */
    private boolean yesOrNo(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }

    /**
     * Converts the string to a number
     * 
     * @param prompt
     *            the string for prompting
     * @return the integer corresponding to the string
     * 
     */
    public int getNumber(String prompt) {
        do {
            try {
                String item = getToken(prompt);
                Integer number = Integer.valueOf(item);
                return number.intValue();
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }
        } while (true);
    }

    /**
     * Converts the string to a double
     *
     * @param prompt
     *            the string for prompting
     * @return the double corresponding to the string
     *
     */
    public double getDouble(String prompt) {
        do {
            try {
                String item = getToken(prompt);
                Double number = Double.valueOf(item);
                return number.intValue();
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }
        } while (true);
    }

    /**
     * Prompts for a date and gets a date object
     * 
     * @param prompt
     *            the prompt
     * @return the data as a Calendar object
     */
    public Calendar getDate(String prompt) {
        do {
            try {
                Calendar date = new GregorianCalendar();
                String item = getToken(prompt);
                DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
                date.setTime(dateFormat.parse(item));
                return date;
            } catch (Exception fe) {
                System.out.println("Please input a date as mm/dd/yy");
            }
        } while (true);
    }

    /**
     * Prompts for a command from the keyboard
     * 
     * @return a valid command
     * 
     */
    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

    /**
     * Displays the help screen
     * 
     */
    public void help() {
        System.out.println("Enter a number between 0 and 13 as explained below:");
        System.out.println(EXIT + "  to Exit\n");
        System.out.println(ENROLL_A_MEMBER + "  to enroll a member");
        System.out.println(REMOVE_A_MEMBER + "  to remove a member");
        System.out.println(RETRIEVE_MEMBER_INFO + "  to retrieve member's information");
        System.out.println(ADD_PRODUCT + "  to add a product ");
        System.out.println(CHECK_OUT_A_MEMBER_CART + "  to checkout a members cart");
        System.out.println(RETRIEVE_PRODUCT_INFO + "  to retrieve products info");
        System.out.println(PROCESS_SHIPMENT + "  to process  a shipment");
        System.out.println(CHANGE_PRICE + "  to change price of a product");
        System.out.println(PRINT_TRANSACTION + "  to print a transaction");
        System.out.println(LIST_ALL_MEMBERS + " to list all members");
        System.out.println(LIST_ALL_PRODUCTS + " to list all products");
        System.out.println(SAVE + " to save data");
        System.out.println(HELP + " for help");
    }

    /**
     * Method to be called for adding a member. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for adding the
     * member.
     *
     */
    public void addMember() {
        String name = getToken("Enter name of the member");
        String address= getToken("Enter the address of the member");
        int phoneNumber = getNumber("Enter the phone number of the member");
        double feePaid = getDouble("Enter the fee paid");
        boolean result;
        result=groceryStore.enrollMember(name,address,phoneNumber,feePaid);
        if(result){
            System.out.println("Member added successfully");
        }else{
            System.out.println("Failed to add member");
        }

    }

    /**
     * Method to be called for removing a member. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for removing the
     * member.
     *
     */
    public void removeAMember() {
        String memberId = getToken("Enter the Id of the member");
        boolean result = groceryStore.removeMember(memberId);
        if(result){
            System.out.println("Member removed successfully");
        }else{
            System.out.println("Failed to remove member");
        }

    }


    /**
     * Method to be called for retrieving member information. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for retrieving
     * member information.
     * 
     */
    public void retrieveMemberInfo() {
        String memberId = getToken("Enter the id of the memeber");
        Member result = groceryStore.retrieveMember(memberId);
        if(result==null){
            System.out.println("No such member exist");
        }else{
            System.out.println(result);
        }

    }

    /**
     * Method to be called for adding a product. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for adding the
     * a product.
     *
     */

    public void addProduct() {
        String name = getToken("Enter the product name");
        int quantity = getNumber("Enter the stock quantity");
        double price = getDouble("Enter price of the product");
        int minimumLevel = getNumber("Enter the minimum reorder level");
        boolean result = groceryStore.addProduct(name,quantity,price,minimumLevel);
        if(result){
            System.out.println("Successfully added the product");
        }else {
            System.out.println("Failed to add the product");
        }

    }


    /**
     * Method to be called for checking out member's  product. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for checking out
     * member's product.
     * 
     */
    public void checkOut() {
            String memberId = getToken("Enter the member id");
            Transaction result = groceryStore.checkout(memberId);
            if(result != null){
                //TODO
            }
    }

    /**
     * Method to be called for retrieving product information. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for retrieving
     * product info.
     * 
     */
    public void retrieveProductInfo() {
        String productId = getToken("Enter the id of the product");
        Product result = groceryStore.retrieveProduct(productId);
        if(result==null){
            System.out.println("No such product exist");
        }else{
            System.out.println(result);
        }

    }

    /**
     * Method to be called for processing a shipment. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for processing
     * a shipment.
     * 
     */
    public void processShipment() {
        String productId = getToken("Enter the product Id");
        int quantity = getNumber("Enter the quantity");
        boolean result = groceryStore.processShipment(productId,quantity);
        if(result){
            System.out.println("Updated stock successfully");
        }else{
            System.out.println("Failed to update the stock");
        }

    }

    /**
     * Method to be called for changing a price of a product. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for changing
     * a product's price.
     * 
     */
    public void changePrice() {
        String productId = getToken("Enter the product id");
        double price = getDouble("Enter the new price");
        boolean result = groceryStore.changePrice(productId,price);
        if(result){
            System.out.println("Updated price successfully");
        }else{
            System.out.println("Failed to update the price");
        }
    }

    /**
     * Method to be called for printing transactions. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for printing
     * a transaction.
     *
     * 
     */
    public void getTransaction() {
        String memberID = getToken("Enter the id of the member");
        GregorianCalendar startDate =(GregorianCalendar) getDate("Enter the start date");
        GregorianCalendar endDate = (GregorianCalendar) getDate("Enter the end date");
        Iterator result = groceryStore.printTransactions(memberID,startDate,endDate);
        if(result==null){
            System.out.println("No transaction found");
        }else{

            while (result.hasNext()) {
                Transaction transaction = (Transaction) result.next();
                System.out.println(transaction);
            }
            System.out.println("No more transaction to print");
        }


    }

    /**
     * Method to be called for listing all members. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for listing
     * all members.
     * 
     */
    public void listAllMembers() {
        Iterator result = groceryStore.listMembers();
        if(result==null){
            System.out.println("No members to display");
        }else{
            while (result.hasNext()) {
                Member member = (Member) result.next();
                System.out.println(member);
            }
            System.out.println("No more members to print");
        }
    }

    /**
     * Method to be called for listing all products. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for listing
     * all products.
     * 
     */
    public void listAllProducts() {
        Iterator result = groceryStore.listProducts();
        if(result==null){
            System.out.println("No products to display");
        }else{
            while (result.hasNext()) {
                Product product = (Product) result.next();
                System.out.println(product);
            }
            System.out.println("No more members to print");
        }

    }

    /**
     * Method to be called for saving the grocery store object. Uses the appropriate
     * grocery store method for saving.
     * 
     */
    private void save() {
        if (groceryStore.save()) {
            System.out.println(" The Grocery Store has been successfully saved in the file GroceryStoreData \n");
        } else {
            System.out.println(" There has been an error in saving \n");
        }
    }

    /**
     * Method to be called for retrieving saved data. Uses the appropriate
     * grocery store method for retrieval.
     * 
     */
    private void retrieve() {
        try {
            if (groceryStore == null) {
                groceryStore = groceryStore.retrieve();
                if (groceryStore != null) {
                    System.out.println(" The Grocery Store has been successfully retrieved from the file GroceryStoreData \n");
                } else {
                    System.out.println("File doesnt exist; creating new GroceryStore object");
                    groceryStore = GroceryStore.getInstance();
                }
            }
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * Orchestrates the whole process. Calls the appropriate method for the
     * different functionalities.
     * 
     */
    public void process() {
        int command;
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
            case ENROLL_A_MEMBER:
                addMember();
                break;
            case REMOVE_A_MEMBER:
                removeAMember();
                break;
            case RETRIEVE_MEMBER_INFO:
                retrieveMemberInfo();
                break;
            case ADD_PRODUCT:
                addProduct();
                break;
            case RETRIEVE_PRODUCT_INFO:
                retrieveProductInfo();
                break;
            case CHECK_OUT_A_MEMBER_CART:
                checkOut();
                break;
            case PROCESS_SHIPMENT:
                processShipment();
                break;
            case CHANGE_PRICE:
                changePrice();
                break;
            case PRINT_TRANSACTION:
                getTransaction();
                break;
            case LIST_ALL_MEMBERS:
                listAllMembers();
                break;
            case LIST_ALL_PRODUCTS:
                listAllProducts();
                break;

            case SAVE:
                save();
                break;
            case HELP:
                help();
                break;
            }
        }
    }

    /**
     * The method to start the application. Simply calls process().
     * 
     * @param args
     *            not used
     */
    public static void main(String[] args) {
        UserInterface.instance().process();
    }
}