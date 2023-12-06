import java.util.Scanner;
import java.util.UUID;

public class Menu {
    TransactionsService service;
    Menu() { service = new TransactionsService(); }

    public void printMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV – remove a transfer by ID");
        System.out.println("6. DEV – check transfer validity");
        System.out.println("7. Finish execution");
    }

    public void runMenu(String[] s) {
        if (s[0].equals("--profile=dev")) {
            Scanner in_ = new Scanner(System.in);
            printMenu();
                while (in_.hasNextInt()) {
                    switch (in_.nextInt()) {
                        case 1 -> {
                            System.out.println("Enter a user name and a balance");
                            service.addUser(new User(in_.next(), in_.nextInt()));
                            System.out.println("User with id = " + service.getIdUserLast() + " is added");
                            System.out.println("---------------------------------------------------------");
                            printMenu();
                        }
                        case 2 -> {
                            System.out.println("Enter a user ID");
                            int id = in_.nextInt();
                            System.out.println(service.getUserName(id) + " - " + service.getBalanceUser(id));
                            System.out.println("---------------------------------------------------------");
                            printMenu();
                        }
                        case 3 -> {
                            boolean retry;
                            do {
                                System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                                try {
                                    service.makeTransaction(in_.nextInt(), in_.nextInt(), in_.nextInt());
                                    System.out.println("The transfer is completed");
                                    System.out.println("---------------------------------------------------------");
                                    retry = false;
                                } catch (IllegalTransactionException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Please enter the amount that meets your balance");
                                    retry = true;
                                }
                            } while (retry);
                            printMenu();
                        }
                        case 4 -> {
                            System.out.println("Enter a user ID");
                            Transaction[] transactions = service.getTransactionsUser(in_.nextInt());
                            for(Transaction transaction : transactions) System.out.println(transaction);
                            System.out.println("---------------------------------------------------------");
                            printMenu();
                        }
                        case 5 -> {
                            try {
                                System.out.println("Enter a user ID and a transfer ID");
                                Transaction remove = service.removeTransactionUser(in_.nextInt(), UUID.fromString(in_.next()));
                                System.out.println(remove + " removed");
                                System.out.println("---------------------------------------------------------");
                                printMenu();
                            } catch (TransactionNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case 6 -> {
//                            System.out.println("Check results:");
//                            Transaction[] invalidTransactions = service.getInvalidTransaction();
//                            for(Transaction transaction : invalidTransactions) {
//
//                            }System.out.println("Transfer "transaction.);
                        }
                        case 7 -> {
                            return;
                        }
                        default -> printMenu();
                    }
                }
            }
        }
    }

