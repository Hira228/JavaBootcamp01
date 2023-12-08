public class TransactionNode {
    Transaction transaction;
    TransactionNode next;

    TransactionNode(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setNext(TransactionNode next) {
        this.next = next;
    }

    public TransactionNode getNext() {
        return next;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
