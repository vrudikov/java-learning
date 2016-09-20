package com.blackhorse.multithreading.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author vrudi
 */
public class EchoClient {

    public static void main(String[] args) {
        int portNumber = 5432;
        String hostName = "localhost";

        try {
            Socket echoSocket = new Socket(hostName, portNumber);
            System.out.println("Client 1: Connected to " + echoSocket.getInetAddress());

            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

            System.out.println("Client 1: What do you want to send to echo server");

            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));


            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Client 1: What do you want to send to echo server");
                System.out.println("echo: " + in.readLine());
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        }
    }

}