/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121_poe_part01;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Message {
    
    // Check if message ID is no more than 10 characters
    public boolean checkMessageID(String messageID){
        return messageID != null && messageID.length() <=10;
    }
    //Check recipient cell number format (max 10 chars, has international code)
    public boolean checkRecipientCell(String recipientCell){
        //Example regex checking for international code and max 10 characters after or overall depending on formatting
        String regex= "^\\+\\d{1,3}\\d{7,10}$";
        return recipientCell != null && recipientCell.length() <=12 && Pattern.matches(regex, recipientCell);
    }
    // Check if message is less than 250 characters
    public boolean checkMessageLength(String message){
        return message != null && message.length() <=250;
    }
    // Create Message Hash
    public String createMessageHash(String messageID,int messageNumber,String messageText){
        String prefix = messageID.length() >=2 ? messageID.substring(0, 2) : messageID;
        
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].replaceAll("[^a-zA-Z]", "") : "";
        String lastWord = words.length > 1 ? words[words.length - 1].replaceAll("[^a-zA-Z]", "") : firstWord;
        
        String combinedWords = (firstWord + lastWord).toUpperCase();
        
        return prefix + ":" + messageNumber + ":" + combinedWords;
    }
    // Handle message option choice (Send, Disregard, Store)
    public String sentMessage(int choice){
        switch (choice){
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Ïnvalid choice.";
        }
    }
    //Return formatted message details string
    public String printMessages(String messageID, String messageHash, String recipient, String message){
        return "Message ID: "+ messageID + "\n" +
               "Message Hash: "+ messageHash + "\n" +
                "Recipient: "+ recipient + "\n" +
                "Message: " + message;
    }
    // Return total number of messages
    public int returnTotalMessages(int totalSent){
        return totalSent;
    }
    // Custom method to store message in JSON format
    public String storeMessageJSON(String messageID, String messageHash, String recipient,String message){
        return "{/n" +
               " \"MessageID\": \"" + messageID + "\",\n" +
               " \"MessageHash\": \"" + messageHash + "\",\n" + 
               " \"recipient\": \"" + recipient + "\",\n" +
               " \"message\": \"" + message + "\",\n" + 
        
        "}";
    }
}
    

