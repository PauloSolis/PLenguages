package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    private static final int portNumber = 9090;
    ServerSocket serverSocket = null;
    private static ArrayList<ServerProcess> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(8);
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(portNumber);

        while (true) {
            System.out.println("Waiting for connection");
            Socket client = listener.accept();
            System.out.println("Connection succesful");
            ServerProcess clientThread = new ServerProcess(client, clients);
            clients.add(clientThread);
            pool.execute(clientThread);
        }


    }
}
