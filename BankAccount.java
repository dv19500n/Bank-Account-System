import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
public class BankAccount{
    String accountNumber;
    String ownerName;
    double balance = 0.00;
    DecimalFormat decimal = new DecimalFormat("#.##");
    public BankAccount(String accountNumber, String ownerName){
        this.accountNumber=accountNumber;
        this.ownerName=ownerName;
    }
    public void deposit(double amount){
        balance += amount;
        System.out.println("New Balance: "+decimal.format(balance));
    }
    public void withdraw(double amount){
        if (amount > balance){
            System.out.println("Insufficient Funds.");
        }
        else{
            balance -= amount;
            System.out.println("New Balance: "+decimal.format(balance));
        }
    }
    public void getBalance(){
        System.out.println("Current Balance: "+decimal.format(balance));
    }
    public void getDetails(){
        System.out.println("Account Name: "+ownerName);
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Account Balance: "+balance);
    }
    public static void saveToFile(ArrayList<BankAccount> accounts){
        try{
            FileWriter writer = new FileWriter("bank_account.txt");
            for(BankAccount account:accounts){
                writer.write(account.ownerName+"\n");
                writer.write(account.accountNumber+"\n");
                writer.write(account.balance+"\n");
                
            }
            writer.close();
            System.out.println(" Account details saved to file.");
        }
        catch (IOException e){
            System.out.println("An error occured while saving to file");
            e.printStackTrace();
        }
    }
    public static ArrayList<BankAccount> loadFromFile(){
        ArrayList<BankAccount> accounts=new ArrayList<>();
        try{
            File file =new File("bank_account.txt");
            if (file.exists()){
                Scanner fileScanner = new Scanner(file);
                while(fileScanner.hasNextLine()){
                    String ownerName= fileScanner.nextLine();
                    String accountNumber = fileScanner.nextLine();
                    double balance = Double.parseDouble(fileScanner.nextLine());
                    BankAccount account = new BankAccount(accountNumber, ownerName);
                    account.balance=balance;
                    accounts.add(account);
                }
                fileScanner.close();
                System.out.println("Details from file loaded.");
            }
        }
        catch(IOException e){
            System.out.println("An error occurred can not load from file.");
            e.printStackTrace();
        }
        return accounts;
    }
    public static BankAccount findAccount(ArrayList<BankAccount> accounts, String accountNumber){
        for (BankAccount account:accounts){
            if(account.accountNumber.equals(accountNumber)){
                return account;
            }
        }
        return null;
    }
    public static BankAccount findName(ArrayList<BankAccount> accounts, String ownerName){
        for (BankAccount account:accounts){
            if(account.ownerName.equals(ownerName)){
                return account;
            }
        }
        return null;
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<BankAccount> accounts = loadFromFile();
        while(true){
            System.out.println("\nBank Account Managment System");
            System.out.println("1. Create a new account");
            System.out.println("2. Access an existing account");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice ==1){
                System.out.println("Enter Account Name: ");
                String accountName= scanner.nextLine();
                System.out.println("Enter Account Number: ");
                String accountNum=scanner.nextLine();
                if (findAccount(accounts, accountNum)!= null){
                    System.out.println("Account number already exists. Please enter another account number.");
                }
                else if(findName(accounts, accountName)!= null){
                    System.out.println("Account name already exitis. Please enter another name.");
                }
                else{
                    BankAccount newAccount = new BankAccount(accountNum,accountName);
                    accounts.add(newAccount);
                    System.out.println("Account has been created.");
                }
            }
            else if(choice ==2){
                System.out.println("Enter Account Number: ");
                String accountNum=scanner.nextLine();
                BankAccount account=findAccount(accounts, accountNum);
                if (account==null){
                    System.out.println("Account not found.");
                }
                else{
                    System.out.println("\nAccount Options");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Check Balance");
                    System.out.println("4. View Account Details");
                    System.out.println("5. Back to Main Menu");
                    System.out.println("Enter your choice: ");
                    int accountChoice=scanner.nextInt();
                    scanner.nextLine();
                    while(accountChoice !=5){
                        if(accountChoice == 1){
                            System.out.println("Deposit Amount: ");
                            double numberD= scanner.nextDouble();
                            scanner.nextLine();
                            account.deposit(numberD);
                            System.out.println("\nAccount Options");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. View Account Details");
                            System.out.println("5. Back to Main Menu");
                            System.out.println("Enter your choice: ");
                            accountChoice=scanner.nextInt();
                            scanner.nextLine();
                        }
                        else if(accountChoice ==2){
                            System.out.println("Withdraw Amount: ");
                            double numberW= scanner.nextDouble();
                            scanner.nextLine();
                            account.withdraw(numberW);
                            System.out.println("\nAccount Options");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. View Account Details");
                            System.out.println("5. Back to Main Menu");
                            System.out.println("Enter your choice: ");
                            accountChoice=scanner.nextInt();
                            scanner.nextLine();
                        }
                        else if (accountChoice ==3){
                            account.getBalance();
                            System.out.println("\nAccount Options");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. View Account Details");
                            System.out.println("5. Back to Main Menu");
                            System.out.println("Enter your choice: ");
                            accountChoice=scanner.nextInt();
                            scanner.nextLine();
                        }
                        else if(accountChoice==4){
                        account.getDetails();
                            System.out.println("\nAccount Options");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. View Account Details");
                            System.out.println("5. Back to Main Menu");
                            System.out.println("Enter your choice: ");
                            accountChoice=scanner.nextInt();
                            scanner.nextLine();
                        }
                        else{
                            System.out.println("Invalid Choice. Please try again.");
                            System.out.println("\nAccount Options");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. View Account Details");
                            System.out.println("5. Back to Main Menu");
                            System.out.println("Enter your choice: ");
                            accountChoice=scanner.nextInt();
                            scanner.nextLine();
                        }
                    }
                }
            }
            else if(choice==3){
                saveToFile(accounts);
                System.out.println("Exiting program. All account details saved.");
                break;
            }
            else{
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}