
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
   
    // input scanner 
    private Scanner in;
    // all the products of the store
    private Product[] allProducts= {
        new Product("Shampoo", "Hair", "", 24.95),
        new Product("Conditioner", "Hair", "", 24.95),
        new Product("Dry Shampoo", "Hair", "", 19.95),
        new Product("Hair Treatments", "Hair", "", 14.95),
        new Product("Cleanser", "Face", "", 35.95),
        new Product("Moisturizer", "Face", "", 49.95),
        new Product("Masks", "Face", "", 29.95),
        new Product("Toner", "Face", "", 24.95),
        new Product("Hydrate", "Body", "", 35.95),
        new Product("Smoothing", "Body", "", 35.95),
        new Product("Firming", "Body", "", 45.95),
        new Product("Butters", "Body", "", 45.95),
        new Product("Rose argan", "Fragrance", "", 59.95),
        new Product("African Paradise", "Fragrance", "", 59.95),
        new Product("Citrus", "Fragrance", "", 45.95),
        new Product("Jasmine Cherry Blossom", "Fragrance", "", 45.95)
    };
    
    // ArrayList to store the ordered products
    private ArrayList<OrderedProduct> orderedProducts = new ArrayList<>();
    private double tax = 0d; // assume sales tax is zero
    
    public Store() {
        // create the scanner to scan the inputs
        in = new Scanner(System.in);
    }
    
    private void printProductList(){
        System.out.println("Product List: ");
        // print the topics of the table
        System.out.println("--------------------------------------------------");
        System.out.printf("  %-28s%-12s%-12s\n","Name","Category","Price");
        System.out.println("--------------------------------------------------");
        // print the product list
        
        int number = 1; // product index
        for (Product p : allProducts) {
            // create the format of the a product of the product table
            String row = String.format("(%-2s) %-25s%-12s$%-12.2f",number,p.getName(),p.getCategory(),p.getPrice());
            // print the data aligning to left
            System.out.println(row);
            number++;
        }
    }
    
    private double calculateTotal(){
        double total = 0d; // sub total of the invoice 
        for (OrderedProduct p : orderedProducts) {
            // calculate the total by adding the total price of each item
            total += (p.getProduct().getPrice()*p.getQuantity());
        }
        // return the total
        return total;
    }
    
    private void printReceipt(){
        System.out.println("===================RECEIPT=======================");
        // print the topics of the table
        System.out.println("--------------------------------------------------");
        System.out.printf("%-25s%-12s%-12s\n","Name","Quantity","Total Price");
        System.out.println("--------------------------------------------------");
        // print the ordered product list
        
        for (OrderedProduct ordered_pro : orderedProducts) {
            Product p = ordered_pro.getProduct();
            // create the format of the a product of the product table
            String row = String.format("%-25s%-12d$%-12.2f",p.getName(),ordered_pro.getQuantity(),p.getPrice()*ordered_pro.getQuantity());
            // print the data aligning to left
            System.out.println(row);
            
        }
        System.out.println("--------------------------------------------------");
        //print the subtotal
        System.out.printf("Subtotal = $%.2f\n",calculateTotal());
        // print the tax
        System.out.printf("Tax = $%.2f\n",tax);
        // print the grand total
        System.out.printf("Grandtotal = $%.2f\n",(calculateTotal()+tax));
        // print the payment infomation
        System.out.println("Paid By The Credit Card!");
        System.out.println("==================================================");
        // finish the receipt
    }
    
    public static void main(String[] args) {
        // create one Store object 
        Store store = new Store(); 
        
        while (true) {            
            // print the welcome note
            System.out.println("Welcome to the Beauty Coder Storefront!\n");
            System.out.println("All of our products are 100% organic and cruelty free.\n");
            System.out.println("Please select from our menu...\n");
            // print the product list
            store.printProductList();
            // print empty line
            System.out.println("\nOrder Products:\n");
            while (true) { 
                // order a product
                System.out.print("Enter the serial number of the product: ");
                // read the user ordered product serial number(menu number)
                int number = store.in.nextInt();
                // ask the quantity from the user
                System.out.print("Enter the quantity: ");
                // read the user input quantity
                int quantity = store.in.nextInt();
               
                // identify the product from all the products of store
                Product p = store.allProducts[number-1];
                // get the price of the product
                double price = p.getPrice();
                // create an OrderedProduct object to insert it to the ordered_products list
                OrderedProduct ordered_product = new OrderedProduct(store.allProducts[number-1], quantity);
                // add the product to the arrayList
                store.orderedProducts.add(ordered_product);
                // print the line total of each ordered product
                System.out.printf("Item price * Quantity = $%.2f x %d = $%.2f\n",price,quantity,(price*quantity));
                // ask for ordering more products
                System.out.print("Do you want to order more products(y/n): ");
                // read the option of the user
                //------------------------------------
                store.in.nextLine(); // to skip the inconvenience reads of the scanner 
                String option = store.in.nextLine();
                //-------------------------------------
                // if the user want to complete his or her order
                if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                    System.out.println("\nYour order completed!");
                    // print the subtotal
                    System.out.printf("Subtotal = $%.2f\n",store.calculateTotal());
                    // print the tax
                    System.out.printf("Tax = $%.2f\n",store.tax);
                    // print the grand total
                    System.out.printf("Grandtotal = $%.2f\n",(store.calculateTotal()+store.tax));
                    
                    // Enter the credit card details
                    System.out.println("\nCredit Card Details");
                    // ask the card number
                    System.out.print("Enter card number: ");
                    // read the card number from the user
                    String card_number = store.in.nextLine();
                    // ask the expiration 
                    System.out.print("Enter expiration(MM/yyyy): ");
                    // read the expiration from the user
                    String expiration = store.in.nextLine();
                    // ask the cvv
                    System.out.print("Enter CVV: ");
                    // read the cvv of the card from the user
                    int cvv = store.in.nextInt();
                    // print the reciept
                    store.printReceipt();
                    // and break the loop
                    break;
                }

            }
            // ask to continue the process of the pos terminal
            System.out.print("Do you want to continue(y/n): ");
            // get the user option
            store.in.nextLine();
            String option = store.in.nextLine();
            // if the user option is no, break the loop and stop the program
            if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                break;
            }
            
            // clear the previous orderProducts arraylist
            store.orderedProducts.clear();
            // create new arraylist of orderedProducts
            store.orderedProducts = new ArrayList<>();
        }
  
    }
}