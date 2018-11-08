public class TextFileFormat implements MyContentFormatter {
    Transaction transaction;

    public TextFileFormat(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String contentFormat() {
        return String.format("%4d  %10s%11.2f%10s      %-10s\n",
                transaction.getId(), String.valueOf(transaction.getDate()),
                transaction.getAmount(), transaction.getType(), transaction.getNote());
    }
}
