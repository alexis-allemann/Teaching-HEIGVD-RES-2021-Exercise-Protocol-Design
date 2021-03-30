package ch.heigvd.res.labo3.tcpserver;

import java.util.logging.Logger;

public class Server {
    final static Logger LOG = Logger.getLogger(Server.class.getName());
    final static int PORT = 9999;

    public void serveClients() {
        LOG.info("Starting the Receptionist Worker on a new thread...");
        new Thread(new CalculatorWorker()).start();
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
