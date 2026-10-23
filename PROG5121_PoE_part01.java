/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog5121_poe_part01;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class PROG5121_PoE_part01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Scanner scanner= new Scanner(System.in);
    Login userLogin= new Login();
    Message msgManager= new Message();
    
    System.out.println("=== USER REGISTRATION ===");
    
    System.out.println("Enter your First Name:");
    String firstName= scanner.nextLine();
    System.out.println("Enter your Last Name:");
    String lastName= scanner.nextLine();
    System.out.println("Enter Username (max 5 chars,must contain '_'):");
    String username= scanner.nextLine();
    System.out.println("Enter Password (min 8 chars,1 upper,1 number,1 special):");
    String password= scanner.nextLine();
    System.out.println("Enter SA Cell Phone Number (e.g. +27848865981):");
    String cellNumber= scanner.nextLine();
    
    //Attempt Registration
    String regStatus= userLogin.registerUser(username,password,cellNumber,firstName, lastName);
    System.out.println("\n" + regStatus);
    
    //Stop program if registration fails based on message content
    if (!regStatus.contains("successfully")){
        System.out.println("\nRegistration failed.Please restart and try again.");
        scanner.close();
        return;        
    }
    System.out.println("\n=== USER LOGIN ===");
    
    System.out.println("Enter Username: ");
    String loginUsername= scanner.nextLine();
    System.out.println("Enter Password: ");
    String loginPassword= scanner.nextLine();
    
    //Attempt login
     boolean isAuthenticated= userLogin.loginUser(loginUsername, loginPassword);
     String loginStatusMessage= userLogin.returnLoginStatus(isAuthenticated);
     
    System.out.println("\n" + loginStatusMessage);

    if (!isAuthenticated){
        scanner.close();
        return;
    }   

   
    // Display the mandatory QuickChat welcome message
    System.out.println("\nWelcome to QuickChat.");

    // Numeric Menu loop
    int choice= 0;
    do {
    System.out.println("\nPlease choose an option:");
    System.out.println("1) Send Messages");
    System.out.println("2) Show recently sent messages");
    System.out.println("3) Quit");
    System.out.print("Enter choice(1,2, or 3): ");
    
    if (scanner.hasNextInt()) {        
    choice = scanner.nextInt();
    scanner.nextLine(); 
    }else{
      System.out.print("Invalid input.Please a valid number (1,2, or 3).");
      scanner.next();
     continue;
    }
    switch (choice) {
    case 1:
    System.out.print("How many messages do you wish to enter? ");
    while (!scanner.hasNextInt()) { 
     System.out.print("Please enter a valid integer for the number of messages:");
     scanner.nextLine();
    }
    int numMessages = scanner.nextInt();
    scanner.nextLine();
    
    int totalSentCounter = 0;

    for (int i = 0; i < numMessages; i++) {
      System.out.println("\n--- Message " + (i + 1) + " ---");

      System.out.print("Enter Message ID (max 10 chars): ");
      String mID = scanner.nextLine();
       if (!msgManager.checkMessageID(mID)) {
      System.out.println("Error: Message ID must be no more than 10 characters.");
      i++;
      continue;
     }

     System.out.print("Enter Recipient Cell Number: ");
     String recipient = scanner.nextLine();
      if (!msgManager.checkRecipientCell(recipient)) {
      System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");
       i++;
       continue;
        }

      System.out.print("Enter Message Text (max 250 chars): ");
      String messageText = scanner.nextLine();
      if (!msgManager.checkMessageLength(messageText)) {
        System.out.println("Please enter a message of less than 250 characters.");
        i++; 
       continue;
        }
       System.out.println("Message sent.");

       String hash = msgManager.createMessageHash(mID, i, messageText);
       System.out.println("Generated Hash: " + hash);

      System.out.println("Select action: 1) Send Message  2) Disregard Message  3) Store Message");
       while (!scanner.hasNextInt()) {
        System.out.print("Please enter 1,2, or 3:");
         scanner.nextLine();
        }
        int actionChoice= scanner.nextInt();
        scanner.nextLine();
                        
       String actionResult = msgManager.sentMessage(actionChoice);
         System.out.println(actionResult);

     if (actionChoice == 1 || actionChoice == 3) {
     totalSentCounter++;
     System.out.println("\n--- Full message Details ---");
    System.out.println( msgManager.printMessages(mID, hash, recipient, messageText));
        }
    }
    System.out.println("\nTotal messages processed/sent: " + msgManager.returnTotalMessages(totalSentCounter));
      break;

     case 2:
       System.out.println("Coming Soon.");
         break;

     case 3:
       System.out.println("Exiting QuickChat. Goodbye!");
         break;

     default:
      System.out.println("Invalid option, please choose 1, 2, or 3.");
            }
        } while (choice != 3);
         scanner.close();
    
    }
}
