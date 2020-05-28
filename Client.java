package com.company;
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final int portNumber = 9090;
    private static final String hostname = "127.0.0.1";

    public static void main(String[] args) throws IOException {


        PrintWriter out;
        BufferedReader in;
        InputStreamReader ir;

        Socket clientSocket = new Socket(hostname, portNumber);

        ir = new InputStreamReader(clientSocket.getInputStream());
        in = new BufferedReader(ir);
        BufferedReader keyboard= new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        while (true) {
            System.out.print("> ");
            String command = keyboard.readLine();
            if (command.equals("quit")) break;
            out.println(command);
            String serverResponse = in.readLine();
            System.out.println(serverResponse);
        }

        clientSocket.close();
        System.exit(0);

    }

}
