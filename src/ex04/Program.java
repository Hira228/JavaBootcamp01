import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User a = new User("Matvey", 1000);
        User b = new User( "Aboba", 500);
        User c = new User( "Aboba", 500);

        TransactionsService service = new TransactionsService();

        service.addUser(a);
        service.addUser(b);

        try {
            System.out.println("Balance before transaction");
            System.out.println("-> " + service.getBalanceUser(a.getIdentifier()));
            System.out.println("-> " + service.getBalanceUser(b.getIdentifier()));
            service.makeTransaction(a.getIdentifier(), b.getIdentifier(), 100);
            service.makeTransaction(a.getIdentifier(), b.getIdentifier(), 100);
            service.makeTransaction(a.getIdentifier(), b.getIdentifier(), 100);

            Transaction[] arrA = service.getTransactionsUser(a.getIdentifier());
            Transaction[] arrB = service.getTransactionsUser(b.getIdentifier());
            System.out.println("Transaction User A");
            for (Transaction transaction : arrA) System.out.println("-> " + transaction);
            System.out.println("Transaction User B");
            for (Transaction transaction : arrB) System.out.println("-> " + transaction);
            System.out.println("Balance after transaction");
            System.out.println("-> " + service.getBalanceUser(a.getIdentifier()));
            System.out.println("-> " + service.getBalanceUser(b.getIdentifier()));

            System.out.println("Remove transaction from user A");
            service.removeTransactionUser(a.getIdentifier(), arrA[0].getIdentifier());
            arrA = service.getTransactionsUser(a.getIdentifier());
            arrB = service.getTransactionsUser(b.getIdentifier());
            System.out.println("Transaction User A");
            for (Transaction transaction : arrA) System.out.println("-> " + transaction);
            System.out.println("Transaction User B");
            for (Transaction transaction : arrB) System.out.println("-> " + transaction);

            System.out.println("Invalid Transactions");
            Transaction[] invalid = service.getInvalidTransaction();

            for (Transaction transaction : invalid) System.out.println("-> " + transaction);

            service.makeTransaction(a.getIdentifier(), b.getIdentifier(), 10000);   // insufficient funds to make a transaction


        } catch (TransactionNotFoundException s) { System.out.println(s.getMessage());}


    }
}
