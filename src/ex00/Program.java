public class Program {
    public static void main(String[] args) {
        User a = new User(1, "Matvey", 1000);
        User b = new User(2, "Aboba", 500);

        System.out.println(a);
        System.out.println(b);

        Transaction t = new Transaction(b, a, Transaction.TypeTransferCategory.CREDITS , 500);


        System.out.println(t);

        System.out.println(a);
        System.out.println(b);
        Transaction tt = new Transaction(b, a, Transaction.TypeTransferCategory.DEBITS, 500);
        System.out.println(tt);

        System.out.println(a);
        System.out.println(b);

        Transaction ttt = new Transaction(b, a,  Transaction.TypeTransferCategory.CREDITS,500);
        System.out.println(ttt);

        System.out.println(a);
        System.out.println(b);
    }
}
