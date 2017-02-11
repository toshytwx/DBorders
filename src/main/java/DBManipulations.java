import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by User on 10.02.2017.
 */
public class DBManipulations {
    private Connection connection;
    private static final String DATA_BASE_PATH = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
    private static final String DATA_BASE_USER ="root";
    private static final String DATA_BASE_PASSWORD ="root";
    private OrderList orderList = new OrderList();

    public void getOrderList() {
        orderList.OrderListInfo();
    }

    public void createDBconnection() {
        try {
            connection = DriverManager.getConnection(DATA_BASE_PATH, DATA_BASE_USER, DATA_BASE_PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установленно");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addClientDB(Scanner scanner){
        String sql = "INSERT  INTO client(name, phone) VALUES(?,?)";
        System.out.println("Type client name: ");
        String clientName = scanner.nextLine();
        System.out.println("Type client phone: ");
        String clientPhone = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,clientName);
            preparedStatement.setString(2,clientPhone);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }
    public void addProductDB(Scanner scanner){
        String sql = "INSERT  INTO product(category, price, name) VALUES(?,?,?)";
        System.out.println("Type product category: ");
        String productCategory = scanner.nextLine();
        System.out.println("Type product price: ");
        Integer productPrice = Integer.parseInt(scanner.nextLine());
        System.out.println("Type product name: ");
        String productName = scanner.nextLine();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,productCategory);
            preparedStatement.setInt(2,productPrice);
            preparedStatement.setString(3,productName);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public void addNewOrderDB(Scanner scanner){
        try {
            addClientDB(scanner);
            Client client = getClient(processingSelectQuery("SELECT *FROM client"));
            System.out.println("We've got such products: ");
            processingProductResultSet(processingSelectQuery("SELECT * FROM product"));
            System.out.println("Please type name of product you want to order: ");
            String orderName = scanner.nextLine();
            Product product = getProduct(processingSelectQueryForWhere("SELECT * FROM product WHERE name=?", orderName));
            orderList.addOrder(client, product);
            String sqlOrder =  "INSERT INTO orderslist(client_id,orderName, product_id) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlOrder);
            preparedStatement.setInt(1,client.getClientId());
            preparedStatement.setString(2,orderName);
            preparedStatement.setInt(3,product.getProductId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        System.out.println(e.getMessage());
        }
     }

    private ResultSet processingSelectQuery(String query) throws SQLException{
        PreparedStatement ps1 = connection.prepareStatement(query);
        return ps1.executeQuery();
    }

    private ResultSet processingSelectQueryForWhere(String query, String parametr) throws SQLException{
        PreparedStatement ps1 = connection.prepareStatement(query);
        ps1.setString(1,parametr);
        return ps1.executeQuery();
    }
    private void processingProductResultSet(ResultSet resultSet) throws SQLException{
        ArrayList<Product> productsArrayList= new ArrayList<Product>();
        while(resultSet.next()){
                Product productList = new Product();
                productList.setName(resultSet.getString("name"));
                productList.setCategory(resultSet.getString("category"));
                productList.setPrice(resultSet.getDouble("price"));
                productList.setProductId(resultSet.getInt("product_id"));
                productsArrayList.add(productList);
        }
        if(productsArrayList.isEmpty()){
            System.out.println("There are no any products!");
        }else{
            System.out.println(productsArrayList.toString());
        }
    }
    private Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        while (resultSet.next()) {
            product.setName(resultSet.getString("name"));
            product.setCategory(resultSet.getString("category"));
            product.setPrice(resultSet.getDouble("price"));
            product.setProductId(resultSet.getInt("product_id"));
        }
        return product;
    }

    private Client getClient(ResultSet resultSet) throws SQLException{
        Client client = new Client();
        while (resultSet.next()) {
            client.setName(resultSet.getString("name"));
            client.setClientId(resultSet.getInt("client_id"));
            client.setPhone(resultSet.getString("phone"));
        }
        return client;
    }
}