import java.io.*;
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

    public static void writeFile(String content, String filename, boolean bool) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename, bool))) {
            buffer.write(content);
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(Account account, String filename) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                // "\\s" == any spaces
                // "\\S" == any string
                String[] splitLine = line.split("\\s+");
                account.add(new Transaction(
                        convertToDate(splitLine[0]), Double.parseDouble(splitLine[1]), splitLine[2], splitLine[3]
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
