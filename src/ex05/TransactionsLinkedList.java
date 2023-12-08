import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionNode Head;
    private TransactionNode Current;
    private int CountTransactions = 0;

    public TransactionsLinkedList() {
        Head = null;
        Current = null;
    }

    @Override
    public void addTransaction(TransactionNode transaction) {
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

        if (Head.getTransaction().getIdentifier().equals(identifier)) {
            TransactionNode remove = Head;
            Head = Head.getNext();
            CountTransactions--;
            return remove.getTransaction();
        }

        TransactionNode temp = Head;
        while (temp.getNext() != null && !temp.getNext().getTransaction().getIdentifier().equals(identifier)) {
            temp = temp.getNext();
        }

        if (temp.getNext() != null) {
            TransactionNode remove = temp.getNext();
            temp.setNext(temp.getNext().getNext());
            CountTransactions--;
            return remove.getTransaction();
        } else {
            throw new TransactionNotFoundException("Transaction not found!");
        }
    }


    @Override
    public Transaction[] toArray() {
        Transaction[] arr = new Transaction[CountTransactions];
        TransactionNode temp = Head;
        for(int i = 0; i < CountTransactions && temp != null; ++i) {
            arr[i] = temp.getTransaction();
            temp = temp.getNext();
        }
        return arr;
    }

    @Override
    public int getCountTransactions() { return CountTransactions; }
}
