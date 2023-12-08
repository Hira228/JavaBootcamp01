import java.util.UUID;

final public class Transaction {
    public enum TypeTransferCategory {
        DEBITS,
        CREDITS
    }

    public Transaction(User recipient, User sender, TypeTransferCategory transferCategory, final long transferAmount) {
        this.Recipient = recipient;
        this.Sender = sender;
        this.TransferCategory = transferCategory;
        this.TransferAmount = transferAmount;

        if (transferCategory == TypeTransferCategory.CREDITS && transferAmount > 0) {
            recipient.setBalance(recipient.getBalance() + transferAmount);
            sender.setBalance(sender.getBalance() - transferAmount);
        } else if (transferCategory == TypeTransferCategory.DEBITS && transferAmount < 0) {
            recipient.setBalance(recipient.getBalance() + transferAmount);
            sender.setBalance(sender.getBalance() - transferAmount);
        } else {
            throw new IllegalArgumentException("Invalid transaction category or amount");
        }
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
