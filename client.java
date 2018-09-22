/**
 * Created by User on 9/21/18.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class client {

    private static final int PORT = 1500; // Port number
    private static Socket link;
    private static String server_ip = "127.0.0.1"; // Server IP Address


    public static void main(String args[]) {

        /** Step 1 - Establish connection to the server */
        try {
            link = new Socket(server_ip, PORT);  // Establishing end-to-end point connection
            System.out.println("Connection Established");            // Confirmation
            System.out.println("");
        } catch (IOException e) {
            System.out.println("Failed to connect to server");
            System.exit(1);
        }
            serverConnect(link);

    }

    private static void serverConnect(Socket connection){

        /** Variable declarations */

        int number;     //number of repetitions of the string
        int i;          //loop variable
        String word;    //word to be repeated


        //Information received from the server
        Scanner input = null;   //Create a scanner to receive packets from  the server
        try
        {
            input = new Scanner(connection.getInputStream());
            System.out.println("Receiving Server Packets");
            System.out.println();
        }
        catch (IOException e)
        {
            System.out.println("Packet Retrieval Failure");
            System.exit(1);
        }

        //Information being pushed to the server
        PrintWriter output = null;  //Send information to server
        try
        {
            output = new PrintWriter(connection.getOutputStream(), true);
            System.out.println("Sending Client Packets");
            System.out.println();
        }
        catch (IOException e) {
            System.out.println("Packet Sending Failure");
            System.exit(1);

        }


        /** Step 2 Prompt user for command line input */

        /**  Initialize scanner for user input */
        Scanner userInput = new Scanner(System.in); // Scanner object for user input

        /** Accept  number and string from user input */
        System.out.println("Please enter a number n");
        number = userInput.nextInt();
        System.out.println("Please enter a word to be repeated n times: ");
        word = userInput.next();

        /** Send the entries */
        output.println(number);
        output.println(word);

        /** Step 3 Display server output on client */

        //Output information from the server
        System.out.println();
        i = 1;
        while (input.hasNextLine())
        {
            System.out.println(i+")"+" "+ input.nextLine());
            i++;
        }
    }
}
