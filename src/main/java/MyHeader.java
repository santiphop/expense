import java.io.*;
import java.sql.*;
import java.time.LocalDate;

public class MyHeader {
    public static LocalDate convertToDate(String currentDate) {
        String[] date = currentDate.split("-");
        int[] intDate = new int[3];
        for (int i = 0; i < 3; i++) {
            intDate[i]=Integer.parseInt(date[i]);
        }
        return LocalDate.of(intDate[0], intDate[1], intDate[2]);
    }

    public static void writeTextFile(String content, String filename, boolean bool) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename, bool))) {
            buffer.write(content);
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDBFile(String query, String filename) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:" + filename;
            Connection conn = DriverManager.getConnection(dbURL);

            if (conn != null) {
                System.out.println("Connected to the database.");
                // display database information
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Product name: " + dm.getDatabaseProductName());

                System.out.println("----- Insert in Transactions table -----");

                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void readTextFile(Account account, String filename) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                // "\\s" == any spaces
                // "\\S" == any string
                line = line.trim();
                String[] splitLine = line.split("\\s+");
                account.add(new Transaction(
                        convertToDate(splitLine[1]), Double.parseDouble(splitLine[2]), splitLine[3], splitLine[4]
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDBFile(Account account, String filename) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:" + filename;
            Connection conn = DriverManager.getConnection(dbURL);

            if (conn != null) {
                System.out.println("Connected to the database.");
                // display database information
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Product name: " + dm.getDatabaseProductName());

                System.out.println("----- Data in Transactions table -----");

                String query = "select * from transactions";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String date = resultSet.getString(2);
                    double amount = resultSet.getDouble(3);
                    String type = resultSet.getString(4);
                    String note = resultSet.getString(5);
                    account.add(new Transaction(
                            convertToDate(date), amount, type, note
                    ));

                    System.out.println("id:"+id+" date:"+date+" amount:"+amount+" type: "+type+" note: "+note);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
