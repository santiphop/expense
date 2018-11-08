public class DatabaseFileFormat implements MyContentFormatter {
    Transaction transaction;

    public DatabaseFileFormat(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String contentFormat() {
        return "insert into transactions values ("
                + transaction.getId() + ", \"" + String.valueOf(transaction.getDate()) + "\", " + transaction.getAmount()
                + ", \"" + transaction.getType() + "\", \"" + transaction.getNote() + "\")";

    }
}
