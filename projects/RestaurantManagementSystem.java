import java.time.LocalDateTime;
import java.util.*;

class MenuItem {
    int id;
    String name;
    double price;
    boolean available;

    MenuItem(int id, String name, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }
}

class Reservation {
    String customerName;
    String phoneNumber;
    int tableNumber;
    boolean reserved;

    Reservation(String customerName, String phoneNumber, int tableNumber, boolean reserved) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.tableNumber = tableNumber;
        this.reserved = reserved;
    }
}

class Order {
    String customerName;
    List<MenuItem> items = new ArrayList<>();
    String status;
    boolean paymentDone;
    boolean served;
    LocalDateTime paymentTime;

    Order(String customerName) {
        this.customerName = customerName;
        this.status = "Placed";
        this.paymentDone = false;
        this.served = false;
        this.paymentTime = null;
    }

    double getTotalAmount() {
        double sum = 0;
        for (MenuItem item : items) {
            sum += item.price;
        }
        return sum;
    }
}

public class RestaurantManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<MenuItem> menu = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    static List<String> feedbacks = new ArrayList<>();
    static List<String> servants = new ArrayList<>();
    static int menuIdCounter = 12;

    public static void main(String[] args) {
        loadSampleMenu();
        loadServants();
        while (true) {
            System.out.println("\n--- Welcome to Can-Do Crew Diner ---");
            System.out.println("1. Customer Panel");
            System.out.println("2. Admin Panel");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    customerPanel();
                    break;
                case 2:
                    adminPanel();
                    break;
                case 3:
                    System.out.println("Thank you for visiting Can-Do Crew Diner!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    static void loadSampleMenu() {
        menu.add(new MenuItem(1, "Veg Biryani", 180, true));
        menu.add(new MenuItem(2, "Chicken Burger", 150, true));
        menu.add(new MenuItem(3, "Cold Coffee", 90, true));
        menu.add(new MenuItem(4, "Ice Cream", 100, true));
        menu.add(new MenuItem(5, "Butter Naan", 30, true));
        menu.add(new MenuItem(6, "Butter Chicken", 300, true));
        menu.add(new MenuItem(7, "Mojito", 200, true));
        menu.add(new MenuItem(8, "Can-Do Crew Special Dish", 500, true));
    }

    static void loadServants() {
        servants.add("Ravi Kumar - Senior Waiter");
        servants.add("Anita Sharma - Junior Waiter");
        servants.add("Suresh Patel - Table Cleaner");
    }

    static void customerPanel() {
        sc.nextLine();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = sc.nextLine();

        while (true) {
            System.out.println("\n--- Customer Panel ---");
            System.out.println("1. View Menu");
            System.out.println("2. Check Table Availability");
            System.out.println("3. Reserve Table");
            System.out.println("4. Place Order");
            System.out.println("5. Check Order Status");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    checkTableAvailability();
                    break;
                case 3:
                    reserveTable(name, phone);
                    break;
                case 4:
                    placeOrder(name);
                    break;
                case 5:
                    checkOrderStatus(name);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    static void viewMenu() {
        System.out.println("\n--- Menu ---");
        for (MenuItem item : menu) {
            System.out.println(item.id + ". " + item.name + " - " + item.price + " - " + (item.available ? "Available" : "Not Available"));
        }
    }

    static void checkTableAvailability() {
        int totalTables = 15;
        int reservedCount = 0;
        for (Reservation res : reservations) {
            if (res.reserved) reservedCount++;
        }
        if (reservedCount >= totalTables) {
            System.out.println("No tables available.");
        } else {
            System.out.println("Tables available: " + (totalTables - reservedCount));
        }
    }

    static void reserveTable(String name, String phone) {
        checkTableAvailability();
        System.out.print("Enter table number to reserve (1-15): ");
        int tableNo = sc.nextInt();
        if (tableNo < 1 || tableNo > 15) {
            System.out.println("Invalid table number. Please select between 1 to 15.");
            return;
        }
        boolean alreadyReserved = false;
        for (Reservation res : reservations) {
            if (res.tableNumber == tableNo && res.reserved) {
                alreadyReserved = true;
                break;
            }
        }
        if (alreadyReserved) {
            System.out.println("Sorry, Table " + tableNo + " is already reserved.");
        } else {
            reservations.add(new Reservation(name, phone, tableNo, true));
            System.out.println("Congratulations...! Table " + tableNo + " reserved successfully.");
        }
    }

    static void placeOrder(String name) {
        Order order = new Order(name);
        while (true) {
            viewMenu();
            System.out.print("Enter item ID to order (0 to finish): ");
            int id = sc.nextInt();
            if (id == 0) break;
            MenuItem selected = null;
            for (MenuItem item : menu) {
                if (item.id == id && item.available) {
                    selected = item;
                    break;
                }
            }
            if (selected != null) {
                order.items.add(selected);
                System.out.println(selected.name + " added to your order.");
            } else {
                System.out.println("Invalid item ID or item not available.");
            }
        }
        if (order.items.size() == 0) {
            System.out.println("No items ordered.");
            return;
        }
        orders.add(order);
        System.out.println("Order placed & preparing...");
        order.status = "Preparing";
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        order.status = "Prepared";
        System.out.println("Your order is prepared!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        order.status = "Served";
        order.served = true;
        System.out.println("Order served! Enjoy your meal.");
        makePayment(name);
    }

    static void checkOrderStatus(String name) {
        for (Order order : orders) {
            if (order.customerName.equals(name)) {
                System.out.println("Your order status: " + order.status);
                return;
            }
        }
        System.out.println("No orders found.");
    }

    static void makePayment(String name) {
        for (Order order : orders) {
            if (order.customerName.equals(name)) {
                if (!order.served) {
                    System.out.println("You can pay after your order is served.");
                    return;
                }
                if (order.paymentDone) {
                    System.out.println("Payment already done.");
                    return;
                }
                double totalAmount = order.getTotalAmount();
                System.out.println("Total Amount to pay: " + totalAmount);
                while (true) {
                    System.out.print("Enter the amount to pay: ");
                    double paidAmount = sc.nextDouble();
                    if (paidAmount == totalAmount) {
                        order.paymentDone = true;
                        order.paymentTime = LocalDateTime.now();
                        System.out.println("Payment successful. Thank you!");
                        giveFeedback();
                        return;
                    } else {
                        System.out.println("Incorrect amount entered. Please pay the exact amount.");
                    }
                }
            }
        }
        System.out.println("No order found.");
    }

    static void giveFeedback() {
        sc.nextLine();
        System.out.print("Enter your feedback: ");
        String fb = sc.nextLine();
        feedbacks.add(fb);
        System.out.println("Thank you for your feedback!");
    }

    static void adminPanel() {
        while (true) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. View Menu");
            System.out.println("2. Add Menu Item");
            System.out.println("3. Remove Menu Item");
            System.out.println("4. Update Menu Item");
            System.out.println("5. View Reservations");
            System.out.println("6. View Orders");
            System.out.println("7. View Feedback");
            System.out.println("8. View Servant Details");
            System.out.println("9. View Payment History");
            System.out.println("10. Back to Main Menu");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    addMenuItem();
                    break;
                case 3:
                    removeMenuItem();
                    break;
                case 4:
                    updateMenuItem();
                    break;
                case 5:
                    viewReservations();
                    break;
                case 6:
                    viewOrders();
                    break;
                case 7:
                    viewFeedbacks();
                    break;
                case 8:
                    viewServants();
                    break;
                case 9:
                    viewPaymentHistory();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    static void addMenuItem() {
        sc.nextLine();
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Is it available? (true/false): ");
        boolean available = sc.nextBoolean();
        menu.add(new MenuItem(menuIdCounter++, name, price, available));
        System.out.println("Menu item added successfully.");
    }

    static void removeMenuItem() {
        System.out.print("Enter item ID to remove: ");
        int id = sc.nextInt();
        MenuItem toRemove = null;
        for (MenuItem item : menu) {
            if (item.id == id) {
                toRemove = item;
                break;
            }
        }
        if (toRemove != null) {
            menu.remove(toRemove);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    static void updateMenuItem() {
        System.out.print("Enter item ID to update: ");
        int id = sc.nextInt();
        MenuItem toUpdate = null;
        for (MenuItem item : menu) {
            if (item.id == id) {
                toUpdate = item;
                break;
            }
        }
        if (toUpdate != null) {
            sc.nextLine();
            System.out.print("Enter new name: ");
            String newName = sc.nextLine();
            System.out.print("Enter new price: ");
            double newPrice = sc.nextDouble();
            System.out.print("Is it available? (true/false): ");
            boolean newAvailable = sc.nextBoolean();

            toUpdate.name = newName;
            toUpdate.price = newPrice;
            toUpdate.available = newAvailable;

            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    static void viewReservations() {
        System.out.println("\n--- Reservations ---");
        for (Reservation res : reservations) {
            System.out.println("Customer: " + res.customerName + ", Phone: " + res.phoneNumber + ", Table: " + res.tableNumber + ", Reserved: " + res.reserved);
        }
    }

    static void viewOrders() {
        System.out.println("\n--- Orders ---");
        for (Order order : orders) {
            System.out.println("Customer: " + order.customerName + ", Items: " + order.items.size() + ", Status: " + order.status + ", Payment: " + (order.paymentDone ? "Paid" : "Pending"));
        }
    }

    static void viewFeedbacks() {
        System.out.println("\n--- Feedbacks ---");
        for (String fb : feedbacks) {
            System.out.println("Feedback: " + fb);
        }
    }

    static void viewServants() {
        System.out.println("\n--- Servant Details ---");
        for (String servant : servants) {
            System.out.println(servant);
        }
    }

    static void viewPaymentHistory() {
        System.out.println("\n--- Payment History ---");
        boolean found = false;
        for (Order order : orders) {
            if (order.paymentDone) {
                found = true;
                System.out.println("Customer: " + order.customerName + ", Amount: " + order.getTotalAmount() + ", Payment Time: " + order.paymentTime);
            }
        }
        if (!found) {
            System.out.println("No payments recorded yet.");
        }
    }
}