package ch.heigvd.res.labo3.tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServantWorker implements Runnable {
    final static Logger LOG = Logger.getLogger(CalculatorWorker.class.getName());
    final static int NB_PARAMETERS = 3;

    private Socket clientSocket;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public ServantWorker(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String line;
        out.println("Welcome to the calculator Server.\nSend me operations and conclude with the QUIT command.");
        out.flush();
        try {
            LOG.info("Reading until client sends QUIT or closes the connection...");
            while ((line = in.readLine()) != null) {
                if (line.equalsIgnoreCase("QUIT"))
                    break;

                String[] arguments = line.split(" ");

                if (arguments.length != NB_PARAMETERS)
                    out.println("ERROR Invalid number of arguments");

                String operation = line.substring(3);
                int firstNumber;
                int secondNumber;

                try {
                    firstNumber = Integer.parseInt(arguments[1]);
                    secondNumber = Integer.parseInt(arguments[2]);
                } catch (NumberFormatException ex) {
                    out.println("ERROR Invalid number detected");
                    break;
                }

                switch (operation) {
                    case "ADD":
                        out.println("RESULT " + (firstNumber + secondNumber));
                        break;
                    case "SUB":
                        out.println("RESULT " + (firstNumber - secondNumber));
                        break;
                    case "MUL":
                        out.println("RESULT " + (firstNumber * secondNumber));
                        break;
                    case "DIV":
                        if (secondNumber == 0)
                            out.println("ERROR Division by 0");
                        out.println("RESULT " + (firstNumber + secondNumber));
                        break;
                    default:
                        out.println("ERROR Invalid operation");
                        break;
                }

                out.println("> " + line.toUpperCase());
                out.flush();

            }

            LOG.info("Cleaning up resources...");
            clientSocket.close();
            in.close();
            out.close();

        } catch (IOException ex) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex1) {
                    LOG.log(Level.SEVERE, ex1.getMessage(), ex1);
                }
            }
            if (out != null) {
                out.close();
            }
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException ex1) {
                    LOG.log(Level.SEVERE, ex1.getMessage(), ex1);
                }
            }
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
