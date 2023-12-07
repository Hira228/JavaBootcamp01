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
            userList.getUserById(idRecipient).getTransactionsList().addTransaction(
                    new Transaction(
                            userList.getUserById(idRecipient),
                            userList.getUserById(idSender),
                            transferAmount,
                            Transaction.TypeTransferCategory.DEBITS,
                            identifier
                    )
            );


            userList.getUserById(idSender).getTransactionsList().addTransaction(
                    new Transaction(
                            userList.getUserById(idRecipient),
                            userList.getUserById(idSender),
                            -transferAmount,
                            Transaction.TypeTransferCategory.CREDITS,
                            identifier
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

    public Transaction[] getInvalidTransaction()  {
        TransactionsList transactionsListCredits = new TransactionsLinkedList();
        TransactionsList transactionsListDebits = new TransactionsLinkedList();
        TransactionsList invalidTransactionsList1 = new TransactionsLinkedList();
        TransactionsList invalidTransactionsList2 = new TransactionsLinkedList();

        for (int i = 0; i < userList.getCountUsers(); ++i) {
            for (int j = 0; j < userList.getUserByIndex(i).getTransactionsList().toArray().length; ++j) {
                if(userList.getUserByIndex(i).getTransactionsList().toArray()[j].getTransferCategory() == Transaction.TypeTransferCategory.CREDITS) {
                    transactionsListCredits.addTransaction(userList.getUserByIndex(i).getTransactionsList().toArray()[j]);
                } else transactionsListDebits.addTransaction(userList.getUserByIndex(i).getTransactionsList().toArray()[j]);
            }
        }

        for(int )
    }

    public int getIdUserLast() { return userList.getUserByIndex(userList.getCountUsers() - 1).getIdentifier(); }

    public String getUserName(int id) { return userList.getUserById(id).getName(); }

}
