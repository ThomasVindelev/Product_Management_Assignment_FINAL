import java.sql.SQLException;

public class Logic {

    private DB db = new DB();
    private Color color = new Color();

    public Logic() throws SQLException {
    }

    public void read() throws SQLException {
        System.out.println();
        for (Product product : db.getProducts()) {
            color.printTxtBlue("ID: " + product.getId() + "\nType: " + product.getType()
                    + "\nName: " + product.getName() + "\nPrice: " + product.getPrice() + "\nLocation: "
                    + product.getLocation() + "\nShelf number: S:" + product.getShelfNumber() + "\n").print(true);
            color.clearTxtBuffer();
        }
    }

    public void printProductTypes() throws SQLException {
        System.out.println("Select product type:\n");
        for (ProductType productType : db.getProductTypes()) {
            System.out.print("ID: " + productType.getId() + " " + productType.getProductType() + "\n");
        }
    }

    public void printProductLocations() throws SQLException {
        System.out.println("Select product locations:\n");
        for (Location location : db.getProductLocations()) {
            System.out.print("ID: " + location.getId() + " " + location.getAddress() + "\n");
        }
    }

    public boolean verifyProduct(int id) throws SQLException {
        return db.verifyProduct(id);
    }

    public void confirmProduct(Product product, String choice) throws SQLException {
        if (product.getPrice() > 1000 && product.getPrice() > 0) {
            System.out.println("Price must not be more than 1000.");
        } else if (product.getShelfNumber() < 0 || product.getShelfNumber() > 1000 ) {
            System.out.println("Shelf location must be between 1-1000.\n");
        } else if (db.shelfAlreadyTaken(product) && choice.equals("1")) {
            System.out.println("Shelf number already taken.");
        } else {
            if (choice.equals("1")) {
                db.createProduct(product);
                color.printTxtGreen("\nProduct created!\n").print(true);
                color.clearTxtBuffer();
            } else {

                db.updateProduct(product);
                color.printTxtYellow("\nProduct updated!\n").print(true);
                color.clearTxtBuffer();
            }
        }
    }

    public void deleteProduct(Product product) throws SQLException {
        db.deleteProduct(product);
        color.printTxtRed("\nProduct deleted!\n").print(true);
        color.clearTxtBuffer();
    }
}