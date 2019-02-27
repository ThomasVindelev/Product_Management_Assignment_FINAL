import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    private Logic logic = new Logic();

    public Menu() throws SQLException {
    }

    public void start() throws SQLException {

        Scanner scanner =       new Scanner(System.in);
        boolean isRunning =     true;

        System.out.println("Welcome to my-KEA!");

        while (isRunning) {
            System.out.println("1. Create product" + "\n2. Update product" +
                    "\n3. Delete product" + "\n4. Get products" + "\nType /quit to exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    prompt(scanner, choice);
                    break;
                case "2":
                    logic.read();
                    prompt(scanner, choice);
                    break;
                case "3":
                    logic.read();
                    prompt(scanner, choice);
                    break;
                case "4":
                    logic.read();
                    break;
                case "/quit":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Not a valid option.");
                    break;
            }
        }
    }

    public void prompt(Scanner scanner, String choice) throws SQLException {
        int id = 0;
        Scanner idScanner = new Scanner(System.in);
        if (choice.equals("1") || choice.equals("2")) {
            if (choice.equals("2")) {
                System.out.println("Choose product to update by ID:");
                id = idScanner.nextInt();
                if (!logic.verifyProduct(id)) {
                    System.out.println("Not a valid ID.");
                    return;
                }
            }
            logic.printProductTypes();
            String type = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Price (max 1000): ");
            String price = scanner.nextLine();
            logic.printProductLocations ();
            String location = scanner.nextLine();
            System.out.print("Shelf number (0-1000): ");
            int shelfNumber = idScanner.nextInt();

            Product product = new Product(id, type, name, Integer.parseInt(price), location, shelfNumber);
            logic.confirmProduct(product, choice);
        } else {
            System.out.println("Choose product to delete by ID:");
            Product product = new Product();
            product.setId(idScanner);
            if (!logic.verifyProduct(product.getId())) {
                System.out.println("Not a valid ID.");
                return;
            }
            logic.deleteProduct(product);
        }
    }
}