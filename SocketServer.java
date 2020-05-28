/*
    Copyright (C) <year>  <name of author>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
