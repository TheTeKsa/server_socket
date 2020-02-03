package fr.theteksa.server_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public Sender(Socket client) {
        this.client = client;
    }

    public void traiteRequete() {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
            while (true) {
                String s = rd.readLine();
                switch (s) {
                    case "info":
                        printWriter.println(new String(client.getInetAddress().getHostAddress()));
                        printWriter.println(client.getPort());
                        break;
                    default:
                        printWriter.println("Bonjour");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
