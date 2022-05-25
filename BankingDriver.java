import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.lang.StringBuffer;

public class BankingDriver {
    public static void main(String[] args) {
        // create subclasses
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(new CheckingAccount(56094324,"Jerry Filmore",(float)12345.54,(float)50.00));
        accounts.add(new CheckingAccount(43589345,"Penelope Newton",(float)342345.54,(float)50.00));
        accounts.add(new LoanAccount(54355312,"Karen Seaholm",(float)134545.89));
        accounts.add(new LoanAccount(19223546,"Ron Bartleby",(float)22809.05));
        accounts.get(0).postCredit((float)234.44);
        accounts.get(0).postDebit((float)36.24);
        accounts.get(1).postCredit((float)52.87);
        accounts.get(1).postCredit((float)122.54);
        accounts.get(2).postDebit((float)4500.44);
        accounts.get(2).postDebit((float)256.79);
        accounts.get(3).postCredit((float)34.30);
        accounts.get(3).postDebit((float)180.07);

        Scanner userInput = new Scanner(System.in);
        int menuOption;

        System.out.println("Hello. Welcome to the customer account banking system. Please select an option from the menu below.");

        do {
            System.out.println("Please input a number for a menu option.");
            System.out.println("1. Add a new bank account.");
            System.out.println("2. Post a credit to an account.");
            System.out.println("3. Post a debit to an account.");
            System.out.println("4. Print the transactions for an account.");
            System.out.println("5. Print a list of all accounts' numbers, customer names, date of last transaction, and currenct balance.");
            System.out.println("6. Exit");

            menuOption = userInput.nextInt();
            userInput.nextLine();

            // throw an exception if the input is a decimal number or less than 1 or greater than 6
            switch(menuOption) {
                case 1:
                    System.out.println("Which kind of account would you like to add?");
                    System.out.println("1. Checking account");
                    System.out.println("2. Loan account");

                    int menuOption1 = userInput.nextInt();
                    userInput.nextLine();

                    switch(menuOption1) {
                        case 1:
                            // add checking account
                            System.out.println("Please enter the account number, the account holder name, the balance of the account, and an overdraft limit for the account (in order).");

                            int numberAccount1 = userInput.nextInt();
                            userInput.nextLine();
                            String holderAccount1 = userInput.nextLine();
                            float balanceAccount1 = userInput.nextFloat();
                            float limitOverdraft = userInput.nextFloat();

                            accounts.add(new CheckingAccount(numberAccount1,holderAccount1,balanceAccount1,limitOverdraft));
                            break;
                        case 2:
                            // add loan account
                            System.out.println("Please enter the account number, the account holder name, and the balance of the account (in order).");

                            int numberAccount2 = userInput.nextInt();
                            userInput.nextLine();
                            String holderAccount2 = userInput.nextLine();
                            float balanceAccount2 = userInput.nextFloat();

                            accounts.add(new LoanAccount(numberAccount2,holderAccount2,balanceAccount2));
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid option. Please enter a number corresponding to the options shown.");
                    }
                    break;
                case 2:
                    System.out.println("Here are a list of the accounts. Which account would you like to post a credit to?");
                    System.out.println("Please enter a corresponding account index.");

                    for (int i = 0; i < accounts.size(); i++) {
                        System.out.printf("Account index: %d Account number: %d Account type: %s%n", i, accounts.get(i).getAccountNumber(), accounts.get(i).getClass().toString().matches("CheckingAccount") ? "Checking" : "Loan");
                    }

                    int menuOption2 = userInput.nextInt();
                    userInput.nextLine();

                    if(menuOption2 > accounts.size()-1) {
                        throw new IllegalArgumentException("Invalid option. This account does not exist. Please enter an index corresponding to an existing account.");
                    }
                    else {
                        System.out.println("What would you like the credit amount to be?");
                        float creditAmt = userInput.nextFloat();

                        accounts.get(menuOption2).postCredit(creditAmt);
                    }
                    break;
                case 3:
                    System.out.println("Here are a list of the accounts. Which account would you like to post a debit to?");
                    System.out.println("Please enter a corresponding account index.");

                    for (int i = 0; i < accounts.size(); i++) {
                        System.out.printf("Account index: %d Account number: %d Account type: %s%n", i, accounts.get(i).getAccountNumber(), accounts.get(i).getClass().toString().matches("CheckingAccount") ? "Checking" : "Loan");
                    }
                    int menuOption3 = userInput.nextInt();
                    userInput.nextLine();

                    if(menuOption3 > accounts.size()-1) {
                        throw new IllegalArgumentException("Invalid option. This account does not exist. Please enter an index corresponding to an existing account.");
                    }
                    else {
                        System.out.println("What would you like the debit amount to be?");
                        float debitAmt = userInput.nextFloat();

                        accounts.get(menuOption3).postCredit(debitAmt);
                    }
                    break;
                case 4:
                    System.out.println("Here are a list of the accounts. Which account would you like to print transactions from?");
                    System.out.println("Please enter the corresponding account index.");

                    for (int i = 0; i < accounts.size(); i++) {
                        System.out.printf("Account index: %d Account number: %d Account type: %s%n", i, accounts.get(i).getAccountNumber(), accounts.get(i).getClass().toString().matches("CheckingAccount") ? "Checking" : "Loan");
                    }

                    int menuOption4 = userInput.nextInt();
                    userInput.nextLine();

                    if(menuOption4 > accounts.size()-1) {
                        throw new IllegalArgumentException("Invalid option. This account does not exist. Please enter an index corresponding to an existing account.");
                    }
                    else {
                        System.out.println("Which type of transactions records would you like to see?");
                        System.out.println("1. Credits");
                        System.out.println("2. Debits");
                        System.out.println("3. All transactions");

                        int typeTransaction = userInput.nextInt();
                        userInput.nextLine();

                        switch(typeTransaction) {
                            case 1:
                                accounts.get(menuOption4).reportCredit();
                                break;
                            case 2:
                                accounts.get(menuOption4).reportDebit();
                                break;
                            case 3:
                                accounts.get(menuOption4).reportTransaction();
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid option. This account does not exist. Please choose a number corresponding to the transaction types shown.");
                        }
                    }

                    break;
                case 5:
                    System.out.println("Here are a list of all of the accounts, their numbers, the account holder names, the date of most recent transaction, their balance, account type, and an overdraft limit if applicable.");

                    for (int i = 0; i < accounts.size(); i++) {
                        System.out.printf("Account number: %d Account holder: %s Date of last transaction: %s Account balance: $%.2f Account type: %s%s%n", accounts.get(i).getAccountNumber(), accounts.get(i).getAccountHolder(), accounts.get(i).getDateOfLastTransaction() != "" ? accounts.get(i).getDateOfLastTransaction() : "N/A", accounts.get(i).getAccountBalance(), accounts.get(i).getClass().toString().matches("class CheckingAccount") ? "Checking" : "Loan", accounts.get(i).getClass().toString().matches("class CheckingAccount") ? String.format(" Overdraft limit: $%.2f",((CheckingAccount) accounts.get(i)).getOverdraftLimit()) : "");
                    }
                    break;
                case 6:
                    // exit program
                    menuOption = 6;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid menu option. Please enter a number corresponding to the options shown.");
            }
        } while (menuOption != 6);

        userInput.close();
    }
}