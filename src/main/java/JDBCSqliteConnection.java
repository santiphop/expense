import java.sql.*;

public class JDBCSqliteConnection {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:expense.db";
            Connection conn = DriverManager.getConnection(dbURL);

            if (conn != null) {
                System.out.println("Connected to the database....");
                // display database information
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Product name: " + dm.getDatabaseProductName());

                System.out.println("----- Data in Book table -----");

                String query = "select * from transactions";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String date = resultSet.getString(1);
                    double amount = resultSet.getDouble(2);
                    String type = resultSet.getString(3);
                    String note = resultSet.getString(4);

                    System.out.println("date:"+date+" amount:"+amount+" type: "+type+" note: "+note);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}