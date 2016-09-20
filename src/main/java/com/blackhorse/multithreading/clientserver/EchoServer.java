package com.blackhorse.multithreading.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author vrudi
 */
public class EchoServer {

    public static void main(String[] args) {
        int portNumber = 5432;
        String serverIP = "localhost";

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started at port: " + portNumber);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Got connection from " + clientSocket.getInetAddress() +
                            " port number " + clientSocket.getPort());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Got message " + inputLine + " from " + clientSocket.toString());
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port"
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

}