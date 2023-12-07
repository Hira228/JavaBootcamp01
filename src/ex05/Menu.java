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
                            boolean retry;
                            do {
                                try {
                                    System.out.println("Enter a user ID");
                                    int id = in_.nextInt();
                                    System.out.println(service.getUserName(id) + " - " + service.getBalanceUser(id));
                                    System.out.println("---------------------------------------------------------");
                                    retry = false;
                                } catch (UserNotFoundException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Please try again");
                                    retry = true;
                                }
                            } while(retry);
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
                                } catch (IllegalTransactionException | UserNotFoundException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Please try again");
                                    retry = true;
                                }
                            } while (retry);
                            printMenu();
                        }
                        case 4 -> {
                            boolean retry;
                            do {
                                try {
                                    System.out.println("Enter a user ID");
                                    Transaction[] transactions = service.getTransactionsUser(in_.nextInt());
                                    for (Transaction transaction : transactions) System.out.println(transaction);
                                    System.out.println("---------------------------------------------------------");
                                    retry = false;
                                } catch (UserNotFoundException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Please try again");
                                    retry = true;
                                }
                            } while (retry);
                            printMenu();
                        }
                        case 5 -> {
                            boolean retry;
                            do {
                                try {
                                    System.out.println("Enter a user ID and a transfer ID");
                                    Transaction remove = service.removeTransactionUser(in_.nextInt(), UUID.fromString(in_.next()));
                                    remove.outputOption = 2;
                                    System.out.println(remove);
                                    remove.outputOption = 1;
                                    System.out.println("---------------------------------------------------------");
                                    retry = false;
                                } catch (TransactionNotFoundException | UserNotFoundException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Please try again");
                                    retry = true;
                                }
                            } while(retry);
                            printMenu();
                        }
                        case 6 -> {
                            System.out.println("Check results:");
                            Transaction[] invalidTransactions = service.getInvalidTransaction();
                            if(invalidTransactions.length == 0) System.out.println("All transactions are valid");
                            else {
                                for (Transaction transaction : invalidTransactions) {
                                    transaction.outputOption = 3;
                                    System.out.println(transaction);
                                    transaction.outputOption = 1;
                                }
                            }
                            System.out.println("---------------------------------------------------------");
                            printMenu();
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

