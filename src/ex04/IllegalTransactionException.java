public class IllegalTransactionException extends TransactionNotFoundException {
    IllegalTransactionException(String message){
        super(message);
    }
}
