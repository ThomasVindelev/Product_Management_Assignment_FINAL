import java.util.Scanner;

public class ProductType {

    private int id;
    private String productType;

    public ProductType(int id,String productType) {
        this.id = id;
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(Scanner scanner) {
        this.id = scanner.nextInt();
    }

    public String getProductType() {
        return productType;
    }

    public void setproductType(String productType) {
        this.productType = productType;

    }
}