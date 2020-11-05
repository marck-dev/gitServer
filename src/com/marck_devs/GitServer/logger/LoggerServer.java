package com.marck_devs.GitServer.logger;

import com.marck_devs.GitServer.Constant;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

public class LoggerServer extends Thread implements Closeable {

    private static LoggerServer instance;

    public static LoggerServer getService() {
        if (instance == null)
            instance = new LoggerServer();
        return instance;
    }

    private static HashSet<Socket> clientes = new HashSet<>();
    private static HashMap<Socket, LoggerProcess> process = new HashMap<>();

    private LoggerServer() {
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(Constant.LOGGER_PORT);
            while (true) {
                checkClients();
                Socket cliente = server.accept();
                clientes.add(cliente);
            }
        } catch (IOException e) {
            Logger.LOGGER.log(e);

        }
    }

    public void checkClients() {
        for (Socket s : clientes) {
            if (s.isClosed()) {
                process.get(s).interrupt();
                process.remove(s);
                clientes.remove(s);
            }
        }
    }

    @Override
    public void close() throws IOException {
        for (Socket s : clientes) {
            s.close();
            process.get(s).interrupt();
        }
        clientes = new HashSet<>();
        process = new HashMap<>();
        this.interrupt();
    }
}
