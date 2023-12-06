import java.util.UUID;

final public class Transaction {

    public enum TypeTransferCategory {
        DEBITS,
        CREDITS
    }
    private Transaction  Next;
    private final UUID Identifier;
    private final User Recipient;
    private final User Sender;

    private final int ownerTransaction;
    private final TypeTransferCategory TransferCategory;
    long TransferAmount;

    public Transaction(User Recipient, User Sender, final long TransferAmount, TypeTransferCategory TransferCategory, UUID Identifier) throws IllegalTransactionException {
        if(TransferCategory == TypeTransferCategory.DEBITS && TransferAmount > 0) {
            Recipient.setBalance(Recipient.getBalance() + TransferAmount);
            ownerTransaction = Recipient.getIdentifier();
        }
        else if(TransferCategory == TypeTransferCategory.CREDITS && TransferAmount < 0 && Sender.getBalance() >= -TransferAmount) {
            Sender.setBalance(Sender.getBalance() + TransferAmount);
            ownerTransaction = Sender.getIdentifier();
        } else throw new IllegalTransactionException("Insufficient funds to make a transaction");
        this.Recipient = Recipient;
        this.Sender = Sender;
        this.TransferCategory = TransferCategory;
        this.TransferAmount = TransferAmount;
        this.Identifier = Identifier;
    }


    //getters

    public UUID getIdentifier() { return Identifier; }
    public User getRecipient() { return Recipient; }
    public User getSender() { return Sender; }
    public TypeTransferCategory getTransferCategory() { return TransferCategory; }
    public long getTransferAmount() { return TransferAmount; }
    public Transaction getNext() { return Next == null ? null : Next; }
    public int getOwnerTransaction() { return ownerTransaction; }


    //setters
    public void setNext(Transaction next) { Next = next; }

    @Override
    public String toString() {
        if(ownerTransaction == Recipient.getIdentifier()) {
            return "From " + Sender.getName() +
                    "(id = " + Sender.getIdentifier() + ") " + "+" + TransferAmount +
                    " with id = " + Identifier;
        }
        return "To " + Recipient.getName() +
                "(id = " + Recipient.getIdentifier() + ") " + TransferAmount +
                " with id = " + Identifier;
    }

}
