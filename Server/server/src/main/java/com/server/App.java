package com.server;

import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try {
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Server inizializzato");

            while (true) {
                Socket socket = server.accept();
                System.out.println("Un client si è connesso!");
                
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (Exception e) {
            System.out.println("ERRORE GENERICO");
            System.out.println(e.getMessage());
            return;
        }
    }
}