package com.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Socket inizializzato");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
            Scanner scanner = new Scanner(System.in);
            int risposta = 0;
            
            System.out.println("Thread serviente: " + input.readLine());

            do{
                System.out.print("Inserisci un numero: ");
                String in = scanner.nextLine();
                output.writeBytes(in + "\n");
                
                risposta = Integer.parseInt(input.readLine());
                if(risposta == 1){
                    System.out.println("---->Inserisci un numero MAGGIORE \n");
                }
                if(risposta == 2){
                    System.out.println("--->Inserisci un numero MINORE \n");
                }
            }while(risposta != 3);

            System.out.println("Hai VINTO!!!! ----- Numero tentativi: " + input.readLine());

            scanner.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("ERRORE GENERICO");
            System.out.println(e.getMessage());
            return;
        }

    }
}
