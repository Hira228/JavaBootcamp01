import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.UUID;

public class TransactionsService {
    private final UsersArrayList userList;

    TransactionsService() {
        userList = new UsersArrayList();
    }

    public void addUser(User user) { userList.addUser(user);}

    public long getBalanceUser(int id) throws UserNotFoundException {
        try {
            return userList.getUserById(id).getBalance();
        } catch (UserNotFoundException s) {
            throw new UserNotFoundException(s.getMessage());
        }
    }

    public void makeTransaction(int idSender, int idRecipient, long transferAmount) throws UserNotFoundException, IllegalTransactionException {
        UUID identifier = UUID.randomUUID();
        try {
            if(idSender == idRecipient) throw new UserNotFoundException("You can't send money to yourself");
            userList.getUserById(idRecipient).getTransactionsList().addTransaction(new TransactionNode(
                            new Transaction(
                                    userList.getUserById(idRecipient),
                                    userList.getUserById(idSender),
                                    transferAmount,
                                    Transaction.TypeTransferCategory.DEBITS,
                                    identifier
                            )
            )
            );

            userList.getUserById(idSender).getTransactionsList().addTransaction(
                    new TransactionNode(
                        new Transaction(
                                userList.getUserById(idRecipient),
                                userList.getUserById(idSender),
                                -transferAmount,
                                Transaction.TypeTransferCategory.CREDITS,
                                identifier
                        )
                    )
            );
        } catch (UserNotFoundException s) { throw new UserNotFoundException(s.getMessage()); }
    }

    public Transaction[] getTransactionsUser(int id) throws UserNotFoundException {
        try {
            return userList.getUserById(id).getTransactionsList().toArray();
        } catch (UserNotFoundException s) {
            throw new UserNotFoundException(s.getMessage());
        }
    }

    public Transaction removeTransactionUser(int identifierUser, UUID identifierTransaction) throws TransactionNotFoundException {
        Transaction remove;
        try {
            remove = userList.getUserById(identifierUser).getTransactionsList().removeTransaction(identifierTransaction);
        } catch (UserNotFoundException s) {
            throw new TransactionNotFoundException(s.getMessage());
        }
        return remove;
    }

    public Transaction[] getInvalidTransaction() {
        TransactionsList transactionsListCredits = new TransactionsLinkedList();
        TransactionsList transactionsListDebits = new TransactionsLinkedList();
        TransactionsList invalidTransactions = new TransactionsLinkedList();

        for (int i = 0; i < userList.getCountUsers(); ++i) {
            for (int j = 0; j < userList.getUserByIndex(i).getTransactionsList().toArray().length; ++j) {
                Transaction currentTransaction = userList.getUserByIndex(i).getTransactionsList().toArray()[j];
                if (currentTransaction != null && currentTransaction.getTransferCategory().equals(Transaction.TypeTransferCategory.CREDITS)) {
                    transactionsListCredits.addTransaction(new TransactionNode(currentTransaction));
                } else if (currentTransaction != null) {
                    transactionsListDebits.addTransaction(new TransactionNode(currentTransaction));
                }
            }
        }
//        for (int i = 0; i < userList.getCountUsers(); ++i) {
//            System.out.println("-----------start");
//            for (int j = 0; j < userList.getUserByIndex(i).getTransactionsList().toArray().length && userList.getUserByIndex(i).getTransactionsList().toArray()[j] != null; ++j) {
//                System.out.println(userList.getUserByIndex(i).getTransactionsList().toArray()[j]);
//            }
//            System.out.println("-----------end");
//        }
//        System.out.println("-----------start");
//        for(Transaction transaction : transactionsListCredits.toArray()) System.out.println(transaction);
//        for(Transaction transaction : transactionsListDebits.toArray()) System.out.println(transaction);
//        System.out.println("-----------end");

        if (transactionsListCredits.toArray().length > 0 && transactionsListDebits.toArray().length > 0) {
            checkInvalidTransactions(transactionsListCredits, transactionsListDebits, invalidTransactions);
            checkInvalidTransactions(transactionsListDebits, transactionsListCredits, invalidTransactions);
        }
        return invalidTransactions.toArray();
    }

    private void checkInvalidTransactions(TransactionsList list1, TransactionsList list2, TransactionsList invalidTransactions) {
        for (Transaction transaction : list1.toArray()) {
            boolean find = false;

            for (Transaction otherTransaction : list2.toArray()) {
                if (transaction != null && otherTransaction != null && transaction.getIdentifier().toString().equals(otherTransaction.getIdentifier().toString())) {
                    find = true;
                    break;
                }
            }

            if (!find && transaction != null) {
                invalidTransactions.addTransaction(new TransactionNode(transaction));
            }
        }
    }



    public int getIdUserLast() { return userList.getUserByIndex(userList.getCountUsers() - 1).getIdentifier(); }

    public String getUserName(int id) { return userList.getUserById(id).getName(); }

}
