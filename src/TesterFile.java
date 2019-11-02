import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;
import java.util.TimeZone;

public class TesterFile {
    public static GroceryStore groceryStore = GroceryStore.getInstance();
    private Random random = new Random();
    private char[] chars = {'a','b','c','d','e','f','g','h',
            'i','j','k','l','m','n','o','p','q','r','s',
            't','u','v','w','x','y','z'};
    private String[] membersToTest = new String[10];
    private String[] productsToTest = new String[40];
    private Member memberToTest;


    public void startTesting(){
        createRandomMembers();
        createRandomProducts();
        assertBusinessRulesOneToEight();
    }

    private void createRandomMembers(){
        System.out.println("Enrolling members...");
        int[] phoneNumbers = {1234567890,2038991830,2014534225,2024566336,2047575676,2022233333,2052345554};
        double minimumFee = 12;
        for(int i=0;i<7;i++) {
            String memberName = generateAString();
            membersToTest[i] = memberName;
            String address = generateAString();
            double feePaid = Double.valueOf(String.format("%.2f",minimumFee + new Random().nextDouble()*10));
                memberToTest = new Member(memberName, address, phoneNumbers[i], feePaid);
               groceryStore.enrollMember(memberName,address,phoneNumbers[i],feePaid);

        }
        System.out.println("finished enrolling members");


    }

    private void createRandomProducts(){
        System.out.println("Adding products...");
        int minimumPrice = 1;
        for(int i=0;i<30;i++){
            String productName = generateAString();
            productsToTest[i] = productName;
            int quantity = random.nextInt(100);
            double price = Double.valueOf(String.format("%.2f",minimumPrice+random.nextDouble()*10));
            int minimumLevel =  random.nextInt(10);
            groceryStore.addProduct(productName,quantity,price,minimumLevel);
        }

        System.out.println("finished adding products");

    }

    private void assertBusinessRulesOneToEight(){

        assert(groceryStore.enrollMember("jack","123 ave",2038577777,33.22));
        assert(groceryStore.removeMember(2));
        //this should be false because there is no such member with id 100
        assert(!groceryStore.removeMember(100));
       //this test will ensure retrieveMember returns a list with at-least
        //one member for the given name.
        assert(groceryStore.retrieveMember(memberToTest.getName()).hasNext());
        //this should return false as two products with the same name should not exist.
        assert(groceryStore.addProduct(productsToTest[0],43,44.34,3));
        Member member = groceryStore.retrieveMember(3);
        Product product = groceryStore.retrieveProduct(33);
        Transaction transaction = new Transaction(member);
        //return type 0 is used for successful transaction and 1 for
        // //unsuccessful transactions
        assert(groceryStore.checkout(product,product.getQuantity(),transaction)==1);
        //checkout should return 1, not enough stock, when attempt was made
        //to buy more than the available quantity.
        assert(groceryStore.checkout(product,1,transaction)==0);
        //retrieve product should not return null for an existing product
        assert(groceryStore.retrieveProduct(productsToTest[0])!=null);
        //retrieve product should return null for non existing product
        //parameter selected because the length of all product names is less than\
        //seven.
        assert(groceryStore.retrieveProduct("abcdefgttt")==null);
        //item 102 does not exist, thus processShipment should return false.
        assert(!groceryStore.processShipment(102,12));
        //item 0 can not exist, due to the reason that the first few
        //numbers are assigned to members. since Id are assigned
        //in increasing order, 0 could never be a valid product id.
        assert(!groceryStore.changePrice(0,21));

        //printing transaction for today for member id of 3
        System.out.println("Printing transactions. ");
        Iterator iterator = groceryStore.printTransactions(3,
                (new GregorianCalendar(TimeZone.getTimeZone("canada/central"))),
                (new GregorianCalendar(TimeZone.getTimeZone("canada/central"))));
        while(iterator.hasNext()){
            Transaction transactionToPrint = (Transaction) iterator.next();
            System.out.println(transactionToPrint);
        }

        System.out.println("Listing all members ");
        Iterator iterator1 = groceryStore.listMembers();
        while(iterator1.hasNext()){
            Member member1 = (Member) iterator1.next();
            System.out.println(member1);
        }

        System.out.println("Listing all products ");
        Iterator iterator2 = groceryStore.listProducts();
        while(iterator2.hasNext()){
            Product product1 = (Product) iterator2.next();
            System.out.println(product1);
        }


    }

    private String generateAString(){
        String s="";
        for(int i=0;i<7;i++){
            s+=chars[random.nextInt(26)];
        }
        System.out.println(s);
        return s;

    }



}
