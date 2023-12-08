import java.util.UUID;

public interface TransactionsList {
    void addTransaction(TransactionNode transaction);
    Transaction removeTransaction(UUID identifier) throws TransactionNotFoundException;
    Transaction[] toArray();
    int getCountTransactions();
}
