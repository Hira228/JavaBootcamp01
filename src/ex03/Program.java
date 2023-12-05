import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User a = new User("Matvey", 1000);
        User b = new User( "Aboba", 500);


        Transaction t = new Transaction(b, a,500, Transaction.TypeTransferCategory.DEBITS);
        Transaction tt = new Transaction(b, a,  500, Transaction.TypeTransferCategory.DEBITS);
        Transaction ttt = new Transaction(b, a,  500, Transaction.TypeTransferCategory.DEBITS);

        UUID kkk = UUID.randomUUID();

        TransactionsLinkedList list = new TransactionsLinkedList();
        list.addTransaction(t);
        list.addTransaction(tt);
        list.addTransaction(ttt);
        Transaction[] arr = list.toArray();
        System.out.println("Arr Before Remove");
        for(int i = 0; i < arr.length; ++i) System.out.println(arr[i]);

        try {
            list.removeTransaction(tt.getIdentifier());
        } catch (TransactionNotFoundException s) {System.out.println(s.getMessage());}

        Transaction[] arrAfterRemove = list.toArray();
        System.out.println("Arr After Remove");
        for(int i = 0; i < arrAfterRemove.length; ++i) System.out.println(arrAfterRemove[i]);

        System.out.println("Remove unknown UUID");

        // Remove unknown UUID
        try {
            list.removeTransaction(kkk);
        } catch (TransactionNotFoundException s) {System.out.println("-> " + s.getMessage());}
    }
}
