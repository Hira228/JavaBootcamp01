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
//            service.makeTransaction(1 , 2, 300);
//            service.makeTransaction(2 , 1, 325);
//
//            System.out.println("Tr for user 1");
//            for(Transaction transaction : service.getTransactionsUser(1)) {
//                System.out.println(transaction);
//            }
//            System.out.println("Tr for user 2");
//
//            for(Transaction transaction : service.getTransactionsUser(2)) {
//                System.out.println(transaction);
//            }
//        } catch (IllegalTransactionException e) { System.out.println(e.getMessage());}
    }
}
