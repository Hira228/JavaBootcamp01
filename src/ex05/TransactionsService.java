import java.util.UUID;

public class TransactionsService {
    private UsersArrayList userList;

    public TransactionsList transactionsListCredits;
    public TransactionsList transactionsListDebits;

    TransactionsService() {
        userList = new UsersArrayList();
        transactionsListCredits = new TransactionsLinkedList();
        transactionsListDebits = new TransactionsLinkedList();
    }

    public void addUser(User user) { userList.addUser(user);}

    public long getBalanceUser(int id) throws UserNotFoundException {
        try {
            return userList.getUserById(id).getBalance();
        } catch (UserNotFoundException s) {
            throw new UserNotFoundException(s.getMessage());
        }
    }

    public void makeTransaction(int idRecipient, int idSender, long transferAmount) throws UserNotFoundException, IllegalTransactionException {
        UUID identifier = UUID.randomUUID();
        try {
            userList.getUserById(idRecipient).getTransactionsList().addTransaction(
                    new Transaction(userList.getUserById(idRecipient),
                            userList.getUserById(idSender), transferAmount,
                            Transaction.TypeTransferCategory.DEBITS, identifier)
            );

            transactionsListCredits.addTransaction(
                    userList.getUserById(idRecipient).getTransactionsList().toArray()[
                            userList.getUserById(idRecipient).getTransactionsList().toArray().length - 1
                            ]
            );

            userList.getUserById(idSender).getTransactionsList().addTransaction(
                    new Transaction(userList.getUserById(idRecipient),
                            userList.getUserById(idSender), -transferAmount,
                            Transaction.TypeTransferCategory.CREDITS, identifier)
            );

            transactionsListDebits.addTransaction(
                    userList.getUserById(idSender).getTransactionsList().toArray()[
                            userList.getUserById(idSender).getTransactionsList().toArray().length - 1
                            ]
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
            for(int i = 0; i < transactionsListCredits.toArray().length; ++i) {
                if (transactionsListCredits.toArray()[i].getRecipient().getIdentifier() == identifierUser &&
                        transactionsListCredits.toArray()[i].getIdentifier().equals(identifierTransaction))
                {
                    transactionsListCredits.removeTransaction(identifierTransaction);
                }
            }
            for (int i = 0; i < transactionsListDebits.toArray().length; ++i) {
                if(transactionsListDebits.toArray()[i].getSender().getIdentifier() == identifierUser &&
                        transactionsListDebits.toArray()[i].getIdentifier().equals(identifierTransaction))
                {
                    transactionsListDebits.removeTransaction(identifierTransaction);
                }
            }
        } catch (UserNotFoundException s) {
            throw new TransactionNotFoundException(s.getMessage());
        }
        return remove;
    }

    public Transaction[] getInvalidTransaction()  {
        TransactionsList invalidTransactionsList1 = new TransactionsLinkedList();
        TransactionsList invalidTransactionsList2 = new TransactionsLinkedList();

        Transaction[] creditsArray = transactionsListCredits.toArray();
        Transaction[] debitsArray = transactionsListDebits.toArray();

        for (int i = 0; i < creditsArray.length; ++i) {
            if (creditsArray[i] == null) {
                continue;
            }

            boolean foundMatch = false;

            for (int j = 0; j < debitsArray.length; ++j) {
                if (debitsArray[j] != null && creditsArray[i].getIdentifier().equals(debitsArray[j].getIdentifier())) {
                    foundMatch = true;
                    break;
                }
            }

            if (!foundMatch) {
                invalidTransactionsList1.addTransaction(creditsArray[i]);
            }
        }

        for (int i = 0; i < debitsArray.length; ++i) {
            if (debitsArray[i] == null) {
                continue;
            }

            boolean foundMatch = false;

            for (int j = 0; j < creditsArray.length; ++j) {
                if (creditsArray[j] != null && debitsArray[i].getIdentifier().equals(creditsArray[j].getIdentifier())) {
                    foundMatch = true;
                    break;
                }
            }

            if (!foundMatch) {
                invalidTransactionsList2.addTransaction(debitsArray[i]);
            }
        }

        return invalidTransactionsList1.toArray().length > invalidTransactionsList2.toArray().length ? invalidTransactionsList1.toArray() : invalidTransactionsList2.toArray();
    }

    public int getIdUserLast() { return userList.getUserByIndex(userList.getCountUsers() - 1).getIdentifier(); }

    public String getUserName(int id) { return userList.getUserById(id).getName(); }

}
