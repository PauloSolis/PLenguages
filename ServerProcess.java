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

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ServerProcess implements Runnable {
    protected Socket clientSocket = null;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ServerProcess> clients;

    public ServerProcess(Socket clientSocket, ArrayList<ServerProcess> clients) throws IOException {
        this.clientSocket = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void run() {

        try {
            while (true) {
                String request = in.readLine();
                Movie(request);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            out.println((new Date()).toString());

            out.close();
            try {
                in.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void Movie(String msg) throws IOException {
        OkHttpClient api = new OkHttpClient();

        String b = "https://movie-database-imdb-alternative.p.rapidapi.com/?page=1&r=json&s=";
        String r = b + msg;
        Request movie = new Request.Builder()
                .url(r)
                .get()
                .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "ccf5e3320cmsh0866425d13fd378p18ac98jsnd3b17a0bb6eb")
                .build();

        Response response = api.newCall(movie).execute();
        out.println(response);
    }
}
