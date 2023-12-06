import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction Head;
    private Transaction Current;
    private int CountTransactions = 0;

    public TransactionsLinkedList() {}

    @Override
    public void addTransaction(Transaction transaction){
        if(CountTransactions == 0){
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
        while (temp.getNext() != null) {
            if (temp.getNext().getIdentifier().equals(identifier)) {
                Transaction remove = temp.getNext();
                temp.setNext(temp.getNext().getNext());
                CountTransactions--;
                return remove;
            }
            temp = temp.getNext();
        }

        throw new TransactionNotFoundException("Transaction not found!");
    }


    @Override
    public Transaction[] toArray() {
        Transaction[] arr = new Transaction[CountTransactions];
        Transaction temp = Head;
        int count = 0;
        while (temp != null && count < CountTransactions) {
            arr[count] = temp;
            temp = temp.getNext();
            count++;
        }
        return arr;
    }

    @Override
    public int getCountTransactions() { return CountTransactions; }
}
