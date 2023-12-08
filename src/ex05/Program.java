import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu(args);

//        TransactionsService service = new TransactionsService();
//        User user1 = new User("Mama" , 888);
//        User user2 = new User("Lala" , 888);
//
//        service.addUser(user1);
//        service.addUser(user2);
//        try {
//            service.makeTransaction(1 , 2, 1);
//            service.makeTransaction(2 , 1, 2);
//            service.makeTransaction(2 , 1, 3);
//            service.makeTransaction(1 , 2, 4);
//
//            service.removeTransactionUser(1, user1.getTransactionsList().toArray()[0].getIdentifier());
//            service.removeTransactionUser(1, user1.getTransactionsList().toArray()[0].getIdentifier());
//
//            Transaction[] aa = service.getInvalidTransaction();
//
//            System.out.println("INVALID");
////
//           for (Transaction transaction : aa) System.out.println(transaction);
//
//           aa = service.getInvalidTransaction();
//            System.out.println("INVALID");
//            for (Transaction transaction : aa) System.out.println(transaction);
//
//            System.out.println("INVALID");
//            aa = service.getInvalidTransaction();
//            for (Transaction transaction : aa) System.out.println(transaction);
//
//
//        } catch (TransactionNotFoundException e) { System.out.println(e.getMessage());}
    }
}
