import java.util.UUID;

final public class Transaction {
    public enum TypeTransferCategory {
        DEBITS,
        CREDITS
    }
    private Transaction  Next;
    private final UUID Identifier = UUID.randomUUID();
    private final User Recipient;
    private final User Sender;
    private final TypeTransferCategory TransferCategory;
    long TransferAmount;

    public Transaction(User Recipient, User Sender, final long TransferAmount, TypeTransferCategory TransferCategory) {
        if(TransferCategory == TypeTransferCategory.DEBITS && TransferAmount > 0) {
            Recipient.setBalance(Recipient.getBalance() + TransferAmount);
        }
        else if(TransferCategory == TypeTransferCategory.CREDITS && TransferAmount < 0) {
            Sender.setBalance(Sender.getBalance() - TransferAmount);
        } else throw new IllegalArgumentException("Invalid transfer category or amount");
        this.Recipient = Recipient;
        this.Sender = Sender;
        this.TransferCategory = TransferCategory;
        this.TransferAmount = TransferAmount;
    }


    //getters

    public UUID getIdentifier() { return Identifier; }
    public User getRecipient() { return Recipient; }
    public User getSender() { return Sender; }
    public TypeTransferCategory getTransferCategory() { return TransferCategory; }
    public long getTransferAmount() { return TransferAmount; }
    public Transaction getNext() { return Next == null ? null : Next; }


    //setters
    public void setNext(Transaction next) { Next = next; }

    @Override
    public String toString() {
        return "Recipient: " + Recipient.getName() + '|'
                + " Sender: " + Sender.getName() + '|'
                + " Transfer Category: " + TransferCategory + '|'
                + " TransferAmount: " + TransferAmount + '|'
                + " Identifier: " + Identifier + '|';
    }

}
