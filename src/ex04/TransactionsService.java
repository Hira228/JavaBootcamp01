import java.util.UUID;

public class TransactionsService {
    private UsersArrayList userList;

    private TransactionsList transactionsListCredits;
    private TransactionsList transactionsListDebits;

    public void addUser(User user) { userList.addUser(user);}

    public long getBalanceUser(User user) throws UserNotFoundException {
        try {
            return userList.getUserById(user.getIdentifier()).getBalance();
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

    public Transaction[] getTransactionsUser(User user) throws UserNotFoundException {
        try {
            return userList.getUserById(user.getIdentifier()).getTransactionsList().toArray();
        } catch (UserNotFoundException s) {
            throw new UserNotFoundException(s.getMessage());
        }
    }

    public void removeTransactionUser(UUID identifierTransaction, int identifierUser) throws TransactionNotFoundException {
        try {
            userList.getUserById(identifierUser).getTransactionsList().removeTransaction(identifierTransaction);
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
    }

    public Transaction[] getInvalidTransaction()  {
        TransactionsLinkedList invalidTransactionsList = new TransactionsLinkedList();
        for (int i = 0; i < transactionsListCredits.toArray().length; ++i) {
            boolean foundMatch = false;

            for (int j = 0; j < transactionsListDebits.toArray().length; ++j) {
                if (transactionsListCredits.toArray()[i].getIdentifier().equals(transactionsListDebits.toArray()[j].getIdentifier())) {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                invalidTransactionsList.addTransaction(transactionsListCredits.toArray()[i]);
            }
        }

        return invalidTransactionsList.toArray();
    }

}
