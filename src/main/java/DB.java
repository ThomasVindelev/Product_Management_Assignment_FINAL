import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private PreparedStatement preparedStatement;
    private Connection connection;
    private ResultSet resultSet;
    private String query;

    public DB() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/products?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
    }

    public void createProduct(Product product) throws SQLException {
        query = "INSERT INTO products (`product_type_id`,`name`, `price`, `location`, `shelf`) values(?, ?, ?, ?, ?)";
        executeStatement(product, query);
    }

    public void updateProduct(Product product) throws SQLException {
        query = "UPDATE products SET `product_type_id`=?, `name`=?, `price`=?, `location`=?, `shelf`=? WHERE `id`=" + product.getId() + "";
        executeStatement(product, query);
    }

    private void executeStatement(Product product, String query) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getType());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.setString(4, product.getLocation());
        preparedStatement.setInt(5, product.getShelfNumber());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Product> getProducts() throws SQLException {
        query = "SELECT products.*,product_types.product_type,locations.name as location_name, locations.address " +
                "FROM products " +
                "INNER JOIN product_types ON products.product_type_id=product_types.id " +
                "INNER JOIN locations ON products.location=locations.id " +
                "ORDER BY id ASC ";
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        List<Product> productList = new ArrayList();
        int rowCount = 0;
        while (resultSet.next()) {
            Product product = new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("product_type"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("address"),
                    resultSet.getInt("shelf"));
            productList.add(product);
            rowCount++;
        }
        System.out.println("Number of unique products: " + rowCount + "\n");
        return productList;
    }

    public void deleteProduct(Product product) throws SQLException {
        query = "DELETE FROM products WHERE `id`=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, product.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public boolean verifyProduct(int id) throws SQLException {
        query = "SELECT * FROM products WHERE id=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public boolean shelfAlreadyTaken(Product product) throws SQLException {
        query = "SELECT * FROM products WHERE shelf=? AND location=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, product.getShelfNumber());
        preparedStatement.setInt(2, Integer.parseInt(product.getLocation()));
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public List<ProductType> getProductTypes() throws SQLException {
        query = "SELECT * FROM product_types";
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        List<ProductType> productTypeList = new ArrayList();
        while (resultSet.next()) {
            ProductType productType = new ProductType(
                    resultSet.getInt("id"),
                    resultSet.getString("product_type"));
            productTypeList.add(productType);
        }
        return productTypeList;
    }

    public List<Location> getProductLocations() throws SQLException {
        query = "SELECT * FROM locations";
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        List<Location> locationList = new ArrayList();
        while (resultSet.next()) {
            Location productLocation = new Location(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"));
            locationList.add(productLocation);
        }
        return locationList;
    }

}