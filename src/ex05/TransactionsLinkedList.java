import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction Head;
    private Transaction Current;
    private int CountTransactions = 0;

    public TransactionsLinkedList() {
        Head = null;
        Current = null;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (Current == null) {
            Head = transaction;
            Current = transaction;
        } else {
            Current.setNext(transaction);
            Current = Current.getNext();
        }
        CountTransactions++;
    }

    @Override
    public Transaction removeTransaction(UUID identifier) throws TransactionNotFoundException {
        if (Head == null) {
            throw new TransactionNotFoundException("Transaction not found!");
        }

        if (Head.getIdentifier().equals(identifier)) {
            Transaction remove = Head;
            Head = Head.getNext();
            CountTransactions--;
            return remove;
        }

        Transaction temp = Head;
        while (temp.getNext() != null && !temp.getNext().getIdentifier().equals(identifier)) {
            temp = temp.getNext();
        }

        if (temp.getNext() != null) {
            Transaction remove = temp.getNext();
            temp.setNext(temp.getNext().getNext());
            CountTransactions--;
            return remove;
        } else {
            throw new TransactionNotFoundException("Transaction not found!");
        }
    }


    @Override
    public Transaction[] toArray() {
        Transaction[] arr = new Transaction[CountTransactions];
        Transaction temp = Head;
        for(int i = 0; i < CountTransactions && temp != null; ++i) {
            arr[i] = temp;
            temp = temp.getNext();
        }
        return arr;
    }

    @Override
    public int getCountTransactions() { return CountTransactions; }
}
