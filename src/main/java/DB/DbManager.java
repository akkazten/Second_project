package DB;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.x.Notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DbManager {

    private static Connection connection;

    static {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/first_ee_db?useUnicode=true&serverTimezone=UTC", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean addItem(Items item) {
        int rows = 0;
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO items(id,name,price,amount) " +
                    "VALUES(NULL,?,?,? )" + "");
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setInt(3, item.getAmount());

            rows = statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Items> getItems() {
        ArrayList<Items> items = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT id,name,price,amount " +
                    "FROM items ORDER BY id ASC");


            ResultSet resultSet = statement.executeQuery();
            //at first i get data from database then insert into items List
            while (resultSet.next()) {
                items.add(new Items(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("amount")
                ));

            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;

    }


    //public static ArrayList<Items> getItems(){return null;}
    public static Items getItem(Long id) {
        Items item = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT id,name,price,amount " +
                    "FROM items WHERE id=? LIMIT 1");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                item = new Items(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("amount")
                );
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public static boolean saveItem(Items item) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE items SET  name = ? , price = ?, amount = ? " +
                    "WHERE id = ?");
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setInt(3, item.getAmount());
            statement.setLong(4, item.getId());

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


    public static boolean deleteItem(Items item) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
            "DELETE FROM items WHERE id = ?");

            statement.setLong(1, item.getId());

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
}




