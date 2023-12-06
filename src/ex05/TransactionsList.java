import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    Transaction removeTransaction(UUID identifier) throws TransactionNotFoundException;
    Transaction[] toArray();
    int getCountTransactions();
}
