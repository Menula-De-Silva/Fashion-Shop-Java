import java.util.*;

class Example {
    
    public static final int PROCESSING = 0;
    public static final int DELIVERING = 1;
    public static final int DELIVERED = 2;

    
    public static int lastOrderId = 10000;

    
    public static Map<Integer, Order> orders = new HashMap<>();

    public static void main(String args[]) {
        Home();
    }

    
    public static void Home() {
        Scanner input = new Scanner(System.in);

        System.out.print("\n\n\t\t\t\t /$$$$$$$$                 /$$                            /$$$$$$  /$$");
        System.out.print("\n\t\t\t\t| $$_____/                |__/                           /$$__  $$| $$                          ");
        System.out.print("\n\t\t\t\t| $$    /$$$$$$   /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$ ");
        System.out.print("\n\t\t\t\t| $$$$$|____  $$ /$$_____/| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$");
        System.out.print("\n\t\t\t\t| $$__/ /$$$$$$$|  $$$$$$ | $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$");
        System.out.print("\n\t\t\t\t| $$   /$$__  $$ \\____  $$| $$| $$  | $$| $$  | $$       /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$");
        System.out.print("\n\t\t\t\t| $$  |  $$$$$$$ /$$$$$$$/| $$|  $$$$$$/| $$  | $$      |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/");
        System.out.print("\n\t\t\t\t|__/   \\_______/|_______/ |__/ \\______/ |__/  |__/       \\______/ |__/  |__/ \\______/ | $$____/ ");
        System.out.print("\n\t\t\t\t                                                                                      | $$      ");
        System.out.print("\n\t\t\t\t                                                                                      | $$      ");
        System.out.print("\n\t\t\t\t                                                                                      |__/      ");
        System.out.println("\n____________________________________________________________________________________________________________________________________________________");
        System.out.print("\n\n\n\t\t\t\t[1] Place Order");
        System.out.print("\t\t\t\t\t\t[2] Search Customer");
        System.out.print("\n\n\n\t\t\t\t[3] Search Order");
        System.out.print("\t\t\t\t\t[4] View Reports");
        System.out.print("\n\n\n\t\t\t\t[5] Change Order Status");
        System.out.print("\t\t\t\t\t[6] Delete Order");

        System.out.print("\n\n\n\n\t\tInput Option : ");
        int opt = input.nextInt();

        if (opt > 6 || opt <= 0) {
            System.out.print("\n\n\t\t\t\tError! Invalid Option");
            Home(); 
        } else {
            switch (opt) {
                case 1:
                    placeOrder();
                    break;
                case 2:
                    searchCustomer();
                    break;
                case 3:
                    searchOrder();
                    break;
                case 4:
                    viewReports();
                    break;
                case 5:
                    changeOrderStatus(); 
                    break;
                case 6:
                    deleteOrder(); 
                    break;
                default:
                    System.out.println("\n\n\t\t\tOption not implemented yet.");
                    break;
            }
        }
    }


    public static void placeOrder() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("\n\n\t\t\t  _____  _                   ____          _           ");
        System.out.println("\t\t\t |  __ \\| |                 / __ \\        | |          ");
        System.out.println("\t\t\t | |__) | | __ _  ___ ___  | |  | |_ __ __| | ___ _ __ ");
        System.out.println("\t\t\t |  ___/| |/ _` |/ __/ _ \\ | |  | | '__/ _` |/ _ | '__|");
        System.out.println("\t\t\t | |    | | (_| | (_|  __/ | |__| | | | (_| |  __| |   ");
        System.out.println("\t\t\t |_|    |_|\\__,_|\\___\\___|  \\____/|_|  \\__,_|\\___|_|   ");
        System.out.println("\n\t\t------------------------------------------------------------------");


        lastOrderId++;
        String orderId = "ODR#" + String.format("%05d", lastOrderId);

        System.out.println("\n\n\n\t\tOrder Id : " + orderId);


        String phoneNumber;
        while (true) {
            System.out.print("\n\t\tEnter Customer Phone Number : ");
            phoneNumber = input.next();
            if (isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("\n\t\tInvalid Phone Number! Please enter a valid 10-digit number starting with '0'.");
            }
        }


        System.out.print("\n\t\tEnter A T-Shirt Size (XS/S/M/L/XL/XXL) : ");
        String tshirtSize = input.next().toUpperCase();


        if (!Arrays.asList("XS", "S", "M", "L", "XL", "XXL").contains(tshirtSize)) {
            System.out.println("\n\t\tInvalid T-Shirt Size!");
            Home();
            return;
        }


