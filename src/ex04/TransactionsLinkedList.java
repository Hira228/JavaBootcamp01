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
    public void removeTransaction(UUID identifier) throws TransactionNotFoundException {
        if (Head == null) {
            throw new TransactionNotFoundException("Transaction not found!");
        }

        if (Head.getIdentifier().equals(identifier)) {
            Head = Head.getNext();
            CountTransactions--;
            return;
        }

        Transaction temp = Head;
        while (temp.getNext() != null) {
            if (temp.getNext().getIdentifier().equals(identifier)) {
                temp.setNext(temp.getNext().getNext());
                CountTransactions--;
                return;
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
