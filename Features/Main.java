import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Create Customer Account");
        System.out.println("2. Delete Customer Account");
       
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                customer_acc_creation createCustomer = new customer_acc_creation();
                createCustomer.customer_acc_creation();
                break;
            case 2:
                customer_acc_deletion deleteCustomer = new customer_acc_deletion();
                deleteCustomer.customer_acc_deletion();
                break;
            default:
                System.out.println("Invalid choice!");
        }
        scanner.close();
    }
}
