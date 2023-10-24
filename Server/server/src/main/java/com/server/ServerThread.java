package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class ServerThread extends Thread{ 
    protected Socket socket;
    protected BufferedReader input;
    protected DataOutputStream output;

    public ServerThread(Socket socket) throws Exception{
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.output = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void run(){
        try{
            Random random = new Random();
            int numero_casuale = random.nextInt(1, 100);
            int tentativi = 0;
            int conversione = 0;
            
            output.writeBytes(Thread.currentThread().getName() + "\n");
            
            do{
                String numero_ricevuto = input.readLine();
                conversione = Integer.parseInt(numero_ricevuto);

                if(conversione < numero_casuale){
                    output.writeBytes(1 + "\n");
                    tentativi++;
                }
                if(conversione > numero_casuale){
                    output.writeBytes(2 + "\n");
                    tentativi++;
                }
            }while(conversione != numero_casuale);

            tentativi++;
            output.writeBytes(3 + "\n");
            output.writeBytes(tentativi + "\n");
            socket.close();
        }catch(Exception e){
            System.out.println("ERRORE GENERICO");
            System.out.println(e.getMessage());
            return;
        }
    }
}
