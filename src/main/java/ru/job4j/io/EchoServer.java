package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EchoServer {
    private static final Map<String, Supplier<Boolean>> DISPATCH = new HashMap<>();
    private static final String STOP = "Exit";
    private static final String HELLO = "Hello";
    private static final String ANY = "Any";
    private static String msg;
    private static OutputStream out;
    private static ServerSocket server;
    //private static Socket socket;
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public Supplier<Boolean> stop() {
        return () -> {
            try {
                out.write("Bye\n\n".getBytes(StandardCharsets.UTF_8));
                out.close();
                server.close();
                System.out.println("server closed");
            } catch (IOException e) {
                LOG.error("Exception in server communication: ", e);
            }

            return true;
        };
    }

    public Supplier<Boolean> hello() {
        return () -> {
            try {
                out.write("<h1>Hello, dear friend!<h1>\n\n".getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                LOG.error("Exception in server communication: ", e);
            }

            return true;
        };
    }

    public Supplier<Boolean> any() {
        return () -> {
            try {
                out.write(("You wrote " + msg + "\n\n").getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                LOG.error("Exception in server communication: ", e);
            }

            return true;
        };
    }
    public static void main(String[] args) {
        DISPATCH.put(STOP, new EchoServer().stop());
        DISPATCH.put(HELLO, new EchoServer().hello());
        DISPATCH.put(ANY, new EchoServer().any());
        deployServer();
    }

    private static void deployServer() {
        try {
            server = new ServerSocket(9000);
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try {
                    out = socket.getOutputStream();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String clientMsg = getClientMsg(socket);
                    DISPATCH.get(clientMsg).get();
                } catch (IOException e) {
                    LOG.error("Exception in client-server communication: ", e);
                }
                socket.close();
            }
        } catch (IOException e) {
            LOG.error("Exception on server side: ", e);
        }
    }

    private static String getClientMsg(Socket socket) throws IOException {
        BufferedReader readLine = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        int lineN = 0;
        String message = "";
        String line = readLine.readLine();
        while (line != null && !line.equals("")) {
            lineN++;
            if (lineN == 1) {
                message = line.substring(line.indexOf("msg=") + 4, line.indexOf("HTTP")).trim();
            }
            System.out.println(line);
            line = readLine.readLine();
        }
        if (!STOP.equals(message) && !HELLO.equals(message)) {
            msg = message;
            message = ANY;
        }
        return message;
    }
}
