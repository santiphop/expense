import java.time.LocalDate;

public class Transaction {
    private double amount;
    private String type;
    private LocalDate date;
    private String category;
    private String note;

    public Transaction(LocalDate date, double amount, String note) {
        this.amount = amount;
        type = (amount >= 0) ? "deposit" : "expense";
        this.date = date;
        this.category = "undefined";
        this.note = (note.length() == 0) ? "undefined" : note;
    }

    public Transaction(double amount) {
        this(LocalDate.now(), amount, "aaaa");
    }

    public Transaction(LocalDate date, double amount) {
        this(date, amount, "bbbb");
    }

    public String formatContent() {
        String content = "";
        content += date;
        content += "      ";
        content += amount;
        content += "      ";
        content += note;
        content += "\n";
        return content;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
