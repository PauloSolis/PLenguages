/*
<one line to give the program's name and a brief idea of what it does.>
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
        System.out.println(" Copyright (C) <year>  Paulo Solis\n" +
                "    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.\n" +
                "    This is free software, and you are welcome to redistribute it\n" +
                "    under certain conditions; type `show c' for details.");
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
