package ch.heigvd.res.labo3.tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculatorWorker implements Runnable {
    final static Logger LOG = Logger.getLogger(CalculatorWorker.class.getName());

    @Override
    public void run() {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(Server.PORT);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return;
        }

        while (true) {
            LOG.log(Level.INFO, "Waiting (blocking) for a new client on port {0}", Server.PORT);
            try {
                Socket clientSocket = serverSocket.accept();
                LOG.info("A new client has arrived. Starting a new thread and delegating work to a new servant...");
                new Thread(new ServantWorker(clientSocket)).start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
