import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;
import java.util.TimeZone;

/**
 *
 * This would be used to test the system and randomly generate
 * Member and product objects
 *
 */
public class TesterFile {
    //instantiates the GroceryStore
    public static GroceryStore groceryStore = GroceryStore.getInstance();
    private Random random = new Random();
    //used for generating random strings.
    private char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};
    //used to store existing product and member names.
    private String[] membersToTest = new String[10];
    private String[] productsToTest = new String[40];
    private Member memberToTest;

    /**
     * this starts the testing process.
     */
    public void startTesting() {
        createRandomMembers();
        createRandomProducts();
        assertBusinessRulesOneToEight();
    }

    /**
     * this method will create randomly generated members with a predefined
     * array of phone numbers
     *
     */
    private void createRandomMembers() {
        System.out.println("Enrolling members...");
        int[] phoneNumbers = {1234567890, 2038991830, 2014534225, 2024566336,
                2047575676, 2022233333, 2052345554};
        double minimumFee = 12;
        for (int i = 0; i < 7; i++) {
            String memberName = generateAString();
            membersToTest[i] = memberName;
            String address = generateAString();
            double feePaid = Double.valueOf(String.format("%.2f", minimumFee
                    + new Random().nextDouble() * 10));
            memberToTest = new Member(memberName, address,
                    phoneNumbers[i], feePaid);
            groceryStore.enrollMember(memberName, address,
                    phoneNumbers[i], feePaid);

        }
        System.out.println("finished enrolling members");


    }

    /**
     * this method will create randomly generated products
     *
     */

    private void createRandomProducts() {
        System.out.println("Adding products...");
        int minimumPrice = 1;
        for (int i = 0; i < 30; i++) {
            String productName = generateAString();
            productsToTest[i] = productName;
            int quantity = random.nextInt(100);
            double price = Double.valueOf(String.format("%.2f", minimumPrice
                    + random.nextDouble() * 10));
            int minimumLevel = random.nextInt(10);
            groceryStore.addProduct(productName, quantity, price, minimumLevel);
        }

        System.out.println("finished adding products");

    }

    /**
     * This method tests if methods are returning the correct
     *
     *
     */

    private void assertBusinessRulesOneToEight() {

        //this will try to enroll a member with the given parameters
        //This should return true.
        assert (groceryStore.enrollMember("jack", "123 ave",
                2038577777, 33.22));
        //this will attempt to remove member with a member id of m1,
        //this should return true, as m1 is a valid member id
        assert (groceryStore.removeMember("m1"));
        //
        //this return be false because there is no such member with id m100
        assert (!groceryStore.removeMember("m100"));
        //this test will ensure retrieveMember returns a list with at-least
        //one member for  a given valid id.
        assert (groceryStore.retrieveMember(memberToTest.getName()).hasNext());
        //this should return false as two products with the same name should not exist.
        assert (!groceryStore.addProduct(productsToTest[0], 43, 44.34, 3));
        //the following 3 lines will be used to try  to checkout a member.
        Member member = groceryStore.retrieveMemberByID("m2");
        Product product = groceryStore.retrieveProduct(productsToTest[1]);
        Transaction transaction = new Transaction(member);
        TransactionList.getInstance().addTransaction(transaction);
        //intentionally buying all available quantity to test the following
        //assertion.
        groceryStore.checkout(product, product.getQuantity(), transaction);
        //checkout should return 1, not enough stock, when attempt was made
        //to buy more than the available quantity.
        assert (groceryStore.checkout(product, 1, transaction) == 1);
        //retrieve product should not return null for an existing product
        assert (groceryStore.retrieveProduct(productsToTest[0]) != null);
        //retrieve product should return null for non existing product
        assert (groceryStore.retrieveProduct("fakeID") == null);
        //item p102 does not exist, thus processShipment should return false.
        assert (groceryStore.processShipment("p102", 12) == null);
        //changing a price for non existing product should return null
        assert (groceryStore.changePrice("p1", 21) == null);

        //printing transaction for today for member id of m2
        System.out.println("Printing transactions. ");
        Iterator iterator = groceryStore.printTransactions("m2",
                (new GregorianCalendar(TimeZone.getTimeZone("canada/central"))),
                (new GregorianCalendar(TimeZone.getTimeZone("canada/central"))));
        while (iterator.hasNext()) {
            Transaction transactionToPrint = (Transaction) iterator.next();
            System.out.println(transactionToPrint);
        }
        //listing all members
        System.out.println("Listing all members ");
        Iterator iterator1 = groceryStore.listMembers();
        while (iterator1.hasNext()) {
            Member member1 = (Member) iterator1.next();
            System.out.println(member1);
        }
        //listing all products
        System.out.println("Listing all products ");
        Iterator iterator2 = groceryStore.listProducts();
        while (iterator2.hasNext()) {
            Product product1 = (Product) iterator2.next();
            System.out.println(product1);
        }
    }

    /**
     * this method will generate a random string.
     *
     *
     * @return a randomly generated string.
     */
    private String generateAString() {
        String s = "";
        for (int i = 0; i < 7; i++) {
            s += chars[random.nextInt(26)];
        }
        System.out.println(s);
        return s;

    }
}
