package com.marck_devs.GitServer.logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class LoggerProcess extends Thread {
    private Socket cliente;
    private String key;
    private PrintWriter out;

    LoggerProcess(Socket cliente) throws IOException {
        this.cliente = cliente;
        key = Logger.LOGGER.genIndex();
        out = new PrintWriter(cliente.getOutputStream());
        out.println("CONNECT TO THE SERVER");
        System.out.println("Client connected");
        System.out.println(cliente.getInetAddress().getHostAddress());

    }

    @Override
    public void run() {
        while (Logger.LOGGER.hasNext(key)) {
            Logger.LOGGER.getNext(key).printStackTrace(out);
        }
    }

    @Override
    public void interrupt() {
        try {
            out.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.interrupt();
    }
}
