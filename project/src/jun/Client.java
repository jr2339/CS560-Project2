package jun;

/**
 * Created by raojun on 9/29/16.
 */

import java.net.*;
import java.io.*;

public class Client {
    static String userInput;
    static String screenName = "PC Master Race";
    //The port that we will connect on
    static int port = 23657;
    // This is the host name from project1
    static String hostName   = "127.0.0.1";
    // Init socket
    static Socket socket = null;
    //This is BufferReader to take input from socket
    static BufferedReader Input = null;
    // Output in the screen
    static PrintWriter Output = null;

    //This is our main function
    //Network code can throw an exception
    public static void main(String[] args) throws IOException {
        // Connect to the server and open up the I/O streams
        //Also make sure to give us some error if it catches it
        try {
            socket = new Socket(hostName, port);
            //InputStreamReader() reads bytes and decodes them into characters using a specified charset.
            Input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Output = new PrintWriter(socket.getOutputStream(), true);
            //Get ready to read the input from the client
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(screenName + "\nInput: ");
            //read from the input, send to the server, and print the reply from the server
            while ((userInput = stdIn.readLine()) != null) {
                // Send the input to the server
                Output.println(userInput);

                // Return the server's response
                System.out.println("echo: " + Input.readLine());

                // Ask again for input to keep communicating with the server
                System.out.println("\ninput: ");
            }
            // Close the I/O streams and the socket
            System.err.println("Closing connection to " + hostName);
            Output.close();
            Input.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("ERROR: Cannot find " + hostName + "!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("ERROR: Couldn't get I/O streams for " + hostName + "!");
            System.exit(1);
        }
    }
}
