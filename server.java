import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by User on 9/21/18.
 */
public class server {

    private static ServerSocket server;
    private static final int PORT = 1500;

    public static void main (String args[])
    {
        //Attempt to create server socket and connect to port
        try {
            server = new ServerSocket(PORT);
            System.out.println("PORT connection successful");
        } catch (IOException io) {
            System.out.println("Failed to connect to PORT");
            System.exit(1);
        }
        clientRequest(server);
    }


    /** Method to handle the client request */
    private static void clientRequest(ServerSocket socket)
    {

        /* Variable declaration **/
        int number;
        int i;
        String word;

        //Accept incoming information from socket
        Socket link = null;
        try {
            link = socket.accept();

            //Receive information from the client
            Scanner input = new Scanner(link.getInputStream());
            //Forward information to the client
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            number = input.nextInt();  // Receives the number input from client
            word = input.next();       //Receive word input client

            //Iterate through word number times
            for (i = 0; i < number; i++)
            {
                output.println(word);   //forwards the information to the client
            }
        }
        catch(IOException e)
        {
            System.out.println("Link was unsuccessful");
        }
    }
}
