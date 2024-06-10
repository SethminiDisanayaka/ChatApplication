package server;

import server.Handler.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static ArrayList<Handler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket =new ServerSocket(4500);

        Socket accept;

        while (true){
            System.out.println("waiting for client.....");
            accept= serverSocket.accept();
            System.out.println("Client Connected");
            Handler clientThread =new Handler(accept,clients);
            clients.add(clientThread);
            clientThread.start();
        }
    }
}
//Stub eka kiyanne clientge peththe get way eka eken thamai server ekata request yawanne
//object ekak byte stream karanawa serializable

//Skeleton eken karanne clientgen ena request saha server eken ena response meda idangena handle karanawa
//byte stream ekak object ekak karanawa unserializable

//Buffer reader eken karanne upload karana data server ekata danawa