import java.util.UUID;

final public class Transaction {
    public enum TypeTransferCategory {
        DEBITS,
        CREDITS
    }

    public Transaction(User Recipient, User Sender, final TypeTransferCategory TransferCategory, final long TransferAmount) {
        this.Recipient = Recipient;
        this.Sender = Sender;
        Recipient.setBalance(Recipient.getBalance() + TransferAmount);
        Sender.setBalance(Sender.getBalance() - TransferAmount);
        this.TransferCategory = TransferCategory;
        this.TransferAmount = TransferAmount;
    }

    private final UUID Identifier = UUID.randomUUID();
    private final User Recipient;
    private final User Sender;
    private final  TypeTransferCategory TransferCategory;
    long TransferAmount;

    //getters

    public UUID getIdentifier() { return Identifier; }
    public User getRecipient() { return Recipient; }
    public User getSender() { return Sender; }
    public TypeTransferCategory getTransferCategory() { return TransferCategory; }

    @Override
    public String toString() {
        return "Recipient: " + Recipient.getName() + '|'
                + " Sender: " + Sender.getName() + '|'
                + " Transfer Category: " + TransferCategory + '|'
                + " TransferAmount: " + TransferAmount + '|'
                + " Identifier: " + Identifier + '|';
    }

}
