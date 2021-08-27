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
import java.util.Scanner;
import java.util.function.Supplier;

public class EchoServer {
    private static Map<String, Supplier<Boolean>> dispatch = new HashMap<>();
    private static final String STOP = "Exit";
    private static final String HELLO = "Hello";
    private static final String ANY = "Any";
    private static String msg;
    private static OutputStream out;
    private static Socket socket;
    private static ServerSocket server;
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
        dispatch.put(STOP, new EchoServer().stop());
        dispatch.put(HELLO, new EchoServer().hello());
        dispatch.put(ANY, new EchoServer().any());
        try {
            server = new ServerSocket(9000);
            while (!server.isClosed()) {
                String message;
                socket = server.accept();
                try {
                     out = socket.getOutputStream();
                     Scanner readLine = new Scanner(socket.getInputStream());
                     //BufferedReader in = new BufferedReader(new InputStreamReader());
                     out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
//                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
//                        lines.append(str).append(System.lineSeparator());
//                    }
                    int count = 0;
                    while (readLine.hasNextLine()) {
                        count++;
                        String line = readLine.nextLine();
                        if (count == 1) {
                            message = line.substring(line.indexOf("msg=") + 4, line.indexOf("HTTP")).trim();
                            if (!STOP.equals(message) && !HELLO.equals(message)) {
                                msg = message;
                                message = ANY;
                            }
                            dispatch.get(message).get();
                        }
                        System.out.println(line);
                    }
                    readLine.close();
                } catch (IOException e) {
                    LOG.error("Exception in client-server communication: ", e);
                }
            }
            socket.close();
        } catch (IOException e) {
            LOG.error("Exception on server side: ", e);
        }
    }
}
