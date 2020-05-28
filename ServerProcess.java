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
