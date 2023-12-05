import java.util.UUID;

final public class User {
    private final int Identifier;
    private String Name;
    private long Balance;

    private TransactionsList TransactionsList;
    public User (final String Name, final long Balance) {
        this.Identifier = UserIdsGenerator.getInstance().generateId();
        this.Name = Name;
        this.Balance = Balance < 0 ? 0 : Balance;
    }
    //Getters
    public int getIdentifier() { return Identifier; }
    public String getName() { return Name; }
    public long getBalance() { return Balance; }
    public TransactionsList getTransactionsList() { return TransactionsList; }

    //Setters
    public void setName(final String Name) { this.Name = Name; }
    public void setBalance(final long Balance) { this.Balance = Balance; }

    @Override
    public String toString() {
        return "Identifier: " + Identifier + '|'
                + "Name: " + Name + '|'
                + "Balance: " + Balance + '|';
    }


}
