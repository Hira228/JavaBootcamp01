import java.util.UUID;

final public class Transaction {

    public enum TypeTransferCategory {
        DEBITS,
        CREDITS
    }
    public int outputOption = 1;
    private Transaction  Next;
    private final UUID Identifier;
    private final User Recipient;
    private final User Sender;

    private final int ownerTransaction;
    private final TypeTransferCategory TransferCategory;
    private final long TransferAmount;

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
        if (outputOption == 1) {
            if (ownerTransaction == Recipient.getIdentifier()) {
                return "From " + Sender.getName() +
                        "(id = " + Sender.getIdentifier() + ") " + "+" + TransferAmount +
                        " with id = " + Identifier;
            }
            return "To " + Recipient.getName() +
                    "(id = " + Recipient.getIdentifier() + ") " + TransferAmount +
                    " with id = " + Identifier;
        }
        else if(outputOption == 2) {
            return "Transfer To " + Recipient.getName() + "(id = " + Recipient.getIdentifier() + ") " + (TransferAmount < 0 ? -TransferAmount : TransferAmount) + " removed";
        }
        return (ownerTransaction == Recipient.getIdentifier() ? Recipient.getName() : Sender.getName()) + "(id = "
                + ownerTransaction + ") has an unacknowledged transfer id = " + Identifier + (TransferAmount > 0 ? " from " : " to ") +
                (ownerTransaction == Recipient.getIdentifier() ? Sender.getName() : Recipient.getName()) +
                "(id = " + (ownerTransaction == Recipient.getIdentifier() ? Sender.getIdentifier() : Recipient.getIdentifier()) +
                ") for " + TransferAmount;

    }


}

