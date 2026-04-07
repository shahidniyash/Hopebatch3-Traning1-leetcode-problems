package oops;


import java.util.*;


interface Payment {
 void pay(double amount);
}

class CashPayment implements Payment {
 public void pay(double amount) {
     System.out.println("Paid using Cash: " + amount);
 }
}

class UPIPayment implements Payment {
 public void pay(double amount) {
     System.out.println("Paid using UPI: " + amount);
 }
}

class CardPayment implements Payment {
 public void pay(double amount) {
     System.out.println("Paid using Card: " + amount);
 }
}


class Product {
 private String code;
 private String name;
 private double price;
 private int quantity;

 public Product(String code, String name, double price, int quantity) {
     this.code = code;
     this.name = name;
     this.price = price;
     this.quantity = quantity;
 }

 public String getCode() { return code; }
 public String getName() { return name; }
 public double getPrice() { return price; }
 public int getQuantity() { return quantity; }

 public void reduceQuantity() { quantity--; }
 public void addQuantity(int qty) { quantity += qty; }
}


class Inventory {
 private Product[] products;
 private int size;

 public Inventory(int capacity) {
     products = new Product[capacity];
     size = 0;
 }

 public void addProduct(Product p) {
     if (size < products.length) {
         products[size++] = p;
     } else {
         System.out.println("Inventory Full");
     }
 }

 public Product getProduct(String code) {
     for (int i = 0; i < size; i++) {
         if (products[i].getCode().equals(code)) {
             return products[i];
         }
     }
     return null;
 }

 public void displayProducts() {
     for (int i = 0; i < size; i++) {
         Product p = products[i];
         System.out.println(p.getCode() + " - " + p.getName() + " Rs:" + p.getPrice() + " Qty:" + p.getQuantity());
     }
 }

 public Product[] getAllProducts() {
     return products;
 }

 public int getSize() {
     return size;
 }
}


class VendingMachine {
 private static VendingMachine instance = new VendingMachine();

 private Inventory inventory = new Inventory(10);
 private double balance = 0;
 private double totalSales = 0;

 private VendingMachine() {}

 public static VendingMachine getInstance() {
     return instance;
 }

 public Inventory getInventory() { return inventory; }



 public void insertMoney(double amount) {
     if (amount <= 0) {
         System.out.println("Invalid money!");
         return;
     }
     balance += amount;
     System.out.println("Balance: " + balance);
 }

 public void selectProduct(String code, Payment payment) {
     Product p = inventory.getProduct(code);

     if (p == null) {
         System.out.println("Invalid product code");
         return;
     }

     if (p.getQuantity() == 0) {
         System.out.println("Out of stock");
         return;
     }

     if (balance < p.getPrice()) {
         System.out.println("Insufficient balance");
         return;
     }

   
     payment.pay(p.getPrice());

   
     p.reduceQuantity();
     totalSales += p.getPrice();

     double change = balance - p.getPrice();
     System.out.println("Dispensed: " + p.getName());

     if (change > 0) {
         System.out.println("Change returned: " + change);
     }

     balance = 0;
 }

 public void cancelTransaction() {
     System.out.println("Transaction cancelled. Refund: " + balance);
     balance = 0;
 }



 public void refill(String code, int qty) {
     Product p = inventory.getProduct(code);
     if (p != null) {
         p.addQuantity(qty);
     }
 }

 public void checkInventory() {
     inventory.displayProducts();
 }

 public void collectCash() {
     System.out.println("Collected cash: " + totalSales);
     totalSales = 0;
 }



 public void autoDetectStock() {
     Product[] products = inventory.getAllProducts();
     int size = inventory.getSize();

     for (int i = 0; i < size; i++) {
         if (products[i].getQuantity() < 2) {
             System.out.println("Low stock alert: " + products[i].getName());
         }
     }
 }

 public void salesReport() {
     System.out.println("Total Sales: " + totalSales);
 }
}

public class Oops {
 public static void main(String[] args) {
     VendingMachine vm = VendingMachine.getInstance();

   
  vm.getInventory().addProduct(new Product("A1", "Chips", 20, 5));
  vm.getInventory().addProduct(new Product("A2", "Coke", 30, 5));
  vm.getInventory().addProduct(new Product("A3", "Chocolate", 25, 5));
  vm.getInventory().addProduct(new Product("A4", "Biscuits", 15, 5));
  vm.getInventory().addProduct(new Product("A5", "Juice", 35, 5));
  vm.getInventory().addProduct(new Product("A6", "Water Bottle", 10, 5));
  vm.getInventory().addProduct(new Product("A7", "Sandwich", 40, 5));

  Scanner sc = new Scanner(System.in);

     while (true) {
         System.out.println("\n1.Insert Money 2.Show Products 3.Buy 4.Cancel 5.Admin 6.Exit");
         int choice = sc.nextInt();

         switch (choice) {
             case 1:
                 System.out.print("Enter amount: ");
                 vm.insertMoney(sc.nextDouble());
                 break;

             case 2:
                 vm.checkInventory();
                 break;

             case 3:
                 System.out.print("Enter code: ");
                 String code = sc.next();

                 System.out.println("1.Cash 2.UPI 3.Card");
                 int payChoice = sc.nextInt();

                 Payment payment;
                 if (payChoice == 1) payment = new CashPayment();
                 else if (payChoice == 2) payment = new UPIPayment();
                 else payment = new CardPayment();

                 vm.selectProduct(code, payment);
                 break;

             case 4:
                 vm.cancelTransaction();
                 break;

             case 5:
                 System.out.println("ADMIN: 1.Refill 2.Collect Cash 3.Stock Alert 4.Sales");
                 int admin = sc.nextInt();

                 if (admin == 1) {
                     System.out.print("Code: ");
                     String c = sc.next();
                     System.out.print("Qty: ");
                     int q = sc.nextInt();
                     vm.refill(c, q);
                 } else if (admin == 2) {
                     vm.collectCash();
                 } else if (admin == 3) {
                     vm.autoDetectStock();
                 } else if (admin == 4) {
                     vm.salesReport();
                 }
                 break;

             case 6:
                 System.exit(0);
         }
     }
 }
}
