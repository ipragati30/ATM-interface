import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATM {
    static int pin = 2330, entered_pin, count = 0, option, continue_transaction = 1;
    static float balance = 25000;
    static float[] transactionHistory = new float[100];
    static String[] transactionTypes = new String[100];
    static int numTransactions = 0;
    static int amount;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        displayDateTime();

        while (true) {
            while (pin != entered_pin) {
                reset:
                System.out.println("\nPlease enter your pin: ");
                entered_pin = sc.nextInt();

                if (entered_pin != pin) {
                    System.out.println("\nInvalid pin!!!\nPlease Enter Correct Pin!!!");
                    count++;
                    if (count == 3 && pin != entered_pin) {
                        System.exit(0);
                    }
                }
            }

            while (continue_transaction != 0) {
                count = 0;
                trans:
                System.out.println("\n\t\t\t*************  Available Transaction  *************");
                System.out.println("\n\t\t1. Withdraw");
                System.out.println("\n\t\t2. Deposit");
                System.out.println("\n\t\t3. Transfer");
                System.out.println("\n\t\t4. View Transactions History");
                System.out.println("\n\t\t5. Quit");
                System.out.println("\n\n\tPlease select an option: ");
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        balance = withdraw(balance);
                        break;
                    case 2:
                        balance = deposit(balance);
                        break;
                    case 3:
                        balance = transfer(balance);
                        break;
                    case 4:
                        displayTransactionHistory(transactionHistory, transactionTypes, numTransactions, balance);
                        break;
                    case 5:
                        System.out.println("\nExiting...");
                        System.exit(0);
                    default:
                        System.out.println("\n\t\tInvalid Option!!!");
                }

                System.out.println("\n\tDo you wish to perform another transaction?\n\tPress 1[Yes], 0[No]");
                continue_transaction = sc.nextInt();

                if (continue_transaction == 0) {
                    System.exit(0);
                }
            }
        }
    }

    public static void displayDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("\n\t\t\t\t\t       " + dtf.format(now));
        System.out.println("\n\t\t\t\t******************  WELCOME TO ATM OF OPTIFYX INTERNSHIP PROGRAM  *******************");
    }

    public static void checkBalance(float balance) {
        System.out.printf("\n\t\tYour balance is Rs.%.2f\n", balance);
    }

    public static float withdraw(float balance) {
        System.out.println("\n\t\tEnter the amount to withdraw: ");
        amount = sc.nextInt();

        if (balance < amount) {
            System.out.println("\n\tSorry, Insufficient Balance");
            System.out.println("\n\tPlease Check The Balance Then Try Again");
        } else {
            balance -= amount;
            System.out.printf("\n\tYou have withdrawn Rs.%d. \n\tYour new balance is Rs.%.2f\n", amount, balance);
            saveTransaction(amount, "Withdrawal");
        }
        return balance;
    }

    public static float deposit(float balance) {
        System.out.println("\n\tPlease enter the amount to deposit: ");
        amount = sc.nextInt();

        balance += amount;
        System.out.printf("\n\tYou have deposited Rs.%d. \n\tYour new balance is Rs.%.2f\n", amount, balance);
        saveTransaction(amount, "Deposit");
        return balance;
    }

    public static float transfer(float balance) {
        System.out.println("\n\tEnter the amount to transfer: ");
        amount = sc.nextInt();

        if (balance < amount) {
            System.out.println("\n\tSorry, Insufficient Balance");
        } else {
            balance -= amount;
            System.out.printf("\n\tYou have transferred Rs.%d. \n\tYour new balance is Rs.%.2f\n", amount, balance);
            saveTransaction(amount, "Transfer");
        }
        return balance;
    }

    public static void saveTransaction(int amount, String transactionType) {
        transactionHistory[numTransactions] = amount;
        transactionTypes[numTransactions] = transactionType;
        numTransactions++;
    }

    public static void displayTransactionHistory(float[] transactionHistory, String[] transactionTypes, int numTransactions, float balance) {
        System.out.println("\n\t\tTransaction History:\n");
        for (int i = 0; i < numTransactions; ++i) {
            System.out.printf("\n\n\t\tTransaction %d : Rs.%.2f", i + 1, transactionHistory[i]);
            System.out.println("\n\t\tTransaction Type: " + transactionTypes[i]);
        }
        System.out.printf("\n\n\t\tRemaining Balance: Rs.%.2f\n", balance);
        System.out.println("\n\t\t****************** Thank you for Banking with OPTIFYX ******************\n");
    }
}

