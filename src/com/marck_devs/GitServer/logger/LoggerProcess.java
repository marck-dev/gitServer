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

    }

    @Override
    public void run() {
        while (true) {
            if (Logger.LOGGER.hasNext(key)) {
                out.println(Logger.LOGGER.getNext(key));
            }
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
