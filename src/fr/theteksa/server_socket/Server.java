package fr.theteksa.server_socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serveurSocket = null;

    public Server(int port) {
        try {
            serveurSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void miseEnService() {
        Socket unClient;
        Sender sender = null;

        while (true) {
            try {
                unClient = serveurSocket.accept();
                sender = new Sender(unClient);
                System.out.println("Client connecté");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            Sender finalSender = sender;
            Thread t = new Thread() {
                @Override
                public void run() {
                    finalSender.traiteRequete();
                }
            };
            t.start();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.miseEnService();
    }


}