        System.out.print("\n\t\tEnter QTY : ");
        int qty = input.nextInt();


        double amount = calculateAmount(tshirtSize, qty);
        System.out.println("\n\t\tAmount : " + amount);


        System.out.print("\n\n\t\t\tDo You Want To Place This Order? (y/n) : ");
        String placeOrder = input.next();
        if (placeOrder.equalsIgnoreCase("Y")) {

            Order order = new Order(orderId, phoneNumber, tshirtSize, qty, amount, PROCESSING);
            orders.put(lastOrderId, order);
            System.out.println("\n\t\t\t\tOrder Placed Successfully..!");
        } else {
            System.out.println("\n\t\t\t\tOrder Not Placed..!");
        }

      
        System.out.print("\n\n\t\t\tDo You Want To Place Another Order? (y/n) : ");
        String placeAnotherOrder = input.next();
        if (placeAnotherOrder.equalsIgnoreCase("Y")) {
            placeOrder(); 
        } else {
            Home();
        }
    }

   
    public static double calculateAmount(String size, int qty) {
        Map<String, Double> sizePrice = new HashMap<>();
        sizePrice.put("XS", 600.00);
        sizePrice.put("S", 800.00);
        sizePrice.put("M", 900.00);
        sizePrice.put("L", 1000.00);
        sizePrice.put("XL", 1100.00);
        sizePrice.put("XXL", 1200.00);
        return sizePrice.get(size) * qty;
    }

    
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("0[0-9]{9}"); 
    }

   
    public static void searchCustomer() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("\n\n\t\t\t   _____                     _        _____          _                            ");
        System.out.println("\t\t\t  / ____|                   | |      / ____|        | |                           ");
        System.out.println("\t\t\t | (___   ___  __ _ _ __ ___| |__   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ");
        System.out.println("\t\t\t  \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__|");
        System.out.println("\t\t\t  ____) |  __/ (_| | | | (__| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |   ");
        System.out.println("\t\t\t |_____/ \\___|\\__,_|_|  \\___|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|   ");
        System.out.println("\n\t\t---------------------------------------------------------------------------------------------------");

        System.out.print("\n\n\n\t\tEnter Customer Phone Number : ");
        String phoneNumber = input.next();

        if (isValidPhoneNumber(phoneNumber)) {
            boolean found = false;
            for (Order order : orders.values()) {
                if (order.getCustomerPhone().equals(phoneNumber)) {
                    System.out.println("\n\t\tOrder ID: " + order.getOrderId() + ", Size: " + order.getTshirtSize() + ", Quantity: " + order.getQuantity() + ", Amount: " + order.getAmount());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("\n\t\tNo Orders Found for the given Customer.");
            }
        } else {
            System.out.println("\n\t\tInvalid Phone Number..!");
        }

        
        Home();
    }

    
    public static void searchOrder() {
        Scanner input = new Scanner(System.in);

		System.out.println("\n\n\t\t\t   _____                     _        ____          _           ");
		System.out.println("\t\t\t  / ____|                   | |      / __ \\        | |          ");
		System.out.println("\t\t\t | (___   ___  __ _ _ __ ___| |__   | |  | |_ __ __| | ___ _ __ ");
		System.out.println("\t\t\t  \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |  | | '__/ _` |/ _ \\ '__|");
		System.out.println("\t\t\t  ____) |  __/ (_| | | | (__| | | | | |__| | | | (_| |  __/ |   ");
		System.out.println("\t\t\t |_____/ \\___|\\__,_|_|  \\___|_| |_|  \\____/|_|  \\__,_|\\___|_|   ");
		System.out.println("\n\t\t---------------------------------------------------------------------------------------------------");

		
        System.out.print("\n\n\n\t\tEnter Order ID (Format: ODR#XXXXX) : ");
        String orderId = input.next();

        boolean found = false;
        for (Order order : orders.values()) {
            if (order.getOrderId().equals(orderId)) {
                System.out.println("\n\t\tOrder ID: " + order.getOrderId() + ", Phone: " + order.getCustomerPhone() + ", Size: " + order.getTshirtSize() + ", Quantity: " + order.getQuantity() + ", Amount: " + order.getAmount());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\n\t\tOrder ID Not Found.");
        }

        
        Home();
    }

    
    public static void viewReports() {
       
       
        System.out.println("\n\n\t\t\t  _____                  _       ");
        System.out.println("\t\t\t |  __ \\                | |      ");
        System.out.println("\t\t\t | |__) |___ _ __   ___ | |_ ___ ");
        System.out.println("\t\t\t |  _  // _ \\ '_ \\ / _ \\| __/ __|");
        System.out.println("\t\t\t | | \\ \\  __/ |_) | (_) | |_\\__ \\");
        System.out.println("\t\t\t |_|  \\_\\___| .__/ \\___/ \\__|___/");
        System.out.println("\t\t\t            | |                  ");
        System.out.println("\t\t\t            | |                  ");
        System.out.println("\n\t\t--------------------------------------------------------------------------\n\n\n\n");
       
       
        for (Order order : orders.values()) {
            System.out.println("Order ID: " + order.getOrderId() + ", Phone: " + order.getCustomerPhone() + ", Size: " + order.getTshirtSize() + ", Quantity: " + order.getQuantity() + ", Amount: " + order.getAmount() + "\n");
        }
        Home(); 
    }


    public static void changeOrderStatus() {
        Scanner input = new Scanner(System.in);
		
		System.out.println("\n\n\t\t\t   ____          _              _____ _        _             ");
        System.out.println("\t\t\t  / __ \\        | |            / ____| |      | |            ");
        System.out.println("\t\t\t | |  | |_ __ __| | ___ _ __  | (___ | |_ __ _| |_ _   _ ___ ");
        System.out.println("\t\t\t | |  | | '__/ _` |/ _ \\ '__|  \\___ \\| __/ _` | __| | | / __|");
        System.out.println("\t\t\t | |__| | | | (_| |  __/ |     ____) | || (_| | |_| |_| \\__ \\");
        System.out.println("\t\t\t  \\____/|_|  \\__,_|\\___|_|    |_____/ \\__\\__,_|\\__|\\__,_|___/");
        System.out.println("\n\t\t--------------------------------------------------------------------------");



        System.out.print("\n\n\n\t\tEnter Order ID (Format: ODR#XXXXX) : ");
        String orderId = input.next();

        boolean found = false;
        for (Order order : orders.values()) {
            if (order.getOrderId().equals(orderId)) {
                System.out.println("\n\t\tCurrent Status: " + getStatusString(order.getStatus()));
                System.out.print("\n\t\tEnter New Status (0: Processing, 1: Delivering, 2: Delivered) : ");
                int newStatus = input.nextInt();
                if (newStatus >= 0 && newStatus <= 2) {
                    order.setStatus(newStatus);
                    System.out.println("\n\t\tOrder Status Updated Successfully.");
                } else {
                    System.out.println("\n\t\tInvalid Status Entered.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\n\t\tOrder ID Not Found.");
        }

        Home(); 
    }


    public static String getStatusString(int status) {
        switch (status) {
            case PROCESSING:
                return "Processing";
            case DELIVERING:
                return "Delivering";
            case DELIVERED:
                return "Delivered";
            default:
                return "Unknown";
        }
    }


    public static void deleteOrder() {
        Scanner input = new Scanner(System.in);
		
		System.out.println("\n\n\t\t\t  _____       _     _           ____          _           ");
        System.out.println("\t\t\t |  __ \\     | |    | |        / __ \\        | |          ");
        System.out.println("\t\t\t | |  | | ___| | ___| |_ ___  | |  | |_ __ __| | ___ _ __ ");
        System.out.println("\t\t\t | |  | |/ _ \\ |/ _ \\ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|");
        System.out.println("\t\t\t | |__| |  __/ |  __/ ||  __/ | |__| | | | (_| |  __/ |   ");
        System.out.println("\t\t\t |_____/ \\___|_|\\___|\\__\\___|  \\____/|_|  \\__,_|\\___|_|   ");
		System.out.println("\n\t\t--------------------------------------------------------------------------");
		
			
		
        System.out.print("\n\n\n\t\tEnter Order ID (Format: ODR#XXXXX) : ");
        String orderId = input.next();

        boolean found = false;
        for (int id : orders.keySet()) {
            if (orders.get(id).getOrderId().equals(orderId)) {
                orders.remove(id);
                System.out.println("\n\t\tOrder Deleted Successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\n\t\tOrder ID Not Found.");
        }

        Home(); 
    }
}


class Order {
    public String orderId;
    public String customerPhone;
    public String tshirtSize;
    public	int quantity;
    public double amount;
	public int status;

    public Order(String orderId, String customerPhone, String tshirtSize, int quantity, double amount, int status) {
        this.orderId = orderId;
        this.customerPhone = customerPhone;
        this.tshirtSize = tshirtSize;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getTshirtSize() {
        return tshirtSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
