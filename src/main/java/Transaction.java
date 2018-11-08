import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private double amount;
    private String type;
    private String category;
    private String note;

    public Transaction(LocalDate date, double amount, String type, String note) {
        if (amount >= 0) {
            this.amount = amount;
            this.type = type;
            this.date = date;
            this.category = "undefined";
            this.note = (note.length() == 0) ? "undefined" : note;
        }
    }

    public Transaction(LocalDate date, double amount, String type) {
        this(date, amount, type, "");
    }

    public String formatContent() {
        String content = "";
        content += date;
        content += "      ";
        content += amount;
        content += "      ";
        content += type;
        content += "      ";
        content += note;
        content += "\n";
        return content;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        checkType();
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

    private void checkType() {
        if (amount >= 0) {
            type = "deposit";
        } else {
            type = "expense";
            amount = -amount;
        }
        type = (amount >= 0) ? "deposit" : "expense";
    }
}
