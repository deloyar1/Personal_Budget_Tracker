import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE = "transactions.csv";

    public static void saveTransaction(Transaction t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(t.toCSV());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Transaction t = Transaction.fromCSV(line);
                if (t != null) list.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}