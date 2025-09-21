public class Transaction {
    private String type;
    private double amount;
    private String category;
    private String date;

    public Transaction(String type, double amount, String category, String date) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }

    public String toCSV() {
        return String.format("%s,%f,%s,%s", type, amount, category, date);
    }

    public static Transaction fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            return new Transaction(parts[0], Double.parseDouble(parts[1]), parts[2], parts[3]);
        }
        return null;
    }
}