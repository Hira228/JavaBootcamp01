public class Program {
    public static void main(String[] args) {
        User a = new User(1, "Matvey", 1000);
        User b = new User(2, "Aboba", 500);

        System.out.println(a.toString());
        System.out.println(b.toString());

        Transaction t = new Transaction(b, a, Transaction.TypeTransferCategory.DEBITS, 500);
        Transaction tt = new Transaction(b, a, Transaction.TypeTransferCategory.DEBITS, 500);

        System.out.println(t.toString());

        System.out.println(a.toString());
        System.out.println(b.toString());

        System.out.println(tt.toString());

        System.out.println(a.toString());
        System.out.println(b.toString());
    }
}
